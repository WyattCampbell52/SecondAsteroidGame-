/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import images.ImageManager;
import images.ResourceTools;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author WyattCampbell
 */
public class AnimatedImageManager extends ImageManager {
//    Need an arraylist for every direction
    public static final String SHIP_MOVING = "SHIP_MOVE";
    public static final String SHIP_STILL = "SHIP_STILL";    
//    For listing and storing Strings
    public static ArrayList<String> SHIP_MOVE_IMAGE_NAMES;
    public static ArrayList<String> SHIP_STILL_IMAGE_NAMES;
    
    
    
    public AnimatedImageManager(){
//        Making the arraylist
     SHIP_MOVE_IMAGE_NAMES = new ArrayList<>();
//     Add all the strings under the corisponding arraylist
     SHIP_MOVE_IMAGE_NAMES.add(SHIP_MOVING);
     //        Making the arraylist
     SHIP_STILL_IMAGE_NAMES = new ArrayList<>();
//     Add all the strings under the corisponding arraylist
     SHIP_STILL_IMAGE_NAMES.add(SHIP_STILL);
     
//     add images
     this.addImage(SHIP_MOVING, ResourceTools.loadImageFromResource("secondasteroidgame/American_Ship_Flame.png"));
     this.addImage(SHIP_STILL, ResourceTools.loadImageFromResource("secondasteroidgame/American_Ship.png"));
    }
    
    
    
}
