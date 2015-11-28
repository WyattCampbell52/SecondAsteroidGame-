/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author WyattCampbell
 */
public class Lazer2 {

    public Lazer2(int x, int y, Image image) {
        this.image = image;
        this.x = x;
        this.y = y;

        body = new ArrayList<>();
        body.add(new Point(x, y));

    }

    //<editor-fold defaultstate="collapsed" desc="Movement and Boundries">
    public void move() {
//        copy of current head
        Point newLazer = new Point(getLazer());
        if (newLazer.x < 0) {
            newLazer.x = 0;
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
            
            }
        }
//Add head
        body.add(0, newLazer);
//Remove head
        body.remove(body.size() - 1);

    }
            //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Graphics">
    public void draw(Graphics graphics) {
        graphics.setColor(bodyColor);
        for (int i = 0; i < body.size(); i++) {
//            System.out.println("body location = " + body.get(i).toString());
            graphics.drawImage(image, x, y, null);

        }
    }
        //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    Ship ship;
    private Image image;
    private KeyEvent e;
    private ArrayList<Point> body;
    private Color bodyColor = Color.red;
    private int x, y;

    public Point getLazer() {
        return body.get(0);
    }

    /**
     * @return the direction
     */
    public KeyEvent getKeyEvent() {
        return e;
    }

    /**
     * @param direction the direction to set
     */
    public void setKeyEvent(KeyEvent keyevent) {
        this.e = keyevent;
    }
    //</editor-fold>

}
