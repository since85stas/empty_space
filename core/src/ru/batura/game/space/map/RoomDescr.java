package ru.batura.game.space.map;

public class RoomDescr {
    public String name;
    public String descr;
    public String enterText;
    public int darkness;
    public Action[] action;

    public RoomDescr () {
    }

    public RoomDescr (String name, String descr, String enterText) {
        this.name = name;
        this.descr = descr;
        this.enterText = enterText;
    }

    public static class Action {
        public String descr;   // текст условия
        public String actionType[]; // маркер для типа условия
        public String result[];     // результат выбора, если не важен то пробел " "
        public String conditionType[]; // маркер для типа условия
        public int condition[];     // критерий для срабатывания условия, если нет то 0
        public float chance; // вероятность срабатывания условия от 0 до 1

        public Action(){
        }

        public Action(String descr) {
            this.descr = descr;
        }
    }
}
