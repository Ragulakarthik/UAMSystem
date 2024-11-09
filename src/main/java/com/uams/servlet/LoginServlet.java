package com.uams.servlet;

import java.io.IOException;
import java.util.Optional;

import com.uams.entity.users;
import com.uams.repository.UserRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final UserRepository userRepository;

    public LoginServlet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<users> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            users user = userOptional.get();
            String role = user.getRole().name();
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", role);

            if (user.getPassword().equals(password)) {
                if ("ADMIN".equalsIgnoreCase(role)) {
                    response.sendRedirect("software");
                }
                else if ("EMPLOYEE".equalsIgnoreCase(role)) {
                    response.sendRedirect("requestAccess");
                }
                else if ("MANAGER".equalsIgnoreCase(role)) {
                    response.sendRedirect("pendingRequests");
                }
                else {
                    response.getWriter().write("Login successful, but you're not an admin/manager/employee.");
                }
            } else {
                response.getWriter().write("Invalid password.");
            }
        } else {
            response.getWriter().write("User not found.");
        }
    }
}
