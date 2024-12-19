package myy803.socialbookstore.services;

import myy803.socialbookstore.formsdata.BookDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface BookOfferService {
    public void deleteById(int theId);
    public void saveOffer(BookDto bookOfferDto,String username);
    public void getOffer(String username, Model model);
    public void showOfferForm(Model model);
}
