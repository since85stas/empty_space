package ru.batura.game.space.params;

public final class RoomConst {
    // типы комнат
    public static final int TYPE_EMPTY = 3;

    // состояния комнат
    public static final int CONDITION_UNKNOWN = 33;
    public static final int CONDITION_KNOWN = 34;
    public static final int CONDITION_VISITED = 35;
    public static final int CONDITION_NEAR = 36;
    public static final int CONDITION_CURRENT = 37;

    // обознач путей
    public static final int PATH_LEFT  = -1;
    public static final int PATH_RIGHT = -2;
    public static final int PATH_DOWN  = -3;
    public static final int PATH_UP    = -4;
}
