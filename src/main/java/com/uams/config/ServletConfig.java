package com.uams.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uams.repository.RequestRepository;
import com.uams.repository.SoftwareRepository;
import com.uams.repository.UserRepository;
import com.uams.servlet.ApprovalServlet;
import com.uams.servlet.LoginServlet;
import com.uams.servlet.RequestServlet;
import com.uams.servlet.SignUpServlet;
import com.uams.servlet.SoftwareServlet;

@Configuration
public class ServletConfig {

    private final UserRepository userRepository;
    
    private final SoftwareRepository softwareRepository;
    
    private final RequestRepository requestRepository;

    public ServletConfig(UserRepository userRepository, SoftwareRepository softwareRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.softwareRepository = softwareRepository;
        this.requestRepository = requestRepository;
    }

    @Bean
    public ServletRegistrationBean<LoginServlet> loginServlet() {
        return new ServletRegistrationBean<>(new LoginServlet(userRepository), "/login");
    }
    
    @Bean
    public ServletRegistrationBean<SignUpServlet> signUpServlet() {
        return new ServletRegistrationBean<>(new SignUpServlet(userRepository), "/signup");
    }
    
    @Bean
    public ServletRegistrationBean<SoftwareServlet> softwareServlet() {
        return new ServletRegistrationBean<>(new SoftwareServlet(softwareRepository), "/software");
    }
    
    @Bean
    public ServletRegistrationBean<RequestServlet> requestServlet() {
        return new ServletRegistrationBean<>(new RequestServlet(requestRepository,softwareRepository,userRepository), "/requestAccess");
    }
    
    @Bean
    public ServletRegistrationBean<ApprovalServlet> approvalServlet() {
        return new ServletRegistrationBean<>(new ApprovalServlet(requestRepository), "/pendingRequests");
    }
}
