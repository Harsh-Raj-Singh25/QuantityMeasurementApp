# QuantityMeasurementApp
## UC18 - Google Authentication, OAuth2, & JWT Security

### Overview
> UC18 elevates the application from a public API to a secure, production-ready microservice. This use case implements a **Stateless Authentication Architecture** by integrating **Google OAuth2** for delegated identity verification and **JSON Web Tokens (JWT)** for secure, session-less API access.

By offloading password management to Google and utilizing cryptographic tokens, the backend is now highly secure, horizontally scalable, and protected against unauthorized access.

---

### Key Technical Implementations
* **OAuth2 Delegated Authentication (`spring-boot-starter-oauth2-client`):** Integrated Google Sign-In to verify user identities without ever storing or handling passwords in the local database.
* **Stateless API Security (JWT):** Replaced traditional server-side sessions with cryptographic JSON Web Tokens (`jjwt`). The server is now 100% stateless, drastically reducing memory overhead.
* **Custom Security Filters (`OncePerRequestFilter`):** Engineered a `JwtAuthenticationFilter` to intercept all incoming HTTP requests, extract Bearer tokens, and cryptographically verify their signatures before allowing access to the controllers.
* **Identity Synchronization (`DefaultOAuth2UserService`):** Built a custom OAuth2 user service that catches successful Google logins, extracts the profile payload, and seamlessly persists new users into the local database (`UserEntity`).
* **Role-Based Access Control (RBAC) Foundation:** Configured `SecurityConfig` to maintain public routes for API documentation (Swagger/H2) while strictly enforcing `.authenticated()` requirements for all `/api/v1/quantities/**` endpoints.

---

### The Authentication Flow

1. **The Handshake:** Client navigates to `/oauth2/authorization/google` and authenticates via Google's secure portal.
2. **The Interception:** Spring Security catches the successful redirect. `CustomOAuth2UserService` queries the database and registers the user if they are new.
3. **The Token Generation:** `OAuth2LoginSuccessHandler` triggers `JwtUtils` to generate a secure, HMAC-SHA256 signed JSON Web Token containing the user's email and a 24-hour expiration.
4. **The VIP Pass (API Access):** The client attaches the JWT to the `Authorization` header (`Bearer <token>`) for all future requests. `JwtAuthenticationFilter` verifies the signature and grants access to the REST Controllers.

---

### API Access Rules

| Endpoint Path | Protection Level | Description |
| :--- | :--- | :--- |
| `/oauth2/authorization/google` | **Public** | Trigger the Google login flow. |
| `/swagger-ui/**`, `/v3/api-docs/**` | **Public** | Access interactive API documentation. |
| `/h2-console/**` | **Public** | Database management dashboard. |
| `/api/v1/quantities/**` | **Secured (JWT Required)** | Core business logic endpoints (Add, Compare, History). |

---

### Local Setup & Configuration
To run this application locally, you must provide your own Google Cloud Console credentials.

1. Navigate to the [Google Cloud Console](https://console.cloud.google.com/).
2. Create an **OAuth 2.0 Client ID** (Web Application).
3. Set the Authorized Redirect URI to: `http://localhost:8080/login/oauth2/code/google`
4. Update your `src/main/resources/application.properties` with the generated keys:
```properties
spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_CLIENT_SECRET