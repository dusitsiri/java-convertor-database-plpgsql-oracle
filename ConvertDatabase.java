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
          
          boolean sendingMail = false;
          String regex = "\\([^0][0-9]* rows affected\\)";
        
          while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
					  if (StringUtils.isNotBlank(line) && Pattern.matches(regex, line)) {
					  	sendingMail = true;
					  }
          }
        }
        
}
}
