import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunLengthEncoding {
	public static String packer(String text) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i))) {
				StringBuilder digits = new StringBuilder();
				digits.append(text.charAt(i));
				while (i + 1 < text.length() && Character.isDigit(text.charAt(i + 1))) {
					i++;
					digits.append(text.charAt(i));
				}
				str.append(digits);
				digits.delete(0, digits.length());
				str.append("-");
			}else if (i + 1 < text.length() && text.charAt(i) != text.charAt(i + 1)) { // "-" -> "0+"
				if (text.charAt(i) == '-') {
					str.append("0+");
				}else {
				StringBuilder singleLetters = new StringBuilder();
				while (i + 1 < text.length() && (text.charAt(i) != text.charAt(i + 1))) {
					singleLetters.append(text.charAt(i));
			        if (Character.isDigit(text.charAt(i + 1))) {
				        break;
			        }
			        i++;
				}
			    if (i == text.length() - 1) {
				    singleLetters.append(text.charAt(i));
			    }
			    str.append(singleLetters);
				}
			}
			else if (i == text.length() - 1) {
				str.append(text.charAt(i));
			}
			if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
				int symbolLength = 1;
				while (i + 1 < text.length() && (text.charAt(i) == text.charAt(i + 1))) {
					symbolLength++;
					i++;
				}
				str.append(symbolLength);
			    if (text.charAt(i) == '-') { //"---" -> "3--"
				    str.append("--");
			    }else str.append(text.charAt(i));
			}
		}
		return str.toString();
	}
	public static String unpacker(String text) {
		StringBuilder str = new StringBuilder();
		Pattern pattern = Pattern.compile("(?<special1>[0-9]+\\-{2})|(?<special2>[0-9]+\\-)|(?<special3>0\\+)|(?<digits>[0-9]+)|(?<symbols>\\D+)"); // число повторений или буква/последовательность символов
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			if (matcher.group("special1") != null) {
				int number = Integer.parseInt(matcher.group("special1").substring(0, (matcher.group()).length()-2));
				while ((number--) != 0) {
					str.append("-");
				}
			}else if (matcher.group("special2") != null) {
				str.append(matcher.group("special2").substring(0, (matcher.group()).length()-1));
			}else if (matcher.group("special3") != null) {
				str.append("-");
			}else if (matcher.group("digits") != null) {
				int number = Integer.parseInt(matcher.group("digits"));
				matcher.find();
			    if (matcher.group("symbols").length()==1) {
				    while ((number--) != 0) {
					    str.append(matcher.group("symbols"));
				    }
			}else while ((number--) != 0) {
				str.append(matcher.group().charAt(0));
			}
				str.append(matcher.group().substring(1));
		    }else if (matcher.group("symbols") != null) {
			    str.append(matcher.group());
		    }
		}
        return str.toString();
	}
	public static File coder(String arg, String out, String in) {
		File input = new File(in);
		File output = new File(out);
		if (output.exists()) {
			try {
				output.createNewFile();
			} catch (IOException e) {
				System.out.println("Error");
			}
		}
		try (PrintWriter pw = new PrintWriter(output);BufferedReader br = new BufferedReader(new FileReader(input));){
			String line;
			while ((line = br.readLine()) != null) {
				if (arg.equals("-z")) {
					pw.println(packer(line));
				} else if (arg.equals("-u")) {
					pw.println(unpacker(line));
				} else System.out.println("error");
			}
		}
	    catch (IOException e) {
		    System.out.println("Error");
	    }
	    return output;
	}
}
