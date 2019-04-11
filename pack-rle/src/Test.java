import static org.junit.Assert.assertEquals;

import java.io.File;


class Test {
	private RunLengthEncoding rle = new RunLengthEncoding();
	File output1 = new File("rleOut.txt");
	File output2 = new File("rle12.txt");

	@org.junit.jupiter.api.Test
	void packer() {
		assertEquals("2-1abcdef2b1-" , rle.packer("2abcdefbb1"));
		assertEquals("1a4 2b" , rle.packer("a    bb"));
	}
	
	@org.junit.jupiter.api.Test
	void unpacker() {
		assertEquals("22adcbbAAAAA" , rle.unpacker("22-1adc2b5A"));
		assertEquals("adc  dd" , rle.unpacker("1adc2 2d"));
	}
	@org.junit.jupiter.api.Test
	void main() {
		assertEquals( output1, rle.coder("-z" , "rleOut.txt" , "rle.txt"));
		assertEquals( output2, rle.coder("-u" , "rle12.txt" , "rleOut.txt"));
	}


}
