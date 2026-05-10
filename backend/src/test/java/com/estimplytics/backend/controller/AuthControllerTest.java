package com.estimplytics.backend.controller;

import com.estimplytics.backend.dto.TokenRequestDTO;
import com.estimplytics.backend.dto.TokenResponseDTO;
import com.estimplytics.backend.entity.Role;
import com.estimplytics.backend.repository.UserRepository;
import com.estimplytics.backend.security.JwtService;
import com.estimplytics.backend.security.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private TokenBlacklistService tokenBlacklistService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @Test
    void login_shouldReturnTokenWhenCredentialsAreValid() {
        TokenRequestDTO request = new TokenRequestDTO();
        request.setEmail("user@test.com");
        request.setPassword("secret");

        UserDetails userDetails = User.withUsername("user@test.com").password("admin123").authorities("ROLE_USER").build();
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userRepository.findByEmail("user@test.com")).thenReturn(Optional.of(
                com.estimplytics.backend.entity.User.builder()
                        .id(UUID.randomUUID())
                        .email("user@test.com")
                        .name("Daniel")
                        .role(Role.ANALYST)
                        .password("admin123")
                        .build()
        ));
        when(jwtService.generateToken(userDetails, "Daniel")).thenReturn("jwt-token");
        when(jwtService.getAccessTokenSeconds()).thenReturn(3600L);

        ResponseEntity<TokenResponseDTO> response = authController.login(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAccessToken()).isEqualTo("jwt-token");
        assertThat(response.getBody().getTokenType()).isEqualTo("Bearer");
        assertThat(response.getBody().getExpiresIn()).isEqualTo(3600L);
    }

    @Test
    void login_shouldThrowBadCredentialsWhenAuthenticationFails() {
        TokenRequestDTO request = new TokenRequestDTO();
        request.setEmail("user@test.com");
        request.setPassword("bad");
        AuthenticationException exception = mock(AuthenticationException.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(exception);

        assertThatThrownBy(() -> authController.login(request)).isInstanceOf(BadCredentialsException.class);
    }

    @Test
    void logout_shouldBlacklistTokenWhenAuthorizationHeaderIsValid() {
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getHeader("Authorization")).thenReturn("Bearer abc.def.ghi");

        ResponseEntity<Void> response = authController.logout(servletRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(tokenBlacklistService).addToBlacklist("abc.def.ghi");
    }

    @Test
    void logout_shouldReturnBadRequestWhenAuthorizationHeaderIsMissing() {
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getHeader("Authorization")).thenReturn(null);

        ResponseEntity<Void> response = authController.logout(servletRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
