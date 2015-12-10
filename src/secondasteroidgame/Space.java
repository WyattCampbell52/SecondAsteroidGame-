/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import audio.AudioPlayer;
import environment.Environment;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Space extends Environment {

    Ship ship;

    //<editor-fold defaultstate="collapsed" desc="ArrayLists">
    private ArrayList<Asteroid> fullAsteroids;
    private ArrayList<Asteroid> leftHalfAsteroids;
    private ArrayList<Asteroid> rightHalfAsteroids;
    private ArrayList<Asteroid> leftQuaterTopAsteroids;
    private ArrayList<Asteroid> leftQuaterBottomAsteroids;
    private ArrayList<Asteroid> rightQuaterTopAsteroids;
    private ArrayList<Asteroid> rightQuaterBottomAsteroids;
    private ArrayList<Laser> lasers;

    private ArrayList<Laser> getLasersCopy() {
        return new ArrayList<>(lasers);
    }

//</editor-fold>
    Hub hub;
    int points;

    String object;
    String rightHalfObject;
    String leftHalfObject;
    String rightQuaterTopObject;
    String leftQuaterTopOject;
    String leftQuaterBottomObject;
    String rightQuaterBottomObject;
    String name;
    String score;

    Image shipChoice;
    Image lazerImage;
    Image background;
    Image fullAsteroid;
    Image leftHalfAsteroid;
    Image rightHalfAsteroid;
    Image leftQuaterTopAsteroid;
    Image leftQuaterBottomAsteroid;
    Image rightQuaterTopAsteroid;
    Image rightQuaterBottomAsteroid;

    public Space() {
//        name = "American";
        name = JOptionPane.showInputDialog("What Ship? American, Soviet, Trump, or Campbell");
        System.out.println(name);
        if (name.equals("American")) {
            object = "Asteroid";
            lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
            background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy_1.jpg");
        } else if (name.equals("Trump")) {
            object = "Mexican";
            background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Us_Mexico_Border.jpg");
            lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Eagle.png");
        }
        

        fullAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/Full_" + object + ".png");
        leftHalfAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + object + "_Left_Half.png");
        rightHalfAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + object + "_Right_Half.png");
        leftQuaterTopAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + object + "_Left_First_Half.png");
        leftQuaterBottomAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + object + "_Left_Second_Half.png");
        rightQuaterTopAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + object + "_Right_First_Half.png");
        rightQuaterBottomAsteroid = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + object + "_Right_Second_Half.png");
        shipChoice = ResourceTools.loadImageFromResource("SecondAsteroidGame/" + name + "_Ship.png");
        ship = new Ship(shipChoice, 400, 300, new Velocity(0, 0), 0, 0, name);
        this.setBackground(background);

        //        <editor-fold defaultstate="collapsed" desc="Asteroids American">
        fullAsteroids = new ArrayList<>();
        fullAsteroids.add(new Asteroid(fullAsteroid, 100, 200, TrigonometryCalculator.getVelocity(Math.toRadians(45), 7), 0, 0));

        leftHalfAsteroids = new ArrayList<>();

        rightHalfAsteroids = new ArrayList<>();

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
        score = "Score " + points;

        if (ship != null) {
            ship.move();
            ship.boundries();
        }

        if (lasers != null) {
            for (Laser laser : lasers) {
                laser.move();
                laser.boundries();
            }
        }

        //<editor-fold defaultstate="collapsed" desc="Asteroid">
        if (fullAsteroids != null) {
            for (Asteroid asteroid : fullAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }

        if (leftHalfAsteroids != null) {
            for (Asteroid asteroid : leftHalfAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (rightHalfAsteroids != null) {
            for (Asteroid asteroid : rightHalfAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (leftQuaterTopAsteroids != null) {
            for (Asteroid asteroid : leftQuaterTopAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (leftQuaterBottomAsteroids != null) {
            for (Asteroid asteroid : leftQuaterBottomAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (rightQuaterTopAsteroids != null) {
            for (Asteroid asteroid : rightQuaterTopAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
        if (rightQuaterBottomAsteroids != null) {
            for (Asteroid asteroid : rightQuaterBottomAsteroids) {
                asteroid.move();
                asteroid.boundries();
                asteroid.rotate();
            }
        }
//</editor-fold>

        cleanLaser();
        contact();
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
    private void contact() {

        if (fullAsteroids != null) {
            ArrayList<Laser> toLaserRemoves = new ArrayList<>();
            ArrayList<Asteroid> toAsteroidRemoves = new ArrayList<>();

            for (Laser laser : lasers) {
                for (Asteroid asteroid : fullAsteroids) {
//                    if (laser.getX() == asteroid.getX()) {
                    if (laser.getY() == asteroid.getY() - 5) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");                        
                        toAsteroidRemoves.add(asteroid);
                        toLaserRemoves.add(laser);
                        System.out.println("Dead");
                        leftHalfAsteroids.add(new Asteroid(leftHalfAsteroid, asteroid.getX() + 1, asteroid.getY(), new Velocity(-asteroid.getVelocity().x, -asteroid.getVelocity().y), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        rightHalfAsteroids.add(new Asteroid(rightHalfAsteroid, asteroid.getX() - 5, asteroid.getY(), new Velocity(+asteroid.getVelocity().x, +asteroid.getVelocity().y), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        points = (points + 10);
                    }
                }
                for (Asteroid asteroid : leftHalfAsteroids) {
                    if (laser.getY() == asteroid.getY()) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");                        
                        toLaserRemoves.add(laser);
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead 2");
                        leftQuaterTopAsteroids.add(new Asteroid(leftQuaterTopAsteroid, asteroid.getX() - 5, asteroid.getY(), new Velocity(asteroid.getVelocity().x +5, asteroid.getVelocity().y-5), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        leftQuaterBottomAsteroids.add(new Asteroid(leftQuaterBottomAsteroid, asteroid.getX() + 5, asteroid.getY() - 5, new Velocity(asteroid.getVelocity().x + 5, asteroid.getVelocity().y +5), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        points = (points + 15);
                    }
                }
                for (Asteroid asteroid : rightHalfAsteroids) {
                    if (laser.getY() == asteroid.getY()) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");                        
                        toLaserRemoves.add(laser);
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead 2");
                        rightQuaterTopAsteroids.add(new Asteroid(rightQuaterTopAsteroid, asteroid.getX() + 5, asteroid.getY() + 5, new Velocity(asteroid.getVelocity().x + 5, -asteroid.getVelocity().y), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        rightQuaterBottomAsteroids.add(new Asteroid(rightQuaterBottomAsteroid, asteroid.getX() - 5, asteroid.getY(), new Velocity(-asteroid.getVelocity().x, -asteroid.getVelocity().y + 5), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        points = (points + 15);
                    }
                }
                for (Asteroid asteroid : rightQuaterTopAsteroids) {
                    if (laser.getY() == asteroid.getY()) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");                        
                        toLaserRemoves.add(laser);
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead 3");
                        points = (points + 20);
                    }
                }
                for (Asteroid asteroid : rightQuaterBottomAsteroids) {
                    if (laser.getY() == asteroid.getY()) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");                       
                        toLaserRemoves.add(laser);
                        toAsteroidRemoves.add(asteroid);
                        fullAsteroids.add(new Asteroid(fullAsteroid, 0, 0, asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        System.out.println("Dead 3");
                        points = (points + 20);
                    }
                }
                for (Asteroid asteroid : leftQuaterTopAsteroids) {
                    if (laser.getY() == asteroid.getY()) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");
                        toLaserRemoves.add(laser);
                        toAsteroidRemoves.add(asteroid);
                        System.out.println("Dead 3");
                        points = (points + 20);
                    }
                }
                for (Asteroid asteroid : leftQuaterBottomAsteroids) {
                    if (laser.getY() == asteroid.getY()) {
                        AudioPlayer.play("/secondasteroidgame/Bomb");
                        toLaserRemoves.add(laser);
                        toAsteroidRemoves.add(asteroid);
                        fullAsteroids.add(new Asteroid(fullAsteroid, 0, 0, asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                        System.out.println("Dead 3");
                        points = (points + 20);
                    }
                }
            }
            
            lasers.removeAll(toLaserRemoves);
            fullAsteroids.removeAll(toAsteroidRemoves);
            leftHalfAsteroids.removeAll(toAsteroidRemoves);
            rightHalfAsteroids.removeAll(toAsteroidRemoves);
            leftQuaterTopAsteroids.removeAll(toAsteroidRemoves);
            leftQuaterBottomAsteroids.removeAll(toAsteroidRemoves);
            rightQuaterTopAsteroids.removeAll(toAsteroidRemoves);
            rightQuaterBottomAsteroids.removeAll(toAsteroidRemoves);
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
            if (name.equals("American")) {
            AudioPlayer.play("/secondasteroidgame/shooting");
            }else if (name.equals("Trump")) {
            AudioPlayer.play("/secondasteroidgame/trump");
            }
            lasers.add(new Laser(lazerImage, ship.getX(), ship.getY(), TrigonometryCalculator.getVelocity(Math.toRadians(ship.getAngle() + 90), ship.getSpeed() + 7), 0, ship.getAngle() + 90));
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        if (hub != null) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Calibri", Font.BOLD, 36));
            graphics.drawString(score, 380, 30);
        }

        //<editor-fold defaultstate="collapsed" desc="Asteroid">
        if (fullAsteroids != null) {
            for (Asteroid asteroid : fullAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (leftHalfAsteroids != null) {
            for (Asteroid asteroid : leftHalfAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (rightHalfAsteroids != null) {
            for (Asteroid asteroid : rightHalfAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (leftQuaterTopAsteroids != null) {
            for (Asteroid asteroid : leftQuaterTopAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (leftQuaterBottomAsteroids != null) {
            for (Asteroid asteroid : leftQuaterBottomAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (rightQuaterTopAsteroids != null) {
            for (Asteroid asteroid : rightQuaterTopAsteroids) {
                asteroid.draw(graphics);
            }
        }
        if (rightQuaterBottomAsteroids != null) {
            for (Asteroid asteroid : rightQuaterBottomAsteroids) {
                asteroid.draw(graphics);
            }
        }
//</editor-fold>

        if (lasers != null) {
            for (Laser lazer : getLasersCopy()) {
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
