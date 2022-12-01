package ru.javarush.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.enums.ResultParam;
import ru.javarush.quest.enums.UserReaction;
import ru.javarush.quest.features.User;
import ru.javarush.quest.service.ResultService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ResultServlet", value = "/result")
public class ResultServlet extends HttpServlet {

    ResultService resultService;

    private static final Logger LOGGER = LogManager.getLogger(ResultServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        resultService = (ResultService) servletContext.getAttribute("resultService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        User currentUser = (User) currentSession.getAttribute("currentUser");

        request.setAttribute("userPoint", currentUser.getPoint());

        ResultParam questResult = resultService.getQuestResult(currentUser);
        request.setAttribute("questResult", questResult);

        getServletContext().getRequestDispatcher("/WEB-INF/result.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userReaction = request.getParameter("userReaction");

        HttpSession currentSession = request.getSession();
        User currentUser = (User) currentSession.getAttribute("currentUser");

        resultService.changeStatistics(currentUser);

        if (UserReaction.YES.getNameParam().equals(userReaction)) {
            resultService.resetParamForCurrentUser(currentUser);
            LOGGER.info("Пользователь решил сыграть еще. Переходим сразу на страницу вопросов, начиная с 1го");
            response.sendRedirect("question");
        }
        if (UserReaction.NO.getNameParam().equals(userReaction)) {
            LOGGER.info("Пользователь решил закончить игру. Отображаем стартовую страницу регистрации");
            response.sendRedirect("restart");
        }
    }
}
