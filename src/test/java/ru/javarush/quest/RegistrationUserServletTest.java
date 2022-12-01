package ru.javarush.quest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javarush.quest.features.User;
import ru.javarush.quest.service.RegistrationUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationUserServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext context;

    @Mock
    private ServletConfig config;

    @Mock
    private RegistrationUserService registrationUserService;

    private RegistrationUserServlet registrationUserServlet;

    @BeforeEach
    void setup() throws ServletException {
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute("registrationService")).thenReturn(registrationUserService);
        when(request.getSession()).thenReturn(session);

        registrationUserServlet = new RegistrationUserServlet();
        registrationUserServlet.init(config);
    }

    @Test
    void doPost_positive_whenUserIsNew() throws IOException, ServletException {
        User user = mock(User.class);

        when(session.getAttribute("currentUser")).thenReturn(null);
        when(request.getParameter("userName")).thenReturn("User1");
        when(registrationUserService.initOrCreateCurrentUser("User1")).thenReturn(user);

        registrationUserServlet.doPost(request, response);

        verify(session, times(1)).setAttribute("currentUser", user);
        verify(session, times(1)).setAttribute("userName", "User1");
        verify(response, times(1)).sendRedirect("question");
    }

    @Test
    void doPost_positive_whenUserIsExist() throws IOException, ServletException {
        User user = mock(User.class);

        when(session.getAttribute("currentUser")).thenReturn(user);

        registrationUserServlet.doPost(request, response);

        verify(request, times(0)).getParameter("userName");
        verify(registrationUserService, times(0)).initOrCreateCurrentUser("User1");
        verify(session, times(0)).setAttribute("currentUser", user);
        verify(session, times(0)).setAttribute("userName", "User1");
        verify(response, times(1)).sendRedirect("question");
    }
}
