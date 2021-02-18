package ru.service.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.service.library.dto.response.UserReaderRs;
import ru.service.library.dto.Status;
import ru.service.library.repo.dao.BookDAO;
import ru.service.library.repo.dao.UserDAO;
import ru.service.library.repo.entity.Book;
import ru.service.library.repo.entity.UserReader;
import ru.service.library.services.DataTransfer;

import java.util.List;

@RestController
@RequestMapping(path = "/subscription")
public class SubscribeController {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DataTransfer dataTransfer;

    @GetMapping("/user/{id_user}/book/{id_book}")
    public Status subscribe(@PathVariable Integer id_user, @PathVariable Integer id_book) {
        try {
            List<Book> selectedBook = bookDAO.selectBookById(id_book);
            if (selectedBook == null) {
                return new Status("Book with id " + id_book + " is not existed");
            }
            List<UserReader> selectedUser = userDAO.selectUserById(id_user);
            if (selectedUser == null) {
                return new Status("User with id " + id_user + " is not existed");
            }
            Book book = selectedBook.get(0);
            UserReader user = selectedUser.get(0);
            book.getSubscribes().add(selectedUser.get(0).getSubscribe());
            user.getSubscribe().getBooks().add(selectedBook.get(0));

            userDAO.addUser(user);
            bookDAO.addBook(book);
        } catch (Exception e) {
            return new Status("ERROR");
        }
        return new Status("SUCCESS");
    }

    @GetMapping("/{type}")
    public UserReaderRs getUserByTypeSubscription(@PathVariable String type){
        return new UserReaderRs(new Status("Success"),
                dataTransfer.transferUserReaderFromDB(userDAO.getUsersByTypeSub(type.toUpperCase())));
    }

}
