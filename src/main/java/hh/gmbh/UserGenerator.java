package hh.gmbh;

import hh.gmbh.db.entities.UserEntity;
import hh.gmbh.db.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class UserGenerator implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        userRepository.save(UserEntity.builder()
                        .username("user")
                        .password(passwordEncoder.encode("password"))
                        .roles(Arrays.asList( "ROLE_USER"))
                        .build()
        );

        userRepository.save(UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("password"))
                        .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                        .build()
        );

        log.debug("printing all users...");
        userRepository.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
