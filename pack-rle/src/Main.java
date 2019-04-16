public class Main {
	public static void main(String[] args) {
		String arg = "noArg";
		String out = "noOut";
		String in = null;
		RunLengthEncoding rle = new RunLengthEncoding();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u") || args[i].equals("-z") ) {
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
		if(!arg.equals("noArg"))
		rle.coder(arg, out, in);
	}
}
