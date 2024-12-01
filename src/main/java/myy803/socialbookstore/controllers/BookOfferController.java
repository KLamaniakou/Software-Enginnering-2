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
public class BookOfferController {


    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookAuthorMapper bookAuthorMapper;

    @Autowired
    private BookCategoryMapper bookCategoriesMapper;



    @RequestMapping("/user/offers")
    public String listBookOffers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        List<BookDto> bookOffersDtos = userProfile.buildBookOffersDtos();

        model.addAttribute("offers", bookOffersDtos);

        return "user/offers";
    }

    @RequestMapping("/user/show_offer_form")
    public String showOfferForm(Model model) {
        List<BookCategory> categories = bookCategoryMapper.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("offer", new BookDto());

        return "user/offer-form";
    }

    @RequestMapping("/user/save_offer")
    public String saveOffer(@ModelAttribute("offer") BookDto bookOfferDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        userProfile.addBookOffer(bookOfferDto.buildBookOffer(bookAuthorMapper, bookCategoriesMapper));

        userProfileMapper.save(userProfile);

        return "redirect:/user/offers";
    }


    @RequestMapping("/user/delete_book_offer")
    public String deleteBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
        /*
         * TODO - have to implement this user story
         */
        System.err.println("Delete Book Offer for book id: " + bookId);

        return "redirect:/user/dashboard";
    }


}
