# Order Tracking System

## Project Objective
An online application for entering and managing order-related information including customer name, order item, order date, delivery date, status, and remarks. The system allows users to add new orders and view existing order records.

## Features
- **Add Order Record**: Create new orders with automatic ID generation
- **View Order Record**: Search orders by customer name and order date
- **View All Orders**: Display all orders in the system
- **Validation**: Comprehensive input validation and error handling
- **Duplicate Prevention**: Checks for existing orders before insertion

## Technology Stack
- **Backend**: Java Servlet API (Jakarta EE)
- **Frontend**: JSP (JavaServer Pages) & HTML
- **Database**: Oracle Database
- **Architecture**: MVC Pattern with DAO, Service, and Bean layers

## Project Structure
```
com.wipro.order
├── util
│   ├── DBUtil.java                 # Database connection utility
│   └── InvalidInputException.java  # Custom exception class
├── bean
│   └── OrderBean.java              # Order entity
├── dao
│   └── OrderDAO.java               # Data access operations
├── service
│   └── Administrator.java          # Business logic layer
└── servlets
    └── MainServlet.java            # Controller servlet
```

## Database Design

### Table: ORDER_TB
| Column Name    | Data Type          | Constraints |
|----------------|-------------------|-------------|
| ORDERID        | VARCHAR2(12)      | Primary Key |
| CUSTOMERNAME   | VARCHAR2(50)      | Not Null    |
| ORDERITEM      | VARCHAR2(50)      | Not Null    |
| ORDER_DATE     | DATE              | Not Null    |
| DELIVERY_DATE  | DATE              |             |
| STATUS         | VARCHAR2(20)      |             |
| REMARKS        | VARCHAR2(100)     |             |

### Sequence: ORDER_SEQ
- **Start Value**: 10
- **Max Value**: 99
- **Increment By**: 1

## Order ID Generation Format
Format: `YYYYMMDD` + `First Two Letters of Customer Name (Uppercase)` + `Sequence Number`

**Example**: `20241130RA11`
- Date: 2024-11-30
- Customer: "Rajesh" → RA
- Sequence: 11

## Application Components

### 1. Utility Classes (com.wipro.order.util)

#### DBUtil
- `static Connection getDBConnection()`: Establishes and returns database connection

#### InvalidInputException
- `String toString()`: Returns "Invalid Input"

### 2. Bean Class (com.wipro.order.bean)

#### OrderBean
Properties:
- `orderId`: Order ID
- `customerName`: Customer name
- `orderItem`: Ordered item
- `orderDate`: Order date (java.util.Date)
- `deliveryDate`: Scheduled delivery date
- `status`: Order status (Pending/Shipped/Delivered)
- `remarks`: Additional comments
- Getters and Setters for all properties

### 3. DAO Layer (com.wipro.order.dao)

#### OrderDAO Methods
- `createRecord(OrderBean bean)`: Inserts order, returns orderId or "FAIL"
- `fetchRecord(String customerName, Date orderDate)`: Retrieves order or null
- `generateOrderID(String customerName, Date orderDate)`: Generates unique order ID
- `recordExists(String customerName, Date orderDate)`: Checks for duplicate orders
- `fetchAllRecords()`: Returns ArrayList of all orders

### 4. Service Layer (com.wipro.order.service)

#### Administrator Methods

**addRecord(OrderBean bean)**
Validations:
1. Null check for bean, customerName, orderDate → "INVALID INPUT"
2. Customer name length < 2 → "INVALID CUSTOMER NAME"
3. Order item length < 2 → "INVALID ORDER ITEM"
4. Duplicate check → "ALREADY EXISTS"
5. Generates orderId and inserts record

**viewRecord(String customerName, Date orderDate)**
- Fetches and returns order bean

**viewAllRecords()**
- Returns list of all orders

### 5. Servlet Layer (com.wipro.order.servlets)

#### MainServlet Operations

