package dormitory.filter.receptionist;

import dormitory.models.Receptionist;
import dormitory.models.ReceptionistRole;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/login", "/signUp", "/createPassword", "/addReceptionist", "/getInfo"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        Receptionist receptionist = (Receptionist) session.getAttribute("receptionist");
        if (receptionist == null || receptionist.getReceptionistRole() == null || receptionist.getReceptionistRole().equals(ReceptionistRole.INACTIVE)) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("/loginConductor");
        }
    }
}
