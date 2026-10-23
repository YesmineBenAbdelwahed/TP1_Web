package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();

        Integer visits = (Integer) session.getAttribute("visits");
        String fullName = (String) session.getAttribute("fullName");
        String birthDate = (String) session.getAttribute("birthDate");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Show Session</title></head>");
        out.println("<body>");

        if (visits == null) {
            visits = 1;
            out.println("<h2>Welcome on my site</h2>");
        } else {
            visits++;
            out.println("<h2>Welcome back</h2>");
        }

        session.setAttribute("visits", visits);
        out.println("<p>Number of visits: " + visits + "</p>");

        if (fullName == null || birthDate == null) {

            out.println("<form action='ShowSession' method='post'>");
            out.println("Full name: <input type='text' name='fullName' required><br><br>");
            out.println("Date of birth: <input type='date' name='birthDate' required><br><br>");
            out.println("<input type='submit' value='Submit'>");
            out.println("</form>");

        } else {

            LocalDate today = LocalDate.now();
            LocalDate birth = LocalDate.parse(birthDate);
            LocalDate nextBirthday = birth.withYear(today.getYear());

            if (nextBirthday.isBefore(today)) {
                nextBirthday = nextBirthday.plusYears(1);
            }

            long days = ChronoUnit.DAYS.between(today, nextBirthday);

            out.println("<p>Hi, " + fullName + ". There are "
                    + days + " days to your birthday.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String fullName = request.getParameter("fullName");
        String birthDate = request.getParameter("birthDate");

        HttpSession session = request.getSession();
        session.setAttribute("fullName", fullName);
        session.setAttribute("birthDate", birthDate);

        response.sendRedirect("ShowSession");
    }
}
