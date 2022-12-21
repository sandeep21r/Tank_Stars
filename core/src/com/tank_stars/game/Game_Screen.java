package com.tank_stars.game;

import Scens.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesHandlerImpl;

import javax.crypto.interfaces.PBEKey;

import java.nio.file.attribute.UserPrincipal;

import static com.badlogic.gdx.utils.JsonValue.ValueType.object;

public class Game_Screen implements Screen {

    private Vector3 touchpos = new Vector3();
    final Tank_Stars_Game tank_stars_game;

    private final float PPM = 32;
    private OrthographicCamera camera;
    int z =0;
    private SpriteBatch batch;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private  int Health_one ;
    private  int Health_two ;

    public void setHealth_one(int health_one) {
      this.player1.setHealth(10);

    }

    public void setHealth_two(int health_two) {
       this.player2.setHealth(10);
    }

    private int kill_tank_2;
    private int is_tank_2_is_destroyed;

    private int kil_tank_1;
    private int is_tank_1_is_destroyed;


    private int kill_air_drop;
    private int is_air_drop_remove;

    private  int is_remove_shoot_missile;
    private int kill_shoot_missile;

    private OrthogonalTiledMapRenderer renderer;

    private Sprite pause_button;
    private Sprite background;
    private Sprite health_bar;
    private Sprite health_bar2;
    private Sprite missile;
    private int done = 0;
    private int done2 =0;

    private Sprite fire_button;
    private  Sprite left_button;
    private Sprite right_button;
    private Sprite angle;
    private Sprite fuel;
    private Sprite fuel1;
    private Sprite tanks1;
    private Sprite tanks2;
    private Sprite leftmissile;
    private Sprite rightmissile;
    private Sprite air_drop;
    int i = 0;
    int j =0;
    private BitmapFont font1;
    private FreeTypeFontGenerator fontGenerator;

    int odd ;
    int fuel_1 = 1000;
    int fuel_2 =1000;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter1;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter2;

    private BitmapFont font2;
    int flag_2 = -9;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter3;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter4;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter5;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter6;
    private BitmapFont font3;
    private BitmapFont font4;
    private BitmapFont font5;
    private BitmapFont font6;
//    private Sprite terror;
    private Sprite power;

    private Sprite left_button2;
    private Sprite right_button2;
    private Sprite fuel_icon;
    private Sprite fuel_icon2;
    private Sprite shooting_body;


    private World world;
    int shooting = -5;
    int flag = 1;
    int a =9;
    int flag_3 = -9;
    private World doing_world;
    private Body player;
    int x =-9;
    private Body player_tank;
    private Body player_tank2;
    private  Body airdrop;
    private  Body shoot_flag;

    private Box2DDebugRenderer b2dr;
    private float w;
    private float h;
    private Hud hud;

    private Tank player1;
    private Tank player2;
    String string_fuel1;
    String string_fuel2;
    String string_health1;
    String string_health2;
    String string_power1;
    String string_power2;
    String string_angle_of_fire1;
    String string_angle_of_fire2;


