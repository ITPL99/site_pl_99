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
                 .setPassword("admin")
                 .setUsername("admin")
                 .setRoleEntityList(roleEntitySet);
            userRepository.save(admin);
        }

        if(userRepository.findByUsername("guest").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                    .setPassword("guest")
                    .setUsername("guest")
                    .setRoleEntityList(roleEntitySet.stream().filter(x->x.getTitle().equals("GUEST")).toList());
            userRepository.save(admin);
        }

        if(userRepository.findByUsername("user").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                    .setPassword("user")
                    .setUsername("user")
                    .setRoleEntityList(roleEntitySet.stream().filter(x->x.getTitle().equals("USER")).toList());
            userRepository.save(admin);
        }

        if(userRepository.findByUsername("user-guest").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                    .setPassword("user-guest")
                    .setUsername("user-guest")
                    .setRoleEntityList(
                            roleEntitySet.stream()
                                    .filter(x->x.getTitle().equals("USER") || x.getTitle().equals("GUEST") )
                                    .toList());
            userRepository.save(admin);
        }
        if(userRepository.findByUsername("admin-guest").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                    .setPassword("admin-guest")
                    .setUsername("admin-guest")
                    .setRoleEntityList(
                            roleEntitySet.stream()
                                    .filter(x->x.getTitle().equals("ADMIN") || x.getTitle().equals("GUEST") )
                                    .toList());
            userRepository.save(admin);
        }
        if(userRepository.findByUsername("admin-user").isEmpty()) {
            UserEntity admin = new UserEntity();
            admin
                    .setPassword("admin-user")
                    .setUsername("admin-user")
                    .setRoleEntityList(
                            roleEntitySet.stream()
                                    .filter(x->x.getTitle().equals("ADMIN") || x.getTitle().equals("USER") )
                                    .toList());
            userRepository.save(admin);
        }
    }
}
