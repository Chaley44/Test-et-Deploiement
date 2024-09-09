package sn.ism;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sn.ism.entity.User;
import sn.ism.service.UserServiceImpl;

public class UserServiceTests {

	private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
    }

    @Test
    void testSaveUser() {
        User user = new User(4L, "Fabrice", "fabrice@example.com", "123456");
        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("Fabrice", savedUser.getName());
        assertEquals(4L, savedUser.getId());
    }

    @Test
    void testFetchUserById() {
        User user = userService.fetchUserById(1L);

        assertNotNull(user);
        assertEquals("Jordane", user.getName());
    }

    @Test
    void testFindAllUsers() {
        List<User> users = userService.findAllUsers();

        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    void testFetchUserByIdNotFound() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.fetchUserById(99L);
        });

        assertEquals("User not found", exception.getMessage());
    }
    
    @Test
    void testUpdateUser() {
        User updatedUser = new User(1L, "Jordan", "jordan@doe.com", "newpassword");
        User user = userService.updateUser(1L, updatedUser);

        assertNotNull(user);
        assertEquals("Jordan", user.getName());
        assertEquals("jordan@doe.com", user.getAdresse());
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        Exception exception = assertThrows(RuntimeException.class, () -> userService.fetchUserById(1L));
        assertEquals("User not found", exception.getMessage());
    }
}
