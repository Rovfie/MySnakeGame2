package com.snake.game;

import java.util.ArrayList;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

class SnakeBlock{
	int dir, olddir;
	int x, y;
	int xq, yq, oldxq, oldyq;
	Rectangle clipRect;
	float vel = Snake.vel; 
	int index;
	int xatturn, yatturn;
	
	public SnakeBlock(int dir, int x, int y, int index) {
		this.dir = dir;
		this.olddir = dir;
		this.x = x;
		this.y = y;
		//this.xq = x - x%MySnakeGame.squarewidth; //xq;
		//this.yq = y - y%MySnakeGame.squareheight; //yq;
		
		xq = Math.round(x/MySnakeGame.squarewidth) * MySnakeGame.squarewidth;
		yq = Math.round(y/MySnakeGame.squareheight) * MySnakeGame.squareheight;
		
		oldxq = xq;
		oldyq = yq;
		this.index = index;
		
		clipRect = new Rectangle(x, y, MySnakeGame.squarewidth, MySnakeGame.squareheight);
	}
	
	public void update(float dt, int index){
		if(dir == Input.Keys.UP){
			y += vel/dt;
		}
		if(dir == Input.Keys.DOWN){
			y -= vel/dt;
		}
		if(dir == Input.Keys.LEFT){
			x -= vel/dt;
		}
		if(dir == Input.Keys.RIGHT){
			x += vel/dt;
		}
		updateClipRect();
		//olddir = dir;
		oldxq = xq;
		oldyq = yq;
		//xq = x - x%MySnakeGame.squarewidth;
		//yq = y - y%MySnakeGame.squarewidth;
		
		xq = Math.round(x/MySnakeGame.squarewidth) * MySnakeGame.squarewidth;
		yq = Math.round(y/MySnakeGame.squareheight) * MySnakeGame.squareheight;
		
		if(oldxq!=xq||oldyq!=yq){
			olddir=dir;
		}
		if(index!=0){
			//System.out.println("niet nul");
			if( (oldxq!=xq /*&& Math.abs(xq-Snake.blocks.get(index-1).xq)==MySnakeGame.squarewidth*/) || (oldyq!=yq /*&& Math.abs(yq-Snake.blocks.get(index-1).yq)==MySnakeGame.squareheight*/) ){
				dir = Snake.blocks.get(index-1).olddir;
				//x=xq;
				//y=yq;
			}
			/*if(xq==Snake.blocks.get(index-1).oldxq || yq==Snake.blocks.get(index-1).oldyq){
				dir = Snake.blocks.get(index-1).olddir;
			}*/
		}
	}
	
	void updateClipRect(){
		clipRect = new Rectangle(x, y, MySnakeGame.squarewidth, MySnakeGame.squareheight);
	}
}

public class Snake implements InputProcessor {

	int x = 0;
	int y = 0;
	int xq = 0, yq = 0;
	static float vel = 0.05f;
	int dir = 22;
	boolean running = true;
	ArrayList<Rectangle> occupiedFields = new ArrayList<Rectangle>();
	int bodyLength = 0;
	int lastPressedKey = -1;
	int xqatpress=0, yqatpress=0;
	public Rectangle rect;
	
	public static ArrayList<SnakeBlock> blocks = new ArrayList<SnakeBlock>();
	
	public Snake(int PosX, int PosY){
		this.x = PosX;
		this.y = PosY;
		//System.out.println(x + " . " + y + " . "+MySnakeGame.squarewidth);
		rect = new Rectangle(x, y, MySnakeGame.squarewidth, MySnakeGame.squareheight);
		addSquare(Input.Keys.UP, x+20, y+20);
		addSquare(Input.Keys.UP, x+20, y+20 - MySnakeGame.squareheight);
		//addSquare(Input.Keys.UP, x+20, y+20);
		//Rectangle rect2 = new Rectangle(x+MySnakeGame.squarewidth, y, MySnakeGame.squarewidth, MySnakeGame.squareheight);
		//System.out.println(rect.x + " . " + rect.height);
		occupiedFields.add(rect);
		//occupiedFields.add(rect2);bodyLength++;
		
	}
	
	
	public void addSquare(int dir, int x, int y){
		SnakeBlock newblock = new SnakeBlock(dir, x, y, bodyLength);
		bodyLength++;
		
		blocks.add(newblock);
		
	}
	
	public void update(float dt, ShapeRenderer renderer) {
		
		/*
		Rectangle newrect = occupiedFields.get(bodyLength-1);
		if(dir == Input.Keys.RIGHT){
			newrect.x += vel/dt;
			//TODO Make space wrap around
			//if(newrect.x > MySnakeGame.canvaswidth){
			//	newrect.x -= MySnakeGame.canvaswidth;
			//}
		}
		if(dir == Input.Keys.DOWN){
			newrect.y -= vel/dt;
		}
		if(dir == Input.Keys.LEFT){
			newrect.x -= vel/dt;
		}
		if(dir == Input.Keys.UP){
			newrect.y += vel/dt;
		}
		occupiedFields.add(newrect);
		occupiedFields.remove(0);
		*/
		//x = (int) occupiedFields.get(bodyLength-1).x;
		//y = (int) occupiedFields.get(bodyLength-1).y;
		
		x = (int) blocks.get(0).x;
		y = (int) blocks.get(0).y;
		
		
		
		//xq = (int) x - x%MySnakeGame.squarewidth;
		//yq = (int) y - y%MySnakeGame.squareheight;
		
		xq = Math.round(x/MySnakeGame.squarewidth) * MySnakeGame.squarewidth;
		yq = Math.round(y/MySnakeGame.squareheight) * MySnakeGame.squareheight;
		
		if(lastPressedKey != -1){
			if(xq!=xqatpress || yq!=yqatpress){
				dir=lastPressedKey;
				//x=xq;
				//y=yq;
				blocks.get(0).olddir = blocks.get(0).dir;
				blocks.get(0).dir = lastPressedKey;
				blocks.get(0).x = xq;
				blocks.get(0).y = yq;
				
				/*for (int i = 0; i < blocks.size(); i++) {
					blocks.get(i).x=blocks.get(i).xq;
					blocks.get(i).y=blocks.get(i).yq;
				}*/
				
				lastPressedKey = -1;
				System.out.println("yooo");
			}
		}
		
		renderer.begin(ShapeType.Filled);
		for(int i = 0; i < bodyLength; i++){
			SnakeBlock b = blocks.get(i);
			//if(i==0) System.out.println("i is nu nul oh ja b.x is dit: "+b.x);
			b.update(dt, i);
			renderer.rect(b.x, b.y, MySnakeGame.squarewidth, MySnakeGame.squareheight);
		}
		renderer.end();

		
		
	}
	
	//if()
	
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.RIGHT && dir!=Input.Keys.LEFT){
			//dir = keycode; //22
			lastPressedKey = keycode;
			xqatpress=xq;
			yqatpress=yq;
			//System.out.println(xq);
		}
		if(keycode == Input.Keys.DOWN && dir !=Input.Keys.UP){
			//dir = keycode; //20
			lastPressedKey = keycode;
			xqatpress=xq;
			yqatpress=yq;
		}
		if(keycode == Input.Keys.LEFT && dir!=Input.Keys.RIGHT){
			//dir = keycode; //21
			lastPressedKey = keycode;
			xqatpress=xq;
			yqatpress=yq;
		}
		if(keycode == Input.Keys.UP && dir!=Input.Keys.DOWN){
			//dir = keycode; //19
			lastPressedKey = keycode;
			xqatpress=xq;
			yqatpress=yq;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
