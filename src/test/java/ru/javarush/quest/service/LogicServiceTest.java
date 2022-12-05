package ru.javarush.quest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.features.Answer;
import ru.javarush.quest.features.Question;
import ru.javarush.quest.features.User;
import ru.javarush.quest.init.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
class LogicServiceTest {

    @Mock
    QuestionRepository questionRepository;

    LogicService logicService;

    @BeforeEach
    void setup() {
        logicService = new LogicService(questionRepository);
    }

    @Test
    void test_getCurrentQuestion_positive() {
        User user = mock(User.class);
        Question question = mock(Question.class);
        when(user.getCurrentQuestionId()).thenReturn(5);
        when(questionRepository.getQuestion(5)).thenReturn(question);

        Assertions.assertEquals(question, logicService.getCurrentQuestion(user));
    }

    @Test
    void test_getCurrentQuestion_checkThrowException_whenUserIsNull() {
        User user = mock(User.class);
        when(user.getCurrentQuestionId()).thenReturn(null);
        when(questionRepository.getQuestion(null)).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> logicService.getCurrentQuestion(user));
    }

    @Test
    void test_checkCurrentAnswer_positive_answerIsRight() {
        User user = mock(User.class);
        Answer answer = mock(Answer.class);
        Question question = mock(Question.class);
        List<Answer> answerList = mock(ArrayList.class);

        when(user.getCurrentQuestionId()).thenReturn(5);
        when(questionRepository.getQuestion(5)).thenReturn(question);
        when(answer.isRight()).thenReturn(true);
        when(question.getAnswers()).thenReturn(answerList);
        when(answerList.get(2)).thenReturn(answer);

        Assertions.assertEquals(true, logicService.checkUserAnswer(user, "2"));
    }

    @Test
    void test_checkEndOfQuest_positive_questIsEnd() {
        User user = mock(User.class);
        Question question = mock(Question.class);
        when(user.getCurrentQuestionId()).thenReturn(5);
        when(questionRepository.getQuestion(5)).thenReturn(question);
        when(question.isEnd()).thenReturn(true);

        Assertions.assertEquals(true, logicService.checkEndOfQuest(user));
    }

    @Test
    void test_getNextCurrentQuestion() {
        User user = mock(User.class);
        Question question = mock(Question.class);
        Question nextQuestion = mock(Question.class);
        when(user.getCurrentQuestionId()).thenReturn(5);
        when(questionRepository.getQuestion(5)).thenReturn(question);
        when(question.getNextQuestionId()).thenReturn(6);
        when(questionRepository.getQuestion(6)).thenReturn(nextQuestion);

        Assertions.assertEquals(nextQuestion, logicService.getNextCurrentQuestion(user));
    }

    @Test
    void check_changeUserPoint() {
        User user = mock(User.class);
        Answer answer = mock(Answer.class);
        Question question = mock(Question.class);
        List<Answer> answerList = mock(ArrayList.class);

        when(user.getCurrentQuestionId()).thenReturn(5);
        when(questionRepository.getQuestion(5)).thenReturn(question);
        when(question.getAnswers()).thenReturn(answerList);
        when(answerList.get(2)).thenReturn(answer);
        when(answer.getWeight()).thenReturn(1);
        when(user.getPoint()).thenReturn(2);

        logicService.changeUserPoint(user, "2");
        verify(user, times(1)).setPoint(3);
    }

    @Test
    void check_changeUserCurrentQuestionId() {
        User user = mock(User.class);
        Question nextQuestion = mock(Question.class);
        when(nextQuestion.getId()).thenReturn(6);

        logicService.changeUserCurrentQuestionId(user, nextQuestion);
        verify(user, times(1)).setCurrentQuestionId(6);
    }
}
