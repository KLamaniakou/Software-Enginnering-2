package myy803.socialbookstore.datamodel.recomstrategies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.UserProfileMapper;

@Component
public class FavouriteCategoriesStrategy extends RecommendationsStrategyTemplate {

	// Constructor to inject the UserProfileMapper
	public FavouriteCategoriesStrategy(UserProfileMapper userProfileMapper) {
		super(userProfileMapper);
	}

	// Implement the logic for retrieving recommended books from favourite categories
	@Override
	protected List<BookDto> retrieveRecommendedBooks(UserProfile userProfile) {
		return userProfile.getBooksOfFavouriteCategories(); // Fetch books from the user's favourite categories
	}
}
