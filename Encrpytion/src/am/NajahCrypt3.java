package am;

public class NajahCrypt3 {

	private String myKey;
	
	public NajahCrypt3 (String key) {
		myKey = key;
	}
	
	public String encryptMessage (String messageToEncrypt) {
		
		StringBuilder result = new StringBuilder();
		
		char[] codeList = messageToEncrypt.toCharArray();
		char[] keyList = myKey.toCharArray();
		
		int maxCount = myKey.length();
		int i = 0;
		
		for (Character code: codeList) {
			
			int key = Character.getNumericValue(keyList[i]);
			
				if (key % 2 == 0) {
					
					int res = code + key;
					result.append((char)res);
					
				}
				
				else {
					
					int res = code-key;
					result.append((char)(res));
				}
				
				i++;
				
				if (i == maxCount) {
					i = 0;
				}
		}
		return result.toString();
	}
	
	public String decryptMessage (String messageToDecrypt) {
		
		StringBuilder result = new StringBuilder();
		
		char[] codeList = messageToDecrypt.toCharArray();
		char[] keyList = myKey.toCharArray();
		
		int maxCount = myKey.length();
		int i = 0;
		
		for (Character code: codeList) {
			
			int key = Character.getNumericValue(keyList[i]);
			
				if (key % 2 == 0) {
					
					int res = code - key;
					result.append((char)res);
					
				}
				
				else {
					
					int res = code + key;
					result.append((char)(res));
				}
				
				i++;
				
				if (i == maxCount) {
					i = 0;
				}
		}
		return result.toString();
		
	}
	
}
	

