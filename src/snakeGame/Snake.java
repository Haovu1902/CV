package snakeGame;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Rectangle> body;
	private int w = Game.width;
	private int h = Game.height;
	private int d = Game.dimension;

	private String move; // UP, DOWN, LEFT, RIGHT

	public Snake() {
		body = new ArrayList<>();

		Rectangle temp = new Rectangle(d, d); // khởi tạo đầu rắn
		temp.setLocation(3*d, h / 2 * d);// cho đầu rắn chạy từ giữa
		body.add(temp); //thêm đầu rắn vào mảng body

		temp = new Rectangle(d, d);
		temp.setLocation(2*d, (h / 2) * d); // thân rắn
		body.add(temp);

		temp = new Rectangle(d, d);
		temp.setLocation(d, (h / 2) * d); // đuôi rắn
		body.add(temp);

		move = "RIGHT";  // rắn chạy từ bên phải
	}

	public void move() {
		Rectangle first = body.get(0);  // lấy ra đầu rắn

		Rectangle temp = new Rectangle(Game.dimension, Game.dimension); // tạo 1 hình chữ nhật mới
//di chuyển của rắn
		if (move == "UP") {
			temp.setLocation(first.x, first.y - Game.dimension);  
		} else if (move == "DOWN") {
			temp.setLocation(first.x, first.y + Game.dimension);
		} else if (move == "LEFT") {
			temp.setLocation(first.x - Game.dimension, first.y);
		} else {
			temp.setLocation(first.x + Game.dimension, first.y);
		}

		body.add(0, temp); // thêm vào hình chữ nhật mới vào đầu mảng
		body.remove(body.size() - 1);  // cắt đi đuôi con rắn
	}
//tăng độ dài của rắn khi ăn thức ăn 
	public void grow() {
		Rectangle first = body.get(0);

		Rectangle temp = new Rectangle(Game.dimension, Game.dimension);

		if (move == "UP") {
			temp.setLocation(first.x, first.y - Game.dimension);
		} else if (move == "DOWN") {
			temp.setLocation(first.x, first.y + Game.dimension);
		} else if (move == "LEFT") {
			temp.setLocation(first.x - Game.dimension, first.y);
		} else {
			temp.setLocation(first.x + Game.dimension, first.y);
		}

		body.add(0, temp);
	}

	public ArrayList<Rectangle> getBody() {
		return body;
	}

	public void setBody(ArrayList<Rectangle> body) {
		this.body = body;
	}

	public int getX() {
		return body.get(0).x;
	}

	public int getY() {
		return body.get(0).y;
	}

	public String getMove() {
		return move;
	}

	public void up() {
		move = "UP";
	}

	public void down() {
		move = "DOWN";
	}

	public void left() {
		move = "LEFT";
	}

	public void right() {
		move = "RIGHT";
	}
}
