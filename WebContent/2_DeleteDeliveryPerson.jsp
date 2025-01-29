<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*"%>
    <%@ page import="fuel.in.connect.Dbconnection"%>
<%@ page import="fuel.in.getset.getsetvalues"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Delivery Person</title>
<style>
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

    /* Styling for the page */
    center {
        margin-top: 50px;
    }
    .delete-btn {
        background-color: #007bff; /* Blue background */
        color: white;
        padding: 6px 12px; /* Smaller padding for buttons */
        border-radius: 5px;
        text-decoration: none;
        font-weight: bold;
        display: inline-block;
        margin: 5px;
        transition: background-color 0.3s ease;
    }

    .delete-btn:hover {
        background-color: #0056b3; /* Darker blue on hover */
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
        position: fixed;
        bottom: 20px;
        left: 50%;
        transform: translateX(-50%);
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
				
				
				PreparedStatement ps = con.prepareStatement("select * from deliveryperson where stationId=?");
				int sId=getsetvalues.getId();
				ps.setInt(1,sId);
				ResultSet rs = ps.executeQuery();
				System.out.println("Succefully Executed");
		%>


		<table border="2">
			<tr>
				<th>Delivery Person ID</th>
				<th>Station ID</th>
				<th>Name</th>
				<th>City</th>
				<th>MobileNo</th>
				<th>Address</th>
				<th>Pincode</th>
				<th>Email</th>
				<th>Password</th>
				<th>Delete</th>
				
				
			</tr>
			<%
				while (rs.next()) {
			%>
			<TR>
				<TD><%=rs.getInt(1)%></TD>
				<TD><%=rs.getInt(2)%></TD>
				<TD><%=rs.getString(3)%></TD>
				<TD><%=rs.getString(4)%></TD>
				<TD><%=rs.getString(5)%></TD>
				<TD><%=rs.getString(6)%></TD>
				<TD><%=rs.getInt(7)%></TD>
				<TD><%=rs.getString(8)%></TD>
				<TD><%=rs.getString(9)%></TD>
				<TD><a class = "delete-btn" href="2_Delete.jsp">Delete</a></TD>
				
				
			</TR>
			<%}

	
}catch(Exception e)
{
	e.printStackTrace();
}%>
		</table>
	</center>
	<a href="2_UpdateDeliveryPerson.html" class="dashboard-link">Go to Dashboard</a>

</body>
</html>