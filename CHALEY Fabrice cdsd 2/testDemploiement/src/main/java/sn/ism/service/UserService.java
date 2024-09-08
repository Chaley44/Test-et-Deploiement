package sn.ism.service;

import java.util.List;

import sn.ism.entity.User;

public interface UserService {
    User saveUser(User user) ;
    User fetchUserById(Long id);
    List<User> findAllUsers();

}
