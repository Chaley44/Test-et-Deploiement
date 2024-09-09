package sn.ism.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sn.ism.entity.User;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>(
            List.of(new User(1L, "Jordane", "john@doe.com", "123456"),
                    new User(2L, "Jane", "jane@doe.com", "123456"),
                    new User(3L, "Bob", "bob@doe.com", "123456"))
    );

    /**
     * @param user
     * @return User
     */
    @Override
    public User saveUser(User user) {
        //user.setId(RandomGenerator.getDefault().nextLong());
        //user.setName("Fabrice " + user.getName());
        users.add(user);
        return user;
    }

    /**
     * @param id
     * @return User
     */
    @Override
    public User fetchUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return users;
    }
    
    /**
     * @param id
     * @param user
     * @return User
     */
    public User updateUser(Long id, User updatedUser) {
        User user = fetchUserById(id);
        user.setName(updatedUser.getName());
        user.setAdresse(updatedUser.getAdresse());
        user.setPassword(updatedUser.getPassword());
        return user;
    }

    /**
     * @param id
     * @return void
     */
    public void deleteUser(Long id) {
        User user = fetchUserById(id);
        users.remove(user);
    }
}
