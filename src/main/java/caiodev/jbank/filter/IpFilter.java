package caiodev.jbank.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class IpFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {

        var ipAdress = request.getRemoteAddr();
        request.setAttribute("x-user-ip", ipAdress);
        response.setHeader("x-user-ip", ipAdress);

        chain.doFilter(request, response);


    }
}
