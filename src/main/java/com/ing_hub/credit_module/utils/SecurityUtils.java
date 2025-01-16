package com.ing_hub.credit_module.utils;

import com.ing_hub.credit_module.entity.dao.User;
import com.ing_hub.credit_module.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {

    private final UserRepository userRepository;

    private Long getCurrentCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            String currentUsername = authentication.getPrincipal().toString();
            User user = userRepository.findByUsername(currentUsername)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return Long.valueOf(user.getCustomerId());
    }

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    public void validateUserAccess(Long customerId) {
        if (!isAdmin() && !getCurrentCustomerId().equals(customerId)) {
            throw new AccessDeniedException("Access is denied: You can only access your own data.");
        }
    }
}
