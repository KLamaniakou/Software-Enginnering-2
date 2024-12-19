package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsFactory;
import myy803.socialbookstore.datamodel.recomstrategies.RecommendationsStrategy;
import myy803.socialbookstore.datamodel.searchstrategies.SearchFactory;
import myy803.socialbookstore.datamodel.searchstrategies.SearchStrategy;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;
import myy803.socialbookstore.mappers.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BookSearchRecommendationServiceImpl implements BookSearchRecommendationService{
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private SearchFactory searchFactory;

    @Autowired
    private RecommendationsFactory recommendationsFactory;

    @Override
    public void showSearchForm(Model model) {
        SearchDto searchDto = new SearchDto();
        model.addAttribute("searchDto", searchDto);
    }

    @Override
    public void search(SearchDto searchDto, Model model) {
        SearchStrategy searchStrategy = searchFactory.create(searchDto.getSelectedStrategy());
        List<BookDto> bookDtos = searchStrategy.search(searchDto, bookMapper);

        model.addAttribute("books", bookDtos);
    }

    @Override
    public void showRecommendationsForm(Model model) {
        RecommendationsDto recomDto = new RecommendationsDto();
        model.addAttribute("recomDto", recomDto);
    }

    @Override
    public void recommend(String username,RecommendationsDto recomDto, Model model) {
        RecommendationsStrategy recommendationsStrategy = recommendationsFactory.create(recomDto.getSelectedStrategy());
        List<BookDto> bookDtos = recommendationsStrategy.recommend(username);
        model.addAttribute("books", bookDtos);
    }
}
