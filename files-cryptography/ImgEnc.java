import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// all to bytes
final class ImgEnc {

	final String IV = "EEEEEEEEEEEEEEEE";

	private IvParameterSpec ips;
	private Cipher cipher;
	private String pass;
	private SecretKeySpec key;

	{
		ips = new IvParameterSpec(IV.getBytes("UTF-8"));
		// create AES algorithm
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
	}

	public ImgEnc(String pass) throws Exception {
		this.pass = pass;

		// crete key
		key = new SecretKeySpec(pass.getBytes("UTF-8"), "AES");
	}

	public byte[] encrypt(byte[] text) {
		try {
			// init encrypting
			cipher.init(Cipher.ENCRYPT_MODE, key, ips);
			return cipher.doFinal(text);
		}
		catch(Exception err) { err.printStackTrace(); }
		return null;
	}

	public byte[] decrypt(byte[] textEncrypted) {
		try {
			// init decrypting
			cipher.init(Cipher.DECRYPT_MODE, key, ips);
			return cipher.doFinal(textEncrypted);
		}
		catch(Exception err) {
			err.printStackTrace();
		}
		return null;
	}
}