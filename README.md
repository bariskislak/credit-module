# Credit Module API

This project is a credit module API that allows customers to manage their loans and installments.

## Authentication

The system uses Basic Authentication. Available users and their credentials are:

- Admin user:
  - Username: admin
  - Password: 1234
  - Role: ROLE_ADMIN

- Customer users:
  - Username: user1, user2, user3
  - Password: 1234 (same for all users)
  - Role: ROLE_USER

## Running the Application

1. Make sure you have Docker and Docker Compose installed
2. Clone the repository
3. Run the following commands:

## Technical Details

### API Endpoints

- `POST /v1/loans/create` - Create new loan application
- `GET /v1/loans/list` - List customer-based loans
- `GET /v1/loans/{loanId}/installments` - View loan installments
- `POST /v1/loans/{loanId}/pay` - Make loan payment

### Security

- Security layer with Spring Security
- Role-based authorization (USER role)
- Custom Authentication Provider
- Endpoint-based access controls

### Technologies

- Java
- Spring Boot
- Spring Security
- JUnit & Mockito
- Maven

## Installation

1. Clone the project
2. Install Maven dependencies
3. Run the application

## Testing

The project includes comprehensive unit tests:
- Controller tests
- Service tests
- Security tests

## Development

To contribute to the project:
1. Fork it
2. Create a feature branch
3. Commit your changes
4. Open a pull request

## License

This project is specially developed for ING Hub.

