package org.javaacadey.wonder_field.player;

public class PlayerAnswer {
    private String typeAnswer;
    private String answer;

    public PlayerAnswer(String typeAnswer, String answer) {
        this.typeAnswer = typeAnswer;
        this.answer = answer;
    }

    public String getTypeAnswer() {
        return typeAnswer;
    }

    public String getAnswer() {
        return answer;
    }
}
