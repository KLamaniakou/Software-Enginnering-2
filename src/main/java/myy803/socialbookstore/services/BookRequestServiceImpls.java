package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Service
public class BookRequestServiceImpls implements BookRequestService{
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserProfileMapper userProfileMapper;

    @Override
    public void requestBook(String username, int bookId) {
        Optional<Book> requestedBook = bookMapper.findById(bookId);
        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        requestedBook.get().addRequestingUser(userProfile.get());
        bookMapper.save(requestedBook.get());
    }

    @Override
    public void showUserBookRequests(String username,Model model) {
        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        List<BookDto> requests = userProfile.get().buildBookRequestsDtos();
        model.addAttribute("requests", requests);
    }

    @Override
    public void showRequestingUsersForBookOffer(int bookId, Model model) {
        Optional<Book> book = bookMapper.findById(bookId);
        List<UserProfileDto> requestingUsers = book.get().getRequestingUserProfileDtos();

        model.addAttribute("requesting_users", requestingUsers);
        model.addAttribute("book_id", bookId);
    }
}
