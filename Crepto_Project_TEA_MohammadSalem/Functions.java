import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Functions {

	static DataInputStream dataIn;
	static 	DataOutputStream dataOut;
	static int[] image = new int[2];
	static int cipher[] = new int[2];


    public Functions(){ // empty constructor

    }

    public static int[] readKey_IV(int numOfDigits){
		Scanner scan = new Scanner(System.in);
		int[] key = new int[4];
		int count = 0;
		while (count < numOfDigits) {
			try {
				System.out.print("Enter integer " + (count + 1) + ": ");
				key[count] = scan.nextInt();
				System.err.println(key[count]);
				count++;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter an integer.");
				scan.next(); // Clear the invalid input
			}  // dont close the scanner becuse you need it in IV clalling
		}
		return key;
	}

    ////////////////////////////////////////////////////////////////////////////////////////

	public static void ENC_DEC_img_ECB(int[] key, int mode) throws IOException {
		
		TEA_ECB ecb = new TEA_ECB(key);
		
	    // mode = 1 for encreption
		// mode = 2 for decreption
		if(mode == 1) {
			dataIn = new DataInputStream(new FileInputStream("images\\Aqsa1.bmp"));
			dataOut = new DataOutputStream(new FileOutputStream("images\\ECB_ENC.bmp"));
		}
		else if(mode == 2) {
			dataIn = new DataInputStream(new FileInputStream("images\\ECB_ENC.bmp"));
			dataOut = new DataOutputStream(new FileOutputStream("images\\ECB_DEC.bmp"));
		}
		// first 10 plain text without any encreption or decreption
		for(int i=0;i<10;i++){
			if(dataIn.available() > 0){
				image[0] = dataIn.readInt();
				image[1] = dataIn.readInt();
				dataOut.writeInt(image[0]);
				dataOut.writeInt(image[1]);
			}
		}

		boolean checkLast = true;

		while(dataIn.available() > 0){
			try{
				image[0] = dataIn.readInt();
				image[1] = dataIn.readInt();	
				if(mode == 1) {
					cipher = ecb.encrypt(image);
				} 
				else if(mode == 2) {
					cipher = ecb.decrypt(image);
				} 
				
				dataOut.writeInt(cipher[0]);
				dataOut.writeInt(cipher[1]);
			
				
				checkLast = false; // each time rise a flag if image file end 
			}
			////////////
			catch(EOFException e){
				if(!checkLast){
					dataOut.writeInt(image[0]);
					dataOut.writeInt(image[1]);
				}
				else
					dataOut.writeInt(image[0]);
			}

		}
		dataIn.close();
		dataOut.close();

	}    


	//////////////////////////////////////////////////////////////////////////////////////
	public static void ENC_DEC_img_CBC(int[] key, int mode, int[] IV) throws IOException {

		TEA_CBC cbc = new TEA_CBC(key);
		
		if(mode == 1) {
			dataIn = new DataInputStream(new FileInputStream("images\\Aqsa1.bmp"));
			dataOut = new DataOutputStream(new FileOutputStream("images\\CBC_ENCOut.bmp"));
		}else if(mode == 2) {
			dataIn = new DataInputStream(new FileInputStream("images\\CBC_ENCOut.bmp"));
			dataOut = new DataOutputStream(new FileOutputStream("images\\CBC_DEC.bmp"));
		}

		// first 10 plain text without any encreption or decreption
		for(int i=0;i<10;i++){
			if(dataIn.available() > 0){
				image[0] = dataIn.readInt();
				image[1] = dataIn.readInt();
				dataOut.writeInt(image[0]);
				dataOut.writeInt(image[1]);
			}
		}

		boolean checkLast = true;
		boolean firstTime = true;
		int[] copyImg = new int[2];

		while(dataIn.available() > 0){
			try{
				image[0] = dataIn.readInt();
				checkLast = true;
				image[1] = dataIn.readInt();	
			
				if (mode == 1) {
					if(firstTime){	
						cipher = cbc.encrypt(image, IV);
						firstTime = false;	
					}
					else
						cipher = cbc.encrypt(image, cipher);
					
				} 
				else {
					if(firstTime){
						cipher = cbc.decrypt(image,IV);
						firstTime = false;	
					}
					else
						cipher = cbc.decrypt(image,copyImg); // to approve chaining teqneeq		
				}
				dataOut.writeInt(cipher[0]);
				dataOut.writeInt(cipher[1]);
				
				copyImg[0] = image[0];
				copyImg[1] = image[1];
				
				checkLast = false; // each time rise a flag if image file end 
			}
			////////////
			catch(EOFException e){
				if(!checkLast){
					dataOut.writeInt(image[0]);
					dataOut.writeInt(image[1]);
				}else
					dataOut.writeInt(image[0]);
			}

		}
		dataIn.close();
		dataOut.close();

	}
}
