package dzumi.app.demo.demoencrypteddatabases.main;

import android.provider.Settings;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
    private static byte[] key;
    private static String cypherType = "AES";
   
    public Encryptor(String localPass) {

        // save the encoded key for future use
        // - note that this keeps it in memory, and is not strictly safe
        key = encode(localPass.getBytes()).getBytes();
        String keyCopy = new String(key);
        while(keyCopy.length() < 16)
            keyCopy = keyCopy + keyCopy;
       
        byte keyA[] = keyCopy.getBytes();
        if(keyA.length > 16)
        {
            key = new byte[16];
            System.arraycopy(keyA, 0, key, 0, 16);
        }
    }

    private String encode(byte [] s) {
       
        return Base64.encodeToString(s, Base64.URL_SAFE);
    }
   
    private byte[] decode(byte[] s) {
        return Base64.decode(s, Base64.URL_SAFE);
    }


    public byte[] getKey() {
        // return a copy of the key.
        return key.clone();
    }
   
    public String encrypt(String toEncrypt) throws Exception {
       
            //Create your Secret Key Spec, which defines the key transformations
            SecretKeySpec skeySpec = new SecretKeySpec(key, cypherType);

            //Get the cipher
            Cipher cipher = Cipher.getInstance(cypherType);

            //Initialize the cipher
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            //Encrypt the string into bytes
            byte[ ] encryptedBytes = cipher.doFinal(toEncrypt.getBytes());

            //Convert the encrypted bytes back into a string
            String encrypted = encode(encryptedBytes);

            return encrypted;
    }
   
    public String decrypt(String encryptedText) throws Exception {
       
        // Get the secret key spec
            SecretKeySpec skeySpec = new SecretKeySpec(key, cypherType);
       
            // create an AES Cipher
            Cipher cipher = Cipher.getInstance(cypherType);
       
            // Initialize it for decryption
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
       
            // Get the decoded bytes
            byte[] toDecrypt = decode(encryptedText.getBytes());
       
            // And finally, do the decryption.
            byte[] clearText = cipher.doFinal(toDecrypt);

            return new String(clearText);
    }
}