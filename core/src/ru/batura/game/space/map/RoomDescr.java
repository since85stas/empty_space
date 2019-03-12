package ru.batura.game.space.map;

public class RoomDescr {
    public String name;
    public String descr;
    public String enterText;
    public Action[] action;


    public RoomDescr () {
    }

    public RoomDescr (String name, String descr, String enterText) {
        this.name = name;
        this.descr = descr;
        this.enterText = enterText;
    }

    public static class Action {
        public String descr;
//        public int actionType;
//        public int result;
//        public int conditionType;
//        public int condition;

        public Action(){
        }

        public Action(String descr) {
            this.descr = descr;
        }
    }
}
