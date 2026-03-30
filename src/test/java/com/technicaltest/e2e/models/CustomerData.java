package com.technicaltest.e2e.models;

/**
 * Model representing customer billing and shipping data.
 */
public class CustomerData {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String address;
    private final String city;
    private final String postcode;
    private final String country;
    private final String region;

    private CustomerData(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName  = builder.lastName;
        this.email     = builder.email;
        this.phone     = builder.phone;
        this.address   = builder.address;
        this.city      = builder.city;
        this.postcode  = builder.postcode;
        this.country   = builder.country;
        this.region    = builder.region;
    }

    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getEmail()     { return email; }
    public String getPhone()     { return phone; }
    public String getAddress()   { return address; }
    public String getCity()      { return city; }
    public String getPostcode()  { return postcode; }
    public String getCountry()   { return country; }
    public String getRegion()    { return region; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private String city;
        private String postcode;
        private String country;
        private String region;

        public Builder firstName(String firstName) { this.firstName = firstName; return this; }
        public Builder lastName(String lastName)   { this.lastName = lastName; return this; }
        public Builder email(String email)         { this.email = email; return this; }
        public Builder phone(String phone)         { this.phone = phone; return this; }
        public Builder address(String address)     { this.address = address; return this; }
        public Builder city(String city)           { this.city = city; return this; }
        public Builder postcode(String postcode)   { this.postcode = postcode; return this; }
        public Builder country(String country)     { this.country = country; return this; }
        public Builder region(String region)       { this.region = region; return this; }

        public CustomerData build() {
            return new CustomerData(this);
        }
    }

    /**
     * Provides a default valid guest customer dataset to avoid repetition in tests.
     */
    public static CustomerData defaultGuest() {
        return builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe" + System.currentTimeMillis() + "@example.com") // Dynamic email to avoid conflicts if needed
                .phone("5551234567")
                .address("123 Main Street")
                .city("New York")
                .postcode("10001")
                .country("United States")
                .region("New York")
                .build();
    }
}
