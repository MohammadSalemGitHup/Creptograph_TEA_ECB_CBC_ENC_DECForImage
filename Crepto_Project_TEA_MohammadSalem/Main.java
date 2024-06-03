import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int[] key = new int[4];
        int[] IV = new int[2];

	

        Scanner sc = new Scanner(System.in);
        int choice;
		boolean keyCheck = false;
		boolean IVCheck = false;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Encrypt and Decrypt the image with TEA ECB");
            System.out.println("2. Encrypt and Decrypt the image with TEA CBC");
            System.out.println("3. To Stop programme exit..");
            System.out.print("Enter your choice: ");
			
			choice = sc.nextInt();
			
                switch (choice) {
                    case 1:
                        if (!keyCheck) { // to tack key once time in case 1
                            System.out.println("Enter 4 integers for the key:");
                            key = Functions.readKey_IV(4);
                            keyCheck = true;
                        } else {
                            System.out.println("We already have a key...");
                        }
                        // Process ENC and DEC for the Aqsa1.bmp image in ECB mode
                        Functions.ENC_DEC_img_ECB(key, 1);
                        Functions.ENC_DEC_img_ECB(key, 2);
                        System.out.println("Images are encrypted/decrypted by ECB mode of operation");
                        System.out.println("The result is in ENC_EncOut file at channel");
                        System.out.println("The result is in ECB_DEC file as Bob shown it");
                        break;
////////////////////////////////////////////////////////////////
                    case 2:
                        if (!keyCheck) { // to tack key once time in case 2
                            System.out.println("Enter 4 integers for the key:");
                            key = Functions.readKey_IV(4);
                            keyCheck = true;
                        } else {
                            System.out.println("We already have a key... don't enter choice 2 again");
                        }

                        // Tack IV once a Time
                        if (!IVCheck) {
							System.out.println("Enter 2 integers for the IV:");
							IV = Functions.readKey_IV(2);
						    
                            IVCheck = true;
                        } else {
                            System.out.println("We already have an IV.");
                        }

                        // Process ENC and DEC for the Aqsa1.bmp image in CBC mode
                        Functions.ENC_DEC_img_CBC(key, 1, IV);
                        Functions.ENC_DEC_img_CBC(key, 2, IV);
                        System.out.println("Images are encrypted/decrypted by CBC mode of operation");
                        System.out.println("The result is in ENC_EncOut file at channel");
                        System.out.println("The result is in CBC_DEC file as Bob shown it");
                        break;

                    case 3:
                        System.out.println("Exiting the program...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                        break;
                } 
        } while (choice != 3);
		sc.close();   
    }
}
