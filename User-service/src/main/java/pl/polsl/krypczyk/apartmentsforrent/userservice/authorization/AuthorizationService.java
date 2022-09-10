package pl.polsl.krypczyk.apartmentsforrent.userservice.authorization;

import pl.polsl.krypczyk.apartmentsforrent.userservice.user.dto.UserCreatedResponseDTO;
import pl.polsl.krypczyk.apartmentsforrent.userservice.user.dto.UserLoggedInResponseDTO;
import pl.polsl.krypczyk.apartmentsforrent.userservice.user.dto.UserLoginRequestDTO;
import pl.polsl.krypczyk.apartmentsforrent.userservice.user.userdetails.dto.CreateUserRequestDTO;

import java.util.UUID;

public interface AuthorizationService {

    UserCreatedResponseDTO registerNewUser(CreateUserRequestDTO createUserRequestDTO);

    UserLoggedInResponseDTO loginUser(UserLoginRequestDTO userLoginRequestDTO);

    void logoutUser(UUID accessToken);

    void deleteDbContent();
}
