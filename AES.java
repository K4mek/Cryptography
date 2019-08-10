import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;

public final class AES {

	// all need be converted in bytes
	private byte[] encrypt(String text, String pass) throws Exception {
		String iv = "AAAAAAAAAAAAAAAA";
		System.out.println("key size: "+iv.length());

		// crete AES cipher
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

		// create key
		SecretKeySpec key = new SecretKeySpec(pass.getBytes("UTF-8"), "AES");

		// initializing encryption mode
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv.getBytes("UTF-8")));
		return cipher.doFinal(text.getBytes("UTF-8"));
	}

	private byte[] decrypt(String textEncrypted, String pass) throws Exception {
		String iv = "AAAAAAAAAAAAAAAA";

		// create AES cipher
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");

		// crete key
		SecretKeySpec key = new SecretKeySpec(pass.getBytes(), "AES");

		// initializing decrypting mode
		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv.getBytes()));

		return cipher.doFinal(textEncrypted.getBytes());
	}

	public static void main(String[] args) {
		String textPlain = "hello world! I am a robot";
		String pass = "duckduckduckduck";

		AES aes = new AES();

		System.out.println("key: "+pass);
		System.out.println("text plain: "+textPlain);
		try {
			String textEncrypted = new String(aes.encrypt(textPlain, pass));
			System.out.println("text encrypted: "+textEncrypted);
			System.out.println("text decrypted: "+new String(aes.decrypt(textEncrypted, pass)));
		}
		catch(Exception error) {
			error.printStackTrace();
		}
	}
}