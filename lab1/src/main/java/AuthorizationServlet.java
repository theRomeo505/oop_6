import beans.Card;
import command.Command;
import command.factory.CommandFactory;
import command.factory.CommandFactoryImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.write("<h1>Hello Servlet One </h1>");
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if (req.getParameter("login").trim().length()==0||
                req.getParameter("pass").trim().length()==0){
            req.setAttribute("Error","Wrong login , or password");
            req.getRequestDispatcher("/dataError.jsp").forward(req,resp);
        }
        else {
            String sign = req.getParameter("sign");
            CommandFactory factory= CommandFactoryImpl.getFactory();
            Command command;
            if(sign == null){
                req.setAttribute("Error","Error with auth");
                req.getRequestDispatcher("/dataError.jsp").forward(req,resp);
                return;
            }
            else if(sign.equals("in")){
                command = factory.getCommand("Authorization",req,resp);
            }
            else{
                command = factory.getCommand("Registration",req,resp);
            }

            command.execute();
        }
    }
}