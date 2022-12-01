package ru.javarush.quest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.enums.ResultParam;
import ru.javarush.quest.features.User;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultServiceTest {

    private int numberOfQuestions = 10;

    private ResultService resultService;

    @BeforeEach
    void setup() {
        resultService = new ResultService(numberOfQuestions);
    }

    @Test
    void test_getQuestResult_positive_GREATE_RESULT() {
        User user = mock(User.class);
        when(user.getPoint()).thenReturn(8);

        Assertions.assertEquals(ResultParam.GREATE_RESULT, resultService.getQuestResult(user));
    }

    @Test
    void test_getQuestResult_positive_MIDDLE_RESULT() {
        User user = mock(User.class);
        when(user.getPoint()).thenReturn(5);

        Assertions.assertEquals(ResultParam.MIDDLE_RESULT, resultService.getQuestResult(user));
    }

    @Test
    void test_getQuestResult_positive_BAD_RESULT() {
        User user = mock(User.class);
        when(user.getPoint()).thenReturn(0);

        Assertions.assertEquals(ResultParam.BAD_RESULT, resultService.getQuestResult(user));
    }

    @Test
    void test_changeStatistics_positive_whenUserPlayFirstTime() {
        User user = mock(User.class);
        when(user.getBestResult()).thenReturn(0);
        when(user.getPoint()).thenReturn(8);

        resultService.changeStatistics(user);

        verify(user, times(1)).setBestResult(8);
    }

    @Test
    void test_changeStatistics_positive_whenBestResultMoreCurrentPoint() {
        User user = mock(User.class);
        when(user.getBestResult()).thenReturn(8);
        when(user.getPoint()).thenReturn(5);

        resultService.changeStatistics(user);

        verify(user, times(0)).setBestResult(5);
    }

    @Test
    void test_changeStatistics_positive_whenBestResultLessCurrentPoint() {
        User user = mock(User.class);
        when(user.getBestResult()).thenReturn(5);
        when(user.getPoint()).thenReturn(8);

        resultService.changeStatistics(user);

        verify(user, times(1)).setBestResult(8);
    }

    @Test
    void test_resetParamForCurrentUser_possitive() {
        User user = mock(User.class);
        resultService.resetParamForCurrentUser(user);

        verify(user, times(1)).setCurrentQuestionId(1);
        verify(user, times(1)).setPoint(0);
    }
}