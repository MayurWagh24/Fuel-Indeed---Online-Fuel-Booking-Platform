<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.*"%>
    <%@ page import="fuel.in.connect.Dbconnection"%>
<%@ page import="fuel.in.getset.getsetvalues"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete</title>

</head>

<body>
	<%
		Connection con = Dbconnection.connect();
		try {
			PreparedStatement ps = con.prepareStatement("delete from deliveryperson where stationId=?");
			int sId=getsetvalues.getId();
			ps.setInt(1, sId);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("deleted row count = " + i);
				response.sendRedirect("2_UpdateDeliveryPerson.html");
			} else {
				System.out.println("deleted row count = " + i);
				response.sendRedirect("2_DeleteDeliveryPerson.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

	
</body>

</html>