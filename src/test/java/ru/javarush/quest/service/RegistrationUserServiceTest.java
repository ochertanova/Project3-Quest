package ru.javarush.quest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.features.User;
import ru.javarush.quest.init.UserRepository;

class RegistrationUserServiceTest {

    UserRepository userRepository;
    RegistrationUserService registrationUserService;

    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
        registrationUserService = new RegistrationUserService(userRepository);
    }

    @Test
    void test_initOrCreateCurrentUser_whenUserIsExistInRepository() {
        Assertions.assertEquals(userRepository.getUsers().keySet().size(), 0);

        User user = User.builder()
                .name("User1")
                .point(0)
                .bestResult(0)
                .currentQuestionId(1)
                .build();

        registrationUserService.initOrCreateCurrentUser(user.getName());
        Assertions.assertEquals(1, userRepository.getUsers().keySet().size());

        registrationUserService.initOrCreateCurrentUser(user.getName());
        Assertions.assertEquals(1, userRepository.getUsers().keySet().size());
    }

    @Test
    void test_initOrCreateCurrentUser_whenUserIsNew() {
        Assertions.assertEquals(userRepository.getUsers().keySet().size(), 0);

        User user = User.builder()
                .name("User1")
                .point(0)
                .bestResult(0)
                .currentQuestionId(1)
                .build();

        registrationUserService.initOrCreateCurrentUser(user.getName());
        Assertions.assertEquals(1, userRepository.getUsers().keySet().size());
    }

    @Test
    void test_initOrCreateCurrentUser_checkThrowException_whenUserIsNotEmpty() {
        Assertions.assertEquals(userRepository.getUsers().keySet().size(), 0);

        User user = User.builder()
                .name("")
                .point(0)
                .bestResult(0)
                .currentQuestionId(1)
                .build();
        Assertions.assertThrows(IllegalArgumentException.class, () -> registrationUserService.initOrCreateCurrentUser(user.getName()));
    }
}