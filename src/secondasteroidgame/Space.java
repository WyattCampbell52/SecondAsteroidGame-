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
    private ArrayList<Laser> lasers;

//    Laser lazer;
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
//        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        shipChoice = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name + " Ship.png");
        lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
        ship = new Ship(shipChoice, 400, 300, new Velocity(0, 0), 0, 0);
        this.setBackground(background);
        //<editor-fold defaultstate="collapsed" desc="Asteroids">
        asteriods = new ArrayList<>();
        asteriods.add(new Asteroid(fullAsteroid, 100, -10, TrigonometryCalculator.getVelocity(Math.toRadians(90), 7), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 325, TrigonometryCalculator.getVelocity(Math.toRadians(180), 1), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 125, TrigonometryCalculator.getVelocity(Math.toRadians(134), 5), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -10, 305, TrigonometryCalculator.getVelocity(Math.toRadians(247), 8), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -60, 325, TrigonometryCalculator.getVelocity(Math.toRadians(180), 1), 0, 0));
        asteriods.add(new Asteroid(fullAsteroid, -100, 325, TrigonometryCalculator.getVelocity(Math.toRadians(180), 1), 0, 0));
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Laser">
        lasers = new ArrayList<>();
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//        lazers.add(new Laser(lazerImage, ship.getX(), ship.getY(), new Velocity(ship.getX(), ship.getY()), ship.getAngularVelocity(), ship.getAngle()));
//</editor-fold>

        hub = new Hub(score, 380, 30);
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        level++;

        if (ship != null) {
            ship.move();
            ship.boundries();
        }

        if (lasers != null) {
            for (Laser laser : lasers) {
                laser.move();
            }
        }

        if (asteriods != null) {
            for (Asteroid asteroid : asteriods) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }

        cleanLaser();
    }

    private void cleanLaser() {
        if (lasers != null) {
            ArrayList<Laser> toRemove = new ArrayList<>();
            
            for (Laser laser : lasers) {
                if (!laser.isAlive()) {
                    toRemove.add(laser);
                }
            }

            lasers.removeAll(toRemove);
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
            ship.decelarate(1);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
//            lazer.accelerate(2);
            lasers.add(new Laser(lazerImage, ship.getX(), ship.getY(),
                    TrigonometryCalculator.getVelocity(Math.toRadians(ship.getAngle() + 90), 7), 0, ship.getAngle()));
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

        if (hub != null) {
            hub.draw(graphics);
        }

        if (asteriods != null) {
            for (Asteroid asteroid : asteriods) {
                asteroid.draw(graphics);
            }
        }

        if (lasers != null) {
            for (Laser lazer : lasers) {
                if (lazer.isAlive()) {
                    lazer.draw(graphics);
                }
            }
        }

        if (ship != null) {
            ship.draw(graphics);
        }

//        if (lazer != null) {
//            lazer.draw(graphics);
//        }
    }
}
