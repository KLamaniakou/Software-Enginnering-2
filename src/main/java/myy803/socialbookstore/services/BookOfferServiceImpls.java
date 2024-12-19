package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.BookCategory;
import myy803.socialbookstore.datamodel.UserProfile;
import myy803.socialbookstore.formsdata.BookDto;
import myy803.socialbookstore.mappers.BookAuthorMapper;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.mappers.BookMapper;
import myy803.socialbookstore.mappers.UserProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class BookOfferServiceImpls implements BookOfferService{
    @Autowired
    private BookMapper BookM;
    @Autowired
    private BookAuthorMapper bookAuthorMapper;
    @Autowired
    private BookCategoryMapper bookCategoriesMapper;
    @Autowired
    private UserProfileMapper userProfileMapper;
    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Override
    public void deleteById(int theId) {
        BookM.deleteById(theId);
    }

    @Override
    public void saveOffer(BookDto bookOfferDto, String username) {
        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        userProfile.addBookOffer(bookOfferDto.buildBookOffer(bookAuthorMapper, bookCategoriesMapper));

        userProfileMapper.save(userProfile);
    }

    @Override
    public void getOffer(String username, Model model) {

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = optUserProfile.get();
        List<BookDto> bookOffersDtos = userProfile.buildBookOffersDtos();

        model.addAttribute("offers", bookOffersDtos);
    }

    @Override
    public void showOfferForm(Model model) {

        List<BookCategory> categories = bookCategoryMapper.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("offer", new BookDto());
    }
}
