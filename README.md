📧 Reminder Email Scheduler – Spring Boot + PostgreSQL
This project is a Spring Boot-based application that automatically sends reminder emails to users who submitted a form but left one or more fields incomplete. If the form remains incomplete 24 hours after submission, an email reminder is sent to prompt them to finish the form.

✨ Features
✅ Tracks form submissions in a PostgreSQL database.

⏱ Scheduled task runs periodically (e.g., every 5 minutes).

🕒 Checks for forms submitted more than 24 hours ago with incomplete data.

📬 Sends reminder emails to users using Gmail SMTP.

🛠 Fully configurable through application.properties.

🔧 Tech Stack
Java 17+

Spring Boot 3.x

Spring Data JPA

Spring Scheduler

Spring Mail

PostgreSQL

📁 Project Structure
bash
Copy
Edit
src/
├── main/
│   ├── java/com/mailSeduler/ms/
│   │   ├── controller/       # Optional – if REST endpoints are added
│   │   ├── entity/           # Form entity
│   │   ├── repository/       # JPA Repository for form access
│   │   ├── service/          # Email and reminder logic
│   │   └── MailSedApplication.java
│   └── resources/
│       └── application.properties
⚙️ Configuration
Set your credentials and database in application.properties:

properties
Copy
Edit
# App Port
server.port=7821

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/newdb
spring.datasource.username=postgres
spring.datasource.password=ROOT
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.protocol=smtp
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.ssl.protocols=TLSv1.2
📌 Important: For Gmail to send mail securely, create and use an App Password instead of your actual Gmail password.

🧠 How It Works
When a form is submitted, a submittedAt timestamp is stored.

A Spring Scheduler checks every few minutes:

If more than 24 hours have passed

If one or more required fields are null

If both conditions are met, a reminder email is sent to the user's registered email address.

▶️ Running the App
Make sure PostgreSQL is running and the newdb database exists.

Then run:

bash
Copy
Edit
./mvnw spring-boot:run
Or build and run the JAR:

bash
Copy
Edit
./mvnw clean package
java -jar target/ms-0.0.1-SNAPSHOT.jar
📝 Example Output
Console log when scheduler runs and sends mail:

ruby
Copy
Edit
--->>>SCHEDULAR triggered@@@@@@@

Hibernate: SELECT ... FROM informations ...

vinayc200022@gmail.com

$$$$$$$$ EMAIL SENT $$$$$$$$

📬 Email Gotcha

If you're not receiving emails, check your Spam folder.

Ensure your App Password is correct.

Gmail may temporarily block access — visit: Gmail SMTP Setup Help

