package ru.javarush.quest;


import ru.javarush.quest.features.Question;
import ru.javarush.quest.init.QuestInit;
import ru.javarush.quest.init.QuestionRepository;
import ru.javarush.quest.init.UserRepository;
import ru.javarush.quest.service.LogicService;
import ru.javarush.quest.service.RegistrationUserService;
import ru.javarush.quest.service.ResultService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;


@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        QuestInit questInit = new QuestInit();
        HashMap<Integer, Question> questions = questInit.init();

        QuestionRepository questionRepository = new QuestionRepository(questions);

        int numberOfQuestions = questions.keySet().size();
        UserRepository userRepository = new UserRepository();

        context.setAttribute("questions", questions);
        context.setAttribute("userRepository", userRepository);
        context.setAttribute("numberOfQuestions", numberOfQuestions);

        RegistrationUserService registrationUserService = new RegistrationUserService(userRepository);
        context.setAttribute("registrationService", registrationUserService);

        LogicService logicService = new LogicService(questionRepository);
        context.setAttribute("logicService", logicService);

        ResultService resultService = new ResultService(numberOfQuestions);
        context.setAttribute("resultService", resultService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
    }

}
