package org.javaacadey.wonder_field.player;

import org.javaacadey.wonder_field.Game;

public class Player {
    private String name;
    private String city;
    private final static String LETTER_RUS_ALPHABET="АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

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
    public String playerSayLetter() {
        while (true) {
            String letter = Game.scanner.nextLine();
            if (letter.length() == 1 && LETTER_RUS_ALPHABET.contains(letter)) {
                System.out.println("Игрок (" + name + "): " + "буква (" + letter + ")");
                return letter;
            }
        }
    }
    /**
     * 3. Игрок умеет говорить слово: "Игрок (имя игрока): слово (слово из консоли)"
     */
    public String playerSayWord() {
        String word = Game.scanner.nextLine();
        System.out.println("Игрок ("+ name + "): " + "слово ("+word+")");
        return word;
    }
    public PlayerAnswer setStep() {
        System.out.println("Ход игрока " + name + ", " + city);
        while (true){
            System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");
            String typeAnswer = Game.scanner.nextLine();
            String answer=null;
            if (typeAnswer.equals('б')) {
                answer = playerSayLetter();
            } else if (typeAnswer.equals('с')) {
                answer = playerSayWord();
            } else {
                System.out.println("Некорректное значение, введите 'б' или 'с'");
                continue;
            }
            return new PlayerAnswer(typeAnswer,answer);
        }
    }
}
