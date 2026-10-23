package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Lire les cookies</title></head>");
        out.println("<body>");
        out.println("<h2>Valeurs des cookies</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Nom du cookie</th><th>Valeur</th></tr>");

        Cookie[] cookies = request.getCookies();

        String[] names = {"firstName", "LastName", "birthDay", "Sex"};

        for (String name : names) {
            String value = "Non trouvé";

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(name)) {
                        value = URLDecoder.decode(
                                cookie.getValue(), StandardCharsets.UTF_8);
                        break;
                    }
                }
            }

            out.println("<tr><td>" + name + "</td><td>" + value + "</td></tr>");
        }

        out.println("</table>");
        out.println("<p><a href='FORM.html'>Retour au formulaire</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}
