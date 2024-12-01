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
public class UserProfileController {

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Autowired
    private BookAuthorMapper bookAuthorMapper;

    @Autowired
    private BookCategoryMapper bookCategoriesMapper;


    @RequestMapping("/user/profile")
    public String retrieveProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.err.println("Logged use: " + username);

        List<BookCategory> categories = bookCategoryMapper.findAll();
        model.addAttribute("categories", categories);

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(username);
        UserProfile userProfile = null;
        UserProfileDto userProfileDto = null;
        if(optUserProfile.isPresent()) {
            userProfile = optUserProfile.get();
            userProfileDto = userProfile.buildProfileDto();
        } else {
            userProfileDto = new UserProfileDto();
            userProfileDto.setUsername(username);
        }

        model.addAttribute("profile", userProfileDto);

        return "user/profile";
    }

    @RequestMapping("/user/save_profile")
    public String saveProfile(@ModelAttribute("profile") UserProfileDto userProfileDto, Model theModel) {
        System.err.println(userProfileDto.toString());

        Optional<UserProfile> optUserProfile = userProfileMapper.findById(userProfileDto.getUsername());
        UserProfile userProfile = null;
        if(optUserProfile.isPresent())
            userProfile = optUserProfile.get();
        else
            userProfile = new UserProfile();

        userProfileDto.buildUserProfile(userProfile, bookAuthorMapper, bookCategoriesMapper);

        userProfileMapper.save(userProfile);


        return "user/dashboard";
    }

}
