package com.web.hevepratas.security;

import com.web.hevepratas.dtos.UserDTO;
import com.web.hevepratas.entities.User;
import com.web.hevepratas.enums.UserRole;
import com.web.hevepratas.servicies.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;
    private final PasswordEncoder encoder;

    private static final String DEFAULT_PASSWORD = "12345";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = userService.findByEmail(email);

        if(user == null) {
            user = provideUser(oAuth2User);
        }

        authentication = new CustomAuthentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private User provideUser(OAuth2User oAuth2User) {
        UserDTO entity = new UserDTO();

        entity.setUserName(oAuth2User.getAttribute("name"));
        entity.setUserEmail(oAuth2User.getAttribute("email"));
        entity.setUserPassword(DEFAULT_PASSWORD);
        entity.setUserRole(UserRole.USER);

        return  userService.saveByUserEntity(entity);
    }
}
