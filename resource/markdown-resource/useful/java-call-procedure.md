---
title: java调用存储过程
date: 2016-08-20 08:48:57
tags:
- java
---


###  记录一个spring template 调用存储过程的例子
**项目需求，完成填写过一个风险评测相关人数的统计。**

java代码层：

``` java
Map resultMap = (Map) jdbcTemplate.execute(new CallableStatementCreator() {
	public CallableStatement createCallableStatement(Connection con) throws SQLException {
		String storedProc = "{call P_SUM_RISKASSESSMENT_BYDATE(?,?,?,?)}";// 调用的sql
		CallableStatement cs = con.prepareCall(storedProc);
		if (StringUtil.isNotBlank(startTime)) {
			cs.setString(1, startTime);
		} else {
			cs.setString(1, "0");
		}
		if (StringUtil.isNotBlank(endTime)) {
			cs.setString(2, endTime);
		} else {
			cs.setString(2, "0");
		}
		if (StringUtil.isNotBlank(userName)) {
			cs.setString(3, userName);
		} else {
			cs.setString(3, "-1");
		}
		cs.registerOutParameter(4, OracleTypes.CURSOR);// 注册输出参数的类型
		return cs;
	}
}, new CallableStatementCallback() {
	public Object doInCallableStatement(CallableStatement cs) throws SQLException {
		Map rowMap = new HashMap();
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(4);// 获取游标一行的值
		while (rs.next()) {// 转换每行的返回值到Map中
			rowMap.put("REGISTER_SUM", rs.getString("REGISTER_SUM"));
			rowMap.put("DEPOSITORY_SUM", rs.getString("DEPOSITORY_SUM"));
			rowMap.put("RISKING_SUM", rs.getString("RISKING_SUM"));
			rowMap.put("UNRISKING_SUM", rs.getString("UNRISKING_SUM"));
			rowMap.put("RISKEDSUM_PRESENT_MONTH", rs.getString("RISKEDSUM_PRESENT_MONTH"));
			rowMap.put("RISKEDSUM_TODAY", rs.getString("RISKEDSUM_TODAY"));
		}
		rs.close();
		return rowMap;
	}
});
return resultMap;

```

sql代码层：

``` sql
create or replace procedure p_sum_riskassessment_bydate(startdate   in varchar2,
                                                        enddate     in varchar2,
                                                        userName    in varchar2,
                                                        returnvalue out sys_refcursor) is
begin
  open returnvalue for
    select t1.register_sum            register_sum, --注册的人数
           t2.depository_sum          depository_sum, --开通存管的人数
           t3.risking_sum             risking_sum, --已完成风险评估的人数
           t4.unrisking_sum           unrisking_sum, --未进行风险评估的人数
           t5.riskedsum_present_month riskedsum_present_month, --本月进行风险评估的人数
           t6.riskedsum_today         riskedsum_today --今天进行风险评估的人数
      from (select count(1) as register_sum
              from users us
             where us.is_allow_login = '1'
               and ((us.mobile like '%' || userName || '%' and
                   userName <> '-1') or (1 = 1 and userName = '-1'))
               and us.time >
                   decode(startdate,
                          '0',
                          to_date('2012-01-01', 'yyyy-mm-dd'),
                          to_date(startdate, 'yyyy-mm-dd'))
               and us.time <
                   decode(enddate,
                          '0',
                          sysdate,
                          to_date(enddate || ':23:59:59',
                                  'YYYY-MM-DD:HH24:MI:SS'))) t1,
           (select count(1) as depository_sum
              from users us
             where us.user_storage_number is not null
               and us.is_effective = 1
               and us.is_allow_login = '1'
               and ((us.mobile like '%' || userName || '%' and
                   userName <> '-1') or (1 = 1 and userName = '-1'))
               and kh_time(us.id) >
                   decode(startdate,
                          '0',
                          to_date('2012-01-01', 'yyyy-mm-dd'),
                          to_date(startdate, 'yyyy-mm-dd'))
               and kh_time(us.id) <
                   decode(enddate,
                          '0',
                          sysdate,
                          to_date(enddate || ':23:59:59',
                                  'YYYY-MM-DD:HH24:MI:SS'))) t2,
           (select count(distinct event_1709.id) as risking_sum
              from event_1709_survey event_1709
              left join users us
                on us.id = event_1709.id
             where event_1709.surveytype = 2
               and us.is_allow_login = '1'
               and ((us.mobile like '%' || userName || '%' and
                   userName <> '-1') or (1 = 1 and userName = '-1'))
               and event_1709.surveytime >
                   decode(startdate,
                          '0',
                          to_date('2012-01-01', 'yyyy-mm-dd'),
                          to_date(startdate, 'yyyy-mm-dd'))
               and event_1709.surveytime <
                   decode(enddate,
                          '0',
                          sysdate,
                          to_date(enddate || ':23:59:59',
                                  'YYYY-MM-DD:HH24:MI:SS'))
            
            ) t3,
           (select count(1) as unrisking_sum
              from users us
             where id not in (select distinct id
                                from event_1709_survey
                               where surveytype = 2)
               and us.is_allow_login = '1') t4,
           (select count(distinct event_1709.id) as riskedsum_present_month
              from event_1709_survey event_1709
              left join users us
                on us.id = event_1709.id
             where event_1709.surveytime >= trunc(sysdate, 'MM')
               and surveytime <= last_day(sysdate)
               and event_1709.surveytype = 2
               and us.is_allow_login = '1') t5,
           (select count(distinct event_1709.id) as riskedsum_today
              from event_1709_survey event_1709
              left join users us
                on us.id = event_1709.id
             where event_1709.surveytime >= trunc(sysdate)
               and event_1709.surveytype = 2
               and us.is_allow_login = '1') t6;
end p_sum_riskassessment_bydate;
```


