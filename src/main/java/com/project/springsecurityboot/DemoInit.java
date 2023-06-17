package com.project.springsecurityboot;

import com.project.springsecurityboot.models.Role;
import com.project.springsecurityboot.models.User;
import com.project.springsecurityboot.service.RoleServiceImpl;
import com.project.springsecurityboot.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DemoInit {

    private final RoleServiceImpl roleService;
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public DemoInit(RoleServiceImpl roleService, UserDetailsServiceImpl userDetailsService) {
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
    }

    private final Role roleAdmin = new Role("ROLE_ADMIN");
    private final Role roleUser = new Role("ROLE_USER");

    public Set<Role> setAdminRole() {
        Set<Role> adminRole = new HashSet<>();
        adminRole.add(roleAdmin);
        return adminRole;
    }

    public Set<Role> setRoleUser() {
        Set<Role> userRole = new HashSet<>();
        userRole.add(roleUser);
        return userRole;
    }

    @Transactional
    @Bean
    public void addInitUsers() {
        roleService.save(roleAdmin);
        roleService.save(roleUser);

        User admin = new User("Petya", "Ivanov", (byte) 27, "admin@mail.ru",
                "$2a$12$x2jGJqzzWh7mp1c4bNW/MePnpkb5Q.garsy0PN9cmK3Ja0UQ3N432", "Admin",
                setAdminRole()); // пароль: admin
        User user = new User("Vasya", "Petrov", (byte) 17, "user@mail.ru",
                "$2a$12$AyaqSH0/6oYd6yBC2sKfgutia.m2Cz//roNJ0scMTDYmBEba8.87q", "User",
                setRoleUser()); // пароль: user

        userDetailsService.saveUser(admin);
        userDetailsService.saveUser(user);
    }
}
