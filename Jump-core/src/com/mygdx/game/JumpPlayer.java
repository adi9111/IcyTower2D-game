package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class JumpPlayer extends Rectangle {
	
	private Sound jumpSound;
	private Texture texture;
	public boolean CanJump=true;
	public float jumpVeloCity;
	
	
	
	public JumpPlayer(Texture texture)
	{
		this.texture=texture;
		this.height=height;
		this.width=width;
		jumpSound=Gdx.audio.newSound(Gdx.files.internal("jump.ogg"));
		
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture,x,y);
	}
	
	public void jump()
	{
		if(CanJump&&jumpVeloCity>=-100)
		{
			jumpVeloCity+=800;
			CanJump=false;
			jumpSound.play();
		}
	}
	

}
