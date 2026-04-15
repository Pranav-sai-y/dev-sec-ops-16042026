package com.sbi.ems.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * In-memory user store for training purposes.
 *
 * Provides two accounts demonstrating RBAC:
 *   - hr.admin  / Admin@SBI123  → ROLE_ADMIN  (can see salary, manage all)
 *   - emp.user  / User@SBI123   → ROLE_USER   (limited access, salary masked)
 *
 * ── DevSecOps Fixes (A07 — Auth Failures, A02 — Cryptographic Failures) ─────
 *
 *  BEFORE (vulnerable):
 *    .username("sonu").password(encoder.encode("sonu")).roles("USER")
 *    // Weak credentials, single role, no ADMIN user
 *
 *  AFTER (secure):
 *    - Strong passwords following complexity rules
 *    - Separate ADMIN and USER roles enabling RBAC on salary endpoint
 *    - BCrypt encoding (cost 12) — work factor makes brute-force expensive
 *    - In production: replace with DB-backed UserDetailsService
 *
 * ── Training Note ─────────────────────────────────────────────────────────────
 *  In production, use a database-backed UserDetailsService with employee
 *  records. The InMemoryUserDetailsManager is acceptable only for training.
 */
@Configuration
public class EmsUserDetailsService {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        // ADMIN — HR team: full access including salary fields
        UserDetails admin = User.builder()
                .username("hr.admin")
                .password(encoder.encode("Admin@SBI123"))
                .roles("ADMIN", "USER")
                .build();

        // USER — Regular employee: limited access, salary masked
        UserDetails user = User.builder()
                .username("emp.user")
                .password(encoder.encode("User@SBI123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
