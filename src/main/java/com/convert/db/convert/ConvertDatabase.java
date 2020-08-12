package com.convert.db.convert;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class ConvertDatabase {
	public static void main(String[] args) {

		FileInputStream inputStream = null;
		Scanner scanner = null;

		try {
//			Path pathFile = Paths.get(args[0]);
			Path pathFile = Paths.get("O:\\Workspaces\\convert-db\\convert\\plaintext.txt");

			if (Files.exists(pathFile)) {
				// read text file
				inputStream = new FileInputStream(pathFile.toString());
				scanner = new Scanner(inputStream, "UTF-8");

				boolean convertDB = false;
//				String regex = "\\([^0][0-9]* rows affected\\)";
				
				List<String> listOracleSyntax = new ArrayList<String>();

				listOracleSyntax.add("nvl");
				listOracleSyntax.add("decode");

				final CharSequence[] charSeqOracleItems = listOracleSyntax.toArray(new CharSequence[listOracleSyntax.size()]);
				
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (StringUtils.isNotEmpty(line)) {
						for (CharSequence charseq : charSeqOracleItems) {
							if (line.toLowerCase().contains(charseq)) {
//								convertDB = true;
								line = convert(line, charseq);
								System.out.println("main");
								System.out.println(line);
							}
						}
					} else {
						continue;
					}
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String convert(String line, CharSequence charOracle) {
		String stringConverted = "";
		line = line.toLowerCase();
		//charOracle.charAt(charOracle.length()-1) index of charAt is begin from 0, if length = 3 so index = [0, 1, 2]
		int firstIndexCharOracle = line.indexOf(charOracle.charAt(0));
		int lastIndexCharOracle = line.indexOf(charOracle.charAt(charOracle.length()-1));
		
		String remainStr = line.substring(firstIndexCharOracle, lastIndexCharOracle+1);
		if (StringUtils.equals(remainStr, "nvl")) {
			stringConverted = line.replace(remainStr, "coalesce");
		}
		
		return stringConverted;
	}
}