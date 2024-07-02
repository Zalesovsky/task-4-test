package org.example.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ServletUtils {
    public static void info(HttpServletRequest request, String msg) {
        request.getMethod();
        request.getContextPath();
        request.getServletPath();
        request.getRequestURI();
        request.getRequestURL();
        System.out.println(String.format("%s INFO [%s] Method : %s | Path : %s | %s ",
                new Date().toString(), Thread.currentThread().getName(), request.getMethod(), request.getServletPath(), msg));
    }

    public static void forward(HttpServletRequest req, HttpServletResponse resp, String path) {
        info(req, "FORWARD to -> " + path);
        try {
            req.getRequestDispatcher(path).forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void openJSP(HttpServletRequest req, HttpServletResponse resp, String jspName) {
        forward(req, resp, "/" + jspName + ".jsp");
    }

    public static void openGenericMessageJSP(HttpServletRequest req, HttpServletResponse resp, String msg) {
        req.setAttribute("msg", msg);
        openJSP(req, resp, "generic-message");
    }

    public static void showError(HttpServletRequest request, HttpServletResponse response, String msg) {
        request.setAttribute("error-msg", msg);
        openJSP(request, response, "generic-error");
    }

    public static void include(HttpServletRequest req, HttpServletResponse resp, String path, String msg) {
        info(req, "INCLUDE to -> " + path);
        try {
            resp.getWriter().print("<s>" + msg + "</s>");
            RequestDispatcher rd = req.getRequestDispatcher(path);
            rd.include(req, resp);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }



    public static void invalidateSession(HttpServletRequest request) {
        System.out.println("Logout of session # " + request.getSession().getId() + "successful!");
        request.getSession().invalidate();
    }
}
