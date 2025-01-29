<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="fuel.in.connect.Dbconnection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Assign</title>
    <style>
        /* Set background image */
        body {
            background-image: url('img/photos/Fuel Login Page Background Image.webp'); /* Replace with your image path */
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Center the form in the page */
        .form-container {
            width: 400px;
            margin: 150px auto;
            padding: 30px;
            background-color: rgba(255, 255, 255, 0.8); /* Transparent white */
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
        }

        .form-container label {
            font-weight: bold;
            display: block;
            margin-bottom: 10px;
        }

        .form-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }

        .form-container button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<% 
// Retrieve the booking ID parameter
int id = Integer.parseInt(request.getParameter("id"));
Connection con = Dbconnection.connect();

// Check if the form is being submitted
if (request.getMethod().equalsIgnoreCase("POST")) {
    // Retrieve the delivery person ID and delivery code from the form
    int dId = Integer.parseInt(request.getParameter("did"));
    String dcode = request.getParameter("deliverycode");

    try {
        // Update the delivery person ID in the database
        PreparedStatement pst = con.prepareStatement("UPDATE bookings SET deliveryPersonId=?, deliveryCode=? WHERE id=?");
        pst.setInt(1, dId);
        pst.setString(2, dcode);
        pst.setInt(3, id);

        int ni = pst.executeUpdate();
        if (ni > 0) {
            System.out.println("Delivery Person Id and Code Entered Count: " + ni);
            response.sendRedirect("2_ViewPendingBookings.jsp");
        } else {
            response.sendRedirect("2_ViewPendingBookings.jsp");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
} else {
    // If form is not yet submitted, show the form
    try {
        PreparedStatement ps = con.prepareStatement("UPDATE bookings SET Status=? WHERE id=?");
        ps.setInt(2, id);
        String status = "Assigned";
        ps.setString(1, status);

        int i = ps.executeUpdate();
        if (i > 0) {
            System.out.println("Updated row count = " + i);
        } else {
            System.out.println("No rows updated.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Display the form for entering delivery person ID and code
%>
    <div class="form-container">
        <form action="Assign" method="post">
            <label for="DeliveryPersonId">Enter Delivery Person Id</label>
            <input type="number" id="DeliveryPersonId" name="did" required>
            <label for="deliverycode">Enter Delivery Code</label>
            <input type="number" id="deliverycode" name="deliverycode" required>
            <button type="submit">Submit</button>
        </form>
    </div>
<% 
}
%>
</body>
</html>
