import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//<url-pattern>/add</url-pattern>
public class AddDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set Context-type
        response.setContentType("text/html");

        // get output-stream
        PrintWriter out = response.getWriter();

        // get parameters
        int stuID = Integer.parseInt(request.getParameter("sid"));
        String name = request.getParameter("sname").toString();
        int computer = Integer.parseInt(request.getParameter("computer"));
        int maths = Integer.parseInt(request.getParameter("maths"));
        int science = Integer.parseInt(request.getParameter("science"));
        int english = Integer.parseInt(request.getParameter("english"));
        int hindi = Integer.parseInt(request.getParameter("hindi"));

        //calculate parameters
        int tmarks= CalculateData.getTotalMarks(computer, maths, science, english, hindi);
        float percent= CalculateData.getPercentage(tmarks);
        String division= CalculateData.getDivision(percent);

        // set status
        String status =null;

        try {
            
            //get Database Connection
            Connection connection = DatabaseConnection.getConnection();

            // sql-query
            String sql = "INSERT INTO demo.studata VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //set data
            preparedStatement.setInt(1, stuID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, division);
            preparedStatement.setFloat(4, percent);
            preparedStatement.setInt(5, tmarks);
            preparedStatement.setInt(6, computer);
            preparedStatement.setInt(7, maths);
            preparedStatement.setInt(8, science);
            preparedStatement.setInt(9, english);
            preparedStatement.setInt(10, hindi);

            // get updateStatus
            int updateStatus= preparedStatement.executeUpdate();

            if (updateStatus != 0) {
                status = "Student's details added";
                
            } else {
                status = "Student's details NOT added try again";
            }

        } catch (Exception e) { e.printStackTrace(); }

        // resonse web-page
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Add Details</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2 style='text-align: center;'><a href='./index.html'>Back to Home</a></h2>");
        out.println("<h2 style='text-align: center;'>"+status+"</h2>");
        out.println("<br>");
        out.println("<h4 style='text-align: center;'>Want to add more Click below</h4>");
        out.println("<h3 style='text-align: center;'><a href='./add.html'>Add Student's Details</a></h3>");
        out.println("</body></html>");
    }
}
