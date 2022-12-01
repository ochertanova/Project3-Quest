package ru.javarush.quest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.enums.ResultParam;
import ru.javarush.quest.features.User;

public class ResultService {
    private final int numberOfQuestion;

    private static final Logger LOGGER = LogManager.getLogger(ResultService.class);

    public ResultService(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public ResultParam getQuestResult(User currentUser) {

        int userPoint = currentUser.getPoint();

        LOGGER.info("Количество набранных очков пользователя {} равно: {}", currentUser.getName(), userPoint);

        if (userPoint >= (numberOfQuestion * 0.75)) {
            LOGGER.info("Результат - {}", ResultParam.GREATE_RESULT.getParam());
            return ResultParam.GREATE_RESULT;
        } else if (userPoint >= (numberOfQuestion * 0.5) && userPoint < (numberOfQuestion * 0.75)) {
            LOGGER.info("Результат - {}", ResultParam.MIDDLE_RESULT.getParam());
            return ResultParam.MIDDLE_RESULT;
        } else {
            LOGGER.info("Результат - {}", ResultParam.BAD_RESULT.getParam());
            return ResultParam.BAD_RESULT;
        }
    }

    public void changeStatistics(User currentUser) {
        if (currentUser.getBestResult() != 0) {
            if (currentUser.getBestResult() < currentUser.getPoint()) {
                LOGGER.info("Изменяем лучший результат равный: {} для пользователя: {}",
                        currentUser.getBestResult(), currentUser.getName());
                currentUser.setBestResult(currentUser.getPoint());
            }
        } else {
            currentUser.setBestResult(currentUser.getPoint());
            LOGGER.info("Сохраняем лучший результат равный: {} для пользователя: {}",
                    currentUser.getBestResult(), currentUser.getName());
        }
    }

    public void resetParamForCurrentUser(User currentUser) {
        LOGGER.info("Сбрасываем значения point и currentQuestionId к исходым для пользователя: {} ", currentUser.getName());
        currentUser.setPoint(0);
        currentUser.setCurrentQuestionId(1);
    }
}
