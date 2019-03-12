package ru.batura.game.space.map;

import java.util.ArrayList;

public class RoomsFactory {

    private ArrayList<RoomDescr> RoomDescr;

    public RoomDescr createRoom(int id) {

        RoomDescr p = RoomDescr.get(id);

        RoomDescr m = new RoomDescr(p.name, p.descr, p.enterText);
        //...other code to add behaviours

        return m;
    }
}
