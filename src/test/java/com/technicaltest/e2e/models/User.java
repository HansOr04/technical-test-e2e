package com.technicaltest.e2e.models;

/**
 * Model representing a User for test parameterization.
 *
 * <p>Models are plain Java objects (POJOs) used to pass structured data
 * to Tasks and Step Definitions without coupling them to raw strings.</p>
 */
public class User {

    private final String name;
    private final String email;
    private final String password;
    private final String role;

    private User(Builder builder) {
        this.name     = builder.name;
        this.email    = builder.email;
        this.password = builder.password;
        this.role     = builder.role;
    }

    // ── Accessors ─────────────────────────────────────────────────────────────

    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }
    public String getRole()     { return role; }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', role='" + role + "'}";
    }

    // ── Builder ───────────────────────────────────────────────────────────────

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String email;
        private String password;
        private String role = "standard";

        public Builder name(String name)         { this.name = name; return this; }
        public Builder email(String email)       { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder role(String role)         { this.role = role; return this; }

        public User build() {
            if (email == null || email.isBlank()) {
                throw new IllegalStateException("User email is required");
            }
            return new User(this);
        }
    }

    // ── Factory helpers ───────────────────────────────────────────────────────

    /** Convenience factory: standard test user. */
    public static User standard() {
        return builder()
                .name("Test User")
                .email("testuser@example.com")
                .password("Password123!")
                .role("standard")
                .build();
    }

    /** Convenience factory: admin test user. */
    public static User admin() {
        return builder()
                .name("Admin User")
                .email("admin@example.com")
                .password("Admin@12345")
                .role("admin")
                .build();
    }
}
