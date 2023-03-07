package etu1846.framework;

import javax.servlet.*;
import javax.servlet.http.*;

public class Utility {
    public String printRequestedPath(HttpServletRequest request)throws ServletException{
        return request.getServletPath();
    }
    public String printQuery(HttpServletRequest request)throws ServletException{
        return request.getQueryString();
    }
}
