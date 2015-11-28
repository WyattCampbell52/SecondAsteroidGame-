/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import environment.Direction;
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
    private ArrayList<Lazer> lazers;

    Lazer lazer;
    Lazer2 lazer2;
    Hub hub;

    Image shipChoice;
    Image lazerImage;
    Image background;
    Image fullAsteroid;
    String name;
    int level;
    String score;

    public Space() {
        this.setBackground(background);
        score = "Score " + level;
        name = "American";
        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        shipChoice = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name + " Ship.png");
        lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
        ship = new Ship(shipChoice, 400, 300, new Velocity(0, 0), 0, 0);
        lazer2 = new Lazer2(ship.getX(), ship.getY()-100, lazerImage);
        this.setBackground(background);
        //<editor-fold defaultstate="collapsed" desc="Asteroids">
        asteriods = new ArrayList<>();
        asteriods.add(new Asteroid(fullAsteroid, 100, -10, new Velocity(0, -3), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 325, new Velocity(-3, 0), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 125, new Velocity(-4, 2), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 305, new Velocity(-2, 4), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -60, 325, new Velocity(-5, 0), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -100, 325, new Velocity(3, 3), 0, 0));
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Lazer">
        lazers = new ArrayList<>();
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
        lazers.add(new Lazer(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//</editor-fold>


        hub = new Hub(score, 380, 30);
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        level++;
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
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            ship.rotate(-ship.getRotationSpeed());
            System.out.println(ship.getAngle());
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            ship.accelerate(2);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ship.decelarate(2);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            lazer2.move();
        }
            
//        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            bob.setDirection(Direction.RIGHT);
//            bob.move();
    }
    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        
        if (hub != null) {
            hub.draw(graphics);
        }
        
        if (asteriods != null) {
            for (Asteroid asteroid : asteriods){
                asteroid.draw(graphics);
            }
        }
        
        if (lazer != null) {
        }
        if (lazer2 != null) {
            lazer2.draw(graphics);
        }

        if (ship != null) {
            ship.draw(graphics);

        }

    }
}
