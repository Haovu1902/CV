package snakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Graphics
extends JPanel
implements ActionListener{
	private Timer t = new Timer(100, this); // tốc độ để làm mới khung hình ( tốc độ rắn)
	public String state;
	
	private Snake s;
	private Food f;
	private Game game;
	
	public Graphics(Game g) {
		t.start();
		state = "START";
		
		game = g;
		s = g.getPlayer();
		f = g.getFood();
		
		//add a keyListner ( thêm trình đọc phím )
		this.addKeyListener(g);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Game.width * Game.dimension + Game.dimension*2, Game.height * Game.dimension + Game.dimension * 3);
		
		if(state == "START") {
			g2d.setColor(Color.white);
			g2d.setFont(new Font("Tahoma", Font.BOLD, 20));
			g2d.drawString("Press Any Key", 240  , 300);
		}
		
		else if(state == "RUNNING") {
			g2d.setColor(Color.red);
			g2d.fillRect(f.getX() * Game.dimension, f.getY() * Game.dimension, Game.dimension, Game.dimension);
			for(Rectangle r : s.getBody()) {
				if(r == s.getBody().get(0)) {
					g2d.setColor(Color.red);
				}
				else {
					g2d.setColor(Color.green);
				}
				g2d.fill(r);
			}
			g2d.setColor(Color.white);
			g2d.setFont(new Font("Tahoma", Font.BOLD, 15));
			g2d.drawString("Your Score: " + (s.getBody().size()-3), 260, 20); 
			
			g2d.setColor(Color.white);
			g2d.fillRect(Game.width/2 * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect((Game.width/2 - 1) * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect((Game.width/2 - 2) * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect((Game.width/2 - 3) * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect((Game.width/2 + 1) * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect((Game.width/2 + 2) * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect((Game.width/2 + 3) * Game.dimension, Game.height/2 * Game.dimension, Game.dimension, Game.dimension);
			
			g2d.fillRect(Game.width/2 * Game.dimension, (Game.height/2 - 1) * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect(Game.width/2 * Game.dimension, (Game.height/2 - 2) * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect(Game.width/2 * Game.dimension, (Game.height/2 - 3) * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect(Game.width/2 * Game.dimension, (Game.height/2 + 1) * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect(Game.width/2 * Game.dimension, (Game.height/2 + 2) * Game.dimension, Game.dimension, Game.dimension);
			g2d.fillRect(Game.width/2 * Game.dimension, (Game.height/2 + 3) * Game.dimension, Game.dimension, Game.dimension);
	
		}
		
		else {
			t.stop();
			g2d.setColor(Color.red);
			g2d.setFont(new Font("Tahoma", Font.BOLD, 30));
			g2d.drawString("Your Score: " + (s.getBody().size()-3), 220, 300);
		}
	}

	private Object getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
	
}
