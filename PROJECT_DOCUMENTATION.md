# Ticket Booking Application - Full Stack Project

## Project Overview
A comprehensive Java Full Stack Ticket Booking Application supporting Train, Metro, Bus, and Flight bookings.

## Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Security** (JWT + OAuth2)
- **Spring Data JPA**
- **MySQL Database**
- **Maven**
- **iText PDF** (Ticket generation)
- **Twilio** (WhatsApp notifications)
- **JavaMailSender** (Email notifications)

### Frontend
- **React 18**
- **Tailwind CSS**
- **React Router DOM**
- **Axios**
- **React Toastify**
- **React Datepicker**
- **Lucide React Icons**

## Project Structure

```
ticket-booking-app/
├── backend/
│   ├── src/main/java/com/ticketbooking/
│   │   ├── config/           # Security, CORS, Swagger config
│   │   ├── controller/       # REST API controllers
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── entity/           # JPA Entities
│   │   ├── enums/            # Enumerations
│   │   ├── exception/        # Custom exceptions & handler
│   │   ├── repository/       # Spring Data repositories
│   │   ├── security/         # JWT, Authentication
│   │   ├── service/          # Service interfaces & implementations
│   │   └── TicketBookingApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/
    ├── public/
    ├── src/
    │   ├── components/       # Reusable components
    │   ├── pages/            # Page components
    │   ├── services/         # API services
    │   ├── context/          # React Context (Auth)
    │   ├── styles/           # CSS/Tailwind
    │   ├── App.js
    │   └── index.js
    ├── package.json
    └── tailwind.config.js
```

## Features Implemented

### Authentication & Authorization
- Login with Email/Mobile + Password
- Signup with full profile
- Google OAuth2 Login
- Facebook OAuth2 Login
- JWT Token-based Authentication
- Forgot Password (Email reset link)
- Reset Password
- Email Verification
- Mobile OTP Verification

### Booking Features
- Search transports (Train, Metro, Bus, Flight)
- View transport details and amenities
- Book tickets with passenger details
- PNR Number generation (10-character alphanumeric)
- Seat selection and allocation
- Booking confirmation with PDF ticket
- Booking cancellation with refund
- Download ticket PDF

### Payment & Wallet
- Multiple payment methods (UPI, Credit/Debit Card, Net Banking, Wallet)
- Digital Wallet with add money feature
- Wallet transaction history
- Coupon/Promo code application
- Discount calculation (Percentage/Flat)

### Notifications
- Email ticket confirmation with PDF attachment
- WhatsApp ticket confirmation (via Twilio)
- Email booking cancellation
- WhatsApp booking cancellation
- OTP via WhatsApp
- Payment confirmations

### Running Status (No Login Required)
- Check Train running status
- Check Metro running status
- Real-time delay information
- Platform number information
- Status history

### User Management
- User profile management
- Password change
- Profile image upload
- Payment method management
- Booking history

## API Endpoints

### Authentication
- `POST /api/auth/login` - Login
- `POST /api/auth/signup` - Signup
- `POST /api/auth/forgot-password` - Forgot password
- `POST /api/auth/reset-password` - Reset password
- `POST /api/auth/refresh-token` - Refresh JWT token
- `GET /api/auth/verify-email` - Verify email
- `POST /api/auth/send-otp` - Send mobile OTP
- `POST /api/auth/verify-otp` - Verify mobile OTP

### Search (Public)
- `POST /api/search/transports` - Search transports
- `GET /api/search/popular-sources` - Popular sources
- `GET /api/search/popular-destinations` - Popular destinations

### Running Status (Public)
- `GET /api/running-status/{transportNumber}` - Get status
- `GET /api/running-status/type/{type}` - Get by type
- `GET /api/running-status/{transportNumber}/history` - History

### Bookings (Authenticated)
- `POST /api/bookings` - Create booking
- `GET /api/bookings/pnr/{pnrNumber}` - Get by PNR
- `GET /api/bookings/my-bookings` - User bookings
- `POST /api/bookings/{id}/cancel` - Cancel booking
- `GET /api/bookings/{pnr}/download` - Download ticket

