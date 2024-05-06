package com.temelio.apis.users;

import com.temelio.apis.config.UserModelUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.stereotype.Service
public class UserService implements UserDetailsService {

//    private final UserUtils userUtils;
//
//    public UserService(UserUtils userUtils) {
//        this.userUtils = userUtils;
//    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel createUser(UserModel newUserData) {
        newUserData.setPassword(passwordEncoder.encode(newUserData.getPassword()));
        return userRepository.save(newUserData);
    }

    public UserModel updateUser(Long id, UserModel newUserData) {
        Optional<UserModel> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            UserModel updatedUser = existingUser.get();

            if (newUserData.getName() != null) updatedUser.setName(updatedUser.getName());
            if (newUserData.getEmail() != null) updatedUser.setEmail(updatedUser.getEmail());
            if (newUserData.getPhone() != null) updatedUser.setPhone(updatedUser.getPhone());

            userRepository.save(updatedUser);
            return updatedUser;
        }

        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> userInfo = userRepository.findByName(username);
        return userInfo.map(UserModelUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
