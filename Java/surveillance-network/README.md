# Surveillance Network

A Java application that models a simplified surveillance network using object-oriented programming principles. The system manages suspects and their communications, providing analysis and search capabilities through a structured multi-class design.

---

## Project Overview

The application allows users to:

- Manage suspects and their personal information
- Register phone calls and SMS messages
- Analyze communication between suspects
- Detect suspicious messages using keyword searches
- Find common associates between suspects
- Search suspects by country
- Retrieve communication statistics

---

## Object-Oriented Design

The project is organized into six collaborating classes:

| Class | Responsibility |
|--------|----------------|
| `Communication` | Base class for communications |
| `PhoneCall` | Represents phone call records |
| `SMS` | Represents text message records |
| `Suspect` | Stores suspect information and associates |
| `Registry` | Manages suspects and communications |
| `Main` | Demonstrates and tests the application |

A simplified class relationship diagram is available.

---

## Repository Structure

```text
surveillance-network/
│
├── src/
│   ├── Communication.java
│   ├── PhoneCall.java
│   ├── SMS.java
│   ├── Suspect.java
│   ├── Registry.java
│   └── Main.java
│
├── docs/
│   └── Project Specification.pdf
│
├── images/
│   └── class-diagram.png
│
└── README.md
```

---

## Concepts Demonstrated

- Object-Oriented Programming (OOP)
- Inheritance
- Polymorphism
- Encapsulation
- Class Relationships
- Java Collections
- Data Filtering & Search
- Modular Software Design

---

## Technologies

- Java
- Object-Oriented Design

---

## Purpose

This project showcases the implementation of a multi-class Java application using object-oriented design principles to model a realistic information management system. It demonstrates the use of inheritance, object relationships and data processing.
