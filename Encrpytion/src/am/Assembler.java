package am;

public class Assembler {
	
	
	
	public Assembler(){

	}
	
	public String Encrypt(String keys, String text){
		NavarrianCrypt nava = new NavarrianCrypt();
		NajahCrypt najah;
		NajahCrypt2 najahTwo = new NajahCrypt2();
		NajahCrypt3 najah3 = new NajahCrypt3(keys);
		String encryptStr = "";
		int key = 0;
		key = nava.hashKey(keys);
		
		nava.setKey(key);
		encryptStr = nava.Encrypt(text);
		
		najah = new NajahCrypt(keys, "hello");
		encryptStr = najah.Ncrypt(encryptStr);
		
		encryptStr = najahTwo.encryptString(encryptStr, key);
		
		return encryptStr;
	}
	
	public String Decrypt(String keys, String text ){
		NavarrianCrypt nava = new NavarrianCrypt();
		NajahCrypt najah;
		NajahCrypt2 najahTwo = new NajahCrypt2();
		String decryptStr = "";
		int key = nava.hashKey(keys);
		nava.setKey(key);
		
		decryptStr = najahTwo.decryptString(text, key);
		
		najah = new NajahCrypt(keys, "hello");
		decryptStr = najah.Dcrypt(decryptStr);
		
		decryptStr = nava.Decrypt(decryptStr);

		
		return decryptStr;
		
	}

}
