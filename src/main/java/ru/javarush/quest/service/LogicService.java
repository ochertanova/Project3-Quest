package ru.javarush.quest.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.exception.InvalidQuestException;
import ru.javarush.quest.features.Answer;
import ru.javarush.quest.features.Question;
import ru.javarush.quest.features.User;
import ru.javarush.quest.init.QuestionRepository;

public class LogicService {

    private static final Logger LOGGER = LogManager.getLogger(LogicService.class);
    QuestionRepository questionRepository;

    public LogicService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;}

    public Question getCurrentQuestion(User currentUser) {
        return questionRepository.getQuestion(currentUser.getCurrentQuestionId());
    }

    public boolean checkUserAnswer(User currentUser, String answerId) {
        Question question = getCurrentQuestion(currentUser);
        Answer answer = getAnswer(question, answerId);
        return answer.isRight();
    }

    public boolean checkEndOfQuest(User currentUser) {
        Question question = getCurrentQuestion(currentUser);
        return question.isEnd();
    }

    public void changeUserPoint(User currentUser, String answerId) {
        Answer currentAnswer = getAnswer(getCurrentQuestion(currentUser), answerId);
        currentUser.setPoint(currentUser.getPoint() + currentAnswer.getWeight());
    }

    public Question getNextCurrentQuestion(User currentUser) {
        Question question = getCurrentQuestion(currentUser);
        Integer nextQuestionId = question.getNextQuestionId();
        return questionRepository.getQuestion(nextQuestionId);
    }

    public void changeUserCurrentQuestionId(User currentUser, Question nextQuestion) {
        currentUser.setCurrentQuestionId(nextQuestion.getId());
    }

    private Answer getAnswer(Question question, String answerId) {
        int currentAnswerId;
        try {
            currentAnswerId = Integer.parseInt(answerId);
        } catch (IllegalArgumentException ex) {
            throw new InvalidQuestException("answerId должен быть числом", ex);
        }

        if (question == null) {
            LOGGER.error("Ответ и вопрос обязательно должны быть выбраны");
            throw new IllegalArgumentException("Параметры не могут быть null!");
        }
        return question.getAnswers().get(currentAnswerId);
    }
}
