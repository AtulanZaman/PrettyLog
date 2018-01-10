package bk.tailfile.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 */
public class ParseLog {

	public static ParserObject parser (String line) {
		String[] str = line.split(" ");
		String[] parserString = {"", "", "", ""};
		boolean isNewObject = (str[0].equals(";")) ? true : false;
		ParserObject newObject = null;
		
		if (isNewObject) {
			for (int i = 1; i < str.length; i++) {
				// time stamps
				if (i == 1 || i == 2) {
					parserString[0] = parserString[0] + str[i] + " ";
				}
				//log level
				else if (i == 3) {
					parserString[1] = parserString[1] + str[i] + " ";
				}
				//context
				else if (i == 4) {
					parserString[2] = parserString[2] + str[i] + " ";
				}
				//body
				else {
					parserString[3] = parserString[3] + str[i] + " ";
				}
			}
			
			// create new ParserObject, trim trailing white spaces
			newObject = new ParserObject(parserString[0].trim(), parserString[1].trim(), parserString[2].trim(), parserString[3].trim());
		}
		else 
		{
			for (int i = 0; i < str.length; i++) {
				parserString[3] = parserString[3] + str[i] + " ";
			
			}
			// only store body if line doens't start with ";"
			newObject = new ParserObject("", "", "", parserString[3].trim());
		}
		return newObject;
	}
}