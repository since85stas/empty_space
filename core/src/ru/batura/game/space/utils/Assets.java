package ru.batura.game.space.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.I18NBundle;
import ru.batura.game.space.params.Constants;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.soap.Text;

/**
 * Created by seeyo on 03.12.2018.
 */

public final class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();

    public final String loadingImages = "loading.pack";

    public AssetManager assetManager;

    private Assets() {
    }

//    public EnemyAssets enemyAssets;
    public BlueBallAssets blueBallAssets;
    public StarAssets starAssets;
    public TileAssets tileAssets;
    public SkinAssets skinAssets;
    public LockAssets lockAssets;
    public SoundsBase soundsBase;
    public LoadAssets loadAssets;
    public I18NBundle  bundle;
    public BackAssets  backAssets;
    public NumberAssets numberAssets;

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load("mini_star.png", Pixmap.class);
        assetManager.load("mini_lock.png", Pixmap.class);
        assetManager.load("sphere_blue.png", Texture.class);
        assetManager.load("green_rock.png"   ,Texture.class);
        assetManager.load("brown_rock.png"   ,Texture.class);
        assetManager.load("back1.png",Texture.class);
        assetManager.load("pop3.ogg", Sound.class);
        assetManager.load("ping.wav",Sound.class);
        assetManager.load("explosion.wav",Sound.class);
        assetManager.load("128/numbers-0.png",Texture.class);
        assetManager.load("128/numbers-1.png",Texture.class);
        assetManager.load("128/numbers-2.png",Texture.class);
        assetManager.load("128/numbers-3.png",Texture.class);


        // loading skin with parameters
        java.util.Map<String,BitmapFont> fontsByName = initFonts();

        SkinLoader ldr =  new GeneratedFontSkinLoader( new InternalFileHandleResolver() {
            @Override
            public FileHandle resolve(String fileName) {
                Gdx.app.log(TAG,"AssertManager>>>>>>>>>>>>" + fileName);
                return super.resolve(fileName);
            }
        }, fontsByName);
        assetManager.setLoader( Skin.class, ldr );
        assetManager.load( "skin/craftacular-ui.atlas", TextureAtlas.class );
        assetManager.load( "skin/craftacular-ui.json", Skin.class );
        assetManager.finishLoading();
    }

    public void getMainRes() {
        Pixmap starTexture    = assetManager.get  ("mini_star.png");
        Pixmap lockTexture    = assetManager.get("mini_lock.png");
        Texture blueBallTexture = assetManager.get  ("sphere_blue.png");
        Texture greenTileTexture = assetManager.get("green_rock.png");
        Texture brownTileTexture = assetManager.get("brown_rock.png");
        Texture backT            = assetManager.get("back1.png");
        Sound bubbleSound = assetManager.get("pop3.ogg");
        Sound tookSound   = assetManager.get("ping.wav");
//        Sound tookSound = null;
        Sound explosionSound = assetManager.get("explosion.wav");
        Skin mySkin = assetManager.get("skin/craftacular-ui.json");
//        enemyAssets = new EnemyAssets(walkTexture);
        starAssets       = new StarAssets(starTexture);
        blueBallAssets   = new BlueBallAssets(blueBallTexture);
        tileAssets       = new TileAssets(greenTileTexture,brownTileTexture);
        skinAssets       = new SkinAssets(mySkin);
        lockAssets       = new LockAssets(lockTexture);
        soundsBase       = new SoundsBase(bubbleSound,tookSound,explosionSound);
        backAssets       = new BackAssets(backT);

        numberAssets     = new NumberAssets();
        numberAssets.addTexture(assetManager.get("128/numbers-0.png",Texture.class));
        numberAssets.addTexture(assetManager.get("128/numbers-1.png",Texture.class));
        numberAssets.addTexture(assetManager.get("128/numbers-2.png",Texture.class));
        numberAssets.addTexture(assetManager.get("128/numbers-3.png",Texture.class));
        Gdx.app.log(TAG,"finfish load AssertManager>>>>>>>>>>>>" );
    }

    // a small set of images used by the loading screen
    public void initLoadingImages( AssetManager manager){
//        manager.load(loadingImages, TextureAtlas.class);
        manager.load("mini_star.png",Texture.class);
        manager.load("loading_gif/loading.png",Texture.class);

//        manager.load(animation);
        manager.finishLoading();

        Texture text = manager.get("loading_gif/loading.png");
        loadAssets = new LoadAssets(text);
    }

    public class LoadAssets {
        private static final int FRAME_COLS = 7; // #1
        private static final int FRAME_ROWS = 6; // #2
        private static final int FRAME_ROWS_USED = 6;
        public static final float  WALK_LOOP_DURATION = 0.05f;

        public final Animation<TextureRegion> loadAnimation;
        public final Texture fullLoad;
        TextureRegion[] walkFrames; // #5
        SpriteBatch spriteBatch; // #6
        TextureRegion currentFrame; //

        float stateTime;

        public LoadAssets (Texture texture) {

            fullLoad = texture;
            TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS,
                    texture.getHeight()/FRAME_ROWS); // #10
            walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS_USED];
            int index = 0;
            for (int i = 0; i < FRAME_ROWS_USED; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                    walkFrames[index++] = tmp[i][j];
                }
            }
            loadAnimation = new Animation<TextureRegion>(WALK_LOOP_DURATION,walkFrames);
            Gdx.app.log(TAG,"animation load");

        }
    }


    protected java.util.Map<String,BitmapFont> initFonts() {

        Gdx.app.log( "INIT", "Loading fonts..." );

        FileHandle fontFile = Gdx.files.internal("zorque.ttf");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);
        java.util.Map<String,BitmapFont> fontsByName = new HashMap<String,BitmapFont>();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();

        param.borderColor = Color.BLACK;
        param.borderWidth = 0.5f;
        param.shadowOffsetX = 1;
        param.shadowOffsetY = -1;
        param.shadowColor = Color.BLACK;
        float ppi = Gdx.graphics.getPpiY();
        param.size = (int)(Gdx.graphics.getHeight() *Constants.FONT_SMALL);
        fontsByName.put( "small-font", generator.generateFont( param ));

        param.borderColor = Color.BLACK;
        param.borderWidth = 2;
        param.shadowOffsetX = 3;
        param.shadowOffsetY = -3;
        param.shadowColor = Color.BLACK;
        param.size = (int)(Gdx.graphics.getHeight() *Constants.FONT_NORMAL);
        fontsByName.put( "main-font", generator.generateFont( param ));