    public Game_Screen(final Tank_Stars_Game tank_stars_game){
        this.tank_stars_game = tank_stars_game;
        this.player1 = tank_stars_game.getTank_1();
        this.player2 = tank_stars_game.getTank_2();
//        this.Health_two = tank_stars_game.getTank_1().getHealth();
//        this.Health_one = tank_stars_game.getTank_1().getHealth();
//        System.out.println(player1.getAngle());
        this.w = (float) Gdx.graphics.getWidth();
        this.odd = 1;
        this.h = (float)Gdx.graphics.getHeight();
        (this.camera = new OrthographicCamera(this.w,this.h)).setToOrtho(false);
        this.batch = new SpriteBatch();
        hud = new Hud(this.batch);
        mapLoader=new TmxMapLoader();
        map=mapLoader.load("terrain_horaaa.tmx");
        renderer=new OrthogonalTiledMapRenderer(map);

        this.kill_tank_2 = 0;
        this.is_tank_2_is_destroyed =0;

        this.kill_tank_2 =0;
        this.is_tank_2_is_destroyed = 0;

        this.kill_air_drop = 0;
        this.is_air_drop_remove = 0;

        this.is_remove_shoot_missile = 0;
        this.kill_shoot_missile =0;


//        this.terror = new Sprite(new Texture("terrain_red.png"));
//        this.terror.setSize(this.w,this.h/2);
        this.background = new Sprite(new Texture("background.jpeg"));
        this.pause_button = new Sprite(new Texture("pause-button.jpg"));
//        this.health_bar = new Sprite(new Texture("health_bar.png"));
//        this.health_bar2 = new Sprite(new Texture("health_bar2.png"));
        this.fire_button =new Sprite(new Texture("fire_button.png"));
        this.left_button = new Sprite(new Texture("left_button.png"));
        this.right_button = new Sprite(new Texture("right_button.png"));
        this.left_button2 = new Sprite(new Texture("left_button.png"));
        this.right_button2 =  new Sprite(new Texture("right_button.png"));
        this.fuel_icon = new Sprite(new Texture("fuel_icon.png"));
        this.fuel_icon2 = new Sprite(new Texture("fuel_icon.png"));
        this.power = new Sprite(new Texture("power.png"));
        this.angle = new Sprite(new Texture("angle.png"));
        this.fuel =  new Sprite(new Texture("fuel.png"));
        this.fuel1 =  new Sprite(new Texture("fuel.png"));
        this.tanks1 = new Sprite(new Texture("tank1.png"));
        this.tanks2 = new Sprite(new Texture("tank2.png"));



        fontGenerator=new FreeTypeFontGenerator(Gdx.files.internal("yankclipper2.ttf"));
        fontParameter1=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter1.size= (int) (this.w/20);
        fontParameter1.color= Color.BLACK;
        fontParameter1.borderColor=Color.WHITE;




        fontParameter1.borderWidth=(int)(this.w/240);
        font1=fontGenerator.generateFont(fontParameter1);
        fontParameter2=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter2.borderColor=Color.WHITE;
        fontParameter2.borderWidth=(int)(this.w/240);
        fontParameter2.size=(int)(this.w/20);
        fontParameter2.color= Color.BLACK;
        font2=fontGenerator.generateFont(fontParameter2);
        //fuel
        fontParameter3=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter3.size= (int) (this.w/40);
        fontParameter3.color= Color.BLACK;
        fontParameter3.borderColor=Color.WHITE;
        fontParameter3.borderWidth=(int)(this.w/480);
        font3=fontGenerator.generateFont(fontParameter3);
// health
        fontParameter4=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter4.borderColor=Color.WHITE;
        fontParameter4.borderWidth=(int)(this.w/240);
        fontParameter4.size=(int)(this.w/20);
        fontParameter4.color= Color.GREEN;
        font4=fontGenerator.generateFont(fontParameter4);
//power
        fontParameter5=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter5.size= (int) (this.w/40);
        fontParameter5.color= Color.BROWN;
        fontParameter5.borderColor=Color.WHITE;
        fontParameter5.borderWidth=(int)(this.w/480);
        font5=fontGenerator.generateFont(fontParameter5);
// angle
        fontParameter6=new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter6.borderColor=Color.WHITE;
        fontParameter6.borderWidth=(int)(this.w/480);
        fontParameter6.size=(int)(this.w/40);
        fontParameter6.color= Color.BLUE;
        font6=fontGenerator.generateFont(fontParameter6);


        this.background.setSize(this.w,this.h);
        this.pause_button.setSize(this.w/20,this.h/20);
        this.pause_button.setPosition((this.w/2),this.h-this.h/20);
        this.fire_button.setSize(this.w/16,this.h/8);
        this.fire_button.setPosition((this.w/10)+this.w/3+4*(this.w/64)-this.w/32,this.h/30);
        this.left_button.setSize(this.w/16,this.h/8);

        this.right_button.setSize(this.w/16,this.h/8);
        this.left_button2.setSize(this.w/16,this.h/8);
        this.right_button2.setSize(this.w/16,this.h/8);

        this.left_button2.setPosition(this.w/2-this.w/4-this.w/8+this.w/16-this.w/32,this.h/30);
        this.right_button2.setPosition( this.w/2+this.w/4+this.w/8-this.w/16-this.w/32,this.h/30);

        this.right_button.setPosition(this.w/2-this.w/4-this.w/32,this.h/30);
        this.left_button.setPosition(this.w/2+this.w/4-this.w/32,this.h/30);
        this.fuel.setSize(this.w/16,this.h/8);
        this.fuel.setPosition(0,this.h/30);
        this.fuel1.setSize(this.w/16,this.h/8);
        this.fuel1.setPosition((this.w/12)*11,this.h/30);
        this.fuel_icon.setSize(this.w/7-this.w/30,this.h/8);
        this.fuel_icon.setPosition(this.w/20,this.h/30);
        this.fuel_icon2.setSize(this.w/7-this.w/30,this.h/8);
        this.fuel_icon2.setPosition(this.w-this.w/6,this.h/30);
        this.angle.setSize(this.w/6+this.w/24,this.h/6);
        this.angle.setPosition(this.w/2-this.w/4+this.w/16-this.w/32,this.h/120);
        this.power.setSize(this.w/6+this.w/24,this.h/6);
        this.power.setPosition(this.w/2-this.w/4+this.w/16+this.w/4-this.w/32,this.h/120);
        doing_world = new World(new Vector2(0,-500f),false);
        b2dr = new Box2DDebugRenderer();
//        player = createPlayer(100,110,this.w,this.h/12+this.h/12+this.h/60+this.h/30,11.0f,true);
        player_tank = createPlayer(this.w/4,this.h/2,30,30,12.0f,false);
        player_tank2 = createPlayer(this.w-this.w/4,this.h/2,30,30,10.0f,false);





this.doing_world.setContactListener(new Contactlistener(this));

//        doing_world = new World(new Vector2(0,-9.8f),false);



        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(0).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type   = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+ rect.getWidth()/2,rect.getY()+rect.getHeight()/2 );
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
       player = TileObjectUtil.parseTiledObjectLayer(doing_world, map.getLayers().get("hit_layer").getObjects());
//    player =  map.getLayers().get("hit_layer").getObjects();



    }

    @Override
    public void show() {

    }

    public Body createPlayer(float position_x,float position_y ,float width,float height,float denstity,boolean is_static){
        Body pBody;
        BodyDef def = new BodyDef();
        if (is_static){
            def.type = BodyDef.BodyType.StaticBody;
        }
        else{
            def.type = BodyDef.BodyType.DynamicBody;
        }
        def.position.set(position_x,position_y);
        def.fixedRotation = true;
        pBody = doing_world.createBody(def);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        pBody.createFixture(shape,denstity).setUserData(pBody);
        shape.dispose();

        return  pBody;
    }
    public void hit(){
        System.out.println("getting hit");
    }

    public void update(float dt){
        if (this.kill_shoot_missile == 1 && this.is_remove_shoot_missile == 0){
            this.doing_world.destroyBody(this.shoot_flag);
            this.is_remove_shoot_missile = 1;
            flag_2 = -9;
            flag_3 = -9;

            System.out.println("1");
        }

        if (kill_tank_2 ==  1 && is_tank_2_is_destroyed == 0){
            this.doing_world.destroyBody(this.shoot_flag);
            this.doing_world.destroyBody(this.player_tank2);
            is_tank_2_is_destroyed = 1;

            System.out.println("2");
        }

        if (kil_tank_1 == 1 && is_tank_1_is_destroyed ==0){
            System.out.println("agya");
            this.doing_world.destroyBody(this.shoot_flag);
            this.doing_world.destroyBody(this.player_tank);
            this.is_tank_1_is_destroyed = 1;
            System.out.println("4");
        }
        if (kill_air_drop == 1 && is_air_drop_remove == 0){
            System.out.println("removing");
            this.doing_world.destroyBody(this.airdrop);
            this.is_air_drop_remove = 1;
            flag = 1;
        }
        if (flag == 1 && is_air_drop_remove == 1){
            kill_air_drop = 0;
            is_air_drop_remove =0;
        }


        this.doing_world.step(1/60f,6,2);

        inputhandle(dt);

//        this.camera.update();
//        this.batch.setProjectionMatrix(camera.combined);

        renderer.setView(this.camera);
    }
 public void to_kill_tank_2(){
        if (this.player2.getHealth() == 0){
            System.out.println("aya ha");
        this.kill_tank_2 = 1;}
        else {  this.odd = 0; this.kill_shoot_missile = 1;}


 }
 public void to_kill_shoot_flag(){
        this.kill_shoot_missile = 1;
     if (x == 9 ){
         odd = 0;
     }
     if (x == 8 ){
         odd = 1;
     }
        this.flag_2 = 9;
 }
 public void to_kill_tank_1(){
        if (this.player1.getHealth()== 0){

        this.kil_tank_1 = 1;}
        else {
        this.kill_shoot_missile = 1;
        this.odd = 1;}

 }


    public void to_remove_air_drop_tank1(){
        System.out.println("kyu ni aya");
            this.kill_air_drop = 1;
    }
    public void to_remove_air_drop_tank2(){
           this.kill_air_drop = 1;

    }
    @Override
    public void render(float delta) {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(1.0f,1.0f,1.0f,1.0f);
        Gdx.gl.glClear(16384);
//        batch.setProjectionMatrix(this.hud.stage.getCamera().combined);

        batch.begin();

        this.background.draw(this.batch);

        this.pause_button.draw(this.batch);
        renderer.render();

        this.fire_button.draw(this.batch);
        this.left_button.draw(this.batch);
        this.right_button.draw(this.batch);
        this.left_button2.draw(this.batch);
        this.right_button2.draw(this.batch);
        this.angle.draw(this.batch);
        this.fuel.draw(this.batch);
        this.fuel1.draw(this.batch);

        if (is_tank_1_is_destroyed == 0){
            this.tanks1.setSize(this.w/16,this.h/14);
            this.tanks1.setPosition(player_tank.getPosition().x-40,player_tank.getPosition().y-this.h/24-this.h/96);
            this.tanks1.draw(this.batch);

        }
        if (is_tank_2_is_destroyed == 0) {
            this.tanks2.setSize(this.w/16,this.h/14);
            this.tanks2.setPosition(player_tank2.getPosition().x-40,player_tank2.getPosition().y-this.h/24-this.h/96);
            this.tanks2.draw(this.batch);
        }

        this.power.draw(this.batch);
        this.fuel_icon.draw(this.batch);
        this.fuel_icon2.draw(this.batch);
        try{
            string_fuel1= String.valueOf(this.player1.getFuel());
            string_fuel2= String.valueOf(this.player2.getFuel());
            string_health1=String.valueOf(this.player1.getHealth());
            string_health2=String.valueOf(this.player2.getHealth());
            string_power1=String.valueOf(this.player1.getShoot_power());
            string_power2=String.valueOf(this.player1.getShoot_power());
            string_angle_of_fire1=String.valueOf(this.player1.getAngle());
            string_angle_of_fire2=String.valueOf(this.player1.getAngle());
        }
        catch(NullPointerException e){
            System.out.println("this is currently null");
        }
        font2.draw(batch,"PLAYER-2",this.w/2+this.w/4+this.w/100+this.w/100+this.w/100+this.w/100+this.w/100+this.w/100,this.h-this.h/20);
        font1.draw(batch,"PLAYER-1",this.w/100,this.h-this.h/20);
//      fuel
        font3.draw(batch,string_fuel1,this.w/20+this.w/50+this.w/100,this.h/10+this.h/100);
        font3.draw(batch,string_fuel2,this.w-this.w/20-this.w/12,this.h/10+this.h/100);
//health
        font4.draw(batch,string_health1,this.w/2-(this.w/8),this.h-this.h/100);
        font4.draw(batch,string_health2,this.w/2+this.w/18,this.h-this.h/100);

        font5.draw(batch,"5",this.w/2-this.w/4+this.w/16+this.w/4+this.w/32+this.w/80,this.h/12+this.h/50+this.h/200);
        font6.draw(batch,"6°",this.w/2-this.w/4+this.w/16+this.w/32+this.w/80,this.h/12+this.h/50+this.h/200 );
        batch.end();
        if ((this.player1.getFuel() == 700 || this.player1.getFuel() == 700) &&flag == 1 && is_air_drop_remove == 0){
            this.is_air_drop_remove =0;
            this.kill_air_drop = 0;
            airdrop = createPlayer((player_tank.getPosition().x+player_tank2.getPosition().x)/2,this.h,40,40,6.0f,false);
            flag = 0;
        }
        if (flag == 0  && is_air_drop_remove == 0 ){
            throw_air_drop();
            this.batch.begin();
            air_drop.draw(this.batch);
            this.batch.end();
        }
        if ( (this.player1.getFuel() == 600 || this.player2.getFuel() == 600) && flag == 1 && is_air_drop_remove == 0){
            System.out.println("comeomg");
//            this.is_air_drop_remove =0;
//            this.kill_air_drop = 0;
            airdrop = createPlayer((player_tank.getPosition().x+player_tank2.getPosition().x)/2,this.h,40,40,6.0f,false);
            flag = 6;
        }
        if (flag == 6  && is_air_drop_remove == 0 ){
            throw_air_drop();
            this.batch.begin();
            air_drop.draw(this.batch);
            this.batch.end();
        }
        if(shooting == 0 ){
            this.is_remove_shoot_missile = 0;
            this.kill_shoot_missile = 0;
            shoot_flag = createPlayer(player_tank.getPosition().x +this.w/24-this.w/128, this.h/2,5,5,5.0f,false);
            shoot_flag.applyLinearImpulse(10000000,1060000,shoot_flag.getPosition().x,shoot_flag.getPosition().y,true);
            flag_2 = 7;
            this.leftmissile = new Sprite(new Texture("moving_right_bomb-removebg-preview.png"));
            this.leftmissile.setSize(30,30);
            shooting = -1;
        }
        if (flag_2  == 7  ){
            this.leftmissile.setPosition(shoot_flag.getPosition().x-this.w/100-this.w/200+this.w/300,shoot_flag.getPosition().y-this.h/100);
            this.batch.begin();
            this.leftmissile.draw(this.batch);
            this.batch.end();
        }

        if (shooting == 1){
            this.is_remove_shoot_missile = 0;
            this.kill_shoot_missile = 0;
            this.rightmissile = new Sprite(new Texture("moving_left_bomb-removebg-preview.png"));
            this.rightmissile.setSize(30,30);
            shoot_flag = createPlayer(player_tank2.getPosition().x -this.w/24+this.w/128, this.h/2,5,5,5.0f,false);
            shoot_flag.applyLinearImpulse(-10000000,1060000,shoot_flag.getPosition().x,shoot_flag.getPosition().y,true);
            flag_3 = 7;


            shooting = -1;
        }
        if (flag_3 == 7){
            this.rightmissile.setPosition(shoot_flag.getPosition().x-this.w/100-this.w/200,shoot_flag.getPosition().y-this.h/100);
            this.batch.begin();
            this.rightmissile.draw(this.batch);
            this.batch.end();



        }
        b2dr.render(this.doing_world,this.camera.combined);
//        this.hud.stage.draw();
//        this.hud.stage.act();
    }

    public void inputhandle(float dt){
        if (Gdx.input.justTouched()){
            this.touchpos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            this.camera.unproject(touchpos);
            if (touchpos.x >= (this.w/2) && touchpos.x <= this.h-this.h/20+this.w/20 && touchpos.y >= this.h-this.h/20 && touchpos.y<=this.h-this.h/20+ this.h/20){
                tank_stars_game.setScreen(new Pause_Screen(tank_stars_game,this));
            }

        }
        player_tank.setLinearVelocity(0,player_tank.getLinearVelocity().y);
        player_tank2.setLinearVelocity(0,player_tank2.getLinearVelocity().y);

    if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            moving_left();
        }
     if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moving_right();
        }
    if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
        shoot();
        }
    }

    public  void moving_left(){
        if (this.odd == 1){
           if (z == 0){

               z =1;
           }
            if (this.player1.getFuel()== 0){
                return;
            }

            player_tank.setLinearVelocity(-7500000,player_tank.getLinearVelocity().y);

            this.player1.setFuel(1);
            fuel_1  -= 1;

        x =9;

        } else if (this.odd == 0) {
            if (this.player2.getFuel()== 0){
                return;
            }
            player_tank2.setLinearVelocity(-7500000,player_tank2.getLinearVelocity().y);
            this.player2.setFuel(1);
            fuel_2 -= 1;
            x = 8;
        }
    }

    public  void moving_right(){
        if (this.odd == 1){
            if (z == 0){
                System.out.println("plyer 1 turn");
                z =1;
            }
            if (player2.getFuel() == 0){
                return;
            }

            player_tank.setLinearVelocity(75000000,player_tank.getLinearVelocity().y);

            this.player1.setFuel(1);
            fuel_1 -= 1;
            x = 9;


        } else if (this.odd == 0 ) {

            if (player2.getFuel() == 0){
                return;
            }
            player_tank2.setLinearVelocity(75000000,player_tank2.getLinearVelocity().y);
            fuel_2 -= 1;
            this.player2.setFuel(1);
            x =8;

        }
    }

    public void shoot(){

        if(odd == 1){
            if (flag_2 == -9){
            this.shooting = 0;
            this.odd = -1;
            }
        }
        else if(odd == 0 ){
            if (flag_3 == -9){
             this.shooting= 1;
             this.odd = -1;
            }
        }
    }
    public  void throw_air_drop(){
        this.air_drop = new Sprite(new Texture("Airdrop_in_game.jpg"));
        this.air_drop.setSize(80,80);
        this.air_drop.setPosition(airdrop.getPosition().x-this.w/24+this.w/96,airdrop.getPosition().y-this.h/18-this.h/80);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();

    }
}
