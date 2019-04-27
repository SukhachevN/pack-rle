public class Main {
	public static void main(String[] args) {
		String arg = "noArg";String out = "noOut";String in = "noIn";
		RunLengthEncoding rle = new RunLengthEncoding();
		for (int i = 0; i < args.length; i++) {
			if ("-u".equals(args[i]) || "-z".equals(args[i]) ) {
				arg = args[i];
				i++;
				in = args[i];
			}
			if (args[i].equals("-out")) {
				i++;
				out = args[i];
			}
		}
		if(out.equals("noOut")) {
			out="out"+in;
		}
		if (!arg.equals("noArg") && !in.equals("noIn")) {
			rle.coder(arg, out, in);
		}
		else {
			System.out.println("Error");
		}
	}
}
