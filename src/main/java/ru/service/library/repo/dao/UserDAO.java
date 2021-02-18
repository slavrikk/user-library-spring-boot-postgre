package ru.service.library.repo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.service.library.repo.UserRepository;
import ru.service.library.repo.entity.UserReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {

    @Autowired
    private UserRepository userRepository;

    public UserReader addUser(UserReader user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
        return user;
    }

    public List<UserReader> findAllUsers(){
        return userRepository.findAll();
    }

    public List<UserReader> selectUserById(int id) {
        Optional<UserReader> user;
        UserReader userReader;
        try {
         user =   userRepository.findById(id);
         userReader = user.get();
        } catch (Exception e) {
            return null;
        }
        List<UserReader> userReaderList = new ArrayList<>();
        userReaderList.add(userReader);
        return userReaderList;
    }

    public boolean deleteUserById(Integer id){
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    public List<UserReader> getUsersByTypeSub(String type){
       return userRepository.getUserReaderByTypeSubscription(type);
    }
}
