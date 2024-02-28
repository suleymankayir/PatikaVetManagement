# Veterinary Management System

## Introduction

The Veterinary Management System project aims to provide an API for a veterinary clinic to manage its operations independently. This application will be used by veterinary staff to manage various aspects of the clinic's operations.

## Features

### Management of Animals and Owners (Customers)

- Add, update, view, and delete animal records.
- Add, update, view, and delete customer (owner) records.
- Filter owners by name.
- Filter animals by name.
- Display all animals registered under a specific owner, with the ability to filter animals by name.

### Management of Administered Vaccines

- Add, update, view, and delete vaccine records administered to animals.
- Prevent the addition of a new vaccine if the protection end date of the same type of vaccine for the same animal has not passed. This is verified based on vaccine codes and protection end dates.

### Appointment Management

- Create, update, view, and delete appointments for vaccinations and examinations of animals.
- Appointments must include both date and time, stored using LocalDateTime.
- Schedule appointments with veterinarians for various types of examinations at suitable dates and times. Only one appointment per hour per veterinarian is allowed, assuming each examination takes one hour.
- Validate the availability of the veterinarian on the specified date and check for existing appointments at the specified time. If both conditions are met, the appointment should be created. Otherwise, throw a runtime exception with an appropriate error message.

### Veterinary Doctor Management

- Add, update, view, and delete veterinarian records.

### Management of Veterinarians' Available Dates

- Add, update, view, and delete the available dates of veterinarians.
- The available dates of veterinarians will be stored as LocalDate values, without specific time information.

## Installation

To install and run the Veterinary Management System, follow these steps:

1. Clone the repository from [GitHub Repository URL].
2. Install [Dependency Manager] if not already installed.
3. Install dependencies by running `npm install` or `yarn install`.
4. Configure the database connection in `config.js`.
5. Run the application using `npm start` or `yarn start`.
6. Access the API endpoints using the provided routes.

## API Documentation

Detailed documentation for the API endpoints can be found [here](https://github.com/suleymankayir/PatikaVetManagement/blob/main/APIEndpoints.md).

## Technologies Used

- Java
- Spring Boot
- Hibernate
- PostgreSQL
- Maven
