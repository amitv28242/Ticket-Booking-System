package com.ticketbooking.entity;

import com.ticketbooking.enums.Gender;
import com.ticketbooking.enums.UserRole;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String googleId;

    @Column(unique = true)
    private String facebookId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Boolean isEmailVerified = false;

    @Column(nullable = false)
    private Boolean isMobileVerified = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Wallet wallet;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentMethodEntity> paymentMethods = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Passenger> passengers = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String profileImageUrl;
    private String resetPasswordToken;
    private LocalDateTime resetPasswordExpiry;
    private String emailVerificationToken;
    private String mobileOtp;
    private LocalDateTime mobileOtpExpiry;

    // Default Constructor
    public User() {
    }

    // All Args Constructor
    public User(
            Long id,
            String email,
            String mobileNumber,
            String password,
            String firstName,
            String lastName,
            Gender gender,
            LocalDate dateOfBirth,
            String googleId,
            String facebookId,
            UserRole role,
            Boolean isActive,
            Boolean isEmailVerified,
            Boolean isMobileVerified,
            Wallet wallet,
            List<Booking> bookings,
            List<PaymentMethodEntity> paymentMethods,
            List<Passenger> passengers,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String profileImageUrl,
            String resetPasswordToken,
            LocalDateTime resetPasswordExpiry,
            String emailVerificationToken,
            String mobileOtp,
            LocalDateTime mobileOtpExpiry) {

        this.id = id;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.googleId = googleId;
        this.facebookId = facebookId;
        this.role = role;
        this.isActive = isActive;
        this.isEmailVerified = isEmailVerified;
        this.isMobileVerified = isMobileVerified;
        this.wallet = wallet;
        this.bookings = bookings;
        this.paymentMethods = paymentMethods;
        this.passengers = passengers;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.profileImageUrl = profileImageUrl;
        this.resetPasswordToken = resetPasswordToken;
        this.resetPasswordExpiry = resetPasswordExpiry;
        this.emailVerificationToken = emailVerificationToken;
        this.mobileOtp = mobileOtp;
        this.mobileOtpExpiry = mobileOtpExpiry;
    }

    // Builder Method
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    // Inner Builder Class
    public static class UserBuilder {

        private Long id;
        private String email;
        private String mobileNumber;
        private String password;
        private String firstName;
        private String lastName;
        private Gender gender;
        private LocalDate dateOfBirth;
        private String googleId;
        private String facebookId;
        private UserRole role = UserRole.USER;
        private Boolean isActive = true;
        private Boolean isEmailVerified = false;
        private Boolean isMobileVerified = false;
        private Wallet wallet;
        private List<Booking> bookings = new ArrayList<>();
        private List<PaymentMethodEntity> paymentMethods = new ArrayList<>();
        private List<Passenger> passengers = new ArrayList<>();
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String profileImageUrl;
        private String resetPasswordToken;
        private LocalDateTime resetPasswordExpiry;
        private String emailVerificationToken;
        private String mobileOtp;
        private LocalDateTime mobileOtpExpiry;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder googleId(String googleId) {
            this.googleId = googleId;
            return this;
        }

        public UserBuilder facebookId(String facebookId) {
            this.facebookId = facebookId;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public UserBuilder isEmailVerified(Boolean isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
            return this;
        }

        public UserBuilder isMobileVerified(Boolean isMobileVerified) {
            this.isMobileVerified = isMobileVerified;
            return this;
        }

        public UserBuilder wallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }

        public UserBuilder bookings(List<Booking> bookings) {
            this.bookings = bookings;
            return this;
        }

        public UserBuilder paymentMethods(List<PaymentMethodEntity> paymentMethods) {
            this.paymentMethods = paymentMethods;
            return this;
        }

        public UserBuilder passengers(List<Passenger> passengers) {
            this.passengers = passengers;
            return this;
        }

        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserBuilder profileImageUrl(String profileImageUrl) {
            this.profileImageUrl = profileImageUrl;
            return this;
        }

        public UserBuilder resetPasswordToken(String resetPasswordToken) {
            this.resetPasswordToken = resetPasswordToken;
            return this;
        }

        public UserBuilder resetPasswordExpiry(LocalDateTime resetPasswordExpiry) {
            this.resetPasswordExpiry = resetPasswordExpiry;
            return this;
        }

        public UserBuilder emailVerificationToken(String emailVerificationToken) {
            this.emailVerificationToken = emailVerificationToken;
            return this;
        }

        public UserBuilder mobileOtp(String mobileOtp) {
            this.mobileOtp = mobileOtp;
            return this;
        }

        public UserBuilder mobileOtpExpiry(LocalDateTime mobileOtpExpiry) {
            this.mobileOtpExpiry = mobileOtpExpiry;
            return this;
        }

        public User build() {

            User user = new User();

            user.id = this.id;
            user.email = this.email;
            user.mobileNumber = this.mobileNumber;
            user.password = this.password;
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.gender = this.gender;
            user.dateOfBirth = this.dateOfBirth;
            user.googleId = this.googleId;
            user.facebookId = this.facebookId;
            user.role = this.role;
            user.isActive = this.isActive;
            user.isEmailVerified = this.isEmailVerified;
            user.isMobileVerified = this.isMobileVerified;
            user.wallet = this.wallet;
            user.bookings = this.bookings;
            user.paymentMethods = this.paymentMethods;
            user.passengers = this.passengers;
            user.createdAt = this.createdAt;
            user.updatedAt = this.updatedAt;
            user.profileImageUrl = this.profileImageUrl;
            user.resetPasswordToken = this.resetPasswordToken;
            user.resetPasswordExpiry = this.resetPasswordExpiry;
            user.emailVerificationToken = this.emailVerificationToken;
            user.mobileOtp = this.mobileOtp;
            user.mobileOtpExpiry = this.mobileOtpExpiry;

            return user;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<PaymentMethodEntity> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethodEntity> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public LocalDateTime getResetPasswordExpiry() {
        return resetPasswordExpiry;
    }

    public void setResetPasswordExpiry(LocalDateTime resetPasswordExpiry) {
        this.resetPasswordExpiry = resetPasswordExpiry;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public String getMobileOtp() {
        return mobileOtp;
    }

    public void setMobileOtp(String mobileOtp) {
        this.mobileOtp = mobileOtp;
    }

    public LocalDateTime getMobileOtpExpiry() {
        return mobileOtpExpiry;
    }

    public void setMobileOtpExpiry(LocalDateTime mobileOtpExpiry) {
        this.mobileOtpExpiry = mobileOtpExpiry;
    }
}