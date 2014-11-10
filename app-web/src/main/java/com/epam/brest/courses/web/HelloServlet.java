package com.epam.brest.courses.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by beast on 10.11.14. At 10.53
 */
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String parameter = req.getParameter("name");

        resp.setContentType("text/plain");

        try (PrintWriter out = resp.getWriter()) {
            out.print("Hello ");
            out.print(parameter);
        }

    }

}