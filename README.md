<h1 align="center" id="title">Spring POS BACKEND API</h1>

<p id="description">The Spring POS BACKEND API is a powerful Point 
of Sale system built with the Spring Framework, designed 
to manage customer data, items, and orders in a real-time 
retail environment. It facilitates efficient sales transactions,
inventory tracking, and customer management, utilizing Spring 
Data JPA and Hibernate ORM for robust data handling. With a focus
on flexibility and scalability, this API integrates seamlessly with 
various front-end systems and provides well-documented RESTful endpoints,
allowing for easy customization and extension—ideal for small to 
medium-sized businesses.</p>


<h2> Features</h2>

Here are some of the standout features of the project:

*   Comprehensive management of customers, items, and orders.
*   Real-time inventory tracking for efficient stock control.
*   Order processing with multiple payment options, including cash, card, and mobile payments.
*   MySQL database integration powered by Hibernate ORM.
*   Robust exception handling and input validation using Spring Validator.
*   RESTful API design delivering JSON responses for seamless integration.


<p>1. Clone the Repository</p>

```
https://github.com/maleeshaLH/Spring-Assignment-Backend.git
```

<p>2. Build and Run the Application</p>

```
mvn clean install
```

<p>3. The application will start on</p>

```
http://localhost:8080
```

<h2>API Endpoints</h2> 

<h3>Customer Endpoints</h3>

  * GET /customer: Retrieve all customers.
  * POST /customer: Create a new customer.
  * PUT /customer: Update an existing customer.
  * DELETE /customer/{id}: Delete a customer by ID.

<h3>Item Endpoints</h3>

  * GET /customer: Retrieve a list of all customers.
  * POST /customer: Add a new customer.
  * PUT /customer: Update details of an existing customer.
  * DELETE /customer/{id}: Remove a customer by their ID.

<h3>Order Endpoints</h3>

  * POST /order: Create a new order.
  
<h2> Built with</h2>

Technologies used in the project:

*   Spring Framework - Java-based backend framework
*   Hibernate - ORM (Object-Relational Mapping) for database interactions
*   Spring Data JPA - Data repository layer for simplified database access
*   MySQL - Relational database management system
*   Lombok - Reduces boilerplate code with annotations

<h2> API Documentation </h2>

* You can view the detailed API documentation with example requests and responses <a href="">here</a>.

<h2>🛡️ License:</h2>

This project is available under the MIT License. For more information, please refer to the LICENSE file.