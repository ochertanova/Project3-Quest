package ru.javarush.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
class RestartServletTest {
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

    private RestartServlet restartServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(config.getServletContext()).thenReturn(context);
        when(request.getSession()).thenReturn(session);

        restartServlet = new RestartServlet();
        restartServlet.init(config);
    }

    @Test
    void doGet() throws ServletException, IOException {
        when(context.getRequestDispatcher("/WEB-INF/index.jsp")).thenReturn(requestDispatcher);

        restartServlet.doGet(request, response);
        verify(session, times(1)).invalidate();
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}