### Wallet (Authenticated)
- `GET /api/wallet` - Get wallet
- `POST /api/wallet/add-money` - Add money
- `GET /api/wallet/transactions` - Transactions

### Payment Methods (Authenticated)
- `GET /api/payment-methods` - List methods
- `POST /api/payment-methods` - Add method
- `DELETE /api/payment-methods/{id}` - Delete method
- `PUT /api/payment-methods/{id}/set-default` - Set default

### Coupons (Public)
- `POST /api/coupons/apply` - Apply coupon
- `GET /api/coupons/active` - Active coupons
- `GET /api/coupons/validate` - Validate coupon

### User (Authenticated)
- `GET /api/users/profile` - Get profile
- `PUT /api/users/profile` - Update profile
- `POST /api/users/change-password` - Change password

## Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+
- Node.js 18+
- npm or yarn

### Backend Setup

1. **Create MySQL Database**
```sql
CREATE DATABASE ticket_booking_db;
```

2. **Configure application.properties**
Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ticket_booking_db?createDatabaseIfNotExist=true
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

jwt.secret=your-256-bit-secret-key-here-must-be-at-least-32-characters

spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password

twilio.account.sid=your_twilio_sid
twilio.auth.token=your_twilio_token
twilio.whatsapp.number=whatsapp:+14155238886
```

3. **Build and Run**
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. **Install Dependencies**
```bash
cd frontend
npm install
```

2. **Start Development Server**
```bash
npm start
```

The frontend will start on `http://localhost:3000`

### External Services Setup

#### Gmail SMTP (For Email)
1. Enable 2-Factor Authentication
2. Generate App Password
3. Use App Password in application.properties

#### Twilio (For WhatsApp)
1. Create Twilio account
2. Get Account SID and Auth Token
3. Enable WhatsApp Sandbox
4. Update credentials in application.properties

#### Google OAuth2
1. Go to Google Cloud Console
2. Create OAuth2 credentials
3. Add redirect URI: `http://localhost:8080/login/oauth2/code/google`
4. Update client ID and secret

#### Facebook OAuth2
1. Go to Facebook Developers
2. Create App
3. Add Facebook Login product
4. Update client ID and secret

## Database Schema

### Main Tables
- `users` - User accounts
- `wallets` - Digital wallets
- `wallet_transactions` - Wallet transaction history
- `payment_methods` - Saved payment methods
- `transports` - Base transport entity
- `trains`, `flights`, `buses`, `metros` - Transport-specific tables
- `schedules` - Journey schedules
- `seats` - Seat inventory
- `bookings` - Ticket bookings
- `booking_passengers` - Passenger details per booking
- `passengers` - Saved passenger profiles
- `payments` - Payment records
- `coupons` - Discount coupons
- `promo_codes` - Promotional codes
- `running_status` - Real-time transport status
- `notifications` - User notifications

## Security Features
- JWT-based stateless authentication
- BCrypt password hashing
- CORS configuration
- Role-based access control (USER, ADMIN, OPERATOR)
- Input validation with Bean Validation
- Global exception handling
- Secure headers

## Running the Application

1. Start MySQL server
2. Start Redis server (optional, for caching)
3. Run backend: `mvn spring-boot:run`
4. Run frontend: `npm start`
5. Access application at `http://localhost:3000`

## Testing

### Backend Tests
```bash
cd backend
mvn test
```

### API Testing with Swagger
- Open `http://localhost:8080/swagger-ui.html`
- All API endpoints are documented

## Deployment

### Backend Deployment
```bash
mvn clean package
java -jar target/ticket-booking-backend-1.0.0.jar
```

### Frontend Deployment
```bash
npm run build
# Serve build/ folder with any static server
```

## Additional Features
- QR Code generation for tickets
- Excel export for bookings
- Redis caching for performance
- WebSocket for real-time updates
- Actuator for health monitoring
- Scheduled tasks for cleanup

## Support
For any issues or questions:
- Email: support@ticketbooking.com
- Phone: +91 1800-123-4567

---
Generated on 2026-05-02
