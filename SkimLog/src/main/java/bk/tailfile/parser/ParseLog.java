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
	
	public static ParserObject parser (String line, ParserObject previousObject) {
		String[] str = line.split(" ");
		String[] parserString = {"", "", ""};
		boolean isNewObject = (str[0].equals(";")) ? true : false;
		ParserObject newObject;
		
		//System.out.println(Arrays.toString(str));
		
		if (isNewObject) {
			for (int i = 1; i < str.length; i++) {
				// time stamps
				if (i == 1 || i == 2) {
					parserString[0] = parserString[0] + str[i] + " ";
				}
				else if (i == 4) {
					parserString[1] = parserString[1] + str[i];
				}
				else {
					parserString[2] = parserString[2] + str[i] + " ";
				}
			}
			
			// create new ParserObject, trim trailing white spaces
			newObject = new ParserObject(parserString[0].trim(), parserString[1], parserString[2].trim());
			return newObject;
		}
		else if (! (previousObject == null)) {
			previousObject.append(line.trim());
			return previousObject;
		}
		else {
			return null;
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