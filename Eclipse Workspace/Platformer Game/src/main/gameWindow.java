package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

public class gameWindow extends JFrame{
	
	//window variables
	static JFrame frame;
	private BufferedImage icon, cursor, clicked;
	
	//window constructor
	public gameWindow(gamePanel panel) {
		
		//imports game icon from images folder
		icon = importImg("gameIcon.png");
		clicked = importImg("mouseIcon1.png");
		cursor = importImg("mouseIcon2.png");
		
		//creates game frame and default settings
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		frame.pack();
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLocation(-7, 0);
		

        Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "custom_cursor");
		
		frame.setCursor(customCursor);
		
		
		frame.setIconImage(icon);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				panel.getGame().windowFocusLost();
				
			}
			
			
		});
		
		
	}

	private BufferedImage importImg(String name) {
		
		InputStream is = getClass().getResourceAsStream("/"+name);
		
		//load image, close stream
		try {
			return ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				is.close();
			} catch(IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}

	public void changeCursor(String action) {
		Cursor customCursor = null;
		
		if(action == "pressed")
			customCursor = Toolkit.getDefaultToolkit().createCustomCursor(this.cursor, new Point(0, 0), "custom_cursor");
		else if(action == "released")
			customCursor = Toolkit.getDefaultToolkit().createCustomCursor(this.clicked, new Point(0, 0), "custom_cursor");
		else 
			customCursor = Cursor.getDefaultCursor();
		
		frame.setCursor(customCursor);
		
	}
	
}
