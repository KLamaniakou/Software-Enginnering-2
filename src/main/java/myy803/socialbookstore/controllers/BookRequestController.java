package myy803.socialbookstore.controllers;


import java.util.List;
import java.util.Optional;

import myy803.socialbookstore.services.BookRequestServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookRequestController {

    @Autowired
    private BookRequestServiceImpls BookRequestService;

    @RequestMapping("/user/request_book")
    public String request(@RequestParam("selected_book_id") int bookId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        BookRequestService.requestBook(username,bookId);
        return "redirect:/user/dashboard";
    }

    @RequestMapping("/user/requests")
    public String showUserBookRequests(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        BookRequestService.showUserBookRequests(username,model);
        return "/user/book_requests";
    }

    @RequestMapping("/user/book_requesting_users")
    public String showRequestingUsersForBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {

        BookRequestService.showRequestingUsersForBookOffer(bookId,model);

        return "/user/requesting_users";
    }

    @RequestMapping("/user/accept_request")
    public String acceptRequest(@RequestParam("selected_user") String username, @RequestParam("book_id") int bookId, Model model) {
        /*TODO - have to implement this user story*/
        System.err.println("Selected user: " + username + " for book id: " + bookId);

        return "redirect:/user/dashboard";
    }


    @RequestMapping("/user/delete_book_request")
    public String deleteBookRequest(@RequestParam("selected_request_id") int bookId, Model model) {
        /*TODO - have to implement this user story*/
        System.err.println("Delete Book Request for book id: " + bookId);

        return "redirect:/user/dashboard";
    }

}
