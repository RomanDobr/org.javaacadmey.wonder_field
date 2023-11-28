package org.javaacadey.wonder_field;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static final int COUNT_GAMER = 3;
    public static final int COUNT_ROUND = 4;
    public static final int GROUP_ROUND = 3;
    public static final int INDEX_FINAL_ROUND = 3;

    private Question question;
    private Tableau tableau;

    private ArrayList<Question>questions=new ArrayList<>();

    public static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public Game() {
        Yakubovich yakubovich = new Yakubovich();
    }

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        for (int i = 1; i < 5; i++) {
            System.out.println("Введите вопрос #" + i);
            String questionTmp=scanner.nextLine();

            System.out.println("Введите ответ вопрос #" + i);
            String answerTmp = scanner.nextLine();

            question=new Question(questionTmp,answerTmp.toUpperCase());
            questions.add(question);
        }

        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        sleep();
        System.out.println("\n".repeat(50));
    }
    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
