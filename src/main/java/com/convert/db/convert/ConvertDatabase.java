package com.convert.db.convert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class ConvertDatabase {
	
	public static void main(String[] args) {

		FileInputStream inputStream = null;
		Scanner scanner = null;

		try {
//			Path pathFile = Paths.get(args[0]);
			Path pathFile = Paths.get("O:/Workspaces/convert-db/convert/plaintext.txt");
//			Path pathFile = Paths.get("D:/Coding/java-convertor-database-plpgsql-oracle/plaintext.txt");
//			Path pathFile = Paths.get("O:/PL/decode.txt");

			if (Files.exists(pathFile)) {
				// read text file
				inputStream = new FileInputStream(pathFile.toString());
				scanner = new Scanner(inputStream, "UTF-8");
				
				//list oracle syntax
				List<String> listOracleSyntax = new ArrayList<String>();
				listOracleSyntax.add("nvl");
				listOracleSyntax.add("decode");
				final CharSequence[] charSeqOracleItems = listOracleSyntax.toArray(new CharSequence[listOracleSyntax.size()]);
				
				String afterConvertString = "";
				
				//converting oracle to postgresql
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (StringUtils.isNotEmpty(line)) {
						for (CharSequence charseq : charSeqOracleItems) {
							if (line.toLowerCase().contains(charseq)) {
								line = findCharOracle(line, charseq);
							}
						}
						afterConvertString = afterConvertString + line + "\n";
					} else {
						afterConvertString+=" \n";
					}
				}
				
				//write file after converting
				writeFile(afterConvertString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//find index of oracle syntax
	public static String findCharOracle(String line, CharSequence charOracle) {
		String stringConverted = "";
		line = line.toLowerCase();

		//charOracle.toString().length()-1 index of charAt is begin from 0, if length = 3 so index = [0, 1, 2]
		int firstIndexCharOracle = line.indexOf(charOracle.toString());
		int lastIndexCharOracle = firstIndexCharOracle+(charOracle.toString().length()-1);
		
		
		String remainStr = line.substring(firstIndexCharOracle, lastIndexCharOracle+1);
		
		if (StringUtils.equals(remainStr, "nvl")) {
			stringConverted = convertNvl(remainStr, line);
		} else if (StringUtils.equals(remainStr, "decode")) {
			stringConverted = convertDecode(remainStr, line);			
		}
		
		return stringConverted;
	}
	
	//nvl
	public static String convertNvl(String remainStr, String line) {
		String stringConverted = "";
		stringConverted = line.replace(remainStr, "coalesce");
		return stringConverted;
	}
	
	//decode
	public static String convertDecode(String remainStr, String line) {
		String stringConverted = "";
		//1.normal case 2.more one line 
		stringConverted = line.replace(remainStr, "case");
		String[] decodeArry = stringConverted.substring(stringConverted.indexOf("(")+1, stringConverted.indexOf(")")).split(",");
		return stringConverted;
	}
	
	//write file
	public static void writeFile(String text) throws IOException {
		
		try {
			
			FileOutputStream out = new FileOutputStream("O:/Workspaces/convert-db/convert/newPlainText.txt");
//			FileOutputStream out = new FileOutputStream("D:/Coding/java-convertor-database-plpgsql-oracle/newPlainText.txt");
			out.write(text.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
