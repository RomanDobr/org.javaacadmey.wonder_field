package org.javaacadey.wonder_field;

import java.util.ArrayList;


public class Tableau {
    private String correctAnswer;
    private ArrayList<String> lettersOnBoard = new ArrayList<>();

    public void initTableau(Question question) {
        correctAnswer = question.getAnswer();
        for (char ch : correctAnswer.toCharArray()) {
            lettersOnBoard.add(Character.toString(ch).toUpperCase());
        }
    }
    //
    //3. Табло умеет отображать в консоли все буквы в формате: " _  _ А _ _ К " .
    //"_" - для неотгаданных букв, отгаданные буквы в верхнем регистре
    //
    public void display(String s) {
        if (!isInspector(s)) {
            return;
        }
        int i = lettersOnBoard.indexOf(s);
        for (int index = 0; index < lettersOnBoard.size(); index++) {
            if (index != i) {
                System.out.print("_");
            } else if (index == i) {
                openLetters(i);
            }
        }
    }

    //4. Табло умеет открывает букву(ы).
    public void openLetters(int i) {
        if (!isInspector(Integer.toString(i))){
            return;
        }
        System.out.print(correctAnswer.charAt(i));
    }

    //5. Табло умеет открывать слово целиком.
    public void openWord() {
        System.out.println(correctAnswer);
    }

    //6. Табло имеет функцию "Содержит неизвестные буквы" - если есть неразгаданные буквы - вернет истину, иначе ложь.
    public boolean isUnknownLetters(String s) {
        if (s.contains("_")) {
            return true;
        }
        return false;
    }

    //7. Создать метод проверку проверяющий что атрибуты не пусты. Использовать этот метод в пунктах 3,4.
    public boolean isInspector(String s) {
        if (s.length()==0) {
            return false;
        }
        for (char ch : s.toCharArray()){
            if (Character.isLetter(ch)){
                return true;
            }
        }
        return false;
    }
}
