package com.snake.game;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;

public class Food {

	Random random = new Random();
	int x, y;
	int randomx, randomy;
	Rectangle rect;
	
	public Food() {
		newfood();
	}
	
	public void newfood() {
		randomx = random.nextInt(501);
		randomy = random.nextInt(501);
		
		x = randomx - randomx%MySnakeGame.squarewidth;
		y = randomy - randomy%MySnakeGame.squareheight;
		
		rect = new Rectangle(x, y, MySnakeGame.squarewidth, MySnakeGame.squareheight);
	}
	
}
