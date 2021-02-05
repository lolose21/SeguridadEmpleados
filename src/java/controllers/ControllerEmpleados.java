package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import models.Empleado;
import repositories.RepositoryEmpleados;

public class ControllerEmpleados {

    RepositoryEmpleados repo;
    HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String almacenarEmpleadoSession(String IdEmpleado) {
        ArrayList<String> sessionempleados;
        //para almacenar , primero tenemops que saber si
        //existe la session s no
        if (session.getAttribute("EMPLEADOS") == null) {
            //creamos la lista por primera vez
            sessionempleados = new ArrayList<>();

        } else {
            //ya tenemos una lista en la sesion y la recuperamos
            sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");

        }
        //ya tenemos una lista (nueva o recuperarla)
        //debemos añadir el empleado
        //si no quiero repetidos , comprobamos si ya existe o no
        if (sessionempleados.contains(IdEmpleado) == false) {
            //no existe , lo añadimos
            sessionempleados.add(IdEmpleado);
            //actualizamos la session
            session.setAttribute("EMPLEADOS", sessionempleados);
            return "Almacenados: " + sessionempleados.size();
        } else {
            return "ya existe en la sesion" + IdEmpleado;
        }

    }

    public String getEmpleadosSession() throws SQLException {
        //necesitamos recuperar la lista de la session
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        //es posible que no exista la session
        if (sessionempleados == null) {
            return "<h1 style='color:red'>no hay session</h1>";
        } else {
            ArrayList<Empleado> empleados = this.repo.getEmpleadosSession(sessionempleados);
            String html = "<ul>";
            for (Empleado emp : empleados) {
                html += "<li>" + emp.getApellido() + "|";
                html += "<a href='webmostrarempleadossession.jsp?eliminar=";
                html += emp.getIdEmpleado() + "'>ELIMINAR </a>";
                html += "</li>";
            }
            html += "</ul>";
            return html;
        }
    }

    public ControllerEmpleados() {
        this.repo = new RepositoryEmpleados();
    }

    public String getTablaEmpleados() throws SQLException {
        ArrayList<Empleado> empleados = this.repo.getEmpleados();
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        String html = "";
        for (Empleado emp : empleados) {
            html += "<tr>";
            html += "<td>";
            String empno = String.valueOf(emp.getIdEmpleado());
            if (sessionempleados == null) {
                //pintamos el enlace Almacenar
                html += "<a href='webalmacenarempleados.jsp?idempleado=";
                html += emp.getIdEmpleado() + "'>Guardar en session </a>";
            } else if (sessionempleados.contains(empno) == false) {
                //pintamos el enlace almacenar
                html += "<a href='webalmacenarempleados.jsp?idempleado=";
                html += emp.getIdEmpleado() + "'>Guardar en session </a>";
            } else {
                //pintamos cualquier dibujo
                html += "<img src='imagen/gato.jpg' Style='width:25px;height:25px;'/>";
            }

            html += "</td>";
            html += "<td>" + emp.getApellido() + "</td>";
            html += "<td>" + emp.getOficio() + "</td>";
            html += "<td>" + emp.getSalario() + "</td>";
            html += "<td>" + emp.getDepartamento() + "</td>";
            html += "</tr>";
        }
        return html;
    }

    public void eliminarEmpleadosSession(String IdEmpleado) throws SQLException {
        //recuperamos la lista de id empleados de session
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        //ELIMINAMOS EL ID empleado DE LA LISTA
        sessionempleados.remove(IdEmpleado);
        //comprobamos si ya tenemos empleados en la lista
        if (sessionempleados.size() == 0) {
            //si no  hay empleados , borramos la session
            session.setAttribute("EMPLEADOS", null);
        } else {
            //almacenamos la nueva lista (sin EMPLEADOS) en session
            session.setAttribute("EMPLEADOS", sessionempleados);

        }
    }

}
