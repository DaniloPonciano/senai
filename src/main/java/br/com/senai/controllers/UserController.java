package br.com.senai.controllers;

import br.com.senai.models.Users;
import br.com.senai.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)

    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/createUser",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users createUser(@RequestBody Users user) {
        Users newUsers = new Users();
        newUsers.setUsername(user.getUsername());
        newUsers.setPassword(user.getPassword());

        return userRepository.save(newUsers);
    }

    @PutMapping(value = "/updateUser",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users updateUser(@RequestBody Users user){
        Users getUser = userRepository.findById(user.getId()).orElseThrow();
        Users updateUsers = new Users();

        updateUsers.setId(user.getId());
        updateUsers.setUsername(user.getUsername());
        updateUsers.setPassword(user.getPassword());

        return userRepository.save(updateUsers);

    }

    @DeleteMapping(value="/deleteUser/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Users deleteUsers(@PathVariable Long id){
        Users getUser = userRepository.findById(id).orElseThrow();
        userRepository.delete(getUser);
        return getUser;
    }
}
