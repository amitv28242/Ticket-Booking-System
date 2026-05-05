package com.ticketbooking.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import com.ticketbooking.entity.Booking.BookingBuilder;
import com.ticketbooking.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer age;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    
    @Column(nullable = false, unique = true)
    private String idProofNumber;
    
    @Column(nullable = false)
    private String idProofType; // Aadhar, PAN, Passport
    
    @Column(nullable = false)
    private String mobileNumber;
    
    @Column(nullable = false)
    private String email;
    
    private String seatPreference;
    private String mealPreference;
    private String specialAssistance;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

 // Manual Builder Method
    public static PassengerBuilder builder() {
        return new PassengerBuilder();
    }

    // Inner Builder Class
    public static class PassengerBuilder {

        private Long id;
        private User user;
        private String name;
        private Integer age;
        private Gender gender;
        private String idProofNumber;
        private String idProofType;
        private String mobileNumber;
        private String email;
        private String seatPreference;
        private String mealPreference;
        private String specialAssistance;
        private LocalDateTime createdAt;

        public PassengerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PassengerBuilder user(User user) {
            this.user = user;
            return this;
        }

        public PassengerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PassengerBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public PassengerBuilder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public PassengerBuilder idProofNumber(String idProofNumber) {
            this.idProofNumber = idProofNumber;
            return this;
        }

        public PassengerBuilder idProofType(String idProofType) {
            this.idProofType = idProofType;
            return this;
        }

        public PassengerBuilder mobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public PassengerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PassengerBuilder seatPreference(String seatPreference) {
            this.seatPreference = seatPreference;
            return this;
        }

        public PassengerBuilder mealPreference(String mealPreference) {
            this.mealPreference = mealPreference;
            return this;
        }

        public PassengerBuilder specialAssistance(String specialAssistance) {
            this.specialAssistance = specialAssistance;
            return this;
        }

        public PassengerBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Passenger build() {

            Passenger passenger = new Passenger();

            passenger.id = this.id;
            passenger.user = this.user;
            passenger.name = this.name;
            passenger.age = this.age;
            passenger.gender = this.gender;
            passenger.idProofNumber = this.idProofNumber;
            passenger.idProofType = this.idProofType;
            passenger.mobileNumber = this.mobileNumber;
            passenger.email = this.email;
            passenger.seatPreference = this.seatPreference;
            passenger.mealPreference = this.mealPreference;
            passenger.specialAssistance = this.specialAssistance;
            passenger.createdAt = this.createdAt;

            return passenger;
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeatPreference() {
        return seatPreference;
    }

    public void setSeatPreference(String seatPreference) {
        this.seatPreference = seatPreference;
    }

    public String getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(String mealPreference) {
        this.mealPreference = mealPreference;
    }

    public String getSpecialAssistance() {
        return specialAssistance;
    }

    public void setSpecialAssistance(String specialAssistance) {
        this.specialAssistance = specialAssistance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}