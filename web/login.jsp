<%-- 
    https://github.com/serraguti/seguridadempleadosjava
--%>

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
        <h1>LOG IN EMPLEADOS</h1>
        <form method="POST">
            <label>APELLIDO</label>
            <input type="text" name="apellido" required/><br/>
            
            <label>ID EMPLEADO</label>
            <input type="number" name="cajaid" required/><br/>
            <button type="submit" > LOG in</button>
                
        </form>
        <%
        String apellido = request.getParameter("apellido");
        String dato = request.getParameter("cajaid");
        if(apellido != null){
            int idempleado = Integer.parseInt(dato);
            boolean acceso = controllerlogin.existeEmpleado(apellido, idempleado);
            if(acceso == true){
                response.sendRedirect("proteccion/usuarioempleado.jsp");
                
                }else{
                %>
                <h1 style="color: red">Usuario/Password incorrectos</h1>
                <%
                }
            }
        %>
    </body>
</html>
