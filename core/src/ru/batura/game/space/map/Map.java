package ru.batura.game.space.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import ru.batura.game.space.dialogs.ActonDialog;
import ru.batura.game.space.screens.GameScreen;
import ru.batura.game.space.utils.Assets;
import static  ru.batura.game.space.params.RoomConst.*;
import static  ru.batura.game.space.utils.Assets.*;

public class Map {

    private static final String TAG = Map.class.getName().toString();

    int fieldDimension ;
    int roomWidth;
    Vector2 position;
    int width;
    int height;
    Room currentRoom;

    Room[][] rooms;
    Stage stage;
    GameScreen gameScreen;


    public Map(Vector2 position, int width, int height, int fieldSize, Stage stage, GameScreen gameScreen) {
        fieldDimension = fieldSize;
        this.position  = position;
        this.width     = width;
        this.height    = height;
        this.stage = stage;
        this.gameScreen = gameScreen;
        rooms = new Room[fieldSize][fieldSize];

        generateRooms();
    }

    private void generateRooms() {
        roomWidth = width/fieldDimension;

        for (int i = 0; i < fieldDimension ; i++) {
            for (int j = 0; j < fieldDimension; j++) {

                int x = (int)position.x + j * roomWidth;
                int y = (int)position.y + i * roomWidth;
                Vector2 pos   = new Vector2(x,y);
                Vector2 posIJ = new Vector2(i,j);

                final Room room = new Room(pos,posIJ,"room" + i + j,"descr" + i + j);
                room.setRoomsBounds(roomWidth,roomWidth);
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
                room.darkness      = 50 ; // test
                room.addListener(new ClickListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        String str = event.getTarget().getName();
                        if (room.isNearRoom ) {
                            int i = (int)room.IJpos.x;
                            int j = (int)room.IJpos.y;
                            enterNearRoom(i,j);
                        }
                        Gdx.app.log(TAG,"map clicked " + str);

                        return super.touchDown(event, x, y, pointer, button);
                    }
                });
                stage.addActor(room);
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
                Texture texture = null;
                if (room.roomCondition == CONDITION_KNOWN) {
                    texture = Assets.instance.tileAssets.getTexture(1);
                } else {
                    texture = Assets.instance.tileAssets.getTexture(0);
                }

                batch.draw(texture,
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
        Room room = rooms[i][j];
        room.isHeroHere = true;
        room.roomCondition = CONDITION_CURRENT;
        findNearestRooms();
    }

    public void showActionDialog(Room room) {
        ActonDialog dialog = new ActonDialog("text",instance.skinAssets.skin,room,gameScreen);
//        dialog.show(stage);
    }

    public void enterNearRoom(int i, int j) {
        Room curRoom = getCurrentRoom();
        curRoom.isHeroHere = false;
        curRoom.roomCondition = CONDITION_KNOWN;
        rooms[i][j].isHeroHere = true;
        rooms[i][j].roomCondition = CONDITION_CURRENT;
        gameScreen.hero.enterRoom(rooms[i][j]);
        gameScreen.updateHeroStats();
        findNearestRooms();
    }

    public Room getCurrentRoom() {
        Room room = null;
        for (int i = 0; i < fieldDimension; i++) {
            for (int j = 0; j < fieldDimension; j++) {
                if(rooms[i][j].roomCondition == CONDITION_CURRENT) room = rooms[i][j];
            }
        }
        return room;
    }

    public void findNearestRooms() {
        int curPosI =0;
        int curPosJ =0;
        for (int i = 0; i < fieldDimension; i++) {
            for (int j = 0; j < fieldDimension; j++) {
                Room room = rooms[i][j];
                room.isNearRoom = false;

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
