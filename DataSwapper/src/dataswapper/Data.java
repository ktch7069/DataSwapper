/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataswapper;
import java.util.Random;

/**
 *
 * @author tchung
 */
public class Data {
    
    private int documentId;
    private String emailAddress;
    private int maxDocId;
    private int minDocId;
    
    public Data(int maxDocId, int minDocId){
        
        documentId = generateRandomDocId();
    }
            
    public void setEmailAddress(String emailAddress){
        
        this.emailAddress = emailAddress;
    }
    
    public String getEmailAddress(){
        
        return emailAddress;
    }
    
    public int getDocumentId(){
        
        return documentId;
    }
    
    private int generateRandomDocId(){
         
        //get a random number between 1-`00000 
        Random rand = new Random();
        return rand.nextInt((maxDocId - minDocId)+1)+minDocId;
       
    }
    
}
