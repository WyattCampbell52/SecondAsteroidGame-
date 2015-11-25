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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Space extends Environment {

    Ship ship;
    private ArrayList<Asteroid> asteriods;

    Lazer lazer;
    Hub hub;

    Image shipChoice;
    Image lazerImage;
    Image background;
    Image fullAsteroid;
    String name;
    private String score;

    public Space() {
        this.setBackground(background);
        score = "0000";
        name = "American";
        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        shipChoice = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name + " Ship.png");
        lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
        ship = new Ship(shipChoice, 400, 300, new Velocity(0, 0), 0, 0);

        asteriods = new ArrayList<>();
        asteriods.add(new Asteroid(fullAsteroid, 100, -10, new Velocity(0, -3), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 325, new Velocity(-3, 0), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 125, new Velocity(-4, 2), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 305, new Velocity(-2, 4), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -60, 325, new Velocity(-5, 0), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -100, 325, new Velocity(3, 3), 0, 0));

        hub = new Hub(score, 400, 30);
        lazer = new Lazer(lazerImage, ship.getX(), ship.getY(), ship.getVelocity(), ship.getAngularVelocity(), ship.getAngle());
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        if (lazer != null) {
            lazer.move();
            lazer.boundries();
        }

        if (ship != null) {
            ship.move();
            ship.boundries();
        }

        if (asteriods != null) {
            for (Asteroid asteroid : asteriods) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }

        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            ship.rotate(ship.getRotationSpeed());
            lazer.rotate(lazer.getRotationSpeed());
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.rotate(-ship.getRotationSpeed());
            lazer.rotate(-lazer.getRotationSpeed());
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.accelerate(1);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.decelarate(1);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            lazer = new Lazer(lazerImage, ship.getX(), ship.getY(), ship.getVelocity(), ship.getAngularVelocity(), ship.getAngle());
            lazer.accelerate(lazer.getSpeed());
            System.out.println(lazer.getSpeed());
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
        if (hub != null) {
            hub.draw(graphics);
        }
        
        if (asteriods != null) {
            for (Asteroid asteroid : asteriods){
                asteroid.draw(graphics);
            }
        }
        

//        if (xAsteroid != null) {
//            xAsteroid.draw(graphics);
//        }
//        if (yAsteroid != null) {
//            yAsteroid.draw(graphics);
//        }
        if (lazer != null) {
            lazer.draw(graphics);
        }

        if (ship != null) {
            ship.draw(graphics);

        }

    }
}
