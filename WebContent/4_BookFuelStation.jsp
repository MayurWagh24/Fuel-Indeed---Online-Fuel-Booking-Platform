<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="fuel.in.connect.Dbconnection"%>
<%@ page import="fuel.in.getset.getsetcity"%>
<%@ page import="fuel.in.getset.getsetvalues"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Booking Information</title>
<style>
    /* Background Styling */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-image: url('img/photos/Fuel Login Page Background Image.webp');
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
        color: #ffffff;
    }

    /* Form Styling */
    form {
        width: 350px;
        margin: 50px auto;
        padding: 20px;
        background-color: rgba(0, 0, 0, 0.7); /* Semi-transparent background */
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        color: #ffffff;
    }

    label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
        color: #ffffff;
    }

    input[type="text"],
    input[type="number"],
    input[type="date"],
    input[type="submit"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        font-size: 14px;
    }

    input[type="submit"] {
        background-color: #007bff;
        color: white;
        font-weight: bold;
        cursor: pointer;
        transition: background-color 0.3s ease, transform 0.2s ease;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
        transform: scale(1.05);
    }

    input[type="submit"]:active {
        background-color: #004085;
        transform: scale(1.02);
    }

    /* Centered Text */
    h1 {
        text-align: center;
        margin-top: 20px;
        color: #ffffff;
        text-shadow: 0 2px 4px rgba(0, 0, 0, 0.7);
    }
</style>
</head>
<body>

<h1>User Booking Information</h1>

<form action="" method="post">
    <label for="city">Enter City Name:</label>
    <input type="text" id="city" name="city" required>

    <label for="fuelType">Enter Fuel Type:</label>
    <input type="text" id="fuelType" name="fuelType" required>

    <label for="fuelQuantity">Enter Fuel Quantity:</label>
    <input type="number" id="fuelQuantity" name="fuelQuantity" required>

    <label for="date">Enter Date:</label>
    <input type="date" id="date" name="date" required>

    <input type="submit" value="Submit">
</form>

<%
    Connection con = Dbconnection.connect();
    try {
        int sId = Integer.parseInt(request.getParameter("id"));
        int uId = getsetvalues.getId();
        String city = request.getParameter("city");  // Added city field
        String fuelType = request.getParameter("fuelType");
        float fuelQuantity = Float.parseFloat(request.getParameter("fuelQuantity"));
        Date date = Date.valueOf(request.getParameter("date"));

        PreparedStatement pstmt = con.prepareStatement("insert into bookings values(?,?,?,?,?,?,?,?,?,?,?)");
        pstmt.setInt(1, 0);
        pstmt.setInt(2, uId);
        pstmt.setInt(3, sId);
        pstmt.setInt(4, 0);
        pstmt.setString(5, fuelType);
        pstmt.setFloat(6, fuelQuantity);
        pstmt.setFloat(7, 0);
        pstmt.setDate(8, date);
        pstmt.setString(9, city); // Insert city name into database
        pstmt.setString(10, "pending");
        pstmt.setString(11, "Null");

        int i = pstmt.executeUpdate();
        if (i > 0) {
            System.out.println("Fuel information inserted successfully.");
            response.sendRedirect("4_SearchFuelStationBy.html");
        } else {
            response.sendRedirect("4_BookFuelStation.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>
