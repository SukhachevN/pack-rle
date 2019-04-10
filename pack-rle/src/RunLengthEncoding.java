import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {
	public static String packer(String text) {
		StringBuilder str = new StringBuilder();
		for(int i = 0 ; i<text.length(); i++) {
			StringBuilder digits = new StringBuilder();
			digits.append(text.charAt(i));
			try {//если символ/последовательность символов €вл€етс€ числом, то после него будет идти "-"
				if (i+1<text.length() && Integer.parseInt(digits.toString()) > 0) { // проверка €вл€етс€ ли символ цифрой
					try {
						while(i+1<text.length() && Integer.parseInt(digits.toString()) > 0) {
							i++;
							digits.append(text.charAt(i));
						}
					}
						catch (NumberFormatException e) {
							digits.deleteCharAt(digits.toString().length()-1);
							str.append(digits);
							str.append("-");
							digits.delete(0,digits.toString().length());
							i--;
						}
				}
				if (!digits.toString().isEmpty()) {//проверка случа€, когда цифры €вл€ютс€ последними символами в строке
					str.append(digits);
					str.append("-");
				}
			}
			catch (NumberFormatException e ) {
				if (i+1<text.length() && (text.charAt(i) != text.charAt(i+1))) { 
					StringBuilder singleLetters = new StringBuilder();
					while (i+1<text.length() && (text.charAt(i) != text.charAt(i+1))) {
						singleLetters.append(text.charAt(i));
						i++;
					}
					str.append(1);
					str.append(singleLetters);
			}
				if (i+1<text.length() && (text.charAt(i) == text.charAt(i+1))){
					int symbolLength1 = 1;
					while (i+1<text.length() && (text.charAt(i) == text.charAt(i+1))) {
						symbolLength1++;
						i++;
					}
					str.append(symbolLength1);
					str.append(text.charAt(i));
			}
		}
	}
		return str.toString();
}
	public static String unpacker(String text) {
		StringBuilder str = new StringBuilder();
			Pattern pattern = Pattern.compile("[0-9]+|\\D+"); // число повторений или буква/последовательность букв
		    Matcher matcher = pattern.matcher(text);
		    while (matcher.find()) {
		    	
	            int number = Integer.parseInt(matcher.group());
	            matcher.find();
	            if (matcher.group().equals("-")) {
	            	str.append(number);
	            }
	            else {
	            while ((number--) != 0) {
	                str.append(matcher.group());
	            }
		}
		    }
		return str.toString();

}
	 public static void main(String[] args) {
		 try {
			 File file = new File("rle.txt");
			 if(!file.exists()) {
				 file.createNewFile();
			 }
			 PrintWriter pw = new PrintWriter(file);
			 pw.println("12bbbb23ffff");
			 pw.close();
			 BufferedReader br = null;
			 br = new BufferedReader(new FileReader("rle.txt"));
			 String line;
			 while((line = br.readLine()) != null) {
				 System.out.println(unpacker(packer(line)));
			 }
			 br.close();
		 } catch(IOException e) {
			 System.out.println(e);
		 } 
	 }
}

