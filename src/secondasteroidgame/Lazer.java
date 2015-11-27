/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import environment.Actor;
import environment.Direction;
import environment.Velocity;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
public class Lazer {

    public Lazer(Image image, int x, int y, Velocity velocity, int angularVelocity, int angle) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.angle = angle;       
    }

    public void draw() {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle));
        at.setToRotation(getAngleInRadians(), x + (image.getWidth(null) / 2), y + (image.getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(image, x, y, null);
        }
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    KeyEvent e;
    Graphics graphics;
    Lazer lazer;
    Ship ship;
    private int x;
    private int y;
    private int maxX;
    private int minX;
    private int maxY;
    private int minY;
    private Velocity velocity;
    private int angularVelocity;
    private int angle;
    private int speed;
    private Image image;
    private int rotationSpeed = 5;

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
     * @return the velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * @param velocity the velocity to set
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;

        velocity = TrigonometryCalculator.getVelocity(Math.toRadians((angle + 90) % 360), speed);
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

    /**
     * @return the angularVelocity
     */
    public int getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * @param angularVelocity the angularVelocity to set
     */
    public void setAngularVelocity(int angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    /**
     * @return the angle
     */
    public int getAngle() {
        return angle;
    }

    /**
     * @return the angle in radians
     */
    public double getAngleInRadians() {
        return Math.toRadians(angle);
    }

    /**
     * @param angle the angle to set
     */
    public void setAngle(int angle) {
        this.angle = angle;
    }

    void rotate(int rotationSpeed) {
        angle = (angle + rotationSpeed) % 360;
    }

    void accelerate(int velocityChange) {
        setSpeed(speed + 10);
    }

    void move() {
        x -= velocity.x;
        y -= velocity.y;

    }

    void boundries() {
        if (x > 900) {
            x = -50;
        } else if (x < -50) {
            x = 900;
        }
        if (y > 550) {
            y = -100;
        } else if (y < -100) {
            y = 550;
        }

    }

    /**
     * @return the maxX
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(int maxX) {
        maxX = 900;
        this.maxX = maxX;
    }

    /**
     * @return the minX
     */
    public int getMinX() {
        return minX;
    }

    /**
     * @param minX the minX to set
     */
    public void setMinX(int minX) {
        minX = -50;
        this.minX = minX;
    }

    /**
     * @return the maxY
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(int maxY) {
        maxY = 550;
        this.maxY = maxY;
    }

    /**
     * @return the minY
     */
    public int getMinY() {
        return minY;
    }

    /**
     * @param minY the minY to set
     */
    public void setMinY(int minY) {
        minY = -100;
        this.minY = minY;
    }

    /**
     * @return the rotationSpeed
     */
    public int getRotationSpeed() {
        return rotationSpeed;
    }

    /**
     * @param rotationSpeed the rotationSpeed to set
     */
    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    //</editor-fold>

}
