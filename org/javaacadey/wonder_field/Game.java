package org.javaacadey.wonder_field;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static final int COUNT_GAMER = 3;
    private static final int COUNT_ROUND = 4;
    private static final int GROUP_ROUND = 3;
    private static final int INDEX_FINAL_ROUND = 3;

    Question question;
    Tableau tableau;

    static final Scanner scanner = new Scanner(new InputStreamReader(System.in));


    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");

        for (int i = 1; i < 5; i++) {
            System.out.println("Введите вопрос #" + i);
            question.questionList.add(scanner.nextLine());
            System.out.println("Введите ответ вопрос #" + i);
            question.answerList.add(scanner.nextLine());
        }
        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        sleep();
        System.out.println("\n".repeat(50));

//        for (int j = 0; j < 50; j++) {
//            System.out.println("");
//        }
        //scanner.close();
    }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
