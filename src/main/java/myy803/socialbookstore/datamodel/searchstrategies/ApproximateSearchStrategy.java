package myy803.socialbookstore.datamodel.searchstrategies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;

@Component
public class ApproximateSearchStrategy extends SearchStrategyTemplate {
	
	@Autowired
	protected BookMapper bookMapper;

	public ApproximateSearchStrategy(BookMapper bookMapper) {
		super(bookMapper);
	}

	@Override
	protected boolean checkIfAuthorsMatch(SearchDto searchDto, Book book) {
		// Perform approximate author match logic
		return book.authorsListIncludes(searchDto.getAuthors());  // Approximate match for authors
	}
}
