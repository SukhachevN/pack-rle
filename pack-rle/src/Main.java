import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		String arg;
		String out;
		String in;
		Scanner sc = new Scanner(System.in);
		RunLengthEncoding rle = new RunLengthEncoding();
		String str = sc.nextLine();
		Pattern pattern = Pattern.compile("\\S+"); 
	    Matcher matcher = pattern.matcher(str);
	    while (matcher.find()) {
	    	arg = matcher.group();
	    	matcher.find();
	    	matcher.find();
	    	out = matcher.group();
	    	matcher.find();
	    	in = matcher.group();
	    rle.coder(arg, out, in);
	    }
	}
}
