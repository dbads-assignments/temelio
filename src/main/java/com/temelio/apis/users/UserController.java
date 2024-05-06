package com.temelio.apis.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<Optional<UserModel>> getUserById(@PathVariable Long id) {
        try {
            Optional<UserModel> user = userService.getUserById(id);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel newuserData) {
        try {
            UserModel user = userService.createUser(newuserData);

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{id}")
    public ResponseEntity<UserModel> updateuser(@PathVariable Long id, @RequestBody UserModel newuserData) {
        try {
            UserModel updatedUser = userService.updateUser(id, newuserData);
            if (updatedUser != null) return new ResponseEntity<>(updatedUser, HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            // logging for exception into error.logs

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteuser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            // logging for exception into error.logs
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
