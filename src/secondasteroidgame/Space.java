/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import audio.AudioPlayer;
import audio.Playlist;
import audio.SoundManager;
import audio.Source;
import audio.Track;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import path.TrigonometryCalculator;

/**
 *
 * @author WyattCampbell
 */
class Space extends Environment {

    //<editor-fold defaultstate="collapsed" desc="Properties">
    Ship ship;
    Asteroid asteroid;

    //<editor-fold defaultstate="collapsed" desc="ArrayLists">
    private ArrayList<Asteroid> fullAsteroids;
    private ArrayList<Asteroid> leftHalfAsteroids;
    private ArrayList<Asteroid> rightHalfAsteroids;
    private ArrayList<Asteroid> leftQuaterTopAsteroids;
    private ArrayList<Asteroid> leftQuaterBottomAsteroids;
    private ArrayList<Asteroid> rightQuaterTopAsteroids;
    private ArrayList<Asteroid> rightQuaterBottomAsteroids;
    private ArrayList<Laser> lasers;
    private ArrayList<PowerUp> powerUp;

    private ArrayList<Laser> getLasersCopy() {
        return new ArrayList<>(lasers);
    }

//</editor-fold>
    Hub hud;
    Hub hp;

    int limit;
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

    Image poison;
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

    private long startTime;
//</editor-fold>

