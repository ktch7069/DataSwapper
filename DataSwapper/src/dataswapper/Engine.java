/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataswapper;

import java.util.*;

/**
 *
 * @author tchung
 */
public class Engine {
    
    public Engine(){
        
    }
    
    public void run(){
        
        Data data = new Data(1,100000);
        data.setEmailAddress("ktch7069@gmail.com");
        
        System.out.println(data.getEmailAddress());
    }
    
    
    public static void main(String[] args){
        
        Engine engine = new Engine();
        
        engine.run();
        System.out.println("blah");
        
    }
}
