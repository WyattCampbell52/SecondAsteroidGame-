/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author WyattCampbell
 */
public class PowerUp {
    
    public void draw(Graphics graphics){
        graphics.drawImage(getImage(), getX(), getY(), null);
    }
    
    public PowerUp(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;        
        if (type.equals(PowerUp.POWERUP_TYPE_AMERICAN_POISON)) {
            image = poison;
        }
        if (type.equalsIgnoreCase(POWERUP_TYPE_AMERICAN_Health)) {
            image = health;
        }
        if (type.equalsIgnoreCase(POWERUP_TYPE_AMERICAN_Wall)) {
            image = wall;
        }
    }
    
    public Rectangle rectangle() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }
    //<editor-fold defaultstate="collapsed" desc="Properties">
//    Obama
        public static final String POWERUP_TYPE_AMERICAN_POISON = "Poison";
//    Wall duh    
        public static final String POWERUP_TYPE_AMERICAN_Wall = "Wall";
//    Health    
        public static final String POWERUP_TYPE_AMERICAN_Health = "Health";

    
    private int x, y;
    private String type;
    private Image health;
    private Image wall;
    private Image poison;
    private Image image;
    Ship ship;
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    
    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }
//</editor-fold>

    /**
     * @return the health
     */
    public Image getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(Image health) {
        this.health = health;
    }

    /**
     * @return the wall
     */
    public Image getwall() {
        return wall;
    }

    /**
     * @param wall the wall to set
     */
    public void setwall(Image wall) {
        this.wall = wall;
    }

    /**
     * @return the Poison
     */
    public Image getpoison() {
        return poison;
    }

    /**
     * @param poison the Poison to set
     */
    public void setpoison(Image poison) {
        this.poison = poison;
    }
}
