package sa.account.management.service;

import sa.account.management.model.Role;
import sa.account.management.model.User;
import sa.account.management.model.enums.RoleName;
import sa.account.management.repository.RoleRepository;
import sa.account.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role not found!"));
        HashSet<Role> userRoles = new HashSet<>(Collections.singletonList(userRole));
        user.setRoles(userRoles);
        return userRepository.save(user);
    }
}
