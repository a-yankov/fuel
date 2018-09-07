package com.example.fuel_app.services;

import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.UserRole;
import com.example.fuel_app.models.RegistrationModel;
import com.example.fuel_app.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel, User.class);

        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        UserRole role = this.roleService.findRoleByName("ROLE_USER");
        Set<UserRole> roles = new HashSet<>();
        roles.add(role);
        user.setAuthorities(roles);
        user.setPassword(encryptedPassword);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        this.userRepository.save(user);
    }
    @Transactional
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findOneByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        return user;
    }

    public List<User> getAllUsers(){
        return (List<User>) this.userRepository.findAll();
    }

    public User findUserById(int id){
        return this.userRepository.findById(id);
    }
}
