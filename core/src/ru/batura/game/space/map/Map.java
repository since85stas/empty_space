package ru.batura.game.space.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.batura.game.space.utils.Assets;
import static  ru.batura.game.space.params.RoomConst.*;

public class Map {

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
                            position.x,
                            position.y,
                            roomWidth/2,
                            roomWidth/2);
                }

                if (room.roomCondition == CONDITION_UNKNOWN) {
                    batch.draw(Assets.instance.lockAssets.texture,
                            position.x,
                            position.y,
                            roomWidth/2,
                            roomWidth/2);
                }

                if (room.roomCondition == CONDITION_NEAR) {
                    batch.draw(Assets.instance.starAssets.texture,
                            position.x,
                            position.y,
                            roomWidth/2,
                            roomWidth/2);
                }
            }
        }
    }

    public void setHeroInRoom(int i, int j) {
        rooms[i][j].isHeroHere = true;
    }

    public void findNearestRooms() {

    }

}
