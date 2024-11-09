package com.uams.servlet;

import java.io.IOException;

import com.uams.entity.software;
import com.uams.repository.SoftwareRepository;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/software")
public class SoftwareServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final SoftwareRepository softwareRepository;

    public SoftwareServlet(SoftwareRepository softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request)) {
        	response.getWriter().write("accessDenied");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/software.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String softwareName = request.getParameter("softwareName");
        String description = request.getParameter("description");

        StringBuilder accessLevels = new StringBuilder();
        String[] levels = request.getParameterValues("accessLevels");
        if (levels != null) {
            for (String level : levels) {
                accessLevels.append(level).append(" ");
            }
        }

        boolean success = storeSoftware(softwareName, description, accessLevels.toString().trim());

        if (success) {
        	response.sendRedirect("software");
        } else {
            response.getWriter().write("Failed to create software. Please try again.");
        }
    }

    private boolean storeSoftware(String softwareName, String description, String accessLevels) {
        try {
            software software = new software();
            software.setSoftwareName(softwareName);
            software.setDescription(description);
            software.setAccessLevels(accessLevels);

            softwareRepository.save(software);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isAdmin(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");
        return "ADMIN".equalsIgnoreCase(role);
    }
}
