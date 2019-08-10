import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;

public final class DES {

	private DES() {
		try {
			// create key
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			SecretKey keyDES = keyGenerator.generateKey();

			// create cipher
			Cipher cipherDES = Cipher.getInstance("DES/ECB/PKCS5padding");

			// text plain
			String textPlain = "today is wednesday";
			byte[] textByte = textPlain.getBytes();
			System.out.println("text plain: "+textPlain);
			System.out.println("text [byte format]: "+textByte);

			// init process to encrypting
			cipherDES.init(Cipher.ENCRYPT_MODE, keyDES);

			// encrypting
			byte[] textEncrypted = cipherDES.doFinal(textByte);
			System.out.println("text encrypted: "+new String(textEncrypted));

			// init process to decrypting
			cipherDES.init(Cipher.DECRYPT_MODE, keyDES);

			// decrypting
			byte[] textDecrypted = cipherDES.doFinal(textEncrypted);
			System.out.println("text decrypted: "+new String(textDecrypted));

		}
		catch(IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException
		| NoSuchPaddingException | BadPaddingException error) {
			error.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DES();
	}
}