package org.javaacadey.wonder_field;

import org.javaacadey.wonder_field.player.Player;
import org.javaacadey.wonder_field.player.PlayerAnswer;

import java.util.ArrayList;
import java.util.Arrays;

public class Yakubovich {

    //2. Должен уметь начинать шоу: Якубович: Здравствуйте, уважаемые дамы и господа!
    // Пятница! В эфире капитал-шоу «Поле чудес»!

    public void startShow(){
        System.out.println("Здравствуйте, уважаемые дамы и господа!");
        System.out.println("Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    //3. Прощаться: Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!
    public void endShow(){
        System.out.println("Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    //4. Приглашать тройку игроков (придет список игроков и номер раунда).
    //Если это не финальный раунд: "Якубович: приглашаю (номер тройки) тройку игроков: (имена участников через запятую)".
    // Имена придут в виде массива строк. Написать метод соединения строк в одну строку с запятыми.
    //Если это финальный раунд: "Якубович: приглашаю победителей групповых этапов: (имена участников) через запятую".

    public void setShow(ArrayList<String>listPlayers, int roundNumber){
        if (roundNumber != Game.INDEX_FINAL_ROUND){
            System.out.println("приглашаю (" + roundNumber + ") тройку игроков: ("
                    + Arrays.toString(listPlayers.toArray())+")");
        } else if (roundNumber == Game.INDEX_FINAL_ROUND) {
            System.out.println("приглашаю победителей групповых этапов: ("
                    + Arrays.toString(listPlayers.toArray())+")");
        }
    }
    //5. Задавать вопрос раунда: "Якубович: Внимание вопрос! (текст вопроса с новой строки)"
    public void getQuestion(String s){
        System.out.println("Внимание вопрос!");
        System.out.println(s);
    }

    //6. Кричать, в случае победы игрока (придет имя, город, и признак "финальный раунд или нет"):
    //Если не финальный раунд: "Якубович: Молодец! (имя) из (город) проходит в финал!"
    //Если финальный раунд: "Якубович: И перед нами победитель Капитал шоу поле чудес! Это (имя) из (город)"
    public void setWinner(String namePlayer, String cityPlayer, boolean isFinalRound){
        if (!isFinalRound){
            System.out.println("Молодец! (" + namePlayer + ") из (" + cityPlayer +") проходит в финал!");
        }else {
            System.out.println("И перед нами победитель Капитал шоу поле чудес! " +
                    "Это (" + namePlayer +") из (" + cityPlayer +")");
        }
    }

    //7.Проверять ответ игрока (на вход ответ игрока, ответ, табло):
    //Если переданная буква есть в ответе: "Якубович: Есть такая буква, откройте ее!".
    //Если буквы нет: "Якубович: Нет такой буквы! Следующий игрок, крутите барабан!".
    //На следующей строке "__________________________________"
    //
    //Проверять слово:
    //Если переданное слово правильно: "Якубович: (слово)! Абсолютно верно!". Вызывать метод из пункта 6
    //Если переданное слово неверно: "Якубович: Неверно! Следующий игрок!"
    //На следующей строке "__________________________________"
    public void setAnswer(Player player, String answerPlayer, String answer, Tableau tableau){
        if (answerPlayer.length() == 1) {
            if (answer.contains(answerPlayer)) {
                int i = answer.indexOf(answerPlayer);
                System.out.println("Есть такая буква, откройте ее!");
                tableau.openLetters(i);
            } else {
                System.out.println("Нет такой буквы! Следующий игрок, крутите барабан!");
            }
            System.out.println("__________________________________");
        } else if (answerPlayer.length()>1) {
            if (answerPlayer.equals(answer)) {
                System.out.println("(" + answerPlayer + ")! Абсолютно верно!");
                tableau.openWord();
                if (Game.COUNT_ROUND!=Game.INDEX_FINAL_ROUND) {
                    setWinner(player.getName(), player.getCity(), false);
                }else {
                    setWinner(player.getName(), player.getCity(), true);
                }
            } else {
                System.out.println("Неверно! Следующий игрок!");
            }
            System.out.println("__________________________________");
        }

    }

}
