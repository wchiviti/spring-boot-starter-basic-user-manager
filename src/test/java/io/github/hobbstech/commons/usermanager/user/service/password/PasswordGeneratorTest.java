package io.github.hobbstech.commons.usermanager.user.service.password;


import lombok.val;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorTest {

    public static void main(String[] args) {
        new PasswordGeneratorTest().generatePassword();
    }

    private void generatePassword(){
        val rawPassword = "%$Pass123";
        val encrypted = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println(String.format("---> Encrypted password : %s", encrypted));
    }

}