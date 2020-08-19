/*
 * HamletTermCount.java
 * 
 * 18.09.2019
 * 
 * by Barbara Hartmann
 */
package HamletIR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class HamletTermCount {

	
	public static void main(String[] args) throws FileNotFoundException {
    		
		String hamletXMLinput = "src/HamletIR/resources/hamlet.xml";
		String hamletXMLtag = "LINE";
		String hamletLineTXT = "src/HamletIR/resources/hamletLineTemp.txt";
		String hamletStemTXT = "src/HamletIR/resources/hamletStemTemp.txt";
		
/*
*parse the XML document and save it to a txt file
*ATTENTION: delete src/HamletIR/resources/hamletLine.txt" after each run cycle
*/
		System.out.println();
		System.out.println("************** PARSING *************");
		HamletParser myParser = new HamletParser();
		myParser.parseFile(hamletXMLinput, hamletXMLtag, hamletLineTXT); 
				

/*
*apply Porter Stemmer on the before extracted LINE tags in hamletLineTXT
*and save to hamletStemTXT
*/
		System.out.println();
		System.out.println("************** STEMMING *************");

	    Stemmer myStemmer = new Stemmer();
	    try {
	        int hamletLineFileSize = (int) new File(hamletLineTXT).length();
	        char[] w = new char[hamletLineFileSize];
	    	FileInputStream hamletLinesInputFile = new FileInputStream(hamletLineTXT);
	        int inputLineCharTemp = 0;
	        try {
	        	while((inputLineCharTemp=hamletLinesInputFile.read())!=-1) {
	        		int inputLineChar = inputLineCharTemp;
	        		if (Character.isLetter((char) inputLineChar)) {
	        			int j = 0;
	        			while(true) {
	        				inputLineChar = Character.toLowerCase((char) inputLineChar);
	        				w[j] = (char) inputLineChar;
	        				if (j < hamletLineFileSize-1) j++;
	        				inputLineChar = hamletLinesInputFile.read();
	        				if (!Character.isLetter((char) inputLineChar)) {
	        					for (int i = 0; i < j; i++) myStemmer.add(w[i]);
	        					myStemmer.stem();
	        					String stemmedWord;
	        					stemmedWord = myStemmer.toString();
	        					saveToTXT(stemmedWord, hamletStemTXT);	                       
	        					break;
	        				}
	        			}
	        		}
	              if (inputLineChar < 0) break;
	        	}
	        	hamletLinesInputFile.close();
	        	System.out.println("created new file: " + hamletStemTXT);
	        } catch (IOException e) {
	        	System.out.println("error reading " + hamletLineTXT);
	        }
	    } catch (FileNotFoundException e){
	    	System.out.println("file " + hamletLineTXT + " not found");
	    }			

			
/*
*calculate the terms frequencies
*stemmed hamlet lines as input file
*/
		System.out.println();
		System.out.println("************** TERM COUNTING *************");
		WordCounter myCount = new WordCounter();
		myCount.wordCount(hamletStemTXT);

		
		System.out.println("************** END *************");

	}
	
/*
*write the parsed xml content to a txt file
*/	
    public static void saveToTXT(String inputString, String outputFile) 
  		  throws IOException {
    		String txtFileName = outputFile;
  		    BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileName, true));
  		    writer.append(' ');
		    writer.append(inputString); 
  		    writer.close();
  		}

}
