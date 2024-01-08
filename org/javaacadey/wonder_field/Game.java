package org.javaacadey.wonder_field;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import org.javaacadey.wonder_field.player.Player;
import org.javaacadey.wonder_field.player.PlayerAnswer;

public class Game {
    public static final int COUNT_GAMER = 3;
    public static final int COUNT_ROUND = 4;
    public static final int GROUP_ROUND = 3;
    public static final int INDEX_FINAL_ROUND = 3;
    private Question question;
    private Tableau tableau = new Tableau();
    private Player[] winners = new Player[3];
    private Player[] players = new Player[3];
    private Yakubovich yakubovich = new Yakubovich();
    private ArrayList<Question> questions = new ArrayList<>();
    public static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    private void init() {
        System.out.println("Запуск игры \"Поле Чудес\" +- подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");
        for (int i = 1; i < 5; i++) {

            System.out.println("Введите вопрос #" + i);
            String questionRound = scanner.nextLine();

            System.out.println("Введите ответ вопрос #" + i);
            String answerRound = scanner.nextLine();
            question = new Question(questionRound, answerRound.toUpperCase());
            questions.add(question);
        }

        System.out.println("Иницализация закончена, игра начнется через 5 секунд");
        sleep();
        System.out.println("\n".repeat(50));
    }

    public ArrayList<Question> init_test() {
        question = new Question("Столица РТ", "Казань".toUpperCase());
        questions.add(question);
        question = new Question("Столица РФ", "Москва".toUpperCase());
        questions.add(question);
        question = new Question("Столица Белоруссии", "Минск".toUpperCase());
        questions.add(question);
        question = new Question("Спутник Земли", "Луна".toUpperCase());
        questions.add(question);
        return questions;
    }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Player[] setPlayer() {
        for (int i = 1; i <= COUNT_GAMER; i++) {
            System.out.println("Игрок №" + i + " представьтесь: имя,город. Например: Иван,Москва");
            String text = Game.scanner.nextLine();
            String[] name = text.split(",");
            players[i - 1] = new Player(name[0], name[1]);
        }
        return players;
    }

    private String[] getNamePlayers(Player[] players) {
        String[] namePlayers = new String[3];
        for (int i = 0; i < players.length; i++) {
            namePlayers[i] = players[i].getName();
        }
        return namePlayers;
    }

    private boolean stepPlayer(Question question, Player player) {
        while (true) {
            PlayerAnswer playerAnswer = player.setStep();
            if (playerAnswer.getTypeAnswer().equals("б")) {
                String strAnswer = playerAnswer.getAnswer();
                String strQuestion = question.getAnswer();
                if (strQuestion.contains(strAnswer)) {
                    yakubovich.setAnswer(playerAnswer.getAnswer(), question.getAnswer(), tableau);
                    if (Tableau.getUncknowWord().contains(" ")) {
                        continue;
                    }
                    if (tableau.isCheckTableau()) {
                        return true;
                    }
                } else {
                    return false;
                }
            } else if ((playerAnswer.getTypeAnswer().equals("с"))) {
                if (question.getAnswer().equals(playerAnswer.getAnswer())) {
                    tableau.openWord();
                    return true;
                }
                return false;
            }
        }
    }

    private void gameRound(Player[] players, Question question) {
        while (!tableau.isCheckTableau()) {
            for (int i = 0; i < players.length; i++) {
                if (stepPlayer(question, players[i])) {
                    if (winners[0] == null) {
                        winners[0] = yakubovich.setWinner(players[i].getName(), players[i].getCity(), false);
                        break;
                    } else if (winners[1] == null) {
                        winners[1] = yakubovich.setWinner(players[i].getName(), players[i].getCity(), false);
                        break;
                    } else if (winners[2] == null) {
                        winners[2] = yakubovich.setWinner(players[i].getName(), players[i].getCity(), false);
                        break;
                    }
                    if (yakubovich.isFinalRound()) {
                        yakubovich.setWinner(players[i].getName(), players[i].getCity(), true);
                        return;
                    }
                }
            }
        }
    }

    private void playsAllGroupRounds() {
        for (int i = 0; i < GROUP_ROUND; i++) {
            Player[] players = setPlayer();
            tableau.initTableau(questions.get(i));
            yakubovich.setShow(players, i);
            yakubovich.getQuestion(questions.get(i).getQuestion());
            gameRound(players, questions.get(i));
        }
    }

    private void playFinalRound() {
        yakubovich.setFinalRound(true);
        for (int i = 0; i < COUNT_GAMER; i++) {
            yakubovich.setShow(winners, INDEX_FINAL_ROUND);
            tableau.initTableau(questions.get(INDEX_FINAL_ROUND));
            yakubovich.getQuestion(question.getQuestion());
            gameRound(winners, questions.get(INDEX_FINAL_ROUND));
            return;
        }
    }

    public void startGame() {
        init();
        yakubovich.startShow();
        playsAllGroupRounds();
        playFinalRound();
        yakubovich.endShow();
    }

}
