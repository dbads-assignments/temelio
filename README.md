# temelio
foundation and non profit management

Endpoints

1. Create Foundation

    a. post: localhost:8080/foundation
    b. body {"name": "foundation name", "email": "foundation@gmail.com"}
2. Create Nonprofit
   a. post: localhost:8080/nonprofit
   b. body {"name": "non profit name", "email": "nonprofit@gmail.com"}
3. Add user
   a. post: localhost:8080/user
   b. body {"name": "admin", "password": "pass"}
4. send email to nonprofits
   a. post: localhost:8080/nonprofit/sendEmails
   b. body ["nonprofit1@gmail.com","nonprofit2@gmail.com"]
