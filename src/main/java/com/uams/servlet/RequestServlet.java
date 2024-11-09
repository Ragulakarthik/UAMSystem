package com.uams.servlet;

import java.io.IOException;
import java.util.List;

import com.uams.entity.request;
import com.uams.entity.software;
import com.uams.entity.users;
import com.uams.repository.RequestRepository;
import com.uams.repository.SoftwareRepository;
import com.uams.repository.UserRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final RequestRepository requestRepository;
    private final SoftwareRepository softwareRepository;
    private final UserRepository userRepository;

    public RequestServlet(RequestRepository requestRepository, SoftwareRepository softwareRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.softwareRepository = softwareRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<software> softwareList = softwareRepository.findAll();
        request.setAttribute("softwareList", softwareList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/requestAccess.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Long userId = (Long) session.getAttribute("userId");
    	if (userId == null) {
    	    response.sendRedirect("login");
    	    return;
    	}

        users user = userRepository.findById(userId).get();
        Long softwareId = Long.parseLong(request.getParameter("softwareId"));
        software software = softwareRepository.findById(softwareId).get();

        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        if (user != null && software != null) {
            request accessRequest = new request();
            accessRequest.setUser(user);
            accessRequest.setSoftware(software);
            accessRequest.setAccessType(accessType);
            accessRequest.setReason(reason);
            accessRequest.setStatus("Pending");

            requestRepository.save(accessRequest);
            response.sendRedirect("requestAccess");
        } else {
            response.getWriter().write("Failed to submit access request.");
        }
    }
}
