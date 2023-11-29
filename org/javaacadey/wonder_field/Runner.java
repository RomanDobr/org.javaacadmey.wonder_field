package org.javaacadey.wonder_field;

public class Runner {

    public static void main(String[] args) {
        Game game = new Game(new Yakubovich());
        game.init();
        game.startGame();

    }
}
