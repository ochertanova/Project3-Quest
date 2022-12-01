package ru.javarush.quest.init;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.features.User;

class UserRepositoryTest {

    private UserRepository userRepository;

    private User user1 = User.builder()
            .name("User1")
            .point(0)
            .currentQuestionId(1)
            .bestResult(0)
            .build();

    private User user2 = User.builder()
            .name("User2")
            .point(0)
            .currentQuestionId(1)
            .bestResult(0)
            .build();

    private User user3 = User.builder()
            .name("User3")
            .point(0)
            .currentQuestionId(1)
            .bestResult(0)
            .build();

    private User user4 = User.builder()
            .name("")
            .point(0)
            .currentQuestionId(1)
            .bestResult(0)
            .build();

    private User user5 = User.builder()
            .name(null)
            .point(0)
            .currentQuestionId(1)
            .bestResult(0)
            .build();

    private User user6 = User.builder()
            .name("     ")
            .point(0)
            .currentQuestionId(1)
            .bestResult(0)
            .build();

    private User user7;


    @BeforeEach
    void setUserRepository() {
        userRepository = new UserRepository();
    }

    @Test
    void test_saveUser_checkPositive_whenUserIsCorrect() {
        userRepository.saveUser(user1);
        Assertions.assertEquals(userRepository.getUser("User1"), user1);
        Assertions.assertEquals(userRepository.getUsers().keySet().size(), 1);
    }

    @Test
    void test_saveUser_checkThrowException_whenUserNameIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userRepository.saveUser(user5));
    }

    @Test
    void test_saveUser_checkThrowMessageException_whenUserNameIsNull() {
        try {
            userRepository.saveUser(user5);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("name can`t be empty", ex.getMessage());
        }
    }

    @Test
    void test_saveUser_checkThrowException_whenUserNameIsSpace() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userRepository.saveUser(user4));
    }

    @Test
    void test_saveUser_checkThrowMessageException_whenUserNameIsSpace() {
        try {
            userRepository.saveUser(user4);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("name can`t be empty", ex.getMessage());
        }
    }

    @Test
    void test_saveUser_checkThrowException_whenUserNameIsBlank() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userRepository.saveUser(user6));
    }

    @Test
    void test_saveUser_checkThrowMessageException_whenUserNameIsBlank() {
        try {
            userRepository.saveUser(user6);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("name can`t be empty", ex.getMessage());
        }
    }

    @Test
    void test_saveUser_checkThrowException_whenUserIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> userRepository.saveUser(user7));
    }

    @Test
    void test_saveUser_checkThrowMessageException_whenUserIsNull() {
        try {
            userRepository.saveUser(user7);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("user can`t be null", ex.getMessage());
        }
    }

    @Test
    void test_getUsers_checkPositive() {
        userRepository.saveUser(user1);
        userRepository.saveUser(user2);
        userRepository.saveUser(user3);
        Assertions.assertEquals(userRepository.getUsers().size(), 3);
    }

    @Test
    void test_getUser_checkThrowMessageException_whenUserIsNotExist() {
        userRepository.saveUser(user1);
        try {
            userRepository.getUser("Test");
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("User with name isn`t exist in repository", ex.getMessage());
        }
    }

    @Test
    void test_getUser_checkThrowException_whenUserIsNotExist() {
        userRepository.saveUser(user1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> userRepository.getUser("Test"));
    }
}