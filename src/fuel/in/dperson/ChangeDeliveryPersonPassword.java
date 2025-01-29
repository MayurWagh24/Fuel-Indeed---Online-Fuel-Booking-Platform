package fuel.in.dperson;

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

public class ChangeDeliveryPersonPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ChangeDeliveryPersonPassword() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // No need to handle GET requests in this case
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Get the logged-in user's ID (You can change this logic to get the ID from session, if needed)
        int dId = getsetvalues.getId();

        // Create a connection to the database
        Connection con = Dbconnection.connect();
        PreparedStatement ps;
        ResultSet rs;

        try {
            // Query to get the stored password from the database
            ps = con.prepareStatement("SELECT password FROM deliveryperson WHERE dId = ?");
            ps.setInt(1, dId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // Validate old password and ensure new password matches the confirm password
                if (storedPassword.equals(oldPassword)) {
                    if (newPassword.equals(confirmPassword)) {
                        // Update the password in the database
                        ps = con.prepareStatement("UPDATE deliveryperson SET password = ? WHERE dId = ?");
                        ps.setString(1, newPassword);
                        ps.setInt(2, dId);

                        int rowsUpdated = ps.executeUpdate();

                        if (rowsUpdated > 0) {
                            // Redirect to success page with success message
                            response.sendRedirect("3_ChangeDeliveryPersonPassword.html?success=Password%20changed%20successfully!");
                        } else {
                            // Redirect to the change password page with an error message
                            response.sendRedirect("3_ChangeDeliveryPersonPassword.html?error=Password%20update%20failed!");
                        }
                    } else {
                        // New password and confirm password do not match
                        response.sendRedirect("3_ChangeDeliveryPersonPassword.html?error=New%20password%20and%20confirm%20password%20do%20not%20match!");
                    }
                } else {
                    // Old password is incorrect
                    response.sendRedirect("3_ChangeDeliveryPersonPassword.html?error=Incorrect%20old%20password!");
                }
            } else {
                // Delivery person not found
                response.sendRedirect("3_ChangeDeliveryPersonPassword.html?error=User%20not%20found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("3_ChangeDeliveryPersonPassword.html?error=An%20error%20occurred%20while%20updating%20password.");
        }
    }
}
