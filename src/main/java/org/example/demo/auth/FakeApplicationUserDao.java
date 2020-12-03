package org.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.example.demo.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    @Override
    public ApplicationUser addUser(ApplicationUser addedUser) {
        return null;
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser ("anna",
                        passwordEncoder.encode("pass"),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser ("steve",
                        passwordEncoder.encode("pass"),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser ("tom",
                        passwordEncoder.encode("pass"),
                        true,
                        true,
                        true,
                        true)
        );
    }
}
