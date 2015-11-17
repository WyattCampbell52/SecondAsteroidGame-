/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import environment.Actor;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author WyattCampbell
 */
public class Lazer extends Actor {
    
    Image lazer;
    Point position;
    Velocity speed;

    public Lazer(BufferedImage image, Point position, Velocity velocity) {
        super(image, position, velocity);
        
        position = new Point();
        speed = speed;
        lazer = ResourceTools.loadImageFromResource("SecondAsteroidGame/Lazer.png");
        
        
    }

    
    
    }
    
