package ru.javarush.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.enums.ResultParam;
import ru.javarush.quest.enums.UserReaction;
import ru.javarush.quest.features.User;
import ru.javarush.quest.service.ResultService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResultServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext context;

    @Mock
    private ServletConfig config;

    @Mock
    private ResultService resultService;

    private ResultServlet resultServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("resultService")).thenReturn(resultService);
        when(request.getSession()).thenReturn(session);

        resultServlet = new ResultServlet();
        resultServlet.init(config);
    }

    @Test
    void doGet() throws ServletException, IOException {
        User user = mock(User.class);
        when(session.getAttribute("currentUser")).thenReturn(user);
        when(user.getPoint()).thenReturn(8);
        when(resultService.getQuestResult(user)).thenReturn(ResultParam.GREATE_RESULT);
        when(context.getRequestDispatcher("/WEB-INF/result.jsp")).thenReturn(requestDispatcher);

        resultServlet.doGet(request, response);

        verify(request, times(1)).setAttribute("userPoint", 8);
        verify(request, times(1)).setAttribute("questResult", ResultParam.GREATE_RESULT);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void doPost_whenUserStartAgain() throws ServletException, IOException {
        User user = User.builder()
                .name("UserTest")
                .bestResult(8)
                .point(8)
                .currentQuestionId(13)
                .build();

        when(request.getParameter("userReaction")).thenReturn(UserReaction.YES.getNameParam());
        when(session.getAttribute("currentUser")).thenReturn(user);

        resultServlet.doPost(request, response);

        verify(resultService, times(1)).resetParamForCurrentUser(user);
        verify(resultService, times(1)).changeStatistics(user);
        verify(response, times(1)).sendRedirect("question");
    }

    @Test
    void doPost_whenUserIsFinished() throws ServletException, IOException {
        User user = User.builder()
                .name("UserTest")
                .bestResult(8)
                .point(8)
                .currentQuestionId(13)
                .build();
        when(request.getParameter("userReaction")).thenReturn(UserReaction.NO.getNameParam());
        when(session.getAttribute("currentUser")).thenReturn(user);

        resultServlet.doPost(request, response);

        verify(resultService, times(0)).resetParamForCurrentUser(user);
        verify(resultService, times(1)).changeStatistics(user);
        verify(response, times(1)).sendRedirect("restart");
    }
}
