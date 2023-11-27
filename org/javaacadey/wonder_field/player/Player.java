package org.javaacadey.wonder_field.player;

import org.javaacadey.wonder_field.Game;

import java.util.Scanner;

public class Player {
    private String name;
    private String city;

    private Scanner scanner = Game.getScanner();


    public Player(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 2. Игрок умеет кричать букву: Происходит ввод с консоли буквы,
     * после чего вывод на экран "Игрок (имя игрока): буква (буква из консоли)".
     * Должна быть только одна буква из русского алфавита.
     * В ином случае, вывод в консоль: Ошибка! это не русская буква, введите русскую букву и снова ввод новой буквы,
     * до тех пор пока не введет русскую букву. Действие должно возвращать букву.
     */
    public String playerSayLetter(){
        while (true){
            String letter = scanner.nextLine();
            if (letter.length() == 1 &&
                    Character.UnicodeBlock.of(letter.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC)) {
                System.out.println("Игрок ("+ name + "): " + "буква (" + letter + ")");
                return letter;
            }else {
                continue;
            }
        }
    }
    /**
     * 3. Игрок умеет говорить слово: "Игрок (имя игрока): слово (слово из консоли)"
     */
    public String playerSayWord(){
        String word = scanner.nextLine();
        System.out.println("Игрок ("+ name + "): " + "слово ("+word+")");
        return word;
    }

    public class PlayerAnswer{
        private String typeAnswer;
        private String answer;

        public PlayerAnswer(String typeAnswer, String answer) {
            this.typeAnswer = typeAnswer;
            this.answer = answer;
        }

        public PlayerAnswer setStep(){
            System.out.println("Ход игрока " + name + ", " + city);
            while (true){
                System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");
                typeAnswer = scanner.nextLine();
                if (typeAnswer.equals('б')) {
                    answer = playerSayLetter();
                } else if (typeAnswer.equals('с')) {
                    answer = playerSayWord();
                }else {
                    System.out.println("Некорректное значение, введите 'б' или 'с'");
                    continue;
                }
                return new PlayerAnswer(typeAnswer,answer);
            }
        }
    }
}
