/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.criptografia;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author Daniel Lanna
 */
public class Hash {

    public String hash(byte[] value) {

        String senha = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(value);
            BigInteger hash = new BigInteger(1, md.digest());

            senha = new String(hash.toString(16));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return senha;
    }
}
