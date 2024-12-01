package myy803.socialbookstore.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import myy803.socialbookstore.datamodel.Book;
import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsFactory;
import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsStrategy;
import myy803.socialbookstore.datamodel.searchstrategies.SearchFactory;
import myy803.socialbookstore.datamodel.searchstrategies.SearchStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import myy803.socialbookstore.services.UserService;


@Controller
public class BookRequestController {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookMapper bookMapper;




    @RequestMapping("/user/request_book")
    public String request(@RequestParam("selected_book_id") int bookId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Book> requestedBook = bookMapper.findById(bookId);
        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        requestedBook.get().addRequestingUser(userProfile.get());
        bookMapper.save(requestedBook.get());

        return "redirect:/user/dashboard";
    }

    @RequestMapping("/user/requests")
    public String showUserBookRequests(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<UserProfile> userProfile = userProfileMapper.findById(username);
        List <BookDto> requests = userProfile.get().buildBookRequestsDtos();
        model.addAttribute("requests", requests);

        return "/user/book_requests";
    }

    @RequestMapping("/user/book_requesting_users")
    public String showRequestingUsersForBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {

        Optional<Book> book = bookMapper.findById(bookId);
        List<UserProfileDto> requestingUsers = book.get().getRequestingUserProfileDtos();

        model.addAttribute("requesting_users", requestingUsers);
        model.addAttribute("book_id", bookId);

        return "/user/requesting_users";
    }

    @RequestMapping("/user/accept_request")
    public String acceptRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId, Model model) {
        /*
         * TODO - have to implement this user story
         */
        System.err.println("Selected user: " + username + " for book id: " + bookId);

        return "redirect:/user/dashboard";
    }


    @RequestMapping("/user/delete_book_request")
    public String deleteBookRequest(@RequestParam("selected_request_id") int bookId, Model model) {
        /*
         * TODO - have to implement this user story
         */
        System.err.println("Delete Book Request for book id: " + bookId);

        return "redirect:/user/dashboard";
    }

}
