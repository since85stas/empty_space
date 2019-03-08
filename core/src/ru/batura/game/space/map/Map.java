package ru.batura.game.space.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.batura.game.space.utils.Assets;
import static  ru.batura.game.space.params.RoomConst.*;

public class Map {

    private static final String TAG = Map.class.getName().toString();

    int fieldDimension ;
    int roomWidth;
    Vector2 position;
    int width;
    int height;

    Room[][] rooms;


    public Map(Vector2 position, int width, int height, int fieldSize) {
        fieldDimension = fieldSize;
        this.position  = position;
        this.width     = width;
        this.height    = height;
        rooms = new Room[fieldSize][fieldSize];

        generateRooms();
    }

    private void generateRooms() {
        roomWidth = width/fieldDimension;

        for (int i = 0; i < fieldDimension ; i++) {
            for (int j = 0; j < fieldDimension; j++) {

                int x = (int)position.x + j * roomWidth;
                int y = (int)position.y + i * roomWidth;
                Vector2 pos = new Vector2(x,y);

                Room room = new Room(pos,"room " + i + j,"descr" + i + j);
                if (i != 0){
                    room.pathLeft = true;
                }
                if (i != fieldDimension -1) {
                    room.pathRight = true;
                }
                if (j != 0) {
                    room.pathDown = true;
                }
                if (j != fieldDimension -1) {
                    room.pathUp = true;
                }

                room.roomCondition = CONDITION_UNKNOWN;
                rooms[i][j] = room;

            }

        }
    }

    public void render(SpriteBatch batch) {

        batch.draw(Assets.instance.backAssets.texture,
                position.x,
                position.y,
                width,
                height
        );

        for (int i = 0; i < fieldDimension ; i++) {
            for (int j = 0; j < fieldDimension; j++) {
                Room room = rooms[i][j];
                batch.draw(Assets.instance.tileAssets.getTexture(0),
                        room.position.x,
                        room.position.y,
                        roomWidth,
                        roomWidth);
                if (room.isHeroHere) {
                    batch.draw(Assets.instance.blueBallAssets.texture,
                            room.position.x,
                            room.position.y,
                            roomWidth/2,
                            roomWidth/2);
                }

                if (room.roomCondition == CONDITION_UNKNOWN) {
                    batch.draw(Assets.instance.lockAssets.texture,
                            room.position.x,
                            room.position.y,
                            roomWidth/2,
                            roomWidth/2);
                }

                if (room.isNearRoom) {
                    batch.draw(Assets.instance.starAssets.texture,
                            room.position.x,
                            room.position.y,
                            roomWidth/2,
                            roomWidth/2);
                }
            }
        }
    }

    public void setHeroInRoom(int i, int j) {
        rooms[i][j].isHeroHere = true;
        rooms[i][j].roomCondition = CONDITION_CURRENT;
        findNearestRooms();
    }

    public void findNearestRooms() {
        int curPosI =0;
        int curPosJ =0;
        for (int i = 0; i < fieldDimension; i++) {
            for (int j = 0; j < fieldDimension; j++) {
                Room room = rooms[i][j];
                if (room.isHeroHere) {
                    curPosI = i;
                    curPosJ = j;
                }
            }
        }

        Room room = rooms[curPosI][curPosJ];
        try {
            if (room.pathLeft) rooms[curPosI-1][curPosJ].isNearRoom = true;
            if (room.pathRight) rooms[curPosI+1][curPosJ].isNearRoom = true;
            if (room.pathDown) rooms[curPosI][curPosJ-1].isNearRoom = true;
            if (room.pathUp) rooms[curPosI][curPosJ+1].isNearRoom = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            Gdx.app.log(TAG,"exc" + e);
        }


    }

}
