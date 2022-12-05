package ru.javarush.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.features.User;
import ru.javarush.quest.service.RegistrationUserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegistrationUserServlet", value = "/registration")
public class RegistrationUserServlet extends HttpServlet {

    RegistrationUserService registrationUserService;

    private static final Logger LOGGER = LogManager.getLogger(RegistrationUserServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        registrationUserService = (RegistrationUserService) servletContext.getAttribute("registrationService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession currentSession = request.getSession();
        if (currentSession.getAttribute("currentUser") != null) {
            LOGGER.info("Пользователь уже был в сессии - {}", currentSession.getAttribute("currentUser"));
            response.sendRedirect("question");
            return;
        }

        String userName = request.getParameter("userName");
        User currentUser = registrationUserService.initOrCreateCurrentUser(userName);

        LOGGER.info("Сохраняем пользователя в сессию: {}", currentUser.toString());
        currentSession.setAttribute("currentUser", currentUser);
        currentSession.setAttribute("userName", userName);

        LOGGER.info("Переходим к вопросам квеста");
        response.sendRedirect("question");
    }
}
