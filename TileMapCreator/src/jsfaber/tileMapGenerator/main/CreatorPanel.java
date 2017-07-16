package jsfaber.tileMapGenerator.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import jsfaber.tileMapGenerator.handlers.KeyHandler;
import jsfaber.tileMapGenerator.handlers.MouseHandler;

@SuppressWarnings("serial")
public class CreatorPanel extends JPanel implements KeyListener, MouseListener{
    
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;
    
    public int superMapX;
    public int superMapY;
    
    private BufferedImage bufferImage;
    private Graphics2D g;
    
    private Map currentMap;
    private MapGenerator mapgen;
    
    public CreatorPanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    
    public void init() {
        addMouseListener(this);
        addKeyListener(this);
        bufferImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g =  (Graphics2D) bufferImage.getGraphics();
        
        currentMap = new Map(32, 18);
        mapgen = new MapGenerator(currentMap);
    }
    
    public void update() {
        mapgen.update();
        KeyHandler.update();
        MouseHandler.update(this.getMousePosition());
    }
    public void draw() {
        mapgen.draw(g);
    }
    
    public void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(bufferImage, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        KeyHandler.keySet(e.getKeyCode(), true);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        KeyHandler.keySet(e.getKeyCode(), false);
    }

    @Override
    public void mousePressed(MouseEvent e) { 
            MouseHandler.mouseSet( e.getButton(), true);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
            MouseHandler.mouseSet( e.getButton(), false);
    }
    
    public void keyTyped(KeyEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
}
