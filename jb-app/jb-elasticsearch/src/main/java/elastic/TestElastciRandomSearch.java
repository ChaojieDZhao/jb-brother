package elastic;

import initial.util.ElasticSearchDocHelper;
import initial.util.OracleDBHelper;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestElastciRandomSearch
{
	ElasticSearchDocHelper elasticSearchDocHelper = new ElasticSearchDocHelper();

	@Test
	public void test1()
		throws Exception
	{
		Connection connection = OracleDBHelper.getConnection();
		while (true)
		{
			String sql = "select * from tb_bqf_black SAMPLE(1) where rownum <= 1";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next())
			{
				String id = resultSet.getString("id");
				BoolQueryBuilder finalQuery = QueryBuilders.boolQuery();
				MatchQueryBuilder keywdQuery = QueryBuilders.matchQuery("id", id);
				finalQuery.must(keywdQuery);
				SearchRequestBuilder searchRequestBuilder =
					elasticSearchDocHelper.getConnection().prepareSearch("black_list");
				searchRequestBuilder.setQuery(finalQuery);
				SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
				System.out.println("消耗了======> " + searchResponse.getTook());
				System.out.println(searchResponse);
			}
			statement.close();
		}
	}

}