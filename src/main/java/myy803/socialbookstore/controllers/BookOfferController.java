package myy803.socialbookstore.controllers;

import myy803.socialbookstore.services.BookOfferServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import myy803.socialbookstore.formsdata.BookDto;


@Controller
public class BookOfferController {

    @Autowired
    private BookOfferServiceImpls BookOfferService;


    @RequestMapping("/user/offers")
    public String listBookOffers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //we change controller to get books from service
        BookOfferService.getOffer(username,model);

        return "user/offers";
    }

    @RequestMapping("/user/show_offer_form")
    public String showOfferForm(Model model) {
        //we change controller to show offer from service
        BookOfferService.showOfferForm(model);

        return "user/offer-form";
    }

    @RequestMapping("/user/save_offer")
    public String saveOffer(@ModelAttribute("offer") BookDto bookOfferDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //we change controller to save books from service
        BookOfferService.saveOffer(bookOfferDto,username);

        return "redirect:/user/offers";
    }


    @RequestMapping("/user/delete_book_offer")
    public String deleteBookOffer(@RequestParam("selected_offer_id") int bookId, Model model) {
        /*TODO - have to implement this user story */
        BookOfferService.deleteById(bookId);
        System.err.println("Delete Book Offer for book id: " + bookId);

        return "redirect:/user/dashboard";
    }
}
