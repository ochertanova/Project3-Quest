package ru.javarush.quest.init;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javarush.quest.features.Answer;
import ru.javarush.quest.features.Question;

import java.util.HashMap;
import java.util.List;

public class QuestionRepositoryTest {

    private HashMap<Integer, Question> questionsTest;
    QuestionRepository questionRepository;

    private Question questionTest1 = Question.builder()
            .id(1)
            .text("Question1")
            .answers(List.of(Answer.builder()
                    .id(1)
                    .text("Ответ1")
                    .build(), Answer.builder()
                    .id(2)
                    .text("Ответ2")
                    .build()))
            .isEnd(false)
            .build();

    private Question questionTest2 = Question.builder()
            .id(2)
            .text("Question2")
            .answers(List.of(Answer.builder()
                    .id(1)
                    .text("Ответ2")
                    .build(), Answer.builder()
                    .id(2)
                    .text("Ответ2")
                    .build()))
            .isEnd(false)
            .build();

    private Question questionTest3 = Question.builder()
            .id(3)
            .text("Question3")
            .answers(List.of(Answer.builder()
                    .id(1)
                    .text("Ответ1")
                    .build(), Answer.builder()
                    .id(2)
                    .text("Ответ2")
                    .build()))
            .isEnd(false)
            .build();

    private Question questionTest4 = Question.builder()
            .id(3)
            .text("Question4")
            .answers(List.of(Answer.builder()
                    .id(1)
                    .text("Ответ1")
                    .build(), Answer.builder()
                    .id(2)
                    .text("Ответ2")
                    .build()))
            .isEnd(true)
            .build();


    @BeforeEach
    void setQuestions() {
        questionsTest = new HashMap<>();
        questionsTest.put(1, questionTest1);
        questionsTest.put(2, questionTest2);
        questionsTest.put(3, questionTest3);

        questionRepository = new QuestionRepository(questionsTest);
    }

    @Test
    void test_getQuestion_checkPositive_whenIdIsCorrect() {
        Assertions.assertEquals(questionRepository.getQuestion(2), questionTest2);
    }

    @Test
    void test_getQuestion_checkThrowException_whenIdIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> questionRepository.getQuestion(null));
    }

    @Test
    void test_getQuestion_checkMessageException_whenIdIsNull() {
        try {
            Question question = questionRepository.getQuestion(null);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("id can`t be null", ex.getMessage());
        }
    }

    @Test
    void test_getQuestion_checkThrowException_whenIdIsNotExist() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> questionRepository.getQuestion(5));
    }

    @Test
    void test_getQuestion_checkMessageException_whenIdIsNotExist() {
        try {
            Question question = questionRepository.getQuestion(5);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("id isn`t exist", ex.getMessage());
        }
    }

    @Test
    void test_addNewQuestion_checkPositive_whenQuestionIsCorrect() {
        questionRepository.addNewQuestion(questionTest4);
        Assertions.assertEquals(questionsTest.size(), 4);
        Assertions.assertEquals(questionRepository.getQuestion(4), questionTest4);
    }

    @Test
    void test_addNewQuestion_checkThrowException_whenQuestionIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> questionRepository.addNewQuestion(null));
    }

    @Test
    void test_addNewQuestion_checkMessageException_whenQuestionNull() {
        try {
            questionRepository.addNewQuestion(null);
        } catch (IllegalArgumentException ex) {
            Assertions.assertEquals("question can`t be null", ex.getMessage());
        }
    }
}
