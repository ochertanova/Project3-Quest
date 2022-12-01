package ru.javarush.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.features.Answer;
import ru.javarush.quest.features.Question;
import ru.javarush.quest.features.User;
import ru.javarush.quest.service.LogicService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogicServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletConfig config;

    @Mock
    private ServletContext context;

    @Mock
    private RequestDispatcher requestDispatcher;
    @Mock
    private LogicService logicService;

    LogicServlet logicServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("logicService")).thenReturn(logicService);
        when(request.getSession()).thenReturn(session);

        logicServlet = new LogicServlet();
        logicServlet.init(config);

    }

    @Test
    void doGet() throws ServletException, IOException {
        List<Answer> answers = mock(ArrayList.class);
        Question question = mock(Question.class);
        User user = mock(User.class);

        when(question.getAnswers()).thenReturn(answers);
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(logicService.getCurrentQuestion(user)).thenReturn(question);
        when(context.getRequestDispatcher("/WEB-INF/logic.jsp")).thenReturn(requestDispatcher);

        logicServlet.doGet(request, response);

        verify(request, times(1)).setAttribute("currentQuestion", question);
        verify(request, times(1)).setAttribute("currentAnswers", answers);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void doPost_whenAnswerIsRightAndIsNotEnd() throws ServletException, IOException {
        Question nextQuestion = mock(Question.class);
        List<Answer> answers = mock(ArrayList.class);
        User user = mock(User.class);
        String answerId = "5";

        when(request.getParameter("answerId")).thenReturn(answerId);
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(logicService.checkUserAnswer(user, answerId)).thenReturn(true);
        when(logicService.checkEndOfQuest(user)).thenReturn(false);
        when(logicService.getNextCurrentQuestion(user)).thenReturn(nextQuestion);
        when(nextQuestion.getId()).thenReturn(5);
        when(nextQuestion.getAnswers()).thenReturn(answers);
        when(context.getRequestDispatcher("/WEB-INF/logic.jsp")).thenReturn(requestDispatcher);

        logicServlet.doPost(request, response);

        verify(logicService, times(1)).changeUserPoint(user, answerId);
        verify(request, times(1)).setAttribute("currentQuestion", nextQuestion);
        verify(request, times(1)).setAttribute("currentAnswers", answers);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void doPost_whenAnswerIsWrongAndIsEnd() throws ServletException, IOException {
        Question nextQuestion = mock(Question.class);
        List<Answer> answers = mock(ArrayList.class);
        User user = mock(User.class);
        String answerId = "5";

        when(request.getParameter("answerId")).thenReturn(answerId);
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(logicService.checkUserAnswer(user, answerId)).thenReturn(false);
        when(logicService.checkEndOfQuest(user)).thenReturn(true);

        logicServlet.doPost(request, response);

        verify(logicService, times(0)).changeUserPoint(user, answerId);
        verify(request, times(0)).setAttribute("currentQuestion", nextQuestion);
        verify(request, times(0)).setAttribute("currentAnswers", answers);
        verify(requestDispatcher, times(0)).forward(request, response);

        verify(response, times(1)).sendRedirect("result");
    }
}
