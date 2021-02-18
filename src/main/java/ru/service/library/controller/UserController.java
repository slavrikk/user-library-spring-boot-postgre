package ru.service.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.service.library.dto.request.UserReaderRq;
import ru.service.library.dto.response.UserReaderRs;
import ru.service.library.repo.dao.UserDAO;
import ru.service.library.repo.entity.UserReader;
import ru.service.library.repo.entity.UserSubscribe;
import ru.service.library.dto.Status;
import ru.service.library.services.DataTransfer;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DataTransfer dataTransfer;

    @Autowired
    private Validator validator;

    @GetMapping("/all")
    public @ResponseBody
    UserReaderRs findAll() {
        Status status = new Status("Success");
        return new UserReaderRs(status,
                dataTransfer.transferUserReaderFromDB(userDAO.findAllUsers()));
    }

    @GetMapping("/{id}")
    public UserReaderRs findOne(@PathVariable Integer id) {
        Status status;
        List<UserReader> selectedUser = userDAO.selectUserById(id);
        if (selectedUser != null) {
            status = new Status("Success");
            return new UserReaderRs(status, dataTransfer.transferUserReaderFromDB(selectedUser));
        } else {
            status = new Status("User Not Found");
        }

        return new UserReaderRs(status,null);
    }

    @PostMapping()
    public @ResponseBody
    UserReaderRs postUser(@RequestBody UserReaderRq newUser) {
       Set<ConstraintViolation<UserReaderRq>> validate =validator.validate(newUser);
        if(!validate.isEmpty()){
            return new UserReaderRs(new Status("Validation error: "+validate.iterator().next().getMessage()),null);
        }
        UserSubscribe userSubscribe = new UserSubscribe();
        userSubscribe.setType(newUser.getTypeSubscribe());
        UserReader userReader = new UserReader();
        userReader.setName(newUser.getName());
        userReader.setSureName(newUser.getSureName());
        userReader.setEmail(newUser.getEmail());
        userReader.setSubscribe(userSubscribe);
        userSubscribe.setUserReader(userReader);
        UserReader savedUserReader = userDAO.addUser(userReader);
        if(savedUserReader!=null){
            Status status = new Status("Success");
            List<UserReader> userReaderList = new ArrayList<>();
            userReaderList.add(savedUserReader);
            return new UserReaderRs(status,
                    dataTransfer.transferUserReaderFromDB(userReaderList));
        }
        return new UserReaderRs(new Status("ERROR WITH SAVE"),null);

    }


    @DeleteMapping("/{id}")
    public Status delUsers(@PathVariable Integer id) {
        Status status;
        boolean result = userDAO.deleteUserById(id);
        if (result) {
            return new Status( "Success");

        }
        return new Status( "User Not Found");
    }


}
