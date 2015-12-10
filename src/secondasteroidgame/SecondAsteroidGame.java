/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondasteroidgame;

import environment.ApplicationStarter;

/**
 *
 * @author WyattCampbell
 */
public class SecondAsteroidGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationStarter.run("Spaceteroids!", new Space());//, Reso)
    }

}
