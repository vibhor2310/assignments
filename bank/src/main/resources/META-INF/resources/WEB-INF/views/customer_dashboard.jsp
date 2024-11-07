<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.spring.bank.model.Customer" %>
<html>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <body>
        <%
            // Retrieve the list of customers from the request scope
            List<Customer> customers = (List<Customer>) request.getAttribute("listCustomers");
        %>
        <div class="container-fluid" style="margin: 0%; padding: 0px;">
            <div class="row">
                <div class="col-lg-12">
                    <%@ include file="navbar.jsp" %>
                </div>
            </div>
            <div class="row">
                <!-- Sidebar Section with Add Employee Button -->
                <div class="col-sm-2 p-3 bg-light">
                    <button onclick="window.location.href='<%= request.getContextPath() %>/add-customer'" 
                            class="btn btn-primary w-100">Add New Customer</button>
                </div>

                <!-- Main Content Section -->
                <div class="col-md-10 mt-4">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="display-6">Customer List</h1>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Customer Id</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Contact</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for(Customer c : customers) { %>
                                        <tr>
                                            <th scope="row"><%= c.getId() %></th>
                                            <td><%= c.getId() %></td>
                                            <td><%= c.getName() %></td>
                                            <td><%= c.getContact() %></td>
                                            <td><%= c.getGender() %></td>
                                            <td>
                                                <a href="<%=request.getContextPath() %>/delete-customer?cid=<%=c.getId() %>" class="text-danger">Delete</a>
                                            </td>
                                        </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>