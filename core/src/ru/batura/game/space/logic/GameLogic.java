package ru.batura.game.space.logic;

import ru.batura.game.space.SpaceGame;
import ru.batura.game.space.screens.GameScreen;

public class GameLogic {
    SpaceGame game;
    GameScreen screen;

    public GameLogic(SpaceGame game,GameScreen screen) {
        this.game = game;
        this.screen = screen;
    }

    public void startGame() {
        screen.map.setHeroInRoom(0,0);
    }

    public void heroMove() {

    }
}
