package br.com.marcenaria.api_marcenaria;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Teste {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("maria123");
        System.out.println(hash);
    }

}
