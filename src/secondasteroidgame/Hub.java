/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author WyattCampbell
 */
public class Hub {
    public String score;
    public int level;
    private int x;
    private int y;
        public Hub(String score, int x, int y) {
            this.x = x;
            this.y = y;
            this.score = score;
        }
        public void draw(Graphics graphics){
            graphics.setColor(Color.WHITE);
            
            graphics.setFont(new Font("Calibri", Font.BOLD, 36));
            graphics.drawString(score, x, y);
        }
        public void score(){
                    score = "Score " + level;
        }

    
}
