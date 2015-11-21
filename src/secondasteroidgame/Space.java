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

    Ship ship;
    
    Image oldeShip;
    Image background;
    Image fullAsteroid;
    Image lazer;
    Velocity asteroidVelocity;
    String name;
    Velocity lazerVelocity;
    int angle = 0;
    int shipX = 400;
    int shipY = 300;
    int rotationSpeed = 5;
    int shipSpeed = 0;
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
        asteroidVelocity = new Velocity(3,4);
        name = "American";
        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        oldeShip = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name +" Ship.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
        
        //new ship stuff
        ship = new Ship(oldeShip, 400, 300, new Velocity(0, 0), 0, 0);
    }
    
    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        this.lazerY -= lazerVelocity.y;
        this.lazerX -= lazerVelocity.x;
        
        
        if (ship != null) {
            ship.move();
            ship.boundries();
        }
        
        
        this.asteroidX -= asteroidVelocity.x;
        this.asteroidY -= asteroidVelocity.y;
        asteroidX++;
        asteroidX++;
        
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
            ship.rotate(rotationSpeed);
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.rotate(-rotationSpeed);
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.accelerate(1);
            System.out.println(ship.getSpeed());            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.decelarate(1);
            System.out.println(ship.getSpeed());    
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            


        }
        
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (background != null) {
            graphics.drawImage(background, WIDTH, HEIGHT, this);
        }

        if (ship != null) {
            ship.draw(graphics);
        }

//        Graphics2D g2d = (Graphics2D) graphics;
//        AffineTransform olde = g2d.getTransform();
//        
//        if (fullAsteroid != null) {
//            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle));
//            at.setToRotation(getAngleInRadians(), lazerX , lazerY);
//
//            graphics.drawImage(fullAsteroid, asteroidY, asteroidX, this);
//        }
//        
//        g2d.setTransform(olde);
//        g2d.dispose();
        
        
    }

    
    }