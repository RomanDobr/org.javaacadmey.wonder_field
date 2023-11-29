package org.javaacadey.wonder_field;

public class Tableau {
    private String correctAnswer;
    private String[] lettersOnBoard;

    public void initTableau(Question question) {
        correctAnswer = question.getAnswer();
        lettersOnBoard = correctAnswer.split("");
    }

    //
    //3. Табло умеет отображать в консоли все буквы в формате: " _  _ А _ _ К " .
    //"_" - для неотгаданных букв, отгаданные буквы в верхнем регистре
    //
    public void display(String s) {

        if (!isInspector(s)) {
            return;
        }
        int letterCount = 0;
        for (int i = 0; i < lettersOnBoard.length; i++) {
            if (s.equals(lettersOnBoard[i])) {
                letterCount = i;
            }
        }
        for (int index = 0; index < lettersOnBoard.length; index++) {
            if (index != letterCount) {
                System.out.print("_");
            } else if (index == letterCount) {
                openLetters(letterCount);
            }
        }
        System.out.println();
    }

    //4. Табло умеет открывает букву(ы).
    public void openLetters(int i) {
        if (!isInspector(Integer.toString(i))) {
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
        if (s.length() == 0) {
            throw new IllegalArgumentException("Отсутствует буква");
        }
        return true;
    }

    //шаг 5.4Создать метод проверки табло: если табло полностью заполнено, тогда возвращать истину.
    // По сути это означает, что игрок победил.
    public boolean isCheckTableau() {
        for (int i = 0; i < lettersOnBoard.length; i++) {
            if (lettersOnBoard[i].contains("_")) {
                return false;
            }
        }
        return true;
    }

}
