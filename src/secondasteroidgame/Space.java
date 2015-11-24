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
    XAsteroid xAsteroid;

    Image oldeShip;
    Image background;
    Image fullAsteroid;
    Image lazer;
    String name;

    public Space() {
        this.setBackground(background);
        name = "American";
        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        oldeShip = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name + " Ship.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
        ship = new Ship(oldeShip, 400, 300, new Velocity(0, 0), 0, 0);
        xAsteroid = new XAsteroid(fullAsteroid, 100, -10, new Velocity(0, -1), 0, 0);
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {

        if (ship != null) {
            ship.move();
            ship.boundries();
        }
        if (xAsteroid != null) {
            xAsteroid.move();
            xAsteroid.boundries();
        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.rotate(ship.getRotationSpeed());
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.rotate(-ship.getRotationSpeed());
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.accelerate(1);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.decelarate(1);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {

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
        
        if (xAsteroid != null) {
            xAsteroid.draw(graphics);
        }
        
        if (ship != null) {
            ship.draw(graphics);
            
        }
        
    }
}
