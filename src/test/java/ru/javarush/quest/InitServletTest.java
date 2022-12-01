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
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ServletContext context;

    @Mock
    private ServletConfig config;

    private InitServlet initServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(config.getServletContext()).thenReturn(context);

        initServlet = new InitServlet();
        initServlet.init(config);
    }

    @Test
    void doGet() throws ServletException, IOException {
        when(context.getRequestDispatcher("/WEB-INF/index.jsp")).thenReturn(requestDispatcher);

        initServlet.doGet(request, response);
        verify(requestDispatcher, times(1)).forward(request, response);
    }
}
