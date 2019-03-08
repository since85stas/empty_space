package ru.batura.game.space.player;

import ru.batura.game.space.params.HeroConst;

public class Hero  {

    // параметры игрокв
    public int health;
    public int sanity;
    public int heroClass;

    public Hero () {

    }

    private void  getInitParams() {
        health = 100;
        sanity = 100;
        heroClass = HeroConst.TYPE_DEFAULT;
    }

    public void looseHP(int value) {
        health -= value;
    }

    public void getHP(int value) {
        health += value;
    }

    public void looseSanity(int value) {
        sanity -= value;
    }

    public void getSanity (int value) {
        sanity += value;
    }

}
