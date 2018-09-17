/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.Properties;

/**
 *
 * @author Trump-PC
 */
public class CSVParser {
   
    private final String inputPathKey="inputPath";
    private final String outputPathKey="outputPath";
    private final String fieldDelimiterKey="fieldDelimiter";
    private final String fieldQuoteKey="fieldQuote";
    private final String minDocNumberKey="mixDocNumber";
    private final String maxDocNumberKey="maxDocNumber";
    
    //arraylist to store the email address 
    private ArrayList<Data> emailData;
    private String inputPath;
    private String outputPath;
    private char fieldDelimiter;
    private char fieldQuote;
    private int minDocIdNbr;
    private int maxDocIdNbr;
    
    public CSVParser(Properties prop){
        
        inputPath= prop.getProperty(inputPathKey);
        outputPath =prop.getProperty(outputPathKey);
        fieldDelimiter=(prop.getProperty(fieldDelimiterKey)).charAt(0);
        fieldQuote=(prop.getProperty(fieldQuoteKey)).charAt(0);
        minDocIdNbr=Integer.parseInt(prop.getProperty(minDocNumberKey));
        maxDocIdNbr=Integer.parseInt(prop.getProperty(maxDocNumberKey));
    }
    
    public void parseLine() throws Exception{
        
        Scanner scanner = new Scanner(new File(inputPath));
      
        while (scanner.hasNext()) {
            
            //Create a data object and put it to the arraylist
            String emailString = scanner.nextLine();
             //if empty, return!
            if (emailString == null && emailString.isEmpty()) {
                break;
            }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;
         
        char[] chars = emailString.toCharArray();
        
        for (char ch : chars) {
            if (inQuotes) {
                
                startCollectChar = true;
                
                //hitting end quote
                if (ch == fieldQuote) {
                    inQuotes = false;
                    //doubleQuotesInColumn = false;
                } else {
                    //Fixed : allow "" in custom quote enclosed
                    /*if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }*/
                    curVal.append(ch);
                }
            } else {
                    if (ch ==fieldQuote ) {

                        inQuotes = true;

                        //Fixed : allow "" in empty quote enclosed
                        //Not quire sure what this does
                        /*if (chars[0] != '"' && fieldQuote == '\"') {
                            curVal.append('"');
                        }*/
                        //double quotes in column will hit this!
                        //example "blah""Dada","dadada"
                        /*if (startCollectChar) {
                            curVal.append('"');
                        }*/
                    } 
                    //Reaching end of field
                    else if (ch == fieldDelimiter) {
                        
                        Data aDat = new Data(maxDocIdNbr,minDocIdNbr); 
                        aDat.setEmailAddress(curVal.toString());
                        emailData.add(aDat);

                        curVal = new StringBuffer();
                        startCollectChar = false;

                    } else if (ch == '\r') {
                        //ignore LF characters
                        continue;    
                    } 
                    //end of the char array
                    else if (ch == '\n') {
                        //the end, break!
                        break;
                    } else {
                        curVal.append(ch);
                    }
            }

        }
    }
 
  }
  public ArrayList<Data> getEmailData(){
    
        return emailData;
    }
    
}