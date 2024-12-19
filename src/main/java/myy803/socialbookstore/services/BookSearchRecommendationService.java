package myy803.socialbookstore.services;
import myy803.socialbookstore.formsdata.RecommendationsDto;
import myy803.socialbookstore.formsdata.SearchDto;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;

@Service
public interface BookSearchRecommendationService {
    public void showSearchForm(Model model);
    public void search(SearchDto searchDto, Model model);
    public void showRecommendationsForm(Model model);
    public void recommend(String username,RecommendationsDto recomDto, Model model);
}
