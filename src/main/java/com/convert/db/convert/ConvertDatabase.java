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
			Path pathFile = Paths.get(args[0]);

			if (Files.exists(pathFile)) {
				// read text file
				inputStream = new FileInputStream(pathFile.toString());
				scanner = new Scanner(inputStream, "UTF-8");

				boolean convertDB = false;
				String regex = "\\([^0][0-9]* rows affected\\)";
				
				List<String> listOracleSyntax = new ArrayList<String>();

				listOracleSyntax.add("nvl");
				listOracleSyntax.add("decode");

				final CharSequence[] charSeqOracleItems = listOracleSyntax.toArray(new CharSequence[listOracleSyntax.size()]);
				

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (StringUtils.isNotBlank(line)) {
						for (CharSequence charseq : charSeqOracleItems) {
							if (line.toLowerCase().contains(charseq)) {
								convertDB = true;
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
}
