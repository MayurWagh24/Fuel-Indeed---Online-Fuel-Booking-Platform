<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="fuel.in.connect.Dbconnection"%>
<%@ page import="fuel.in.getset.getsetcity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Fuel Stations by City</title>

<style>
    /* Background styling */
    body {
        background-image: url('img/photos/Fuel Login Page Background Image.webp');
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
        color: #f8f9fa;
        margin: 0;
        font-family: Arial, sans-serif;
    }

    /* Table styling */
    table {
        width: 90%; /* Adjust width to fit the screen */
        max-width: 1100px; /* Set a max width for large screens */
        border-collapse: collapse;
        background: rgba(0, 0, 0, 0.6); /* Semi-transparent black background */
        border-radius: 15px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        margin: 20px auto; /* Center the table */
    }

    table, th, td {
        border: 1px solid #ddd; /* Light border color */
        padding: 8px 12px; /* Adjust padding to make the table smaller */
        text-align: center;
        color: #fff; /* White text color for better contrast */
    }

    th {
        background-color: rgba(255, 255, 255, 0.3); /* Light transparent background for headers */
    }

    tr:nth-child(even) {
        background-color: rgba(255, 255, 255, 0.2); /* Slightly lighter background for even rows */
    }

    tr:nth-child(odd) {
        background-color: rgba(255, 255, 255, 0.1); /* Slightly darker background for odd rows */
    }

    /* Book button styling */
    .book-button {
        display: inline-block;
        padding: 5px 10px;
        background-color: #28a745; /* Green background */
        color: white;
        text-decoration: none;
        font-size: 14px;
        font-weight: bold;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3); /* Slight shadow */
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    .book-button:hover {
        background-color: #218838; /* Darker green on hover */
        transform: scale(1.1); /* Slightly enlarge on hover */
    }

    .book-button:active {
        background-color: #1e7e34; /* Even darker green on click */
        transform: scale(1.05); /* Slightly smaller when clicked */
    }

    /* Dashboard link styling */
    .dashboard-link {
        display: block;
        text-align: center;
        font-size: 18px;
        margin-top: 20px;
        padding: 10px;
        background-color: #ff6347; /* Tomato red background */
        color: white;
        text-decoration: none;
        border-radius: 5px;
        font-weight: bold;
        width: 200px;
        margin: 20px auto;
        transition: background-color 0.3s ease;
        position: fixed; /* Fix it at the bottom */
        bottom: 20px; /* 20px from the bottom of the page */
        left: 50%;
        transform: translateX(-50%); /* Center horizontally */
    }

    .dashboard-link:hover {
        background-color: #e55347; /* Darker red on hover */
    }
</style>
</head>
<body>
<center>
    <%
        Connection con = Dbconnection.connect();
        try {
            String status = "Approved";
            String city = getsetcity.getCity();
            PreparedStatement ps = con.prepareStatement("select * from fuelstation where status=? and city=?");
            ps.setString(1, status);
            ps.setString(2, city);
            ResultSet rs = ps.executeQuery();
    %>

    <table border="2">
        <tr>
            <th>Station ID</th>
            <th>Name</th>
            <th>OpenTime</th>
            <th>CloseTime</th>
            <th>Address</th>
            <th>Area</th>
            <th>City</th>
            <th>Pincode</th>
            <th>Petrol Quantity (in Liters)</th>
            <th>Petrol Rate (in Rs.)</th>
            <th>Diesel Quantity (in Liters)</th>
            <th>Diesel Rate (in Rs.)</th>
            <th>MobileNo</th>
            <th>Email</th>
            <th>Status</th>
            <th>Book</th>
        </tr>
        <%
            while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt(1) %></td>
            <td><%= rs.getString(2) %></td>
            <td><%= rs.getString(3) %></td>
            <td><%= rs.getString(4) %></td>
            <td><%= rs.getString(5) %></td>
            <td><%= rs.getString(6) %></td>
            <td><%= rs.getString(7) %></td>
            <td><%= rs.getInt(8) %></td>
            <td><%= rs.getFloat(9) %></td>
            <td><%= rs.getInt(10) %></td>
            <td><%= rs.getFloat(11) %></td>
            <td><%= rs.getInt(12) %></td>
            <td><%= rs.getString(13) %></td>
            <td><%= rs.getString(14) %></td>
            <td><%= rs.getString(16) %></td>
            <td><a href="4_BookFuelStation.jsp?id=<%= rs.getInt(1) %>" class="book-button">Book</a></td>
        </tr>
        <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        %>
    </table>
</center>

<!-- Go to Dashboard Link at the Bottom -->
<a href="4_UserDashboard.html" class="dashboard-link">Go to Dashboard</a>

</body>
</html>
