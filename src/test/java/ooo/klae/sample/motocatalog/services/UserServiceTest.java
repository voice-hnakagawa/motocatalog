package ooo.klae.sample.motocatalog.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService service;
   
    @DisplayName("ユーザ取得成功")
    @Test
    void test01() throws Exception {
        UserDetails user = service.loadUserByUsername("admin");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        assertThat(encoder.matches("password", user.getPassword())).isTrue();
    }
}
