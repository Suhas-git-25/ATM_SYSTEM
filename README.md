Got it! Here's a polished README tailored for a **GitHub repository**—including badges, sections, and formatting to fit the typical GitHub style:

---

# ATM Simulation in Java

[![Java](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

A simple console-based ATM simulation program in Java supporting Checking and Savings accounts with deposit, withdrawal, balance inquiry, and fund transfer functionalities.

---

## Features

* Supports **CheckingAccount** and **SavingsAccount** (with withdrawal limits).
* Deposit and withdraw money with validation.
* Transfer funds between accounts.
* Simple CLI interface for user interaction.
* Basic error handling and input validation.

---

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 8 or higher installed.
* Terminal or command prompt access.

### Running the Program

Clone the repository:

```bash
git clone https://github.com/your-username/atm-simulation-java.git
cd atm-simulation-java
```

Compile and run:

```bash
javac ATM.java
java ATM
```

---

## Usage

1. **Login:** Enter your account number (e.g., `1001`).
2. If multiple accounts under same number (checking & savings), choose the account type.
3. Choose operations from the menu:

   * Check Balance
   * Deposit
   * Withdraw
   * Transfer Funds
   * Exit

---

## Sample Accounts

| Account Number | Owner   | Account Type | Initial Balance |
| -------------- | ------- | ------------ | --------------- |
| 1001           | Alice   | Checking     | \$1500.00       |
| 1001           | Alice   | Savings      | \$2000.00       |
| 1003           | Charlie | Checking     | \$1800.00       |
| 1004           | David   | Savings      | \$2200.00       |
| 1005           | Eve     | Checking     | \$1700.00       |

---

## Code Structure

* **Transactable**: Interface defining `deposit` and `withdraw`.
* **Account**: Abstract class implementing common account functionality.
* **CheckingAccount**: Concrete class for checking accounts.
* **SavingsAccount**: Concrete class for savings accounts with withdrawal limit.
* **ATM**: Main class with program entry and CLI logic.

---

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.



