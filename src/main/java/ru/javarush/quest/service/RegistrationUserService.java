package ru.javarush.quest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.features.User;
import ru.javarush.quest.init.UserRepository;

public class RegistrationUserService {

    private final UserRepository userRepository;

    private static final Logger LOGGER = LogManager.getLogger(RegistrationUserService.class);

    public RegistrationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User initOrCreateCurrentUser(String userName) {
        User currentUser;

        if (userRepository.isExist(userName)) {
            LOGGER.info("Пользователь с именем {} уже был ранее сохранен в репозитории", userName);
            currentUser = userRepository.getUser(userName);
            LOGGER.info("Устанавливаем кол-во набранных очков для пользователя равным 0");
            currentUser.setPoint(0);
            LOGGER.info("Устанавливаем первый вопрос квеста с индексом 1");
            currentUser.setCurrentQuestionId(1);
        } else {
            currentUser = User.builder()
                    .name(userName)
                    .point(0)
                    .currentQuestionId(1)
                    .bestResult(0)
                    .build();
            userRepository.saveUser(currentUser);
            LOGGER.info("Новый пользователь добавлен в репозиторий - {}", currentUser.toString());
        }
        return currentUser;
    }
}
