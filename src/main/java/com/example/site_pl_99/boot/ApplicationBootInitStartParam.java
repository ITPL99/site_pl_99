package com.example.site_pl_99.boot;

import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.repository.RoleRepository;
import com.example.site_pl_99.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"develop","local","test"})
@Component
public class ApplicationBootInitStartParam implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ApplicationBootInitStartParam(
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                 .setPassword("admin")
                 .setUsername("admin")
                 .setRoleEntityList(roleRepository.findAll());
            userRepository.save(admin);
        }
    }
}
