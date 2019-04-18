public class Main {
	public static void main(String[] args) {
		String arg = "noArg";String out = "noOut";String in = "noIn";
		RunLengthEncoding rle = new RunLengthEncoding();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u") || args[i].equals("-z") ) {
				arg = args[i];
				in = args[i+1];
			}
			if (args[i].equals("-out")) {
				out = args[i+1];
			}
		}
		if(out.equals("noOut")) {
			out="out"+in;
		}
		if(!arg.equals("noArg") && !in.equals("noIn"))
		rle.coder(arg, out, in);
	}
}