//        param.size = (int)(Gdx.graphics.getHeight() *Constants.HUD_FONT_INBALLS);
//        fontsByName.put( "inball-font", generator.generateFont( param ));

        generator.dispose();
        return fontsByName;
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }


    public class TileAssets {
        private static final int FRAME_COLS = 3; // #1
        private static final int FRAME_ROWS = 1; // #2

        public Texture texture1;
        public Texture texture2;

        public TileAssets (Texture texture1, Texture texture2 ) {

            this.texture1 = texture1;
            this.texture2 = texture2;
            Gdx.app.log(TAG,"animation load");
        }

        public Texture getTexture(int i) {
            switch (i) {
                case 0:
                    return texture1;

                case 1:
                    return texture2;

            }
            return null;
        }
    }


    public class StarAssets {

        public Texture texture;
        public Texture achieveTexture;
        public Texture menuTexture;

        public StarAssets(Pixmap pixmap) {
//            this.texture = texture;
            Pixmap pixmap_achieve = new Pixmap((int)(Constants.ACHIEVE_HEIGHT*Gdx.graphics.getWidth()),
                    (int)(Constants.ACHIEVE_HEIGHT*Gdx.graphics.getHeight()),
                    pixmap.getFormat());
            pixmap_achieve.drawPixmap(pixmap,
                    0, 0, pixmap.getWidth(), pixmap.getHeight(),
                    0, 0, pixmap_achieve.getWidth(), pixmap_achieve.getHeight()
            );
            Texture texture = new Texture(pixmap_achieve);
            pixmap_achieve.dispose();

            achieveTexture = texture;
            texture = new Texture(pixmap);
            this.texture = texture;
//            pixmap.dispose();
            Gdx.app.log(TAG,"animation load");
        }

    }

    public class LockAssets {

        public Texture texture;
        public Texture achieveTexture;

        public LockAssets(Pixmap pixmap) {
            Pixmap pixmap_achieve = new Pixmap((int)(Constants.ACHIEVE_HEIGHT*Gdx.graphics.getWidth()),
                    (int)(Constants.ACHIEVE_HEIGHT*Gdx.graphics.getHeight()),
                    pixmap.getFormat());
            pixmap_achieve.drawPixmap(pixmap,
                    0, 0, pixmap.getWidth(), pixmap.getHeight(),
                    0, 0, pixmap_achieve.getWidth(), pixmap_achieve.getHeight()
            );
            Texture texture = new Texture(pixmap_achieve);
            pixmap_achieve.dispose();

            achieveTexture = texture;
            texture = new Texture(pixmap);

            this.texture = texture;
            Gdx.app.log(TAG,"animation load");
        }

    }

    public class BlueBallAssets {

        public Texture texture;

        public BlueBallAssets(Texture texture) {
            this.texture = texture;
            Gdx.app.log(TAG,"animation load");
        }

    }


    public class BackAssets {

        public Texture texture;

        public BackAssets(Texture texture) {
            this.texture = texture;
            Gdx.app.log(TAG,"animation load");
        }

    }

    public class NumberAssets {

        public Texture texture;

        public List<Texture> numbersTextures;

        public NumberAssets() {
            numbersTextures = new ArrayList<Texture>();
            Gdx.app.log(TAG,"animation load");
        }

        public void addTexture(Texture texture) {
            numbersTextures.add(texture);
        }

        public Texture getNumTexture(int num) {
                switch (num) {
                    case 0:
                        return (numbersTextures.get(0));
                    case 1:
                        return (numbersTextures.get(1));
                    case 2:
                        return (numbersTextures.get(2));
                    case 3:
                        return (numbersTextures.get(3));
                }
            return null;
        }
     }

    public class SkinAssets {

        public Skin skin;

        public SkinAssets(Skin skin) {
//            BitmapFont font = generateHudFont();
            this.skin = skin;

        }

    }

    public class SoundsBase {

        public Sound bubbleSound;
        public Sound tookSound;
        public Sound explSound;

        public SoundsBase (Sound bubbleSound, Sound tookSound,Sound explSound) {

            this.bubbleSound = bubbleSound;
            this.tookSound   = tookSound;
            this.explSound   = explSound;
            Gdx.app.log(TAG,"animation load");
        }

    }




}