**Operation: newRecord**
- Adds new order record
- Success → `success.html`
- Failure → `error.html`

**Operation: viewRecord**
- Views specific order
- Found → `displayOrder.jsp` with order details
- Not Found → `displayOrder.jsp` with "No matching records exists!"

**Operation: viewAllRecords**
- Views all orders
- Records exist → `displayAllOrders.jsp` with list
- No records → `displayAllOrders.jsp` with "No records available!"

## UI Pages

| Page                 | Description                                    |
|---------------------|------------------------------------------------|
| menu.html           | Main menu with navigation links                |
| addOrder.jsp        | Form to add new order                          |
| success.html        | Success confirmation page                      |
| error.html          | Error message page                             |
| viewOrder.jsp       | Form to search for specific order              |
| displayOrder.jsp    | Displays order details or not found message    |
| viewAllOrders.jsp   | Form to view all orders                        |
| displayAllOrders.jsp| Displays all orders or no records message      |

## Screenshots

### Main Menu
<img width="702" height="431" alt="image" src="https://github.com/user-attachments/assets/46fc975f-1ce7-41a8-9b0e-f851c6f18713" />
*Main menu with navigation options: Add Order Record, View Order Record, View All Orders*

---

### Add Order Record Flow

#### Add Order Form
<img width="946" height="494" alt="image" src="https://github.com/user-attachments/assets/be329165-e58f-4efb-9812-4b42dbfbcdaf" />
*Form to enter new order details: customer name, order item, dates, status, remarks*

#### Successful Order Addition
<img width="738" height="433" alt="image" src="https://github.com/user-attachments/assets/4ad974bc-16af-445a-8a44-1b12ba821b4b" />
*Success page displaying "Added successfully"*

#### Order Addition Error
<img width="706" height="284" alt="image" src="https://github.com/user-attachments/assets/e57b61ac-fdfb-4d15-9025-ac35097f8aa3" />
*Error page displaying "Error! Please try again!"*

---

### View All Orders Flow

#### View All Orders Form
<img width="760" height="491" alt="image" src="https://github.com/user-attachments/assets/2c199376-a68d-49bd-9f91-0b736d3d92f0" />
*Form to trigger viewing all orders*

<img width="815" height="341" alt="image" src="https://github.com/user-attachments/assets/5c7b6bc7-3883-477b-9c2a-430a485c00b5" />


---

## Setup Instructions

### Prerequisites
- Java JDK 11 or higher
- Apache Tomcat 10+ (Jakarta EE compatible)
- Oracle Database 11g or higher
- IDE (Eclipse/IntelliJ IDEA/NetBeans)

### Database Setup

1. **Create Sequence**
```sql
CREATE SEQUENCE ORDER_SEQ
START WITH 10
MAXVALUE 99
INCREMENT BY 1;
```

2. **Create Table**
```sql
CREATE TABLE ORDER_TB (
    ORDERID VARCHAR2(12) PRIMARY KEY,
    CUSTOMERNAME VARCHAR2(50) NOT NULL,
    ORDERITEM VARCHAR2(50) NOT NULL,
    ORDER_DATE DATE NOT NULL,
    DELIVERY_DATE DATE,
    STATUS VARCHAR2(20),
    REMARKS VARCHAR2(100)
);
```

3. **Configure Database Connection**
   - Update `DBUtil.java` with your database credentials
   - Set JDBC URL, username, and password

### Application Deployment

1. **Build the Project**
   - Import project into IDE
   - Build the project (Maven/Gradle or IDE build)

2. **Deploy to Tomcat**
   - Export as WAR file
   - Copy WAR to Tomcat `webapps` directory
   - Start Tomcat server

3. **Access Application**
   - URL: `http://localhost:8080/[context-path]/menu.html`

## Usage Guide

### Adding a New Order

