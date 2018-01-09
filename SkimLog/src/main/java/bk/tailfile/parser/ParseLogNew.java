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

	/**
	 * @param args
	 */
	public static void mainParseLog(String[] args) {
		// TODO Auto-generated method stub
		String filePath = "C:/work/log/out.txt";
		
		try {
			FileReader fr;
			fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			ParserObject p = null;
			while ((line = br.readLine()) != null) {
				p = parser(line, p);

				//System.out.println(p);
				
				//filterByCore(p);
				filterByModel(p);
				
/*				System.out.println(p.getTimeStamp());
				System.out.println(p.getContext());
				System.out.println(p.getBody());*/
			}
			
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ParserObject parser (String line) {
		String[] str = line.split(" ");
		String[] parserString = {"", "", "", ""};
		boolean isNewObject = (str[0].equals(";")) ? true : false;
		ParserObject newObject;
		boolean isCollapsible = false;
		
		//System.out.println(Arrays.toString(str));
		
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
					// checking for collapsible
					if (str[i].contains("nexj.core.persistence.sql.SQLAdapter")) {
						isCollapsible = true;
					}
				}
				//body
				else {
					parserString[3] = parserString[3] + str[i] + " ";
				}
			}
			
			// create new ParserObject, trim trailing white spaces
			newObject = new ParserObject(parserString[0].trim(), parserString[1].trim(), parserString[2].trim(), parserString[3].trim());
			if (isCollapsible) {
				newObject.setIsCollapsible(isCollapsible);
			}
			return newObject;
		}
		else {
			for (int i = 5; i < str.length; i++) {
				parserString[3] = parserString[3] + str[i] + " ";
			
			}
			// only store body if line doens't start with ";"
			newObject = new ParserObject("", "", "", parserString[3].trim());
			return newObject;
		}
	}
	
	/*
	 * Filters by core
	 */
	public static void filterByCore(ParserObject p) {
		if (p.getContext().contains("nexj.core")) {
			System.out.println(p);
		}
	}
	
	/*
	 * Filters by model
	 */
	public static void filterByModel(ParserObject p) {
		if (p.getContext().contains("nexj.model")) {
			System.out.println(p);
		}
	}

}
