package org.javaacadey.wonder_field;

import org.javaacadey.wonder_field.player.Player;

public class Yakubovich {
    private Player player;
    private Player[] players;
    private Question question;
    private boolean isFinalRound = false;

    public void startShow() {
        System.out.println("Здравствуйте, уважаемые дамы и господа!");
        System.out.println("Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    public void endShow() {
        System.out.println("Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    public void setShow(Player[] players, int roundNumber) {
        if (roundNumber != Game.INDEX_FINAL_ROUND) {
            System.out.println("приглашаю (" + (roundNumber + 1) + ") тройку игроков: ("
                    + getNamePlayers(players) + ")");
        } else if (roundNumber == Game.INDEX_FINAL_ROUND) {
            System.out.println("приглашаю победителей групповых этапов: ("
                    + getNamePlayers(players) + ")");
        }
    }

    public void getQuestion(String s) {
        System.out.println("Внимание вопрос!");
        System.out.println(s + "?");
    }

    public Player setWinner(String namePlayer, String cityPlayer, boolean isFinalRound) {
        if (!isFinalRound) {
            System.out.println("Молодец! (" + namePlayer + ") из (" + cityPlayer + ") проходит в финал!");
            return new Player(namePlayer, cityPlayer);
        } else {
            System.out.println("И перед нами победитель Капитал шоу поле чудес! "
                    + "Это (" + namePlayer + ") из (" + cityPlayer + ")");
        }
        return new Player(namePlayer, cityPlayer);
    }

    public void setAnswer(String answerPlayer, String correctAnswer, Tableau tableau) {
        if (answerPlayer.length() == 1) {
            if (correctAnswer.contains(answerPlayer)) {
                int i = correctAnswer.indexOf(answerPlayer);
                System.out.println("Есть такая буква, откройте ее!");
                Tableau.setUncknowWord(i, answerPlayer);
                if (i != correctAnswer.length()) {
                    if (correctAnswer.substring(i + 1, (correctAnswer.length())).contains(answerPlayer)) {
                        String strTmp = correctAnswer.substring((i + 1), (correctAnswer.length()));
                        int y = strTmp.indexOf(answerPlayer) + (i + 1);
                        Tableau.setUncknowWord(y, answerPlayer);
                    }
                }
                tableau.display(answerPlayer);
            } else if (!correctAnswer.contains(answerPlayer)) {
                System.out.println("Нет такой буквы! Следующий игрок, крутите барабан!");
            }
            System.out.println("__________________________________");
        } else if (answerPlayer.length() > 1) {
            if (answerPlayer.equals(correctAnswer)) {
                System.out.println("(" + answerPlayer + ")! Абсолютно верно!");
                tableau.openWord();
            } else if (!answerPlayer.equals(correctAnswer)) {
                System.out.println("Неверно! Следующий игрок!");
            }
            System.out.println("__________________________________");
        }
    }

    public String getNamePlayers(Player[] players) {
        String namesPlayers = "";
        for (Player player : players) {
            namesPlayers += player.getName() + ",";
        }
        namesPlayers = namesPlayers.substring(0, namesPlayers.length() - 1);
        return namesPlayers;
    }

    public boolean isFinalRound() {
        return isFinalRound;
    }

    public void setFinalRound(boolean finalRound) {
        isFinalRound = finalRound;
    }
}
