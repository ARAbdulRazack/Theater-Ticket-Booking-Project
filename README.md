# Theater Ticket Booking Project

## Project Overview
- This project is a Theater Ticket Booking System. 
- This is a web application for managing movie showtimes and booking tickets.
- The application supports three types of users: Normal Users, Admins, and Owners, each with specific permissions.
- The repository is named **Theater-Ticket-Booking-Project**, but the application will run under the name **TicketBooking**.

## Prerequisites

- **Eclipse IDE for Java EE Developers**
- **Apache Tomcat** (configured in Eclipse)
- **PostgreSQL** (or your preferred database)
- **JDK 17** or later

## Setup Instructions

### 1. **Clone the Repository**

Clone the repository to your local machine:
- git clone https://your-repository-url.git
- cd Theater-Ticket-Booking-Project

## Tomcat Installation and Configuration in Eclipse

Download and Install Tomcat:
- Download Apache Tomcat from the official website.
- Extract the downloaded archive to a directory on your machine.

Configure Tomcat in Eclipse:
- Open Eclipse IDE.
- Go to Window > Preferences.
- Select Server > Runtime Environments.
- Click Add and select Apache Tomcat.
- Browse to the directory where you extracted Tomcat and click Finish.
- Click Apply and Close.

Add Tomcat Server to Eclipse:
- Go to the Servers tab in Eclipse.
- Right-click and select New > Server.
- Choose Apache > Tomcat and select the version you installed.
- Click Next and then Finish.

## Database Setup

Create the Database:
- Create a new database in PostgreSQL for the project called **TicketBooking**.

Table Creation:
- Execute the following SQL scripts to create the necessary tables and insert sample data:

CREATE TABLE Theater (
    id BIGSERIAL PRIMARY KEY,
    created_by INT,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Movie (
    id BIGSERIAL PRIMARY KEY,
    created_by INT,
    name VARCHAR(255) NOT NULL,
    director_name VARCHAR(255),
    hero_name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Showtime (
    id BIGSERIAL PRIMARY KEY,
    created_by INT,
    updated_by INT,
    movie_id INT,
    theater_id INT,
    showdate DATE,
    showtime VARCHAR(10),
    cost DOUBLE PRECISION,
    total_seats INT,
    seats integer[],
    booked_seats integer[],
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (movie_id) REFERENCES Movie(id),
    FOREIGN KEY (theater_id) REFERENCES Theater(id)
);

CREATE TABLE "user" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20),
    is_admin BOOLEAN DEFAULT FALSE,
    is_owner BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE bookingHistory (
    id BIGSERIAL PRIMARY KEY,
    booked_by INT,
    theater_id INT,
    movie_id INT,
    showtime_id INT,
    total_cost DOUBLE PRECISION,
    booked_seats INTEGER[],
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booked_by) REFERENCES "user"(id),
    FOREIGN KEY (theater_id) REFERENCES Theater(id),
    FOREIGN KEY (movie_id) REFERENCES Movie(id),
    FOREIGN KEY (showtime_id) REFERENCES Showtime(id)
);

INSERT INTO Theater (created_by, name) VALUES
(1, 'Galaxy Cinema'),
(2, 'Star Theater');

INSERT INTO Movie (created_by, name, director_name, hero_name) VALUES
(1, 'Interstellar', 'Christopher Nolan', 'Matthew McConaughey'),
(2, 'Inception', 'Christopher Nolan', 'Leonardo DiCaprio');

INSERT INTO Showtime (created_by, updated_by, movie_id, theater_id, showdate, showtime, cost, total_seats, seats) VALUES
(1, 1, 1, 1, '2024-09-07', '18:00', 10.50, 5, '{1,2,3,4,5}'),
(2, 2, 2, 2, '2024-09-08', '20:00', 12.00, 5, '{1,2,3,4,5}');

INSERT INTO "user" (name, password, phone_number, is_admin, is_owner) VALUES
('Razack', 'Razack', '6381191039', TRUE, FALSE),
('Abdul', 'Abdul', '7418428081', FALSE, FALSE),
('Kaif', 'Kaif', '9597797173', FALSE, TRUE);

## Configure Database Connection

Update ConnectionUtil Class:
- Locate the ConnectionUtil class in your project.

- Update the database connection parameters to match your setup:
public class ConnectionUtil {
    String jdbcUrl = "jdbc:postgresql://localhost:5432/TicketBooking";
    String username = "postgres";
    String password = "user";
}

- Replace localhost, 5432, TicketBooking, postgres, and user with your actual database details if they differ.
  
## Deploy and Run the Application

Deploy the Application:
- Right-click on the project in Eclipse and select Run As > Run on Server.
- Choose the Tomcat server configured earlier.

Access the Application:
- Open a web browser and navigate to http://localhost:8080/TicketBooking/Login.jsp.

Note: Replace 8080 with the port configured for your Tomcat server if it's different.

## User Roles and Permissions
- Normal User: Can book tickets and view booked history.
- Admin: Can book tickets, view booked history, add and delete showtimes, and manage showtimes for theaters they have access to.
- Owner: Can create admins, theaters, and movies. Can also book tickets, view showtimes, and manage all showtimes and theaters.

## Usage
- View Theater Name, Movie Name, Showtime, Showdate, Cost of 1 Ticket, Available Tickets Count, Booked Tickets
- We can Sign in wih=th new user.
- The movie, theater, showtime, cost everything can be created and deleted.
