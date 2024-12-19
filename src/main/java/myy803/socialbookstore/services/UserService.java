package myy803.socialbookstore.services;

import myy803.socialbookstore.datamodel.User;
import myy803.socialbookstore.formsdata.UserProfileDto;
import org.springframework.ui.Model;

public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
	public User findById(String username);
	public void retrieveProfile(String username,Model model);
	public void saveProfile(UserProfileDto userProfileDto);
}
