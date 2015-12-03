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
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Space extends Environment {

    Ship ship;
    Laser laser;
    
    private ArrayList<FullAsteroid> fullAsteriods;
    private ArrayList<LeftHalfAsteroid> leftHalfAsteroids;
    private ArrayList<RightHalfAsteroid> rightHalfAsteroids;
    private ArrayList<LeftQuaterTopAsteroid> leftQuaterTopAsteroids;
    private ArrayList<LeftQuaterBottomAsteroid> leftQuaterBottomAsteroids;
    private ArrayList<RightQuaterTopAsteroid> rightQuaterTopAsteroids;
    private ArrayList<RightQuaterBottomAsteroid> rightQuaterBottomAsteroids;
    private ArrayList<Laser> lasers;

    Hub hub;

    Image shipChoice;
    Image lazerImage;
    Image background;
    Image fullAsteroid;
    Image leftHalfAsteroid;
    Image rightHalfAsteroid;
    String name;
    int level;
    String score;

    public Space() {
        this.setBackground(background);
        score = "Score " + level;
        name = "American";
//        name = JOptionPane.showInputDialog("What Ship? American or Soviet");
        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full Asteroid.png");
        leftHalfAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Asteroid Left Half.png");
        rightHalfAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Asteroid Right Half.png");        
        shipChoice = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name + " Ship.png");
        lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
        background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy 1.jpg");
        ship = new Ship(shipChoice, 400, 300, new Velocity(0, 0), 0, 0);
        this.setBackground(background);
        //<editor-fold defaultstate="collapsed" desc="FullAsteroids">
        fullAsteriods = new ArrayList<>();
        fullAsteriods.add(new FullAsteroid(fullAsteroid, 100, -10, TrigonometryCalculator.getVelocity(Math.toRadians(90), 7), 0, 0));
        fullAsteriods.add(new FullAsteroid(fullAsteroid, -10, 325, TrigonometryCalculator.getVelocity(Math.toRadians(180), 3), 0, 0));
        fullAsteriods.add(new FullAsteroid(fullAsteroid, -10, 125, TrigonometryCalculator.getVelocity(Math.toRadians(134), 5), 0, 0));
        fullAsteriods.add(new FullAsteroid(fullAsteroid, -10, 305, TrigonometryCalculator.getVelocity(Math.toRadians(247), 8), 0, 0));
        fullAsteriods.add(new FullAsteroid(fullAsteroid, -60, 325, TrigonometryCalculator.getVelocity(Math.toRadians(326), -5), 0, 0));
        fullAsteriods.add(new FullAsteroid(fullAsteroid, -100, 325, TrigonometryCalculator.getVelocity(Math.toRadians(250), 4), 0, 0));
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="HalfAsteroid">
        leftHalfAsteroids = new ArrayList<>();
        
        rightHalfAsteroids = new ArrayList<>();
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="QuaterAsteroid">
        leftQuaterTopAsteroids = new ArrayList<>();
        
        leftQuaterBottomAsteroids = new ArrayList<>();
        
        rightQuaterTopAsteroids = new ArrayList<>();
        
        rightQuaterBottomAsteroids = new ArrayList<>();
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Laser">
        lasers = new ArrayList<>();
//     
//</editor-fold>

        hub = new Hub(score, 380, 30);
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        score = score;
        level++;

        if (ship != null) {
            ship.move();
            ship.boundries();
        }

        if (lasers != null) {
            for (Laser laser : lasers) {
                laser.move();
                laser.boundries();
                        fullContact();

            }
        }

        if (fullAsteriods != null) {
            for (FullAsteroid asteroid : fullAsteriods) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (leftHalfAsteroids != null) {
            for (LeftHalfAsteroid asteroid : leftHalfAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (rightHalfAsteroids != null) {
            for (RightHalfAsteroid asteroid : rightHalfAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        
        
        

        cleanLaser();
        fullContact();
        halfContact();
    }

    //<editor-fold defaultstate="collapsed" desc="Laser Duration">
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
//</editor-fold>
    

    //<editor-fold defaultstate="collapsed" desc="Contact">
    private void fullContact() {
        
        if (lasers != null) {
            ArrayList<Laser> toLaserRemoves = new ArrayList<>();
            ArrayList<FullAsteroid> toAsteroidRemoves = new ArrayList<>();
            
            for (Laser laser : lasers) {
                for (FullAsteroid asteroid : fullAsteriods) {
//                    if (laser.getX() == asteroid.getX()) {
                    if (laser.getY() == asteroid.getY()) {
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead");
                        leftHalfAsteroids.add(new LeftHalfAsteroid(leftHalfAsteroid, asteroid.getX(), asteroid.getY(), asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        rightHalfAsteroids.add(new RightHalfAsteroid(leftHalfAsteroid, asteroid.getX(), asteroid.getY(), asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        score = score + 10;
//                        }
                    }
                }
                fullAsteriods.removeAll(toAsteroidRemoves);
            }
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="HalfContact">
    private void halfContact() {
        
        if (lasers != null) {
            ArrayList<Laser> toLaserRemoves = new ArrayList<>();
            ArrayList<LeftHalfAsteroid> toAsteroidRemoves = new ArrayList<>();
            
            for (Laser laser : lasers) {
                for (LeftHalfAsteroid asteroid : leftHalfAsteroids) {
//                    if (laser.getX() == asteroid.getX()) {
                    if (laser.getY() == asteroid.getY()) {
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead");
                         score = score + 10;
//                        }
                    }
                }
                leftHalfAsteroids.removeAll(toAsteroidRemoves);
            }
        }
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="QuaterContact">
    private void quaterContact() {
        
        if (lasers != null) {
            ArrayList<Laser> toLaserRemoves = new ArrayList<>();
            ArrayList<LeftHalfAsteroid> toAsteroidRemoves = new ArrayList<>();
            
            for (Laser laser : lasers) {
                for (LeftHalfAsteroid asteroid : leftHalfAsteroids) {
//                    if (laser.getX() == asteroid.getX()) {
                    if (laser.getY() == asteroid.getY()) {
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead");
                        leftHalfAsteroids.add(new LeftHalfAsteroid(leftHalfAsteroid, asteroid.getX(), asteroid.getY(), asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        rightHalfAsteroids.add(new RightHalfAsteroid(leftHalfAsteroid, asteroid.getX(), asteroid.getY(), asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        score = score + 10;
//                        }
                    }
                }
                fullAsteriods.removeAll(toAsteroidRemoves);
            }
        }
    }
//</editor-fold>

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
            if (ship.getSpeed() < -2) {
                ship.decelarate(-2);
            }
            ship.decelarate(2);
            System.out.println(ship.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            lasers.add(new Laser(lazerImage, ship.getX(), ship.getY(), TrigonometryCalculator.getVelocity(Math.toRadians(ship.getAngle() + 90), ship.getSpeed() + 7), 0, ship.getAngle()));
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

        if (fullAsteriods != null) {
            for (FullAsteroid asteroid : fullAsteriods) {
                asteroid.draw(graphics);
            }
        }
        //<editor-fold defaultstate="collapsed" desc="Half Asteroids">
        if (leftHalfAsteroids != null) {
            for (LeftHalfAsteroid asteroid : leftHalfAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (rightHalfAsteroids != null) {
            for (RightHalfAsteroid asteroid : rightHalfAsteroids) {
                asteroid.draw(graphics);
            }
//</editor-fold>
            
        //<editor-fold defaultstate="collapsed" desc="Quater Asteroids">
            if (leftQuaterTopAsteroids != null) {
                for (LeftQuaterTopAsteroid asteroid : leftQuaterTopAsteroids) {
                    asteroid.draw(graphics);
                }
            }
            if (rightQuaterTopAsteroids != null) {
                for (RightQuaterTopAsteroid asteroid : rightQuaterTopAsteroids) {
                    asteroid.draw(graphics);
                    
                }
                if (leftQuaterBottomAsteroids != null) {
                    for (LeftQuaterBottomAsteroid asteroid : leftQuaterBottomAsteroids) {
                        asteroid.draw(graphics);
                    }
                }
                if (rightQuaterBottomAsteroids != null) {
                    for (RightQuaterBottomAsteroid asteroid : rightQuaterBottomAsteroids) {
                        asteroid.draw(graphics);
                        
                    }
//</editor-fold>
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
                }
            }
        }
    }
}
