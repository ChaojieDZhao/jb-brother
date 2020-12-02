package elastic;

import initial.util.ElasticSearchDocHelper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

/**
 * @describe
 * @name TestElastciAPI
 */
public class TestElastciSearchAPI
{

	ElasticSearchDocHelper elasticSearchDocHelper = new ElasticSearchDocHelper();

	@Test
	public void test1()
	{
		SearchResponse response = elasticSearchDocHelper.getConnection().prepareSearch("black_list", "customer_address")
			.setTypes("BlackList", "CustomerAddress")
			.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			.setQuery(QueryBuilders.termQuery("area_code", "170"))                 // Query
			.setPostFilter(QueryBuilders.rangeQuery("city").from(12).to(18))     // Filter
			.setFrom(0).setSize(60).setExplain(true)
			.get();
	}
}