/*
 * To change this license header, choose License Hea1ders in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Common;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author ADMIN
 */
public class PasswordProcessingController {

    public String encoding(String password) {
        byte[] data = password.getBytes(StandardCharsets.UTF_8);
        String result = Base64.getEncoder().encodeToString(data);
        return result;
    }

    public String decoding(String encodingPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodingPassword);
        String result = new String(decodedBytes);
        return result;
    }
    
    public static void main(String[] args) {
        PasswordProcessingController pp = new PasswordProcessingController();
        System.out.println(pp.encoding("68686868"));
        System.out.println(pp.decoding("YWJjZDEyMzQ="));
        
    }
}
