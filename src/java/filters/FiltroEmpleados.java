package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Empleado;

public class FiltroEmpleados implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //NECESITAMOS LAS PETICIONES
        HttpServletRequest peticion = (HttpServletRequest) request;
        //necesitamos la uri (string peticion)
        String uri = peticion.getRequestURI();
        //necesitamos recuperar el usuario de la session
        //en nuestro caso , es un empleado
        Empleado empleado = (Empleado) peticion.getSession().getAttribute("EMPLEADO");
        if (uri.contains("proteccion")) {

            //necesitamos validar , zona protegida
            if (empleado != null) {
                //no va a navegar feliz,debemos preguntar si tiene
                //permiso suficiente
                if (empleado.getOficio().equalsIgnoreCase("presidente")
                        || empleado.getOficio().equalsIgnoreCase("director")) {
                    //navega feliz
                    chain.doFilter(request, response);
                } else {
                    //el empleado existe , pero no tiene permiso
                    HttpServletResponse respuesta = (HttpServletResponse) response;
                    respuesta.sendRedirect("../accesodenegado.jsp");
                }

            } else {
                //no se ha validado todavia
                HttpServletResponse respuesta = (HttpServletResponse) response;
                respuesta.sendRedirect("../login.jsp");
            }

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
