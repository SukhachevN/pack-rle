import static org.junit.Assert.assertEquals;


class Test {
	private RunLengthEncoding rle = new RunLengthEncoding();

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
		assertEquals( true, rle.main("z" , "rleOut.txt" , "rle.txt"));
		assertEquals( true, rle.main("u" , "rle12.txt" , "rleOut.txt"));
		
	}


}
