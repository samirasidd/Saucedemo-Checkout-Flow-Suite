# 🚀 SauceDemo Checkout Flow Suite

A **robust automation framework** built using **Selenium WebDriver**, **Java**, **TestNG**, and **Cucumber**, designed to validate the **end-to-end functionality** of the [SauceDemo](https://www.saucedemo.com/) e-commerce platform.

---

## 🌟 Key Features

- ✅ **Page Object Model (POM)** for clean separation of test logic  
- ✅ **Cucumber Integration** for behavior-driven development (BDD)  
- ✅ **Cross-browser testing** support (Chrome, Firefox, Edge)  
- ✅ **Modular architecture** for scalability and maintainability  
- ✅ **TestNG-powered assertions**, groups, and parallel execution  
- ✅ **Maven project** structure for CI/CD readiness  
- ✅ **Optional Allure reporting** for enhanced visibility

---

## 🧰 Tech Stack

| Component         | Technology               |
|------------------|---------------------------|
| Language          | Java 11+                 |
| Test Frameworks   | TestNG 7.6+, Cucumber     |
| Build Tool        | Maven 3.8+               |
| Driver Manager    | WebDriverManager         |
| Reporting         | TestNG, Allure (optional) |
| BDD               | Cucumber (Gherkin syntax) |

---


## 🧪 Test Coverage Overview

| Test Module          | Test Cases | Status |
|----------------------|------------|--------|
| 🔐 Login              | 2          | ✅     |
| 🛍️ Product Browsing   | 2          | ✅     |
| 🛒 Cart Management    | 2          | ✅     |
| 💳 Checkout Process   | 2          | ✅     |
| 📦 Order Confirmation | 1          | ✅     |

✅ **Total: 9 test cases** covering functional, UI, E2E, and integration scenarios.

---

## 📝 Sample Manual Test Cases

| # | Title                    | Type             | ID             | Expected Result                                | Steps                                                                 |
|--:|--------------------------|------------------|----------------|-----------------------------------------------|------------------------------------------------------------------------|
| 1 | Homepage Loading         | Smoke            | SD_INIT_01     | Loads in ≤ 3 sec                              | Open browser → Go to `https://www.saucedemo.com/`                     |
| 2 | Login Functionality      | Functional       | SD_LOGIN_02    | Redirects to `/inventory.html`                | Enter `standard_user` / `secret_sauce` → Click Login                  |
| 3 | Password Change Alert    | Usability        | SD_ALERT_03    | Alert appears, dismissible                    | Login → Observe and dismiss password change alert                     |
| 4 | Product Details Page     | Functional UI    | SD_SCROLL_PROD_04 | Details for “Onesie” displayed               | Scroll → Click 5th product                                           |
| 5 | Add to Cart              | Functional/DB    | SD_CART_05     | Cart count updates                            | Click "Add to Cart" → Cart icon updates                               |
| 6 | Checkout Navigation      | E2E + UI         | SD_CHECKOUT_06 | Goes to `/checkout-step-one.html`             | Click Cart → Checkout                                                 |
| 7 | Checkout Form Validation | Negative Testing | SD_FORM_07     | Redirects to step 2                            | Fill form → Continue                                                  |
| 8 | Order Confirmation       | E2E              | SD_ORDER_08    | Shows “Thank you for your order!”             | Finish checkout flow                                                  |

📌 **Full spreadsheet available here**:  
[📝 SauceDemo Test Cases - Google Sheet](https://docs.google.com/spreadsheets/d/1Bpq4t8b30ty0PktXZ5iM1AbYcVm0MtKkgeKfkFho8q8/edit?gid=0#gid=0)

---

## ▶️ How to Run the Framework

### 📦 Prerequisites

- Java 11+
- Maven 3.8+
- Chrome / Firefox / Edge browsers installed

### 🧪 Run TestNG Tests

mvn clean test

Or directly using the testng.xml suite:

mvn test -DsuiteXmlFile=testng.xml

### 🧪 Run Cucumber Tests

Run with Maven
mvn clean test

Or directly using the testng.xml suite:

mvn test -DsuiteXmlFile=testng.xml

### 🧪 Run Cucumber Tests

Run Cucumber feature files via Maven
mvn test -Dcucumber.options="src/test/resources/features"




---

## 💡 Interesting Additions

- **BDD with Cucumber**: Now supports `.feature` files written in Gherkin, which bridges the gap between business users and QA/test engineers.
- **Alert Handling**: Added validation for dynamic alerts like password change prompts.
- **Scroll-based Testing**: Validates lazy-loaded UI elements after scrolling.
- **CI/CD Friendly**: The structure is designed for easy integration with Jenkins/GitHub Actions.

---

## 🙌 Final Thoughts

This project is a practical showcase of how **clean architecture**, **modular design**, and **modern testing principles** can deliver a scalable automation framework. By combining **TestNG’s power** with **Cucumber's expressiveness**, the suite caters to both technical and non-technical stakeholders.

You’re welcome to fork it, explore it, and extend it for your own test automation needs. Contributions and feedback are always welcome!



