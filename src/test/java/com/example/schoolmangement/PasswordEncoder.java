package com.example.schoolmangement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
     public static void main(String[] args) {
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         String raw = "distinct";
         String encodedPassword = encoder.encode(raw);

         System.out.println(encodedPassword);
    }
}
