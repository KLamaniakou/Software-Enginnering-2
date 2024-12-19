package myy803.socialbookstore.datamodel.searchstrategies;

import java.util.ArrayList;
import java.util.List;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;

public abstract class SearchStrategyTemplate implements SearchStrategy {

    protected BookMapper bookMapper;

    public SearchStrategyTemplate(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public ArrayList<BookDto> search(SearchDto searchDto, BookMapper bookMapper) {
        ArrayList<BookDto> bookDtos = new ArrayList<BookDto>();

        if(searchDto.getTitle() != null) {
            List<Book> books = makeInitialListOfBooks(searchDto);

            boolean authorsMatch = true;

            for(Book book : books) {
                if(!searchDto.getAuthors().equals("")) {
                    authorsMatch = checkIfAuthorsMatch(searchDto, book);
                }
                if(authorsMatch) bookDtos.add(book.buildDto());
            }
        }

        return bookDtos;
    }

    // Common step: search for books by title using the BookMapper
    protected List<Book> makeInitialListOfBooks(SearchDto searchDto) {
        return bookMapper.findByTitleContaining(searchDto.getTitle());
    }

    // Abstract step: checks if authors match (to be implemented by subclasses)
    protected abstract boolean checkIfAuthorsMatch(SearchDto searchDto, Book book);
}