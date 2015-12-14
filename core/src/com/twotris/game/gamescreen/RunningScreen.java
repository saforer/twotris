package com.twotris.game.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.twotris.game.blockstuffs.Block;


public class RunningScreen extends GameScreen {
	final int Width = 10;
	final int Height = 11;
	int score = 0;
	int[][] board;
	static Texture img;
	Block block;
	float countDown;
	BitmapFont font = new BitmapFont();

	public RunningScreen(GameScreenManager gsm) {
		super(gsm);
	}

	public void init() {
		board = getExampleBoard();
		img = new Texture("Block.png");
	}

	public void update (float dt) {



		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			moveBlockLeft();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			moveBlockRight();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			rotateBlockButton();
		}



		final float count = 1.0f;

		if (block == null) {
			if (countDown <= 0) {
				//Time over! Time to get a new block!
				block = new Block();
				//see if the block collides with the field
				for (int x = 0; x < block.shape.length; x++) {
					for (int y = 0; y < block.shape.length; y++) {
						if (block.shape[x][y] != 0) {
							if (isTileThere(x + block.xTopLeft, y + block.yTopLeft)) gameOver();
						}
					}
				}

				countDown = count;
			} else {
				countDown -= dt;
			}
		} else {
			if (countDown <= 0) {

				boolean tileState = false;
				//Look at each individual block -1 in y to make sure nothing is there
				for (int x = 0; x < block.shape.length; x++) {
					for (int y = 0; y < block.shape.length; y++) {
						if (block.shape[x][y] != 0) {
							if (isTileThere(x + block.xTopLeft, y + block.yTopLeft - 1)) tileState = true;
						}
					}
				}

				//Move the piece down
				if (tileState) {
					//A BLOCK IS THERE STOP MOVING

					for (int x = 0; x < block.shape.length; x++) {
						for (int y = 0; y < block.shape.length; y++) {
							if (block.shape[x][y] != 0) {
								board[x + block.xTopLeft][y + block.yTopLeft] = block.shape[x][y];
							}
						}
					}

					//Check for lines that may have been made
					lineCheck();

					block = null;
					countDown = count;

				} else {
					//No collisions, keep going
					block.yTopLeft--;
				}

				countDown = count;
			} else {
				countDown -= dt;
			}
		}
	}

	void rotateBlockButton() {
		if (block != null) {
			boolean okToRotate = true;
			Block afterRotation = new Block(block);
			rotateBlock(afterRotation);
			for (int x = 0; x < afterRotation.shape.length; x++) {
				for (int y = 0; y < afterRotation.shape.length; y++) {

					if (afterRotation.shape[x][y] != 0) {
						if (isTileThere(x + afterRotation.xTopLeft, y + afterRotation.yTopLeft)) {
							okToRotate = false;
						}
					}

				}
			}

			if (okToRotate) block = rotateBlock(block);
		}
	}

	void moveBlockLeft() {
		if (block != null) {
			boolean okToMove = true;
			for (int x = 0; x < block.shape.length; x++) {
				for (int y = 0; y < block.shape.length; y++) {
					if (block.shape[x][y] != 0 ) {
						if (isTileThere(x + block.xTopLeft -1, y + block.yTopLeft)) {
							okToMove = false;
						}
					}
				}
			}
	
			if (okToMove) {
				block.xTopLeft--;
			}
		}
	}

	void moveBlockRight() {
		if (block != null) {
				boolean okToMove = true;
				for (int x = 0; x < block.shape.length; x++) {
					for (int y = 0; y < block.shape.length; y++) {

						if (block.shape[x][y] != 0 ) {
							if (isTileThere(x + block.xTopLeft + 1, y + block.yTopLeft)) {
								okToMove = false;
							}
						}

					}
				}

				if (okToMove) {
					block.xTopLeft++;
				}

		}
	}

	public void render(SpriteBatch sb) {
		//Draw the board
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				if (board[x][y] != 0) {
					//Draw Tile
					sb.draw(img, x*25 + (Gdx.graphics.getWidth() / 3), y*25);
				}
			}
		}


		if (block != null) {
			//Draw the tile
			for (int x = 0; x < block.shape.length; x++) {
				for (int y = 0; y < block.shape.length; y++) {
					if (block.shape[x][y] != 0) {
						sb.draw(img, 25 * (x + block.xTopLeft) + (Gdx.graphics.getWidth() / 3), 25 * (y + block.yTopLeft));
					}
				}
			}
		}

		font.draw(sb, "Score : " + score, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 100);
	}

	public int[][] getExampleBoard() {
		int[][] output = new int[Width][Height];

		/*for (int x = 0; x < Width; x++) {
			for (int y = 0; y < Height; y++) {
				output[x][y] = 1;
			}
		}*/

		return output;
	}

	boolean isTileThere(int x, int y) {
		if (x < 0 || y < 0) return true;
		if (x > Width - 1 || y > Height - 1) return true;
		return board[x][y] != 0;
	}

	Block rotateBlock(Block b) {
		Block output = b;

		int[][] newShape = new int[4][4];

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				newShape[y][3 - x] = b.shape[x][y];
			}
		}


		output.shape = newShape;

		return output;
	}

	void lineCheck() {
		int workingWithY = 0;
		while (workingWithY < Height) {
			//Find out if any rows ARE complete
			boolean completeLine = true;

			while (completeLine) {
				for (int y = 0; y < Height; y++) {
					boolean tempLine = true;
					for (int x = 0; x < Width; x++) {
						if (board[x][y] == 0) tempLine = false;
					}



					if (tempLine) {
						//y will be the line that said it's full
						//make a temp y2 that will iterate, grab everything and pull it down one row
						//Make y-- if it does grab everything on that line, otherwise don't
						score+=1000;
						for (int x = 0; x < Width; x++) {
							for (int y2 = y; y2 < Height; y2++) {
								if ((y2+1) == Height) {
									board[x][y2] = 0;
								} else {
									board[x][y2] = board[x][y2 + 1];
								}
							}
						}
						y--;
					}
				}

				//Check to see if the lines are complete again
				for (int y = 0; y < Height; y++) {
					//Find out if line is complete by seeing if there's any zero held
					completeLine = true;
					for (int x = 0; x < Width; x++) {
						if (board[x][y] == 0) completeLine = false;
					}
				}
			}


			for (int i = 0; i < Height; i++) {

				if (completeLine) {
					for (int x = 0; x < Width; x++) {
						if (i < Height) {
							board[x][i] = board[x][i+1];
						} else {
							board[x][i] = 0;
						}
					}
				} else {
					workingWithY++;
				}

			}


		}
	}

	void gameOver() {
		GameScreenManager.getInstance().moveToScreen(2);
	}
}