package ouhk.comps380f.fproject.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "storeServlet", urlPatterns = {"/items"})
public class StoreServlet extends HttpServlet {

    private final Map<Integer, String> products = new Hashtable<>();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "browse";
        }

        switch (action) {
            case "addToCart":
                this.addToCart(request, response);
                break;
            case "viewCart":
                this.viewCart(request, response);
                break;
            case "emptyCart":
                this.emptyCart(request, response);
                break;
            case "browse":
            default:
                this.browse(request, response);
                break;
        }
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", this.products);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewCart.jsp")
                .forward(request, response);
    }

    private void browse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("products", this.products);
        request.getRequestDispatcher("/WEB-INF/jsp/view/itemInfo.jsp")
                .forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String item;
        try {
            item = request.getParameter("item");
        } catch (Exception e) {
            response.sendRedirect("items");
            return;
        }
        
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Hashtable<>());
        }
        @SuppressWarnings("unchecked")
        Map<String, Integer> cart
                = (Map<String, Integer>) session.getAttribute("cart");
        if (!cart.containsKey(item)) {
            cart.put(item, 0);
        }
        cart.put(item, cart.get(item) + 1);

        response.sendRedirect("items");
    }

    private void emptyCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("cart");
        response.sendRedirect("items?action=viewCart");
    }

}