1. Click "Add Order Record" from main menu
2. Fill in the form:
   - Customer Name (minimum 2 characters)
   - Order Item (minimum 2 characters)
   - Order Date (required)
   - Delivery Date (optional)
   - Status (optional)
   - Remarks (optional)
3. Submit the form
4. System validates and generates unique Order ID
5. Success or error message displayed

### Viewing a Specific Order

1. Click "View Order Record" from main menu
2. Enter customer name and order date
3. Submit to search
4. Order details displayed if found
5. Error message displayed if not found

### Viewing All Orders

1. Click "View All Orders" from main menu
2. Submit the form
3. List of all orders displayed
4. Message shown if no records exist

## Validation Rules

| Field          | Validation Rule                                    |
|----------------|---------------------------------------------------|
| Customer Name  | Cannot be null, minimum 2 characters              |
| Order Item     | Cannot be null, minimum 2 characters              |
| Order Date     | Cannot be null                                    |
| Duplicate Check| No duplicate orders for same customer and date    |

## Return Values & Messages

### addRecord Method Returns:
- `"INVALID INPUT"` - Null values detected
- `"INVALID CUSTOMER NAME"` - Customer name < 2 characters
- `"INVALID ORDER ITEM"` - Order item < 2 characters
- `"ALREADY EXISTS"` - Duplicate order found
- `"FAIL"` - Database insertion failed
- `Order ID` - Success (e.g., "20241130RA11")

### viewRecord Method Returns:
- `OrderBean` object - Order found
- `null` - Order not found

### viewAllRecords Method Returns:
- `List<OrderBean>` - List of orders (empty if no records)

## Sample Test Cases

### Test Case 1: Add Order with Valid Data
- **Input**: Valid customer name, order item, and date
- **Expected**: Order added successfully, unique ID generated

### Test Case 2: Add Order with Invalid Customer Name
- **Input**: Customer name with < 2 characters
- **Expected**: "INVALID CUSTOMER NAME" error

### Test Case 3: Add Order with Null Values
- **Input**: Null customer name or order date
- **Expected**: InvalidInputException, "INVALID INPUT" message

### Test Case 4: Add Duplicate Order
- **Input**: Same customer name and order date as existing record
- **Expected**: "ALREADY EXISTS" error

### Test Case 5: View Order with Valid Details
- **Input**: Existing customer name and order date
- **Expected**: Order details displayed

### Test Case 6: View Order with Invalid Details
- **Input**: Non-existent customer name or order date
- **Expected**: "No matching records exists!" message

### Test Case 7: View All Orders (Records Exist)
- **Input**: Request to view all orders
- **Expected**: List of all orders displayed

### Test Case 8: View All Orders (Empty Database)
- **Input**: Request to view all orders
- **Expected**: "No records available!" message

### Test Case 9: Test Servlet addRecord Method
- **Input**: HTTP request with valid parameters
- **Expected**: Proper servlet processing and response

### Test Case 10: Test Servlet viewRecord Method
- **Input**: HTTP request with customer name and date
- **Expected**: Proper record retrieval and forwarding

## Error Handling

The application handles various error scenarios:
- **Null Input**: InvalidInputException thrown and caught
- **Invalid Data**: Validation messages returned
- **Database Errors**: "FAIL" status returned
- **Duplicate Records**: "ALREADY EXISTS" message
- **Not Found**: Appropriate "not found" messages displayed

## Date Handling

Date conversion for Order ID generation:
```java
DateFormat f = new SimpleDateFormat("yyyyMMdd");
String temp = f.format(orderDate);
```

## Notes

- The UI pages are for functional purposes and not extensively styled
- Focus on backend functionality and validation
- Ensure database connection is properly configured before testing
- All dates use `java.util.Date` format
- Order ID format strictly follows the specified pattern

## Project Assessment

This application covers limited functionalities for assessment purposes:
- Order creation with validation
- Order retrieval (single and all)
- Duplicate prevention
- Proper MVC architecture implementation

## Version
1.0.0
