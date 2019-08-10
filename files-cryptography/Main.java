import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public final class Main {

	private static void writeFile(ImgEnc enc, String file) throws Exception {
		// read the file
		//BufferedImage img = ImageIO.read(file);

		// converting to bytes
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//ImageIO.write(img, "jpeg", baos);

		// converting to string
		//String data = new String(baos.toByteArray());

		// read the file
		Path path = Paths.get(file);
		byte[] data = Files.readAllBytes(path);

		// encrypting
		byte[] textEncrypted = enc.encrypt(data);

		// create output file
		FileOutputStream fos = new FileOutputStream(file+".crypt");
		fos.write(textEncrypted);
	}

	private static void readFile(ImgEnc dec, String file) throws Exception {
		// read the file
		Path path = Paths.get(file);
		String[] ext = file.split("\\.");
		byte[] data = Files.readAllBytes(path);

		// decrypting
		byte[] textDecrypted = dec.decrypt(data);

		// create output file
		//BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(textDecrypted));
		//ImageIO.write(img, "jpeg", new File("dec-wallpaper.jpeg"));
		FileOutputStream fos = new FileOutputStream("dec-"+ext[0]+"."+ext[ext.length-2]);
		fos.write(textDecrypted);
	}

	public static void main(String[] args) {
		try {
			switch(args[0]) {
				case "enc":
					if(args[1].length == 16) {
						ImgEnc enc = new ImgEnc(args[1]);
						Main.writeFile(enc, args[2]);
					}
					else {
						System.out.println("key length < 16 bytes");
					}	
					break;
				case "dec":
					if(args[1].length == 16) {
						ImgEnc dec = new ImgEnc(args[1]);
						Main.readFile(dec, args[2]);
					}
					else {
						System.out.println("key length < 16 bytes");
					}
					break;
				default:
					System.out.println("invalid");	
			}
		}	
		catch(Exception err) {
			err.printStackTrace();
		}	
	}
}