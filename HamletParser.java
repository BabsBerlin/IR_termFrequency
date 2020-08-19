/*
 * HamletParser.java
 * 
 * 18.09.2019
 * 
 * by Barbara Hartmann
 */
package HamletIR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class HamletParser {
	
	public void parseFile(String fileName, String tagName, String outputFile) {
	    try {
		// retrieve the XML document and parse
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        
        // loop through all LINE elements
        NodeList nList = doc.getElementsByTagName(tagName);
        
        for (int temp = 0; temp < nList.getLength(); temp++) {
           Node nNode = nList.item(temp);
           
           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
              Element eElement = (Element) nNode;                                                  
              saveToTXT(eElement.getTextContent(), outputFile);              
           }
        }
  
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		System.out.println("created new file: " + outputFile);
	}
   

	// write the parsed xml content to a txt file
    public void saveToTXT(String inputString, String outputFile) 
  		  throws IOException {
    		String txtFileName = outputFile;
  		    BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileName, true));
  		    writer.append(' ');
		    writer.append(inputString); 
  		    writer.close();
  	}

}
