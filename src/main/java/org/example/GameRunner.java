package org.example;

public class GameRunner {
    public static void main(String[] args) {
        // Play some music
        String filepath = "CasinoJazz.wav";
        PlayMusic music = new PlayMusic();
        music.playMusic(filepath);

        // Initializes the game
        Game game = new Game();
        game.play();
    }
}

