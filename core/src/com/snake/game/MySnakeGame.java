package com.snake.game;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MySnakeGame extends ApplicationAdapter {
	
	Snake snake;
	Food food;
	OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	Random random = new Random();
	
	public static int canvaswidth;
	public static int canvasheight;
	public static final int boardsize = 30;
	public static int squarewidth;
	public static int squareheight;
	
	int score;
	
	public MySnakeGame(int w, int h) {
		super();
		canvaswidth = w;
		canvasheight = h;
		squarewidth = canvaswidth/boardsize;
		squareheight = canvasheight/boardsize;
	}
	
	public MySnakeGame(){
		super();
	}
	
	
	@Override
	public void create () {
		snake = new Snake(0,0);
		Gdx.input.setInputProcessor(snake);
		food = new Food();
		camera = new OrthographicCamera(canvaswidth,canvasheight);
		shapeRenderer = new ShapeRenderer();
		Gdx.graphics.setTitle("Rovers moeder");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0,0,0,255);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float deltaTime = Gdx.graphics.getDeltaTime();
		
		
	//set origin to left bottom corner
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		snake.update(deltaTime, shapeRenderer);
		
		shapeRenderer.begin(ShapeType.Filled);
	//set color for food and snake 
		shapeRenderer.setColor(1,1,1,1);
	//draw foods
		shapeRenderer.rect(food.x, food.y, squarewidth, squareheight);
		
		
		//shapeRenderer.rect(snake.x, snake.y, squarewidth, squareheight);
		
		/*for(int i=0;i<snake.bodyLength;i++){
			shapeRenderer.rect(snake.occupiedFields.get(i).x, snake.occupiedFields.get(i).y, snake.occupiedFields.get(i).width, snake.occupiedFields.get(i).height);
		}*/
		
		shapeRenderer.end();
		
		if(snake.rect.overlaps(food.rect)){
			//System.out.println("WAZA");
			score += 10;
			food.newfood();
			//snake.addSquare();
		}
		
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}
}
	