    public Space() {
        JFrame frame = new JFrame("Input Dialog Example 3");
        String shiptype = (String) JOptionPane.showInputDialog(frame,
                "Game Type",
                "Asteroids",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ships,
                ships[0]);
//        name = "American";
        name = shiptype;
        System.out.println(name);
        if (name.equals("American")) {
            object = "Asteroid";
            lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
            background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy_1.jpg");
        }
        if (name.equals("Soviet")) {
            object = "Soviet";
            lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Bear_head.png");
            background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Galaxy_1.jpg");
//        } else if (name.equals("Trump")) {
//            object = "Mexican";
//            background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Us_Mexico_Border.jpg");
//            lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Eagle.png");
        } else if (name.equals("Trump")) {
            object = "Mexican";
            background = ResourceTools.loadImageFromResource("SecondAsteroidGame/Us_Mexico_Border.jpg");
            lazerImage = ResourceTools.loadImageFromResource("SecondAsteroidGame/Trump_Hair.png");
            poison = ResourceTools.loadImageFromResource("SecondAsteroidGame/Obama.png");
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
        ship.setHealth(1000);

        this.setBackground(background);
        if (ship.isAlive()) {
            //        <editor-fold defaultstate="collapsed" desc="Asteroids">
            fullAsteroids = new ArrayList<>();
            fullAsteroids.add(new Asteroid(fullAsteroid, 100, 200, TrigonometryCalculator.getVelocity(Math.toRadians(45), 7), 0, 0));

            leftHalfAsteroids = new ArrayList<>();
            rightHalfAsteroids = new ArrayList<>();
            leftQuaterTopAsteroids = new ArrayList<>();
            leftQuaterBottomAsteroids = new ArrayList<>();
            rightQuaterTopAsteroids = new ArrayList<>();
            rightQuaterBottomAsteroids = new ArrayList<>();

//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Laser and Power Ups">
            lasers = new ArrayList<>();

            powerUp = new ArrayList<>();
          new PowerUp(500, 500, "Poison");
//</editor-fold>

            hud = new Hub(score, 380, 30);
            hp = new Hub(ship.getHealth() + "", 0, 130);
        }
        setUpSound();
    }
//    Ship Type
    public static final String[] ships = {"American", "Trump", "Soviet", "Campbell"};

    SoundManager soundManager;
    public static final String POWERUP = "PowerUp";
    public static final String AMERICANPIE = "American Pie";

    private void setUpSound() {
//        set up list of trackes in a playlist
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(POWERUP, Source.FILE, "/secondasteroidgame/PowerUp.wav"));
        tracks.add(new Track(AMERICANPIE, Source.FILE, "/secondasteroidgame/American_Pie.wav"));

//        Playlist
        Playlist playlist = new Playlist(tracks);
//        pass the playlist to a sound manager
        soundManager = new SoundManager(playlist);
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
//        powerUps();
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
            if (lasers != null) {

                for (Laser laser : getLasersCopy()) {
                    for (Asteroid asteroid : fullAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toAsteroidRemoves.add(asteroid);
                            toLaserRemoves.add(laser);
                            System.out.println("Dead");
                            leftHalfAsteroids.add(new Asteroid(leftHalfAsteroid, asteroid.getX() + 1, asteroid.getY(), new Velocity(-asteroid.getVelocity().x, -asteroid.getVelocity().y), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            rightHalfAsteroids.add(new Asteroid(rightHalfAsteroid, asteroid.getX() - 5, asteroid.getY(), new Velocity(+asteroid.getVelocity().x, +asteroid.getVelocity().y), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            points = (points + 100);
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Poison"));
                            }

                        }
                        if (ship.rectangle().intersects(asteroid.rectangle())) {
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("hit");
                            ship.addHealth(-30);

                        }
                    }
                    for (Asteroid asteroid : leftHalfAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toLaserRemoves.add(laser);
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("Dead 2");
                            leftQuaterTopAsteroids.add(new Asteroid(leftQuaterTopAsteroid, asteroid.getX() - 5, asteroid.getY(), new Velocity(asteroid.getVelocity().x + 5, asteroid.getVelocity().y - 5), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            leftQuaterBottomAsteroids.add(new Asteroid(leftQuaterBottomAsteroid, asteroid.getX() + 5, asteroid.getY() - 5, new Velocity(asteroid.getVelocity().x + 5, asteroid.getVelocity().y + 5), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            points = (points + 150);
                            //random chance to drop a power up
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Health"));
                            }
                        }
                        if (ship.rectangle().intersects(asteroid.rectangle())) {
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("hit");
                            ship.addHealth(-20);
                        }
                    }

                    for (Asteroid asteroid : rightHalfAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toLaserRemoves.add(laser);
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("Dead 2");
                            rightQuaterTopAsteroids.add(new Asteroid(rightQuaterTopAsteroid, asteroid.getX() + 5, asteroid.getY() + 5, new Velocity(asteroid.getVelocity().x + 5, -asteroid.getVelocity().y), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            rightQuaterBottomAsteroids.add(new Asteroid(rightQuaterBottomAsteroid, asteroid.getX() - 5, asteroid.getY(), new Velocity(-asteroid.getVelocity().x, -asteroid.getVelocity().y + 5), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            points = (points + 150);
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Health"));
                            }
                        }
                        if (ship.rectangle().intersects(asteroid.rectangle())) {
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("hit");
                            ship.addHealth(-20);
                        }
                    }
                    for (Asteroid asteroid : rightQuaterTopAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toLaserRemoves.add(laser);
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("Dead 3");
                            points = (points + 200);
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Health"));
                            }
                        }
                        if (ship.rectangle().intersects(asteroid.rectangle())) {
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("hit");
                            ship.addHealth(-10);
                        }
                    }
                    for (Asteroid asteroid : rightQuaterBottomAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toLaserRemoves.add(laser);
                            toAsteroidRemoves.add(asteroid);
                            fullAsteroids.add(new Asteroid(fullAsteroid, 0, 0, asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            System.out.println("Dead 3");
                            points = (points + 200);
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Wall"));
                            }
                        }
                        if (ship.rectangle().intersects(asteroid.rectangle())) {
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("hit");
                            ship.addHealth(-10);

                        }
                    }
                    for (Asteroid asteroid : leftQuaterTopAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toLaserRemoves.add(laser);
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("Dead 3");
                            points = (points + 200);
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Health"));
                            }
                        }
                        if (ship.rectangle().intersects(asteroid.rectangle())) {
                            toAsteroidRemoves.add(asteroid);
                            System.out.println("hit");
                            ship.addHealth(-10);
                        }
                    }
                    for (Asteroid asteroid : leftQuaterBottomAsteroids) {
                        if (laser.rectangle().intersects(asteroid.rectangle())) {
                            AudioPlayer.play("/secondasteroidgame/Bomb");
                            toLaserRemoves.add(laser);
                            toAsteroidRemoves.add(asteroid);
                            fullAsteroids.add(new Asteroid(fullAsteroid, 0, 0, asteroid.getVelocity(), asteroid.getAngularVelocity(), asteroid.getAngle()));
                            System.out.println("Dead 3");
                            points = (points + 200);
                            if (Math.random() < 0.9) {
                                powerUp.add(new PowerUp(asteroid.getX(), asteroid.getY(), "Wall"));
                            }
                        }
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

    private void powerUps() {

        if (ship != null) {
            for (PowerUp powerUp : powerUp) {
                powerUp.setpoison(ResourceTools.loadImageFromResource("SecondAsteroidGame/Obama.png"));
                powerUp.setHealth(ResourceTools.loadImageFromResource("SecondAsteroidGame/Trump_health.png"));
                powerUp.setwall(ResourceTools.loadImageFromResource("SecondAsteroidGame/Wall.jpg"));
                    if (ship.rectangle().intersects(powerUp.rectangle())) {
                        if (powerUp.getType() == "Poison") {
                            ship.accelerate(0);
                            ship.decelarate(0);
                        }
                        if (powerUp.getType() == "Health") {
                            ship.addHealth(100);
                        }
                        if (powerUp.getType() == "Wall") {
                            fullAsteroids.clear();
                            leftHalfAsteroids.clear();
                            rightHalfAsteroids.clear();
                            leftQuaterTopAsteroids.clear();
                            leftQuaterBottomAsteroids.clear();
                            rightQuaterTopAsteroids.clear();
                            rightQuaterBottomAsteroids.clear();
                
                fullAsteroids.add(new Asteroid(fullAsteroid, 500, 300, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 600, 400, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 700, 500, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 800, 600, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 500, 600, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 600, 500, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 700, 400, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 800, 300, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 500, 400, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 800, 500, new Velocity(0, 0), 0, 0));
                fullAsteroids.add(new Asteroid(fullAsteroid, 900, 800, new Velocity(0, 0), 0, 0));
                        }
                        }
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            soundManager.play(AMERICANPIE, -1);
        }
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
            if (ship.isAlive()) {
                if (name.equals("American")) {
                    AudioPlayer.play("/secondasteroidgame/shooting");
                    lasers.add(new Laser(lazerImage, ship.getX() + 20, ship.getY(), TrigonometryCalculator.getVelocity(Math.toRadians(ship.getAngle() + 90), ship.getSpeed() + 7), 0, ship.getAngle()));
                } else if (name.equals("Trump")) {
                    AudioPlayer.play("/secondasteroidgame/trump");
                    lasers.add(new Laser(lazerImage, ship.getX() + 10, ship.getY(), TrigonometryCalculator.getVelocity(Math.toRadians(ship.getAngle() + 90), ship.getSpeed() + 7), 0, ship.getAngle()));
                } else if (name.equals("Soviet")) {
                    lasers.add(new Laser(lazerImage, ship.getX() + 10, ship.getY(), TrigonometryCalculator.getVelocity(Math.toRadians(ship.getAngle() + 90), ship.getSpeed() + 7), 0, ship.getAngle()));
                }
            }
        }
        if (ship.getHealth() <= 0) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ship.accelerate(0);
                lasers.clear();
                powerUp.clear();
                points = points - points;
                fullAsteroids.clear();
                leftHalfAsteroids.clear();
                rightHalfAsteroids.clear();
                leftQuaterTopAsteroids.clear();
                leftQuaterBottomAsteroids.clear();
                rightQuaterTopAsteroids.clear();
                rightQuaterBottomAsteroids.clear();
                ship.setHealth(1000);
                fullAsteroids.add(new Asteroid(fullAsteroid, 100, 200, TrigonometryCalculator.getVelocity(Math.toRadians(45), 7), 0, 0));
                ship.setVelocity(new Velocity(0, 0));
                ship.setX(400);
                ship.setY(300);
            }
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
        if (powerUp != null) {
            for (int i = 0; i < powerUp.size(); i++) {
                powerUp.get(i).draw(graphics);
            }
        }

        if (hud != null) {
            graphics.setColor(Color.YELLOW);
            graphics.setFont(new Font("Calibri", Font.BOLD, 36));
            graphics.drawString(score, 380, 30);
            if (ship != null) {
                graphics.drawString("Health " + ship.getHealth(), 0, 30);
            }
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

        if (ship != null) {
            if (ship.isAlive()) {
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
            } else {
                graphics.setColor(Color.red);
                graphics.setFont(new Font("Calibri", Font.BOLD, 158));
                graphics.drawString("Game Over", 0, 400);
            }

        }
    }

}
