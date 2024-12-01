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
public class BookSearchRecommendationController {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private SearchFactory searchFactory;

    @Autowired
    private RecommendationsFactory recommendationsFactory;



    @RequestMapping("/user/search")
    public String showSearchForm(Model model) {
        SearchDto searchDto = new SearchDto();
        model.addAttribute("searchDto", searchDto);

        return "user/search_form";
    }

    @RequestMapping("/user/search_offer")
    public String search(@ModelAttribute("searchDto") SearchDto searchDto, Model model) {

        SearchStrategy searchStrategy = searchFactory.create(searchDto.getSelectedStrategy());
        List<BookDto> bookDtos = searchStrategy.search(searchDto, bookMapper);

        model.addAttribute("books", bookDtos);
        return "user/search_results";
    }

    @RequestMapping("/user/recom")
    public String showRecommendationsForm(Model model) {
        RecommendationsDto recomDto = new RecommendationsDto();

        model.addAttribute("recomDto", recomDto);

        return "user/recommendation_form";
    }

    @RequestMapping("/user/recommend_offers")
    public String recommend(@ModelAttribute("recomDto") RecommendationsDto recomDto, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        RecommendationsStrategy recommendationsStrategy = recommendationsFactory.create(recomDto.getSelectedStrategy());
        List<BookDto> bookDtos = recommendationsStrategy.recommend(username);

        model.addAttribute("books", bookDtos);

        return "user/search_results";
    }


}
