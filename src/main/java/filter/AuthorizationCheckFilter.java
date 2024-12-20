package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebFilter(urlPatterns = "/*")
public class AuthorizationCheckFilter extends HttpFilter {
    private static String loginPage;
    private static String[] publicPages;
    private static String[] resourcePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginPage = "/login";
        publicPages = new String[]{"/login", "/register", "/"};
        resourcePath = new String[]{"/css/", "/js/", "/img/"};
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean isAuthorized = (session != null && session.getAttribute("user") != null);
        req.setAttribute("isAuthorized", isAuthorized);

        if (!isAuthorized && !isPublicPage(uri) && !isAvailablePath(uri)) {
            res.sendRedirect(loginPage);
        } else {
            chain.doFilter(req, res);
        }
    }

    private boolean isPublicPage(String uri) {
        for (String publicPage : publicPages) {
            if (uri.equals(publicPage)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAvailablePath(String uri) {
        for (String resourcePath : resourcePath) {
            if (uri.startsWith(resourcePath)) {
                return true;
            }
        }
        return false;
    }
}
