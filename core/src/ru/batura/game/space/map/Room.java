package ru.batura.game.space.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Room extends Actor {
    public String id;
    public String name;
    public String description;
    public Vector2 position;
    public Vector2 IJpos;
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
    public boolean isNearRoom;

    // параметры влияющие на игрока
    public int     darkness ; // уровень темноты в комнате от (10 - светло, до 99 темень)

    public Room (Vector2 position,Vector2 ij, String id, String description) {
        this.position = position;
        this.IJpos    = ij;
        this.id = id;
        this.description = description;
        setName(id);
    }

    public void setRoomsBounds (int width, int height) {
        setBounds(position.x,position.y,width,height);
    }


}
