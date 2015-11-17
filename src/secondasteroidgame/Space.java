/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.JOptionPane;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Space extends Environment {

    Image ship;
    Image background;
    Image fullAsteroid;
    Image lazer;
    Velocity velocity;
    Velocity asteroidVelocity;
    String name;
    Velocity lazerVelocity;
    int angle = 0;
    int shipX = 400;
    int shipY = 300;
    int rotationSpeed = 5;
    int shipSpeed = 5;
    int lazerSpeed = 15;
    int lazerX = 400;
    int lazerY = 300;
    int asteroidX = 100;
    int asteroidY = 100;
    
    
    
    private double getAngleInRadians(){
        return Math.toRadians(angle);
    }

    public Space() {
        this.setBackground(background);
        lazerVelocity = new Velocity(0,0);
        velocity = new Velocity(0, 0);
        asteroidVelocity = new Velocity(3,4);
        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        ship = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name +" Ship.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
    }
    
    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        this.lazerY -= lazerVelocity.y;
        this.lazerX -= lazerVelocity.x;
        this.shipX -= velocity.x;
        this.shipY -= velocity.y;
        this.asteroidX -= asteroidVelocity.x;
        this.asteroidY -= asteroidVelocity.y;
        asteroidX++;
        asteroidX++;
        
        if (shipX < -50) {
            shipX = 900;
        } else if (shipX > 900) {
            shipX = -50;
        }
        
        if (shipY < -100) {
            shipY = 550;
        } else if (shipY > 550) {
            shipY = -100;
        }
        if (lazerX < -50) {
            lazerX = 900;
        } else if (lazerX > 900) {
            lazerX = -50;
        }
        
        if (lazerY < -50) {
            lazerY = 600;
        } else if (lazerY > 600) {
            lazerY = -50;
        }
        if (asteroidX < -100) {
            asteroidX = 600;
        } else if (asteroidX > 600) {
            asteroidX = -100;
        }
        
        if (asteroidY < -75) {
            asteroidY = 1000;
        } else if (asteroidY > 1000) {
            asteroidY = -75;
        }
        
        
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            angle = (++angle % 360);
            angle = ((angle + rotationSpeed) % 360);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            angle = ((angle - rotationSpeed) % 360);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.lazerVelocity = TrigonometryCalculator.getVelocity(Math.toRadians((angle + 90) % 360), shipSpeed);
            this.velocity = TrigonometryCalculator.getVelocity(Math.toRadians((angle + 90) % 360), shipSpeed);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            this.lazerVelocity = TrigonometryCalculator.getVelocity(Math.toRadians((angle + 90) % 360), lazerSpeed);
            


        }
        System.out.println("Angle " + angle);
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
         Graphics2D g2d = (Graphics2D) graphics;
        AffineTransform olde = g2d.getTransform();
        
        if (background != null) {
            graphics.drawImage(background, WIDTH, HEIGHT, this);
        }
        if (fullAsteroid != null) {
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle));
            at.setToRotation(getAngleInRadians(), lazerX , lazerY);

            graphics.drawImage(fullAsteroid, asteroidY, asteroidX, this);
        }
        if (lazer != null) {
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle));
            at.setToRotation(getAngleInRadians(), lazerX , lazerY);
            g2d.setTransform(at);
            g2d.drawImage(lazer, lazerX, lazerY, this);
        }
        if (ship != null) {
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle));
            at.setToRotation(getAngleInRadians(), shipX + (ship.getWidth(this)/ 2), shipY + (ship.getHeight(this) / 2));
            g2d.setTransform(at);
            g2d.drawImage(ship, shipX, shipY, this);
        }
        
        g2d.setTransform(olde);
        g2d.dispose();
    }

    
    }