package initial.start;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import initial.util.MysqlDBHelper;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InitialCarAbbrTest
{
	final String sql =
		"INSERT INTO `opt_abbreviate_map` (`abbreviate`, `province`, `full_name`, `gmt_create`, `gmt_modified`) VALUES (?, ?, ?, now(), now());";

	@Test
	public void test5()
		throws Exception
	{
		Connection connection = MysqlDBHelper.getConnection();
		PreparedStatement pstm = null;
		pstm = connection.prepareStatement("truncate table opt_abbreviate_map");
		pstm.execute();
		try
		{
			FileReader reader = new FileReader(
				"G:\\Java_develop\\project\\jgbasic\\jgbasic-brother\\jgbasic-app\\jgbasic-elasticsearch\\src\\main\\resources\\initial\\car_abbr.txt");
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null)
			{
				if (str != null && !str.trim().equals(""))
				{
					String[] split = str.split(",");
					String abbr = split[0].trim();
					String province = split[1].trim();
					String fullName = split[2].trim();
					System.out.println(abbr + "-" + province + "-" + fullName);
					pstm = connection.prepareStatement(sql);
					pstm.setString(1, abbr);
					pstm.setString(2, province);
					pstm.setString(3, fullName);
					pstm.execute();
				}
			}
			br.close();
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection.close();
			pstm.close();
		}
	}

	@Test
	public void test4()
		throws Exception
	{
		Connection connection = MysqlDBHelper.getConnection();
		PreparedStatement pstm = null;
		pstm = connection.prepareStatement("truncate table opt_abbreviate_map");
		pstm.execute();
		try
		{
			FileReader reader = new FileReader(
				"G:\\Java_develop\\project\\jgbasic\\jgbasic-brother\\jgbasic-app\\jgbasic-elasticsearch\\src\\main\\resources\\initial\\car_abbr_province.txt");
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null)
			{
				if (str != null && !str.trim().equals(""))
				{
					String[] split = str.split(",");
					String abbr = split[0].trim();
					String province = split[1].trim();
					String fullName = split[2].trim();
					System.out.println(abbr + "-" + province + "-" + fullName);
					pstm = connection.prepareStatement(sql);
					pstm.setString(1, abbr);
					pstm.setString(2, province);
					pstm.setString(3, fullName);
					pstm.execute();
				}
			}
			br.close();
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection.close();
			pstm.close();
		}
	}

	@Test
	public void test6()
		throws Exception
	{
		Connection connection = MysqlDBHelper.getConnection();
		PreparedStatement pstm = null;
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(new File(
				"G:\\Java_develop\\project\\jgbasic\\jgbasic-brother\\jgbasic-app\\jgbasic-elasticsearch\\src\\main\\resources\\initial\\CarPrefix.json")));
			Gson gson = new GsonBuilder().create();
			Abbr[] people = gson.fromJson(reader, Abbr[].class);
			System.out.println(people.length);
			for (int i = 0; i < people.length; i++)
			{
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, people[i].getCode());
				pstm.setString(2, people[i].getProvince());
				pstm.setString(3, people[i].getCity());
				pstm.execute();
			}

		}
		catch (FileNotFoundException ex)
		{
			System.out.println("找不到文件");
		}
		finally
		{
			reader.close();
			pstm.close();
			connection.close();
		}
	}
}
