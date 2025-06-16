package com.web.hevepratas.servicies;

import com.web.hevepratas.entities.User;
import com.web.hevepratas.security.CustomAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserService service;

    public User getAuthenticatedUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof CustomAuthentication customAuth) {
            user = customAuth.getUser();
        }

        return user;
    }
}
