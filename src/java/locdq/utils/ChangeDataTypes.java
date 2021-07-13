/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package locdq.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author test
 */
public class ChangeDataTypes {
    public static String bytesToHex(String tmp) throws NoSuchAlgorithmException{
        MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] encodedhash=digest.digest(tmp.getBytes(StandardCharsets.UTF_8));
        
        StringBuilder hexString =new StringBuilder();
        for (int i=0;i<encodedhash.length;i++){
            String hex=Integer.toHexString(0xff&encodedhash[i]);
            if(hex.length()==1){
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public static void main(String[] args) {
        try {
            String result=bytesToHex("123456567");
            System.out.println("REULT: "+result);
            System.out.println("Length: "+result.length());
            System.out.println("==>"+Integer.toHexString(0xff&12));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ChangeDataTypes.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
