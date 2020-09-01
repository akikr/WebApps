import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//<url-pattern>/contact</url-pattern>
public class ContactServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //set Context-type
        response.setContentType("text/html");

        //get output-stream
        PrintWriter out=response.getWriter();

        // get parameter-name
        String name = request.getParameter("name");

        //resonse web-page
        out.println("<!DOCTYPE html>");
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Thank You</title>");
        out.println("</head>");
        out.println("<body style='background-color:lightblue'>");
        out.println("<h2 style='text-align: center;'>Thank you "+name+" for writing to us</h2>");
        out.println("<h2 style='text-align: center;'>We will response soon !!</h2>");
        out.println("<br>");
        out.println("</body></html>");
    }
}
