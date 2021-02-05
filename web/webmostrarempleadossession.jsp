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
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <a href="includes/webseguridadempleadosmenu.jsp"></a>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOSTRAR</title>
        
    </head>
    <body>

        <h1>Mostrar</h1>
        <ul>
            <li>
                <a href="index.html">HOME</a>
            </li>
            <li>
                <a href="webalmacenarempleados.jsp">
                    Almacenar empleados
                </a>
            </li>
            <li>
                <a href="webmostrarempleadossession.jsp">
                    MOSTRAR EMPLEADOS SESSION
                </a>
            </li>
            <li>
                <a href="proteccion/usuarioempleado.jsp">
                    ZONA Empleado
                </a>
            </li>
        </ul>
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
       
      <%=controlleremp.getEmpleadosSession()%>
        
    </body>
    <script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
    <script src="js/Chart.bundle.min.js" type="text/javascript"></script>
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>

</html>
