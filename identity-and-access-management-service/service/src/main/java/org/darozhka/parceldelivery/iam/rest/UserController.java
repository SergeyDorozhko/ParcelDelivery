package org.darozhka.parceldelivery.iam.rest;

import org.darozhka.parceldelivery.iam.dto.UserCreateDto;
import org.darozhka.parceldelivery.iam.dto.UserDto;
import org.darozhka.parceldelivery.iam.dto.UserRegistrationDto;
import org.darozhka.parceldelivery.iam.dto.UserUpdateDto;
import org.darozhka.parceldelivery.iam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author S.Darozhka
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}")
    public UserDto getUserById(@PathVariable(value = "userId") Integer userId) {
        return UserDto.from(userService.getById(userId));
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateDto request) {
        return UserDto.from(
                userService.save(request.toUser()));
    }

    @PostMapping("/sign-up")
    public UserDto registerUser(@RequestBody UserRegistrationDto request) {
        return UserDto.from(
                userService.registerUser(request.toUser()));
    }

    @PutMapping(value = "/{userId}")
    public UserDto updateUser(@PathVariable(value = "userId") Integer userId,
                              @RequestBody UserUpdateDto request) {
        return UserDto.from(
                userService.save(request.toUser(userId)));
    }

}
