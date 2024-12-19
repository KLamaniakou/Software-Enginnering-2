package myy803.socialbookstore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import myy803.socialbookstore.formsdata.UserProfileDto;
import myy803.socialbookstore.mappers.BookCategoryMapper;
import myy803.socialbookstore.services.UserService;


@Controller
public class UserProfileController {

    @Autowired
    BookCategoryMapper bookCategoryMapper;

    @Autowired
    UserService userService;


    @RequestMapping("/user/profile")
    public String retrieveProfile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.retrieveProfile(username,model);
        System.err.println("Logged use: " + username);

        return "user/profile";
    }

    @RequestMapping("/user/save_profile")
    public String saveProfile(@ModelAttribute("profile") UserProfileDto userProfileDto, Model theModel) {
        userService.saveProfile(userProfileDto);
        System.err.println(userProfileDto.toString());
        return "user/dashboard";
    }

}
