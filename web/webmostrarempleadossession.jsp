<%-- 
1) Quitar elementos de Session
2) En lugar de mostrar los String de la sesión, quiero ver los empleados.
3) Cuando almacenemos un empleado, quitamos el LINK de almacenar.
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlleremp"
             class="controllers.ControllerEmpleados"
             scope="request"/>
<%
controlleremp.setSession(session);

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSTRAR</title>
    </head>
    <body>
        <h1>Mostrar</h1>
        <%
        String eliminar =request.getParameter("eliminar");
        if(eliminar != null){
         controlleremp.eliminarEmpleadosSession(eliminar);
        }
         %>
        <button>
        <a href="webalmacenarempleados.jsp">
          VOLVER ALMACENAR 
        </a>
        </button>
       
         <table border="1">
            <thead>
                <tr>
                   
                    <th>APELLIDO</th>
                    <th>OFICIO</th>
                    <th>SALARIO</th>
                    <th>DEPT</th>
                     <th></th>
                     <th></th>
                </tr>
            </thead>
            <tbody>
                
            </tbody>
        </table>
        
    </body>
</html>
