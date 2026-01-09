#  Money Mate

Money Mate is a **personal finance management web application** that helps you take full control of your **income, expenses, and savings**.  
With smart features like **categories, filtering, automated reports, and daily reminders**, financial management becomes effortless.

---

##  Links

-  **Live**: [Money Mate](https://money-mate-imam.netlify.app/)

---

##  Features

###  Secure Authentication
- Register your account and activate via email.
- Login securely with **JWT authentication**.

###  Income Tracking
- Add and manage your incomes.
- Filter incomes by date, keyword, and type.
- Download monthly **Excel reports** or receive them via email.

###  Expense Management
- Track and manage your daily expenses.
- Organize them with **categories**.
- Export detailed reports anytime.

### ️ Categories
- Create and manage categories for incomes & expenses.
- Get better insights by filtering through categories.

###  Excel Reports
- Download **professional Excel sheets** for income & expenses.
- Option to receive reports directly in your email inbox.

###  Advanced Filtering
- Filter transactions by **date, keyword, type, and sort order**.
- Analyze your financial activities quickly and efficiently.

###  Smart Notifications
-  **Daily Reminder Emails** at 10 PM IST reminding you to log your income & expenses.
-  **Daily Expense Summary Emails** at 11 PM IST containing a professional HTML table of your expenses for the day.

---

## ️ Tech Stack

### **Backend**
- Java Spring Boot
- Spring Security (JWT Authentication)
- MySQL / PostgreSQL
- Lombok
- Apache POI (for Excel reports)
- Java Mail (for email reports & reminders)
- Spring Scheduler (for automated notifications)

### **Frontend**
- React + Vite
- Tailwind CSS
- ShadCN/UI components

---

##  Screenshots

### Landing Page
![Landing Page](./screenshots/landing.png)

### About Project
![About Page](./screenshots/about.png)

---

##  Installation & Setup

###  Prerequisites
- [Node.js](https://nodejs.org/) (v16 or above)
- [Java JDK](https://adoptium.net/) (17 or above)
- [Maven](https://maven.apache.org/)
- [MySQL/PostgreSQL](https://www.postgresql.org/)

---

### Backend Setup (Spring Boot)

1. Clone the repository:
   ```bash
   git clone https://github.com/Imam-007/money-mate.git
   cd money-mate
   mvn spring-boot:run
    ```

### Frontend Setup (React + Vite)

1. Clone the repository:
   ```bash
   git clone https://github.com/Imam-007/money-mate.git
   cd money-mate/moneymatewebapp
   npm install
   npm run dev
   ```
