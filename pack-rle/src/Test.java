import static org.junit.Assert.assertEquals;

import java.io.File;


class Test {
	private RunLengthEncoding rle = new RunLengthEncoding();
	File output1 = new File("rleOut.txt");
	File output2 = new File("rle12.txt");

	@org.junit.jupiter.api.Test
	void packer() {
		assertEquals("2-abcdef2b1-" , rle.packer("2abcdefbb1"));
		assertEquals("a4 2b" , rle.packer("a    bb"));
		assertEquals("abcd2e" , rle.packer("abcdee"));
		assertEquals("abcde4-0+2-c1-" , rle.packer("abcde4-2c1"));
		assertEquals("4--abc" , rle.packer("----abc"));
		assertEquals("0-3--0-+" , rle.packer("0---0+"));
		assertEquals("3Cbob" , rle.packer("CCCbob"));
		assertEquals("0002-" , rle.packer("0002"));
	}
	
	@org.junit.jupiter.api.Test
	void unpacker() {
		assertEquals("000" , rle.unpacker("000-"));
		assertEquals("-AAcda4--" , rle.unpacker("0+2Acda4-2--"));
		assertEquals("abcd" , rle.unpacker("abcd"));
		assertEquals("22adcbbAAAAA" , rle.unpacker("22-adc2b5A"));
		assertEquals("adc  dd" , rle.unpacker("adc2 2d"));
		assertEquals("abcde4-2c1" , rle.unpacker("abcde4-0+2-c1-"));
		assertEquals("----2-" , rle.unpacker("4--2-0+"));
		assertEquals("000---0+" , rle.unpacker("000-3--0-+"));
		assertEquals("CCCbob" , rle.unpacker("3Cbob"));
		assertEquals("----abc" , rle.unpacker("4--abc"));
	}
	@org.junit.jupiter.api.Test
	void main() {
		assertEquals( output1, rle.coder("-z" , "rleOut.txt" , "rle.txt"));
		assertEquals( output2, rle.coder("-u" , "rle12.txt" , "rleOut.txt"));
	}


}
