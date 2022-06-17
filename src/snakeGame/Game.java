package snakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;

import javax.swing.JFrame;

import snakeGame.utils.ScoreRepository;

public class Game 
implements KeyListener{  // bắt sự kiện
	private Snake player;
	private Food food;
	private Graphics graphics;
	
	private JFrame window;
	
	public static final int width = 30;
	public static final int height = 30;
	public static final int dimension = 20;
	
	public Game() {
		window = new JFrame();
		
		player = new Snake();
		
		food = new Food(player);
		
		graphics = new Graphics(this);
		
		window.add(graphics);
		
		window.setTitle("Snake");
		window.setSize(width * dimension + dimension*2, height * dimension + dimension*3);
		window.setLocationRelativeTo(null); // cho cửa sổ game ra giữa
		window.setVisible(true); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // để ấn x thì thoát cửa sổ
	}
	
	public void start() {
		graphics.state = "RUNNING";
	}
	
	public void update() {
		if(graphics.state == "RUNNING") {
			if(check_food_collision()) {
				player.grow(); // rắn tăng kích thước
				food.random_spawn(player); // random lại thức ăn  
			}
			else if(check_wall_collision() || check_self_collision()) {
				graphics.state = "END";
				
				String score = String.valueOf(player.getBody().size()-3);
				try {
					FileWriter fw = new FileWriter("resources/endgame.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write("Score: " + score);
					bw.newLine();
					bw.close();
					fw.close();		
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// DB
				try {
					ScoreRepository.add(new Score(Integer.valueOf(score)));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				player.move();
			}
		}
	}
	
	private boolean check_wall_collision() {
		if (player.getX() == Game.width/2 * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			||  player.getX() == (Game.width/2 - 1) * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			|| player.getX() == (Game.width/2 - 2) * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			|| player.getX() == (Game.width/2 - 3) * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			|| player.getX() == (Game.width/2 + 1) * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			|| player.getX() == (Game.width/2 + 2) * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			|| player.getX() == (Game.width/2 + 3) * Game.dimension && player.getY() == Game.height/2 * Game.dimension
			|| player.getX() == Game.width/2 * Game.dimension && player.getY() ==(Game.height/2 - 1) * Game.dimension
			|| player.getX() == Game.width/2 * Game.dimension && player.getY() ==(Game.height/2 - 2) * Game.dimension	
			|| player.getX() == Game.width/2 * Game.dimension && player.getY() ==(Game.height/2 - 3) * Game.dimension
			|| player.getX() == Game.width/2 * Game.dimension && player.getY() ==(Game.height/2 + 1) * Game.dimension
			|| player.getX() == Game.width/2 * Game.dimension && player.getY() ==(Game.height/2 + 2) * Game.dimension
			|| player.getX() == Game.width/2 * Game.dimension && player.getY() ==(Game.height/2 + 3) * Game.dimension
				) {
				return true;
			}
		// kiểm tra va chạm tường
		if(player.getX() < 0 || player.getX() > width * dimension 
				|| player.getY() < 0|| player.getY() > height * dimension) {
			return true;
		}
		return false;
	}
	
	private boolean check_food_collision() {  //kiểm tra rắn ăn thức ăn hay chưa
		if(player.getX() == food.getX() * Game.dimension && player.getY() == food.getY() * Game.dimension) {
			return true;
		}
		return false;
	}
	
	private boolean check_self_collision() { // kiểm tra va chạm với chính nó
		for(int i = 1; i < player.getBody().size(); i++) {
			if(player.getX() == player.getBody().get(i).x &&
					player.getY() == player.getBody().get(i).y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(graphics.state == "RUNNING") {
			if(keyCode == KeyEvent.VK_UP && player.getMove() != "DOWN") {
				player.up();
			}
		
			if(keyCode == KeyEvent.VK_DOWN && player.getMove() != "UP") {
				player.down();
			}
		
			if(keyCode == KeyEvent.VK_LEFT && player.getMove() != "RIGHT") {
				player.left();
			}
		
			if(keyCode == KeyEvent.VK_RIGHT && player.getMove() != "LEFT") {
				player.right();
			}
		}
		else {
			this.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	}

	public Snake getPlayer() {
		return player;
	}

	public void setPlayer(Snake player) {
		this.player = player;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}
	
}
