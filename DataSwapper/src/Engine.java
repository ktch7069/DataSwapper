/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author tchung
 */
public class Engine {
    
    private Properties prop = new Properties();
    private InputStream input=null;
    private ArrayList<Data> emailData;
   
   
    public Engine(){
        //read property file
        try{
            input = new FileInputStream("src/config.properties");
            prop.load(input);
        }catch(IOException ex){
            ex.printStackTrace();
            
        }finally{
            if(input !=null){
              try{
                input.close();
              }catch(IOException e){
                e.printStackTrace();
              }
            }
        }      
    }
    
    public void run()throws IOException{
        
        //make a CSV Parser object
        CSVParser parser = new CSVParser(prop); 
        parser.parseLine();
        
        this.emailData= parser.getEmailData();
        System.out.println(emailData.size());
        System.out.println("Hi");
        
     
        
     
    }
    
    
    public static void main(String[] args){
        
        Engine engine = new Engine();
        
        try{
            engine.run();
            
        }catch(IOException e){
            System.out.println("Boom!");
            System.exit(0);
        }
                
            
        System.out.println("blah");
        
    }
}
