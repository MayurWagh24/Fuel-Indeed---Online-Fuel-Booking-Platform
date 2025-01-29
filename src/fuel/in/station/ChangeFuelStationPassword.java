package fuel.in.station;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fuel.in.connect.Dbconnection;
import fuel.in.getset.getsetvalues;

/**
 * Servlet implementation class ChangeFuelStationPassword
 */
public class ChangeFuelStationPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangeFuelStationPassword() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Do nothing for GET requests
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        int sId = getsetvalues.getId(); // Get the ID of the fuel station

        Connection con = Dbconnection.connect();
        PreparedStatement ps;
        ResultSet rs;

        try {
            // Query to get the stored password from the database
            ps = con.prepareStatement("SELECT password FROM fuelstation WHERE sId = ?");
            ps.setInt(1, sId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // Validate old password and ensure new password matches confirm password
                if (storedPassword.equals(oldPassword)) {
                    if (newPassword.equals(confirmPassword)) {
                        // Update the password in the database
                        ps = con.prepareStatement("UPDATE fuelstation SET password = ? WHERE sId = ?");
                        ps.setString(1, newPassword);
                        ps.setInt(2, sId);

                        int rowsUpdated = ps.executeUpdate();

                        if (rowsUpdated > 0) {
                            // Redirect to success page with success message
                            response.sendRedirect("2_ChangeFuelStationPassword.html?success=Password%20changed%20successfully!");
                        } else {
                            // Redirect to the change password page with an error message
                            response.sendRedirect("2_ChangeFuelStationPassword.html?error=Password%20update%20failed!");
                        }
                    } else {
                        // New password and confirm password do not match
                        response.sendRedirect("2_ChangeFuelStationPassword.html?error=New%20password%20and%20confirm%20password%20do%20not%20match!");
                    }
                } else {
                    // Old password is incorrect
                    response.sendRedirect("2_ChangeFuelStationPassword.html?error=Incorrect%20old%20password!");
                }
            } else {
                // Fuel station not found
                response.sendRedirect("2_ChangeFuelStationPassword.html?error=User%20not%20found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("2_ChangeFuelStationPassword.html?error=An%20error%20occurred%20while%20updating%20password.");
        }
    }
}
