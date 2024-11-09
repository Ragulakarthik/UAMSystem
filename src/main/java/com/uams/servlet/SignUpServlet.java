package com.uams.servlet;

import java.io.IOException;

import com.uams.entity.userRole;
import com.uams.entity.users;
import com.uams.repository.UserRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final UserRepository userRepository;

	public SignUpServlet(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        users newUser = new users();
        newUser.setUsername(username);
        newUser.setPassword(password); 
        newUser.setRole(userRole.EMPLOYEE);
        
        boolean isSaved = saveUser(newUser);

        if (isSaved) {
            response.sendRedirect("login");
        } else {
            request.setAttribute("errorMessage", "User registration failed. Try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");
            dispatcher.forward(request, response);
        }
    }

    private boolean saveUser(users newUser) {
        try {
            userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
