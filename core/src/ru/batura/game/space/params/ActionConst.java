package ru.batura.game.space.params;

public final class ActionConst {
    // типы результатов за выбор действия
    public static final int ACTION_RESULT_TYPE_HP = -22;
    public static final int RESULT_HP_SMALL = 10;
    public static final int RESULT_HP_NORM = 25;
    public static final int RESULT_HP_HIGH = 40;

    public static final int ACTION_RESULT_TYPE_SANITY = -23;
    public static final int RESULT_SANITY_SMALL = 8;
    public static final int RESULT_SANITY_NORM = 24;
    public static final int RESULT_SANITY_HIGH = 35;

    public static final int ACTION_RESULT_TYPE_ITEM = -24;
    public static final int ACTION_RESULT_TYPE_ENEMY = -25;
    public static final int ACTION_RESULT_TYPE_STORY = -26;


    // типы условий на срабатывания того или иного действия
    public static final int ACTION_CONDITION_TYPE_COMMON = -43;

    public static final int ACTION_CONDITION_TYPE_HP = -44;

    public static final int ACTION_CONDITION_TYPE_SANITY = -45;

    public static final int ACTION_CONDITION_TYPE_CLASS = -46;

    public static final int ACTION_CONDITION_TYPE_ITEM = -47;


}
