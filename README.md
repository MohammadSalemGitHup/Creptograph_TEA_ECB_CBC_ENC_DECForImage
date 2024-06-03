In our assignment we implement the TEA algorithm with two mode of operation 
ECB (Electronic Code Bock)
CBC (Cipher text chaining)
I have two classes for ECB,CBC the first one is TEA_CBC.java and the secand one is TEA_CBC.java
Based on the attached document for the project, I worked to employ the method according to the attached encryption and decryption algorithm,
where the length of the key was 128 bits, and the data block 64 bit.

in TEA_ECB.java as a primitive formate
public class TEA_ECB {
// attribute
//constructes for key as array of 4 integer
encrypt(int[] p){
    //encrypt function for ECB  tacke palin text return cipher text
//  after mack proccssing by xoring and shift 
}
decrypt(int[] c){
// decreption function for ECB   tacke  cipher text return palin text 
//  after mack proccssing by xoring and shift 
}
}
//////////////////////
in TEA_CBC.java as a primitive formate
public class TEA_CBC {
// attribute
//constructes for key as array of 4 integer
encrypt(int[] p, int[] prev){
// tack plain text the the previos plain text and return the cipher text after mack proccssing
// by xoring and shift
}
/////
 decrypt(int[] c, int[] prev){
// decreption function for CBC   tacke  cipher text return palin text 
 ////  after mack proccssing by xoring and shift 
 }
}
/////////Also,
Functions class with static attribute(parameters) 
//empty constructe
readKey_IV(){
// method to read from user 
}
ENC_DEC_img_ECB(int[] key, int mode){
    // read image and mack ENC/DEC for the ECB (data in as a image)
}
ENC_DEC_img_CBC(int[] key, int mode, int[] IV){
    // chaining consept  with IV as a previos for the first data in value (IV as a input from user)
    // read image and mack ENC/DEC for the CBC (data in as a image)
}
////////////////////////////////////////
How to use my programe ? How to execute my implementatio?
Answer:
it is very simple
accourding of choice option from the case statment the operation is completly done
 System.out.println("1. Encrypt and Decrypt the image with TEA ECB");
            System.out.println("2. Encrypt and Decrypt the image with TEA CBC");
            System.out.println("3. To Stop programme exit..");
            System.out.print("Enter your choice: ");

if you enter 1 , look at images file cipher text image and plain text image agin .... ECB mode

if you enter 2 , look at images file cipher text image and plain text image agin .... CBC mode  
if you enter 3 stop 
these choises in loop          
