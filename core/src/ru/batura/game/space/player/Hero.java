package ru.batura.game.space.player;

import ru.batura.game.space.map.Room;
import static ru.batura.game.space.params.HeroConst.*;

public class Hero  {

    // параметры игрокв
    public int health;
    public int sanity;
    public int heroClass;
    public float sanityProt ;

    public Hero () {
        getInitParams();
    }

    private void  getInitParams() {
        health = 100;
        sanity = 100;
        heroClass = TYPE_DEFAULT;
        sanityProt = 1;
    }

    public void enterRoom(Room room) {
        int san = calcEnterSanityLoose(room.darkness);
        looseSanity(san);
    }

    private int calcEnterSanityLoose(int dark) {
        int san = (int) (sanityProt*(dark - D_MULT) /DIVIDER);
        return san;
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
