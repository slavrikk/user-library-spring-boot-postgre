package ru.service.library.services;

import org.springframework.stereotype.Service;
import ru.service.library.dto.BookDTO;
import ru.service.library.dto.UserDTO;
import ru.service.library.repo.entity.Book;
import ru.service.library.repo.entity.UserReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DataTransfer {

    public List<BookDTO> transferBooksFromDB(Collection<Book> booksDB) {
        return booksDB
                .stream()
                .map(this::getBookDto)
                .collect(Collectors.toList());
    }

    public BookDTO getBookDto(Book book) {
        return new BookDTO(book);
    }

    public List<UserDTO> transferUserReaderFromDB(List<UserReader> userReadersDB) {
        List<UserDTO> userDTOList = new ArrayList<>();
        for(UserReader userReader:userReadersDB){
            UserDTO userDTO= new UserDTO();
            userDTO.setId(userReader.getId());
            userDTO.setEmail(userReader.getEmail());
            userDTO.setName(userReader.getName());
            userDTO.setSureName(userReader.getSureName());
            userDTO.setSubscribeId(userReader.getSubscribe().getId());
            userDTO.setSubscriptionType(userReader.getSubscribe().getType());
            userDTO.setBooks(transferBooksFromDB(userReader.getSubscribe().getBooks()));
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

}
