package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

public class Jump extends ApplicationAdapter {
	private Music music;
	private Texture PlayerTexture, platformTexture;
	private JumpPlayer player;
	private Array<Platform> platformArray;
	private Camera camera;
	public  Sprite backgroundSprite;
	
	private float gravity= -20;
	SpriteBatch batch;
	
	@Override
	public void create () {
		loadData();
		init();
		
		
	}

	private void init() {
		batch = new SpriteBatch();
		music.play();
		camera = new OrthographicCamera(480,600);
		
		player = new  JumpPlayer(PlayerTexture);
		platformArray = new Array<Platform>();
		
		for(int i=1; i<50;i++)
		{
			Platform p= new Platform(platformTexture);
			p.x=MathUtils.random(480);
			p.y=200*i;
			platformArray.add(p);
		}
		
	}

	private void loadData() {
		PlayerTexture = new Texture("player.jpg");
		platformTexture = new Texture("platform.jpg");
	////	 backGroundTexture = new Texture("back.jpg");
	    //    backgroundSprite =new Sprite(backGroundTexture);
		music=Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
		
		
	}

	@Override
	public void render () {
		update();
	    batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		for(Platform p : platformArray)
		{
			p.draw(batch);
		}
		
		player.draw(batch);
		batch.end();
	}

	

	private void update() {
		HandleInput();
		
		camera.update();
		camera.position.set(player.x+player.width/2,player.y+200,0);
		
		player.y+=player.jumpVeloCity*Gdx.graphics.getDeltaTime();
		if(player.y>0)
		{
			player.jumpVeloCity+=gravity;
			
		}else
		{
			player.y=0;
			player.CanJump=true;
			player.jumpVeloCity=0;
		}
		for(Platform p : platformArray)
		{
			if(isPlayerOnPlatform(p))
			{
				player.CanJump=true;
				player.jumpVeloCity=0;
				player.y=p.y+p.height;
			}
		}
	}

	private boolean isPlayerOnPlatform(Platform p) {
		return player.jumpVeloCity<=0&&player.overlaps(p)&&!(player.y<=p.y);
		
	}

	private void HandleInput() {
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			player.x-=500*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			player.x+=500*Gdx.graphics.getDeltaTime();
		}
		if(Gdx.input.justTouched())
		{
			player.jump();
		}
		
	}
}
