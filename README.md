# Bank Account Simulator API

A REST API simulating basic bank account operations built with Java Spring Boot.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot** — REST API framework
- **Spring Data JPA** — Database operations
- **H2 Database** — In-memory database
- **Maven** — Build and dependency management

## 📁 Project Structure
src/main/java/com/bank/bankaccountsimulator/
├── model/
│   └── Account.java          # Entity class
├── repository/
│   └── AccountRepository.java # Database operations
├── service/
│   └── AccountService.java   # Business logic
└── controller/
└── AccountController.java # REST endpoints
## 🔗 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/accounts | Get all accounts |
| GET | /api/accounts/{id} | Get account by ID |
| POST | /api/accounts | Create new account |
| POST | /api/accounts/{accountNumber}/deposit | Deposit money |
| POST | /api/accounts/{accountNumber}/withdraw | Withdraw money |
| DELETE | /api/accounts/{id} | Delete account |

## 🚀 How to Run

```bash
mvn spring-boot:run
```

API will be available at: `http://localhost:8080`

## 📋 Sample Requests

### Create Account
```json
POST /api/accounts
{
  "ownerName": "Fatima Musayeva",
  "accountNumber": "AZ001",
  "balance": 1000.00,
  "accountType": "SAVINGS"
}
```

### Deposit Money
```json
POST /api/accounts/AZ001/deposit
{
  "amount": 500.00
}
```

### Withdraw Money
```json
POST /api/accounts/AZ001/withdraw
{
  "amount": 200.00
}
```