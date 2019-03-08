package ru.batura.game.space.params;

public final class ActionConst {
    // типы результатов за выбор действия
    public static final String ACTION_RESULT_TYPE_GO = "go";

    public static final String ACTION_RESULT_TYPE_HP = "hp";
    public static final String RESULT_HP_SMALL = "small";
    public static final String RESULT_HP_NORM = "normal";
    public static final String RESULT_HP_HIGH = "high";

    public static  enum RESULT_HP{
        small,medium,high
    }

    public static final String ACTION_RESULT_TYPE_SANITY = "sanity";
    public static final String RESULT_SANITY_SMALL = "small";
    public static final String RESULT_SANITY_NORM = "normal";
    public static final String RESULT_SANITY_HIGH = "high";

    public static final String ACTION_RESULT_TYPE_ITEM = "item";
    public static final String ACTION_RESULT_TYPE_ENEMY = "enemy";
    public static final String ACTION_RESULT_TYPE_STORY = "story";
    public static final String ACTION_RESULT_TYPE_LIGHT = "light";
    public static final String ACTION_RESULT_TYPE_MAP   = "map";


    // типы условий на срабатывания того или иного действия
    public static final String ACTION_CONDITION_TYPE_COMMON = "common";

    public static final String ACTION_CONDITION_TYPE_HP = "hp";

    public static final String ACTION_CONDITION_TYPE_SANITY = "sanity";

    public static final String ACTION_CONDITION_TYPE_CLASS = "class";

    public static final String ACTION_CONDITION_TYPE_ITEM = "item";

    public static final String ACTION_CONDITION_TYPE_LIGHT = "light";
}
