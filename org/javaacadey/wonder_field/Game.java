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
    private Tableau tableau;

    private String[] winners = new String[3];

    private Player[] players = new Player[3];
    private Yakubovich yakubovich;

    private ArrayList<Question> questions = new ArrayList<>();

    public static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public Game(Yakubovich yakubovich) {
        this.yakubovich = yakubovich;
    }

    public void init() {
        tableau = new Tableau();
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

    public void sleep() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Создать метод который создает игроков
    public Player[] setPlayer() {
        for (int i = 1; i <= COUNT_GAMER; i++) {
            System.out.println("Игрок №" + i + " представьтесь: имя,город. Например: Иван,Москва");
            String text = Game.scanner.nextLine();
            String[] name = text.split(",");
            players[i-1] = new Player(name[0], name[1]);
        }
        return players;
    }

    //Создать метод который вытащит все имена игроков в массив
    public String[] getNamePlayers(Player[] players) {
        String[] namePlayers = new String[3];
        for (int i = 0; i < players.length; i++) {
            namePlayers[i] = players[i].getName();
        }
        return namePlayers;
    }
    //5. Создать метод хода игрока (на вход вопрос и игрок), на выход булево: игрок ходит до тех пор,
    // пока он либо не выиграл (возвращается истина), либо не ошибся (возвращается ложь). Если игрок отгадал букву,
    // то должно появится обновленное табло.

    public boolean stepPlayer(Question question, Player player) {
        while (true) {
            PlayerAnswer playerAnswer = player.setStep();
            if (playerAnswer.getTypeAnswer() == "б") {
                if (question.getAnswer().contains(player.playerSayLetter())) {
                    int i = question.getAnswer().indexOf(player.playerSayLetter());
                    tableau.openLetters(i);
                    if (tableau.isCheckTableau()) {
                        return true;
                    }
                } else {
                    return false;
                }
            } else if ((playerAnswer.getTypeAnswer() == "с")) {
                if (question.getAnswer().equals(player.playerSayWord())) {
                    tableau.openWord();
                    return true;
                }
            }
        }
    }
    //6. Создать метод "сыграть раунд": игроки ходят по очереди и пытаются отгадать вопрос, до победы одно из игроков.
    // Если игрок победил в не финальном раунде, то заносится в массив победителей. Когда победитель найден, якубович кричит о победе.

    public void gameRound(Player[] players, Question question) {
        for (int i = 0; i < players.length; i++) {
            if (stepPlayer(question, players[i])) {
                winners[i] = players[i].getName();
                yakubovich.setWinner(players[i].getName(), players[i].getCity(), false);
            }
        }
    }
    //7. создать метод "сыграть все групповые раунды":
    //Играются три раунда.
    //В каждом раунде создаются игроки.
    //На табло добавляется ответ.
    //Якубович приглашает игроков.
    //Якубович спрашивает вопрос.
    //Появляется табло.
    //Играется групповой раунд.

    public void playsAllGroupRounds() {
        for (int i = 0; i < GROUP_ROUND; i++) {
            tableau.initTableau(questions.get(i));
            yakubovich.startShow();
            tableau.display(question.getAnswer());
            yakubovich.setShow(getNamePlayers(setPlayer()), i);
            gameRound(players, question);
            yakubovich.getQuestion(question.getQuestion());
            tableau.display(question.getAnswer());
        }
    }
    //8. Создать метод "сыграй финальный раунд"
    //На табло добавляется ответ.
    //Якубович приглашает победителей.
    //Якубович спрашивает вопрос.
    //Появляется табло.
    //Играется финальный раунд.

    public void playFinalRound() {
        for (int i = 0; i < COUNT_GAMER; i++) {
            tableau.display(question.getAnswer());
            yakubovich.setShow(winners, INDEX_FINAL_ROUND);
            yakubovich.getQuestion(question.getQuestion());
            tableau.display(question.getAnswer());
            Player[] playersWinner = new Player[3];
            for (int index = 0; index < winners.length; index++) {
                for (int j = 0; j < players.length; j++) {
                    if (winners[index].equals(players[j].getName())) {
                        playersWinner[index] = players[j];
                    }
                }
            }
            gameRound(playersWinner, question);
        }
    }

    //9. Создать метод старт:
    public void startGame() {
        yakubovich.startShow();
        playsAllGroupRounds();
        playFinalRound();
        yakubovich.endShow();
    }

}
