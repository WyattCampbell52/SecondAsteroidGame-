/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import environment.Velocity;
import images.Animator;
import images.ImageManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
public class Ship {

    public Ship(Image image, int x, int y, Velocity velocity, int angularVelocity, int angle, String name) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.angle = angle;

        animator = new Animator(new AnimatedImageManager(), AnimatedImageManager.SHIP_MOVE_IMAGE_NAMES, 100);
    }

    public void draw(Graphics graphics) {

        Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle));
        at.setToRotation(getAngleInRadians(), x + (image.getWidth(null) / 2), y + (image.getHeight(null) / 2));
        g2d.setTransform(at);
        g2d.drawImage(getImage(), x, y, null);
        graphics.drawRect(x, y, image.getWidth(null), image.getHeight(null));

        g2d.setTransform(olde);
        g2d.dispose();
    }

    private Animator animator;

//<editor-fold defaultstate="collapsed" desc="Properties">
    private String object;
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
    private int rotationSpeed = 15;
    private int health;

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
//        if (animator != null) {
//            return animator.getCurrentImage();
//        } else {
//            return image;
//        }
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
        setSpeed(speed + velocityChange);
    }

    void decelarate(int velocityChange) {
        setSpeed(speed - velocityChange);
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

    /**
     * @return the object
     */
    public String getObject() {
        return object;
    }

    public Rectangle rectangle() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    /**
     * @param object the object to set
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param health the health to set
     */
    public void addHealth(int health) {
        this.health += health;
    }

    /**
     * @return true if the health is greater than 0, false otherwise
     */
    public boolean isAlive() {
        return (health > 0);
    }
    //</editor-fold>

}
