import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//<url-pattern>/editdata</url-pattern>
public class EditDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set Context-type
        response.setContentType("text/html");

        // get output-stream
        PrintWriter out = response.getWriter();

        //respone web-page
        out.println("<!DOCTYPE html>");
        out.println("<html></html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Update Student's Details</title>");
        out.println("</head><body>");
        out.println("<h2 style='text-align: center;'><a href='./index.html'>Back to Home</a></h2>");
        out.println("<div style='margin-left: 570px; margin-top: 40px;'>");
        out.println("<h3><a href='./update.html'>Click here to Search again</a></h3></div><br>");

        // get parameters
        int stuID = Integer.parseInt(request.getParameter("sid"));
        String name = request.getParameter("sname").toString();
        int computer = Integer.parseInt(request.getParameter("computer"));
        int maths = Integer.parseInt(request.getParameter("maths"));
        int science = Integer.parseInt(request.getParameter("science"));
        int english = Integer.parseInt(request.getParameter("english"));
        int hindi = Integer.parseInt(request.getParameter("hindi"));

        //calculate parameters
        int tmarks=CalculateData.getTotalMarks(computer, maths, science, english, hindi);
        float percent= CalculateData.getPercentage(tmarks);
        String division= CalculateData.getDivision(percent);

        try {
            
            //get Database Connection
            Connection connection = DatabaseConnection.getConnection();

            // sql-query
            String sql = "UPDATE demo.studata SET student_name=?, division=?, percent=?, total_marks=?, computer_marks=?, maths_marks=?, science_marks=?, english_marks=?, hindi_marks=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //set data
            preparedStatement.setInt(10, stuID);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, division);
            preparedStatement.setFloat(3, percent);
            preparedStatement.setInt(4, tmarks);
            preparedStatement.setInt(5, computer);
            preparedStatement.setInt(6, maths);
            preparedStatement.setInt(7, science);
            preparedStatement.setInt(8, english);
            preparedStatement.setInt(9, hindi);

            // get updateStatus
            int updateStatus= preparedStatement.executeUpdate();

            if(updateStatus!=0){
                out.println("<div style='font-size: large; margin-left: 530px; margin-top: 10px;'>");
                out.println("<h2>Student's Data Updated !!</h2></div>");
                out.println("</body></html>");
            }
            else{
                out.println("<div style='font-size: large; margin-left: 530px; margin-top: 10px;'>");
                out.println("<h2>Student's Data NOT Updated !!</h2></div>");
                out.println("</body></html>");
            }
        } 
        catch (Exception e) { e.printStackTrace(); }
    }
}
