package org.javaacadey.wonder_field.player;

import org.javaacadey.wonder_field.Game;

public class Player {
    private String name;
    private String city;
    private final static String LETTER_RUS_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

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

    public String playerSayLetter() {
        while (true) {
            System.out.print("Скажите букву: ");
            String letter = Game.scanner.nextLine();
            if (letter.length() == 1 && LETTER_RUS_ALPHABET.contains(letter)) {
                System.out.println("Игрок (" + name + "): " + "буква (" + letter + ")");
                return letter.toUpperCase();
            }
        }
    }

    public String playerSayWord() {
        System.out.print("Скажите слово: ");
        String word = Game.scanner.nextLine();
        System.out.println("Игрок (" + name + "): " + "слово (" + word + ")");
        return word.toUpperCase();
    }
    public PlayerAnswer setStep() {
        System.out.println("Ход игрока " + name + ", " + city );
        while (true) {
            System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");
            String typeAnswer = Game.scanner.nextLine();
            String answer = null;
            if (typeAnswer.equals("б")) {
                answer = playerSayLetter();
            } else if (typeAnswer.equals("с")) {
                answer = playerSayWord();
            }else {
                System.out.println("Некорректное значение, введите 'б' или 'с'");
                continue;
            }
            return new PlayerAnswer(typeAnswer, answer);
        }
    }
}
