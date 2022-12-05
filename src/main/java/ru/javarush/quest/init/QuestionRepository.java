package ru.javarush.quest.init;

import ru.javarush.quest.features.Question;

import java.util.HashMap;

public class QuestionRepository {

    private HashMap<Integer, Question> questions;

    public QuestionRepository(HashMap<Integer, Question> questions) {
        this.questions = questions;
    }

    public Question getQuestion(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id can`t be null");
        }
        if (!questions.containsKey(id)) {
            throw new IllegalArgumentException("id isn`t exist");
        }
        return questions.get(id);
    }

    public void addNewQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("question can`t be null");
        }
        Integer key = questions.keySet().size() + 1;
        questions.put(key, question);
    }
}
