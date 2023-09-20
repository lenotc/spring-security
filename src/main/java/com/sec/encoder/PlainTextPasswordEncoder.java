package com.sec.encoder;

import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PlainTextPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    void test() throws NoSuchAlgorithmException {
        PasswordEncoder p = new Pbkdf2PasswordEncoder(
                "secret", 16, 310000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        PasswordEncoder p1 = new BCryptPasswordEncoder();
        PasswordEncoder p2 = new BCryptPasswordEncoder(4);
        SecureRandom s = SecureRandom.getInstanceStrong();
        PasswordEncoder p3 = new BCryptPasswordEncoder(4, s);

        PasswordEncoder p4 = new SCryptPasswordEncoder(16384, 8, 1, 32, 64);

        StringKeyGenerator keyGenerator = KeyGenerators.string();
        String salt = keyGenerator.generateKey(); // 8-byte


        BytesKeyGenerator bytesKeyGenerator = KeyGenerators.secureRandom(16); // 8 default
        byte[] key = bytesKeyGenerator.generateKey();
        int keyLength = bytesKeyGenerator.getKeyLength();


        BytesKeyGenerator same = KeyGenerators.shared(16);
        byte[] key1 = same.generateKey();
        byte[] key2 = same.generateKey();

    }
}
