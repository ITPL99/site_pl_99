package com.example.site_pl_99.boot;

import com.example.site_pl_99.entity.RoleEntity;
import com.example.site_pl_99.entity.UserEntity;
import com.example.site_pl_99.repository.RoleRepository;
import com.example.site_pl_99.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<RoleEntity> roleEntitySet = roleRepository.findAll();

        if(userRepository.findByUsername("admin").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                 .setPassword("$2a$04$gkj/BP6UzLvrwsa/gZRuru/U/zjERZmb4a8P5SkLqPKnliOOD2z2G")
                 .setUsername("admin")
                 .setRoles(roleEntitySet)
                 .setEmail("admin@admin.com");
            userRepository.save(admin);
        }
    }
}
