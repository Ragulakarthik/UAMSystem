package com.uams.servlet;

import java.io.IOException;
import java.util.List;

import com.uams.entity.request;
import com.uams.repository.RequestRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pendingRequests")
public class ApprovalServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final RequestRepository requestRepository;

    public ApprovalServlet(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<request> pendingRequests = requestRepository.findByStatus("Pending");
        request.setAttribute("pendingRequests", pendingRequests);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/pendingRequests.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        Long requestId = Long.parseLong(request.getParameter("requestId"));
        String action = request.getParameter("action");

        request accessRequest = requestRepository.findById(requestId).get();

        if (accessRequest != null) {
        	
            if ("approve".equals(action)) {
                accessRequest.setStatus("Approved");
            } else if ("reject".equals(action)) {
                accessRequest.setStatus("Rejected");
            }

            requestRepository.save(accessRequest);

            response.sendRedirect("pendingRequests");
        } else {
            response.getWriter().write("Request not found!");
        }
    }
}
