package com.service;

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthService {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId; // Replace with your Google client ID

    @Value("${spring.security.oauth2.client.registration.facebook.client-id}")
    private String facebookClientId; // Replace with your Facebook client ID

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = null;

        if (authentication != null && authentication.isAuthenticated()) {
            // Check the OAuth2 provider (Google or Facebook)
            String providerId = authentication.getName();

            if (providerId.startsWith(googleClientId)) {
                // Handle Google authentication
                // Use OAuth2 APIs to retrieve user information, e.g., email
                // Example: googleOAuth2UserInfo.getEmail();
            } else if (providerId.startsWith(facebookClientId)) {
                // Handle Facebook authentication
                // Use OAuth2 APIs to retrieve user information, e.g., email
                // Example: facebookOAuth2UserInfo.getEmail();
            }
        }

        return StringUtils.hasText(email) ? email : null;
    }
}

