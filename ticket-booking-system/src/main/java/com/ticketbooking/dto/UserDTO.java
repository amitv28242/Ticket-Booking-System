package com.ticketbooking.dto;

import java.time.LocalDate;

import com.ticketbooking.enums.Gender;

public class UserDTO {

    private Long id;
    private String email;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String profileImageUrl;
    private Boolean isEmailVerified;
    private Boolean isMobileVerified;

    // Default Constructor
    public UserDTO() {
    }

    // All Args Constructor
    public UserDTO(
            Long id,
            String email,
            String mobileNumber,
            String firstName,
            String lastName,
            Gender gender,
            LocalDate dateOfBirth,
            String profileImageUrl,
            Boolean isEmailVerified,
            Boolean isMobileVerified) {

        this.id = id;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.profileImageUrl = profileImageUrl;
        this.isEmailVerified = isEmailVerified;
        this.isMobileVerified = isMobileVerified;
    }

    // Manual Builder Method
    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    // Inner Builder Class
    public static class UserDTOBuilder {

        private Long id;
        private String email;
        private String mobileNumber;
        private String firstName;
        private String lastName;
        private Gender gender;
        private LocalDate dateOfBirth;
        private String profileImageUrl;
        private Boolean isEmailVerified;
        private Boolean isMobileVerified;

        public UserDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public UserDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDTOBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public UserDTOBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserDTOBuilder profileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public UserDTOBuilder isEmailVerified(Boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
            return this;
        }

        public UserDTOBuilder isMobileVerified(Boolean isMobileVerified) {
            this.isMobileVerified = isMobileVerified;
            return this;
        }

        public UserDTO build() {

            UserDTO dto = new UserDTO();

            dto.id = this.id;
            dto.email = this.email;
            dto.mobileNumber = this.mobileNumber;
            dto.firstName = this.firstName;
            dto.lastName = this.lastName;
            dto.gender = this.gender;
            dto.dateOfBirth = this.dateOfBirth;
            dto.profileImageUrl = this.profileImageUrl;
            dto.isEmailVerified = this.isEmailVerified;
            dto.isMobileVerified = this.isMobileVerified;

            return dto;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsMobileVerified() {
        return isMobileVerified;
    }

    public void setIsMobileVerified(Boolean isMobileVerified) {
        this.isMobileVerified = isMobileVerified;
    }
}