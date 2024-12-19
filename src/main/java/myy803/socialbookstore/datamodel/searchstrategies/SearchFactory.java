package myy803.socialbookstore.datamodel.searchstrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import myy803.socialbookstore.mappers.BookMapper;


@Component
public class SearchFactory {

	@Autowired
	private BookMapper bookMapper;


	public SearchStrategy create(String searchStrategy) {
		if(searchStrategy.equals("Exact"))
			return new ExactSearchStrategy(bookMapper);
		else
			return new ApproximateSearchStrategy(bookMapper);
	}
}
