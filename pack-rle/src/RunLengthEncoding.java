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
			} else {
				if (i + 1 < text.length() && text.charAt(i) != text.charAt(i + 1)) {
					if (text.charAt(i) == '-') {
						str.append("0+");
					} else {
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
				} else {
					if (i == text.length() - 1) {
						str.append(text.charAt(i));
					}
				}
				if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
					int symbolLength = 1;
					while (i + 1 < text.length() && (text.charAt(i) == text.charAt(i + 1))) {
						symbolLength++;
						i++;
					}
					str.append(symbolLength);
					if (text.charAt(i) == '-') {
						str.append("--");
					} else
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
			int number = -1;
			try {
				number = Integer.parseInt(matcher.group());
			} catch (NumberFormatException e) {
				str.append(matcher.group());
			}
			if (number < 0) {
				matcher.find();
				number = Integer.parseInt(matcher.group());
			}
			if (matcher.group().charAt(0) == '0' && matcher.group().length() > 1) {
				int k = 0;
				while ((matcher.group().charAt(k)) == '0') { 
					if (k + 1 < matcher.group().length()) {
						k++;
						str.append(0);
					} else {
						break;
					}
				}
			}
			matcher.find();
			if (matcher.group().equals("-")) {
				str.append(number);
			} else {
				if (matcher.group().equals("--")) {
					while ((number--) != 0) {
						str.append("-");
					}
				} else {
					if (matcher.group().length() == 1) {
						if (matcher.group().charAt(0) == ('+') && number == 0) {
							str.append("-");
						} else {
							while ((number--) != 0) {
								str.append(matcher.group());
							}
						}
					} else { 
						if (matcher.group().charAt(0) == ('-')) {
							if ((matcher.group().charAt(1) != ('-'))) {
								str.append(number);
								str.append(matcher.group().substring(1));
							} else {
								while ((number--) != 0) {
									str.append("-");
								}
								str.append(matcher.group().substring(2));
							}
						} else {
							if ((matcher.group().charAt(1) == ('-'))) {
								while ((number--) != 0) {
									str.append("-");
								}
							}
							else {
								if (matcher.group().charAt(0) == ('+') && number == 0) {
									str.append("-");
									str.append(matcher.group().substring(1));
								} else {
									if (matcher.group().length() > 1) {
										while ((number--) != 0) {
											str.append(matcher.group().charAt(0));
										}
										str.append(matcher.group().substring(1));
									} else {
										while ((number--) != 0) {
											str.append(matcher.group());
										}
									}
								}
							}
						}
				    }
				}
			}
		}
		return str.toString();

	}

	public static File coder(String arg, String out, String in) {
		File input = new File(in);
		File output = new File(out);
		try {
			if (!output.exists()) {
				output.createNewFile();
			}
			PrintWriter pw = new PrintWriter(output);
			BufferedReader br = null;
			br = new BufferedReader(new FileReader(input));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				if (arg.equals("-z")) {
					pw.println(packer(line));
					System.out.println(packer(line));
				} else {
					if (arg.equals("-u")) {
						pw.println(unpacker(line));
						System.out.println(unpacker(line));
					} else {
						System.out.println("error");
					}
				}
			}
			pw.close();
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return output;
	}
}
