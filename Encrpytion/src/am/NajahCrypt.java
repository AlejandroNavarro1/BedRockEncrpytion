package am;

public class NajahCrypt {
	
	private String myKey1;
	private String myKey2;
	
	public NajahCrypt (String key1, String key2){
		
		myKey1 = key1;
		myKey2 = key2;
		
	}
	
	
	
//ENCRYPTION	
	public String Ncrypt (String encryptMessage) {
		
		String messageArray[] = new String[encryptMessage.length()];
		
		for (int i = 0; i < encryptMessage.length(); i ++) {
			messageArray[i] = encryptMessage.substring(i, i+1);
		}
		
		for (int j = 0; j < messageArray.length; j++) {
			
			if (messageArray[j].equals(myKey1)) {
				
				messageArray[j] = myKey2;
				
			}
		}
		
		String finalEncryption = "";
		
		for (String s : messageArray) {
			finalEncryption += s;
		}
		
		return finalEncryption;
		
	}
//DECRYPTION	
	public String Dcrypt (String messageToDecrypt) {
		
		String messageArray[] = new String[messageToDecrypt.length()];
		
		for (int i = 0; i < messageToDecrypt.length(); i ++) {
			messageArray[i] = messageToDecrypt.substring(i, i+1);
		}
		
		for (int j = 0; j < messageArray.length; j++) {
			
			if (messageArray[j] .equals(myKey2)) {
				
				messageArray[j] = myKey1;
				
			}
		}
		
		String finalEncryption = "";
		
		for (String s : messageArray) {
			finalEncryption += s;
		}
		
		return finalEncryption;
		
	}
	
}
