package org.example.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@WebServlet(name = "all-users", urlPatterns = {"/all"})
public class GetAllUsersServlet extends HttpServlet {
  @SuppressWarnings("unchecked")
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    HttpSession session = req.getSession();


    Set<User> users = Objects.nonNull(session.getAttribute("users"))
        ? (Set<User>) session.getAttribute("users")
        : Set.of();
    req.setAttribute("users", users);
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }
}
