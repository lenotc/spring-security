package com.sec.encoder;

import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class SSCM {
    void test() {
        String salt = KeyGenerators.string().generateKey();
        String password = "secret";
        String valueToEncrypt = "HELLO";

        BytesEncryptor e = Encryptors.standard(password, salt); // 256-byte EAS
        byte[] encrypted = e.encrypt(valueToEncrypt.getBytes());
        byte[] decrypted = e.decrypt(encrypted);

        BytesEncryptor e2 = Encryptors.stronger(password, salt); // 256-bit Galois/Counter Mode

        TextEncryptor textEncryptor = Encryptors.noOpText();
        String encrypted2 = textEncryptor.encrypt(valueToEncrypt);

        TextEncryptor e3 = Encryptors.text(password, salt);
        String encrypt = e3.encrypt(valueToEncrypt);
        String decrypt = e3.decrypt(encrypt);
    }
}
