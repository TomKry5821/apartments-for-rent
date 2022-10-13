package pl.polsl.krypczyk.apartmentsforrent.userservice.application.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.polsl.krypczyk.apartmentsforrent.userservice.application.user.request.CreateUserRequest;
import pl.polsl.krypczyk.apartmentsforrent.userservice.application.user.request.UserLoginRequest;
import pl.polsl.krypczyk.apartmentsforrent.userservice.application.user.response.CreateUserResponse;
import pl.polsl.krypczyk.apartmentsforrent.userservice.application.user.response.LoginUserResponse;
import pl.polsl.krypczyk.apartmentsforrent.userservice.domain.authorization.AuthorizationService;
import pl.polsl.krypczyk.apartmentsforrent.userservice.domain.authorization.exception.BadCredentialsException;
import pl.polsl.krypczyk.apartmentsforrent.userservice.domain.authorization.exception.UnauthorizedUserException;
import pl.polsl.krypczyk.apartmentsforrent.userservice.domain.user.exception.UserAlreadyExistsException;
import pl.polsl.krypczyk.apartmentsforrent.userservice.domain.user.exception.UserNotFoundException;

import javax.validation.Valid;

@RestController
@RequestMapping("/user/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserResponse registerNewUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws UserAlreadyExistsException, BadCredentialsException {
        return this.authorizationService.registerNewUser(createUserRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginUserResponse loginUser(@Valid @RequestBody UserLoginRequest userLoginRequest) throws UserNotFoundException, BadCredentialsException {
        return this.authorizationService.loginUser(userLoginRequest);
    }

    @PostMapping("{userId}/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logoutUser(@PathVariable("userId") Long userId) throws UnauthorizedUserException, UserNotFoundException {
        this.authorizationService.authorizeUser(userId);
        this.authorizationService.logoutUser(userId);
    }

}
