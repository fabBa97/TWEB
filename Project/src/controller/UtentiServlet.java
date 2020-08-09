package servlet;

import model.Model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;


@WebServlet(name = "UtentiServlet")
public class UtentiServlet extends HttpServlet {

    Model model = null;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext ctx = config.getServletContext();
        String url = ctx.getInitParameter("DB-URL");
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("pwd");
        model = new Model(url, user, pwd);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setContentType("UTF-8"); // per essere robusti rispetto a caratteri speciali (', etc)
        HttpSession session = request.getSession();
        // to maintain the user session
        if (request.getParameter("JSESSIONID") != null) {
            Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
            response.addCookie(userCookie);
        } else {
            String sessionId = session.getId();
            Cookie userCookie = new Cookie("JSESSIONID", sessionId);
            response.addCookie(userCookie);
        }

        if (session.isNew()) {
            System.out.println("New session authentication - " + session.getId());
        }
        else {
            System.out.println("Old session authentication - " + session.getId());
        }

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(userName + " " + password);

        //verify login data on db
        Model.registerDriver();
        dao.Utenti user= Model.autenticazione(userName, password);

        response.setContentType("application/json");
        if ( user == null) {
            try (PrintWriter out = response.getWriter()) {
                out.print(false);
            }
        }
        else {
            session.setAttribute("userName", userName);
            session.setAttribute("permission", user.getRuolo());

            Gson gson = new Gson();
            String juser = gson.toJson(user);
            System.out.println(juser);

            try (PrintWriter out = response.getWriter()) {
                out.print(juser);
                //System.out.println(gson.toJson(true));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Risposta mandata all app vue");

            //response.sendRedirect(getServletContext().getContextPath());
            /*HttpServletResponse newResp;
            ServletContext servletContext = getServletContext();
            RequestDispatcher rd = servletContext.getRequestDispatcher("/index.html");
            try{
                System.out.println("before forward");
                rd.forward(request, newResp);
                System.out.println("forward sent");
            }
            catch (IllegalStateException exception) {
                System.out.println(exception.getMessage());
            }*/

        }
}