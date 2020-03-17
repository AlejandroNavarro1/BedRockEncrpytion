package am;

public class NajahCrypt2 {
	
	public NajahCrypt2 () {
		
	}
	
	public static String encryptString (String input, int key) {
		
		StringBuilder sb = new StringBuilder(input.length());
		
		for (char c : input.toCharArray()) {
			sb.append((char) (c + key));
		}
		return sb.toString();
	}
	
	public static String decryptString (String input, int key) {
		
		return encryptString(input, -key);
		
	}
	
}