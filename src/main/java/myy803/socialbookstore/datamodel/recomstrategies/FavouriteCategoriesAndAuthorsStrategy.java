package myy803.socialbookstore.datamodel.recomstrategies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.UserProfileMapper;

@Component
public class FavouriteCategoriesAndAuthorsStrategy extends RecommendationsStrategyTemplate {

	// Constructor to inject the UserProfileMapper
	public FavouriteCategoriesAndAuthorsStrategy(UserProfileMapper userProfileMapper) {
		super(userProfileMapper);
	}

	@Override
	protected List<BookDto> retrieveRecommendedBooks(UserProfile userProfile) {
		ArrayList<BookDto> bookDtos = new ArrayList<BookDto>();
		bookDtos.addAll(userProfile.getBooksFromFavouriteAuthors());
		bookDtos.addAll(userProfile.getBooksOfFavouriteCategories());
		return bookDtos;
	}
}
