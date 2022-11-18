package ru.javarush.quest.init;

import lombok.Getter;
import ru.javarush.quest.features.Answer;
import ru.javarush.quest.features.Question;

import java.util.HashMap;
import java.util.List;


@Getter
public class QuestInit {
//    private HashMap<Long, Question> quest;
//    private Long questionId = 1L;
//    private Long answerId = 0L;

    public static HashMap<Long, Question> init() {
        HashMap<Long, Question> quest = new HashMap<>();
        Long questionId = 1L;
        long answerId = 1L;
        Question q1 = Question.builder()
                .id(questionId)
                .text("В квартирах героев сериала \"Друзья\" полно постеров и картин. Есть даже советские плакаты! Какой висел в квартире Джоуи и Чендлера?\n")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Чтобы строить — надо знать, чтобы знать — надо учиться")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Культурная программа")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Кенгуру-боксер")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Родина-мать зовет!")
                                .build()))
                .nextQuestionId(2L)
                .build();

        quest.put(q1.getId(), q1);
        answerId = resetAnswerId();


        Question q2 = Question.builder()
                .id(questionId++)
                .text("Кто на самом деле рисовал Розу в «Титанике»?")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Леонардо ДиКаприо")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Джеймс Кэмерон")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Билли Зейн")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Кэти Бейтс")
                                .build()))
                .nextQuestionId(3L)
                .build();

        quest.put(q2.getId(), q2);
        answerId = resetAnswerId();

        Question q3 = Question.builder()
                .id(questionId++)
                .text("Фильм \"Три билборда на границе Эббинга, штат Миссури\" основан на реальных событиях?")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Правда")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Ложь")
                                .right(true)
                                .weight(1)
                                .build()))
                .nextQuestionId(4L)
                .build();

        quest.put(q3.getId(), q3);
        answerId = resetAnswerId();

        Question q4 = Question.builder()
                .id(questionId++)
                .text("В каком советском фильме прозвучала фраза \"С жульём, допустим, надо бороться!\"")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Берегись автомобиля")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Бриллиантовая рука")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Джентельмены удачи")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Любовь и голуби")
                                .build()))
                .nextQuestionId(5L)
                .build();

        quest.put(q4.getId(), q4);
        answerId = resetAnswerId();

        Question q5 = Question.builder()
                .id(questionId++)
                .text("Что на самом деле представляет из себя легендарный зеленый код из «Матрицы»?")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Рецепт суши")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Рецепт пельменей")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Рецепт жаркого")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Рецепт Пад Тая")
                                .build()
                ))
                .nextQuestionId(questionId++)
                .build();

        quest.put(q5.getId(), q5);
        answerId = resetAnswerId();

        Question q6 = Question.builder()
                .id(questionId)
                .text("Какой комик появился в качестве зомби в \"Добро пожаловать в Zомбилэнд\"?")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Крис Рок")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Джим Керри")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Билл Мюррей")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Стив Мартин")
                                .build()))
                .nextQuestionId(questionId++)
                .build();

        quest.put(q6.getId(), q6);
        answerId = resetAnswerId();

        Question q7 = Question.builder()
                .id(questionId)
                .text("Сколько раз смотрел сериал \"Star Trek\" исполнитель главой роли Шелдона Купера Джим Парсонс?")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("1 раз")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Множество раз, является таким же поклонником, как и его герой")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("2 раза")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Ни одного, не любит комиксы, в отличие от своего персонажа")
                                .right(true)
                                .weight(1)
                                .build()))
                .nextQuestionId(questionId++)
                .build();

        quest.put(q7.getId(), q7);
        answerId = resetAnswerId();

        Question q8 = Question.builder()
                .id(questionId)
                .text("Какую книгу читала в метро Людмила из кинофильма \"Москва слезам не верит\", когда впервые встретилась с Гуриным")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("М.А. Булгаков \"Мастер и Маргарита\"")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Э.М. Ремарк\"Три товарища\"")
                                .right(true)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("В.М. Гюго \"Собор парижской богоматери\"")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("А.И. Куприн \"Яма\"")
                                .build()))
                .nextQuestionId(questionId++)
                .build();

        quest.put(q8.getId(), q8);
        answerId = resetAnswerId();

        Question q9 = Question.builder()
                .id(questionId)
                .text("В каком фильме не снимался Тимоти Шаламе?")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Чарли и шоколадная фабрика")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Дюна")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Интелстеллар")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Красивый мальчик")
                                .build()))
                .nextQuestionId(questionId++)
                .build();

        quest.put(q9.getId(), q9);
        answerId = resetAnswerId();

        Question q10 = Question.builder()
                .id(questionId)
                .text("Кто мог сыграть роль Майлза в фильме \"Семь\" вместо Бреда Питта")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("Леонардо ДиКаприо")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Дензел Вашингтон")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Роберт Дауни младший")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Сэм Рокуэлл")
                                .build()))
                .nextQuestionId(questionId++)
                .build();

        quest.put(q10.getId(), q10);
        questionId++;
        answerId=resetAnswerId();

        Question q11 = Question.builder()
                .id(questionId)
                .text("По мотивам какого произведения Эльдар Рязанов снял свой фильм \"Жестокий романс\"")
                .answers(List.of(Answer.builder()
                                .id(answerId)
                                .text("М.Ф. Достоевский \"Бедные люди\"")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("A.Н. Островский \"Бесприданница\"")
                                .right(true)
                                .weight(1)
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("Л.Н. Толстой \"Воскресение\"")
                                .build(),
                        Answer.builder()
                                .id(answerId++)
                                .text("М. Горький \"На дне\"")
                                .build()))
                .isEnd(true)
                .build();

        quest.put(q10.getId(), q10);

        return quest;
    }

    private static long resetAnswerId() {
        return 1L;
    }
}
