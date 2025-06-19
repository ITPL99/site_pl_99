package com.example.site_pl_99.boot;

import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.enums.Active;
import com.example.site_pl_99.repository.RoleRepository;
import com.example.site_pl_99.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


// TODO: На продакшен убрать удалить данный класс
@Profile({"develop","local","test"})
@Component
public class ApplicationBootInitStartParam implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationBootInitStartParam(
            UserRepository userRepository,
            RoleRepository roleRepository, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        List<RoleEntity> roleEntitySet = roleRepository.findAll();

        if(userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
//                    .setActive(Active.NEW)
                 .setPassword(passwordEncoder.encode("admin"))
                 .setUsername("admin")
                 .setRoles(roleEntitySet)
                 .setEmail("admin@admin.com");
            userRepository.save(admin);
        }
    }
}
