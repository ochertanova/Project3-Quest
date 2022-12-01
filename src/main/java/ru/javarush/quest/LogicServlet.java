package ru.javarush.quest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.javarush.quest.features.Question;
import ru.javarush.quest.features.User;
import ru.javarush.quest.service.LogicService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LogicServlet", value = "/question")
public class LogicServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(LogicServlet.class);

    LogicService logicService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        logicService = (LogicService) servletContext.getAttribute("logicService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        User currentUser = (User) currentSession.getAttribute("currentUser");

        Question currentQuestion = logicService.getCurrentQuestion(currentUser);

        request.setAttribute("currentQuestion", currentQuestion);
        request.setAttribute("currentAnswers", currentQuestion.getAnswers());

        getServletContext().getRequestDispatcher("/WEB-INF/logic.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession currentSession = request.getSession();
        User currentUser = (User) currentSession.getAttribute("currentUser");
        String answerId = request.getParameter("answerId");

        boolean currentAnswerIsRight = logicService.checkUserAnswer(currentUser, answerId);

        if (currentAnswerIsRight) {
            logicService.changeUserPoint(currentUser, answerId);
        }

        boolean questIsEnd = logicService.checkEndOfQuest(currentUser);

        if (!questIsEnd) {
            Question nextCurrentQuestion = logicService.getNextCurrentQuestion(currentUser);
            logicService.changeUserCurrentQuestionId(currentUser, nextCurrentQuestion);
            LOGGER.info("Пользователь переходит к вопросу - {}", nextCurrentQuestion.getId());
            request.setAttribute("currentQuestion", nextCurrentQuestion);
            request.setAttribute("currentAnswers", nextCurrentQuestion.getAnswers());
            getServletContext().getRequestDispatcher("/WEB-INF/logic.jsp").forward(request, response);
        } else {
            response.sendRedirect("result");
        }
    }
}
