<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="fuel.in.connect.Dbconnection"%>
<%@ page import="fuel.in.getset.getsetvalues"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Delivered Bookings</title>

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
            String status = "Delivered";
            int uId = getsetvalues.getId();
           
            PreparedStatement ps = con.prepareStatement("select * from bookings where status=? and userId=?");
            ps.setString(1, status);
            ps.setInt(2, uId);
            ResultSet rs = ps.executeQuery();
    %>
    <table border="2">
        <tr>
            <th>ID</th>
            <th>User Id</th>
            <th>Station Id</th>
            <th>Delivery Person Id</th>
            <th>Fuel Type</th>
            <th>Fuel Quantity(In liter)</th>
            <th>Total Bill(In Rs)</th>
            <th>Date</th>
            <th>Status</th>
            <th>Delivery Code</th>
        </tr>
        <%
            while (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                String dateStr = sdf.format(rs.getDate(8)); 
        %>
        <tr>
            <td><%=rs.getInt(1)%></td>
            <td><%=rs.getInt(2)%></td>
            <td><%=rs.getInt(3)%></td>
            <td><%=rs.getInt(4)%></td>
            <td><%=rs.getString(5)%></td>
            <td><%=rs.getFloat(6)%></td>
            <td><%=rs.getFloat(7)%></td>
            <td><%=dateStr%></td> 
            <td><%=rs.getString(9)%></td>
            <td><%=rs.getInt(10)%></td>
        </tr>
        <%
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        %>
    </table>
</center>

<!-- Go to Dashboard Link at the Bottom -->
<a href="4_UserDashboard.html" class="dashboard-link">Go to Dashboard</a>

</body>
</html>
