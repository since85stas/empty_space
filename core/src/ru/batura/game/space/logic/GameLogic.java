package ru.batura.game.space.logic;

import ru.batura.game.space.SpaceGame;
import ru.batura.game.space.screens.GameScreen;

/*
    Пока в данной игре по координатам такая логика
    Точка (0,0) - нижний левый угол
    i - вверх
    j - вправо
 */
public class GameLogic {
    SpaceGame game;
    GameScreen screen;

    public GameLogic(SpaceGame game,GameScreen screen) {
        this.game = game;
        this.screen = screen;
    }

    public void startGame() {
        screen.map.setHeroInRoom(0,2);
    }

    public void enterRoom(int i , int j) {
//        screen.map.enterNearRoom();
    }

    public void heroMove() {

    }
}
