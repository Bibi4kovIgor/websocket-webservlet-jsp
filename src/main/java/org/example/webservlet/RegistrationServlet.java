package org.example.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.model.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@WebServlet(value = "/register")
public class RegistrationServlet extends HttpServlet {
  private Set<User> users = new HashSet<>();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect(req.getContextPath() + "/registration.html");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    users.add(new User(req.getParameter("name"), req.getParameter("email"), req.getParameter("password")));
    saveToSession(req);

    HttpSession session = req.getSession();
    Set<User> users = Objects.nonNull(session.getAttribute("users"))
        ? (Set<User>) session.getAttribute("users")
        : Set.of();
    req.setAttribute("users", users);
    req.getRequestDispatcher("/index.jsp").forward(req, resp);
  }

  private void saveToSession(HttpServletRequest req) {
    HttpSession session = req.getSession();
    session.setAttribute("users", users);
  }

  private void fillDummyUsers() {
    users.addAll(Set.of(
        new User("Ihor", "some@email.com", "123"),
        new User("Tom", "tom@email.com", "123"),
        new User("Bill", "bill@email.com", "123")));
  }

  @Override
  public void init() {
    fillDummyUsers();
  }
}
