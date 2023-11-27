package org.javaacadey.wonder_field;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final int COUNT_GAMER = 3;
    private static final int COUNT_ROUND = 4;
    private static final int GROUP_ROUND = 3;
    private static final int INDEX_FINAL_ROUND = 3;

    private Question question;
    private Tableau tableau;

    ArrayList<Question>questions=new ArrayList<>();

    public static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        for (int i = 1; i < 5; i++) {
            System.out.println("Введите вопрос #" + i);
            String quest=scanner.nextLine();

            System.out.println("Введите ответ вопрос #" + i);
            String answ = scanner.nextLine();

            question=new Question(quest,answ);
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

    public static Scanner getScanner() {
        return scanner;
    }

}
