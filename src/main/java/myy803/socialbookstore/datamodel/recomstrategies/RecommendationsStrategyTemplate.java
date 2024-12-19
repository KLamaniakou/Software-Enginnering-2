package myy803.socialbookstore.datamodel.recomstrategies;

import java.util.List;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.UserProfileMapper;

public abstract class RecommendationsStrategyTemplate implements RecommendationsStrategy {

    private final UserProfileMapper userProfileMapper;

    // Constructor to inject the UserProfileMapper
    public RecommendationsStrategyTemplate(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    // Common method to recommend books, shared across all strategies
    @Override
    public List<BookDto> recommend(String username) {
        // Step 1: Retrieve the user's profile
        UserProfile userProfile = userProfileMapper.findByUsername(username);

        // Step 2: Retrieve the recommended books using the abstract method
        return retrieveRecommendedBooks(userProfile);
    }

    // Abstract method to retrieve recommended books, to be implemented by subclasses
    protected abstract List<BookDto> retrieveRecommendedBooks(UserProfile userProfile);
}
