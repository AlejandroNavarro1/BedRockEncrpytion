package am;

public class NavarrianCrypt {
	
	private String[] alpha = {"A","a","B","b","C","c", "D","d", "E", "e","F","f","G","g","H","h","I","i","J","j","K","k","L","l" 
			,"M", "m","N", "n","O", "o","P", "p","Q", "q","R","r","S","s","T","t","U","u","V","v","W","w","X","x","Y","y","Z", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "#", " ", ".", "/"
			,"@", "$", "%", "^", "-", "_", "+", "=", ")", "(", "*", "&","\"", "?", "/", "!", "|", "{", "}"};

	private int key = 0;

	public NavarrianCrypt(){
		
	}
	
	public NavarrianCrypt(int mkey){
		key = mkey;
	}
	
	public int hashKey(String keyStr){
		int keyInt = 0;
		
		for(int let = 0; let < keyStr.length(); let++) {
			String letter = keyStr.substring(let, let+1);
			int count =0;
			for(int x = 0; x < alpha.length; x++){
				if(alpha[x].equals(letter)){
					keyInt += x;
					x = alpha.length;
				}
			}
		}
		if(keyInt > 200){
			keyInt = keyInt / 10;
		}else if(keyInt > 1000){
			keyInt = keyInt / 100;
		}
		
		return keyInt;
	}
	
	public String Encrypt(String s) {
		
		String encrpytString = "";
		int y = 0;
		
		for(int let = 0; let < s.length(); let++) {
			
			String point = s.substring(let, let + 1);
			int x = 0;
			
			for(String letter : alpha) {
				
				if(point.equals(letter)) {
					
					int len = encrpytString.equals("") ? 0 : encrpytString.split(",").length;
					y = y < key ? y++ : 0;
					
					encrpytString += (len + x + y + key) + ",";
					y++;
					
				}
				x++;
			}
		}
		return encrpytString;
	}
	
	public String Decrypt(String s) {
		
		String encrpytString = "";
		String[] stringLine = s.split(",");
		int strLen = 0;
		int y = 0;
		
		for(String str : stringLine) {
		
			int num = Integer.parseInt(str);
			y = y < key ? y++ : 0;
		
			encrpytString += alpha[num - strLen - y - key];
			y++;
			
			strLen++;
		
		}
		return encrpytString;
	}
	
	public void setKey(int mkey){
		key = mkey;
	}
}
