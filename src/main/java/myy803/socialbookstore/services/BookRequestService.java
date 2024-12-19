package myy803.socialbookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface BookRequestService {
    public void requestBook(String username, int bookId);
    public void showUserBookRequests(String username,Model model);
    public void showRequestingUsersForBookOffer(int bookId, Model model);
}
