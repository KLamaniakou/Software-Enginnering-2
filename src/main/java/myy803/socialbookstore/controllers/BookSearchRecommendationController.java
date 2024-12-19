package myy803.socialbookstore.controllers;


import myy803.socialbookstore.services.BookSearchRecommendationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;

@Controller
public class BookSearchRecommendationController {
    @Autowired
    private BookSearchRecommendationServiceImpl BookSearchReService;


    @RequestMapping("/user/search")
    public String showSearchForm(Model model) {
        BookSearchReService.showSearchForm(model);
        return "user/search_form";
    }

    @RequestMapping("/user/search_offer")
    public String search(@ModelAttribute("searchDto") SearchDto searchDto, Model model) {
        BookSearchReService.search(searchDto,model);
        return "user/search_results";
    }

    @RequestMapping("/user/recom")
    public String showRecommendationsForm(Model model) {
        BookSearchReService.showRecommendationsForm(model);
        return "user/recommendation_form";
    }

    @RequestMapping("/user/recommend_offers")
    public String recommend(@ModelAttribute("recomDto") RecommendationsDto recomDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        BookSearchReService.recommend(username,recomDto,model);
        return "user/search_results";
    }


}
