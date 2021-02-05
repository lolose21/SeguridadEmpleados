<%-- 
    Document   : usuarioempleado
    Created on : 04-feb-2021, 20:06:41
    Author     : Usuario
--%>

<%@page import="models.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controllerlogin"
             class="controllers.ControllerLogin"
             scope="request"/>
<%
controllerlogin.setSession(session);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        Empleado emp = (Empleado)
            session.getAttribute("EMPLEADO");
        %>
       
        <h1 style="color: green">BIENVENIDO USUARIO</h1>
        <h2>
            Perfil: <%=emp.getApellido()%> , <%=emp.getOficio()%>
        </h2>
        <a href="usuarioempleado.jsp?cerrar=1">Cerrar sesion</a>
        <ul>
            <li>
                <a href="../index.html">HOME</a>
            </li>
            <li>
                <a href="../webalmacenarempleados.jsp">
                    Almacenar empleados
                </a>
            </li>
            <li>
                <a href="../webmostrarempleadossession.jsp">
                    MOSTRAR EMPLEADOS SESSION
                </a>
            </li>
            <li>
                <a href="usuarioempleado.jsp">
                    ZONA Empleado
                </a>
            </li>
        </ul>
       <%
       String cerrar = request.getParameter("cerrar");
       if(cerrar != null){
           controllerlogin.cerrarSesion();
           response.sendRedirect("../index.html");
           
       }
       %>
    </body>
</html>
