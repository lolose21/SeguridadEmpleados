<%-- 
 
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
        <title>ALMACENAR</title>
        
    </head>
    <body>
        <%
            //aqui recuperaremos la session
        String idempleado = request.getParameter("idempleado");
        if(idempleado !=null){
        %>
        <h1 style="color: red">
            <%=controlleremp.almacenarEmpleadoSession(idempleado)%>
        </h1>
        <%
        }
        %>
        <h1>Almacenar</h1>
        <button>
        <a href="webmostrarempleadossession.jsp">
            MOSTRAR ALMACENADOS
        </a>
        </button>
        <table border="1">
            <thead>
                <tr>
                    <th></th>
                    <th>APELLIDO</th>
                    <th>OFICIO</th>
                    <th>SALARIO</th>
                    <th>DEPT</th>
                </tr>
            </thead>
            <tbody>
                <%=controlleremp.getTablaEmpleados()%>
            </tbody>
        </table>
            
    </body>
</html>
