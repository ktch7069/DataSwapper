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
    
    //arraylist to store the email address 
    private ArrayList<String> emailAddresses;
    private String inputPath;
    private String outputPath;
    private char fieldDelimiter;
    private char fieldQuote;
    
    public CSVParser(Properties prop){
        
        inputPath= prop.getProperty(inputPathKey);
        outputPath =prop.getProperty(outputPathKey);
        fieldDelimiter=(prop.getProperty("fieldDelimiter")).charAt(0);
        fieldQuote=(prop.getProperty("fieldDelimiter")).charAt(0);
    }


    public List<String> parseLine(String cvsLine, char separators, char customQuote) throws Exception{
        
        Scanner scanner = new Scanner(new File(inputPath));
        
        while (scanner.hasNext()) {
            
            //Create a data object and put it to the arraylist
            String emailAddr = scanner.nextLine();
            //parsing the line
        
                    scanner.close();

                    List<String> result = new ArrayList<>();

                    //if empty, return!
                    if (cvsLine == null && cvsLine.isEmpty()) {
                        return result;
                    }

                    if (customQuote == ' ') {
                        customQuote = DEFAULT_QUOTE;
                    }

                    if (separators == ' ') {
                        separators = DEFAULT_SEPARATOR;
                    }

                    StringBuffer curVal = new StringBuffer();
                    boolean inQuotes = false;
                    boolean startCollectChar = false;
                    boolean doubleQuotesInColumn = false;

                    char[] chars = cvsLine.toCharArray();

                    for (char ch : chars) {

                        if (inQuotes) {
                            startCollectChar = true;
                            if (ch == customQuote) {
                                inQuotes = false;
                                doubleQuotesInColumn = false;
                            } else {

                                //Fixed : allow "" in custom quote enclosed
                                if (ch == '\"') {
                                    if (!doubleQuotesInColumn) {
                                        curVal.append(ch);
                                        doubleQuotesInColumn = true;
                                    }
                                } else {
                                    curVal.append(ch);
                                }

                            }
                        } else {
                            if (ch == customQuote) {

                                inQuotes = true;

                                //Fixed : allow "" in empty quote enclosed
                                if (chars[0] != '"' && customQuote == '\"') {
                                    curVal.append('"');
                                }

                                //double quotes in column will hit this!
                                if (startCollectChar) {
                                    curVal.append('"');
                                }

                            } else if (ch == separators) {

                                result.add(curVal.toString());

                                curVal = new StringBuffer();
                                startCollectChar = false;

                            } else if (ch == '\r') {
                                //ignore LF characters
                                continue;
                            } else if (ch == '\n') {
                                //the end, break!
                                break;
                            } else {
                                curVal.append(ch);
                            }
                        }

                    }

                    result.add(curVal.toString());

                    return result;
    }
        
        
        // line is parsed then we create data object and put it in arraylist.

}

    public void setDate(String path){
        
        
        
    }
    
    public ArrayList emailAddresss(){
    
        return emailAddress;
    }
    
}
