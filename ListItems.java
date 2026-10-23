package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListItems extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String newItem = request.getParameter("newItem");

        HttpSession session = request.getSession();

        ArrayList<String> items =
                (ArrayList<String>) session.getAttribute("items");

        HashMap<String, Integer> counts =
                (HashMap<String, Integer>) session.getAttribute("counts");

        if (items == null) {
            items = new ArrayList<>();
            counts = new HashMap<>();
        }

        if (newItem != null && !newItem.trim().isEmpty()) {
            newItem = newItem.trim();
            items.add(newItem);

            counts.put(newItem, counts.getOrDefault(newItem, 0) + 1);
        }

        session.setAttribute("items", items);
        session.setAttribute("counts", counts);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>List Items</title></head>");
        out.println("<body>");

        out.println("<h2>List of items</h2>");
        out.println("<ul>");

        for (String item : items) {
            out.println("<li>" + item + " - entered "
                    + counts.get(item) + " time(s)</li>");
        }

        out.println("</ul>");
        out.println("<p><a href='items.html'>Add another item</a></p>");

        out.println("</body>");
        out.println("</html>");
    }
}
