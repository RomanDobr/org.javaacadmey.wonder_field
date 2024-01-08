package org.javaacadey.wonder_field;

public class Tableau {
    private String correctAnswer;
    private String[] lettersOnBoard;

    private static String uncknowWord="";

    private Question question;

    public void initTableau(Question question) {
        correctAnswer = question.getAnswer();
        lettersOnBoard = correctAnswer.split("");
        setUncknowWords(correctAnswer);
    }

    public void display(String s) {
        if (!isInspector(s)) {
            return;
        }
        String[] stringTmp = uncknowWord.split("");
        for (String s1 : stringTmp) {
            if (s1.equals(" ")) {
                System.out.print("_");
            }
            System.out.print(s1);
        }
        System.out.println();
    }

    public void openLetters(int i) {
        if (!isInspector(Integer.toString(i))) {
            return;
        }
        System.out.print(correctAnswer.charAt(i));
    }

    public void openWord() {
        uncknowWord = correctAnswer;
        System.out.println(correctAnswer);
    }

    public boolean isUnknownLetters(String s) {
        if (s.contains(" ")) {
            return true;
        }
        return false;
    }

    public boolean isInspector(String s) {
        if (s.length() == 0) {
            throw new IllegalArgumentException("Отсутствует буква");
        }
        return true;
    }

    public boolean isCheckTableau() {
        for (int i = 0; i < uncknowWord.length(); i++) {
            if (uncknowWord.contains(" ")) {
                return false;
            }
        }
        return true;
    }

    private void setUncknowWords(String coorectAnswer) {
        uncknowWord = "";
        for (int i = 0; i < coorectAnswer.length(); i++) {
            uncknowWord += " ";
        }
    }

    public static void setUncknowWord(int i, String letter) {
        String[] str = null;
        str = uncknowWord.split("");
        str[i] = letter;
        uncknowWord = "";
        for (String s : str) {
            Tableau.uncknowWord += s;
        }
    }

    public static String getUncknowWord() {
        return uncknowWord;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
