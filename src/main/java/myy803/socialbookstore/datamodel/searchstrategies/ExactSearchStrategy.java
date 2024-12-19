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
public class ExactSearchStrategy extends SearchStrategyTemplate {

	public ExactSearchStrategy(BookMapper bookMapper) {
		super(bookMapper);
	}

	// Implement the exact author matching logic
	@Override
	protected boolean checkIfAuthorsMatch(SearchDto searchDto, Book book) {
		// Perform exact author match logic
		return book.writtenBy(searchDto.getAuthors());  // Exact match for authors
	}
}
