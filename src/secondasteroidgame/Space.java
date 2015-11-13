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

/**
 *
 * @author WyattCampbell
 */
class Space extends Environment {

    Image ship;
    int angle = 0;
    int shipX = 400;
    int shipY = 300;
    int rotationSpeed = 5;
    Velocity velocity;
    String name;
    
    private double getAngleInRadians(){
        return Math.toRadians(angle);
    }

    public Space() {
        
        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        ship = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name +" Ship.png");
    }
    
    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            angle = (++angle % 360);
            angle = ((angle + rotationSpeed) % 360);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            angle = ((angle - rotationSpeed) % 360);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            shipY = (shipY - 5);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            shipY = (shipY + 5);
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