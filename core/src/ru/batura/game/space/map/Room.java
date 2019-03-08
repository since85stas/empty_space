package ru.batura.game.space.map;

import com.badlogic.gdx.math.Vector2;

public class Room {
    public String name;
    public String description;
    public Vector2 position;
    public String roomType;
    public String textureName;

    public int roomCondition;
    public boolean pathLeft;
    public boolean pathRight;
    public boolean pathDown;
    public boolean pathUp;

    // переменные еняющиеся во время игры
    public boolean isHeroHere;
    public boolean isMonsterHere;

    public Room (Vector2 position, String name, String description) {
        this.position = position;
        this.name = name;
        this.description = description;
    }
}
