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

    public String getDatosSession() {
        //necesitamos recuperar la lista de la session
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADOS");
        //es posible que no exista la session
        if (sessionempleados == null) {
            return "<h1 style='color:red'>no hay session</h1>";
        } else {
            String html = "<ul>";
            for (String dato : sessionempleados) {
                html += "<li>" + dato;
                html += "<a href='webmostrarempleadossession.jsp?eliminar=";
                html += dato + "'>ELIMINAR </a>";
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
        String html = "";
        for (Empleado emp : empleados) {
            html += "<tr>";
            html += "<td>";
            html += "<a href='webalmacenarempleados.jsp?idempleado=";
            html += emp.getIdEmpleado() + "'>Guardar en session </a>";
            html += "</td>";
            html += "<td>" + emp.getApellido() + "</td>";
            html += "<td>" + emp.getOficio() + "</td>";
            html += "<td>" + emp.getSalario() + "</td>";
            html += "<td>" + emp.getDepartamento() + "</td>";
            html += "</tr>";
        }
        return html;
    }

    public String getTablaEliminar() throws SQLException {
        ArrayList<Empleado> empleados = this.repo.getEmpleados();
        String html = "";
        for (Empleado emp : empleados) {
            html += "<tr>";
            html += "<td>";
            html += "<a href='webmostrarempleadossession.jsp?eliminar=";
            html += emp.getIdEmpleado() + "'>ELIMINAR </a>";
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
    /*
    public String getEliminar() {
        ArrayList<String> sessionempleados = (ArrayList) session.getAttribute("EMPLEADO");
        if (sessionempleados == null) {
            return "<h1 style='color:red'>NO HAY SSESION</h1>";
        } else {
             ArrayList<Empleado>empleados = this.repo.getempleadosSession(sessionempleados);
            String html = "";
            for (String emp : empleados) {
                html += "<tr>";
                html += "<td>" + emp.getapellido() + "</td>";

                html += "<td>";
                html += "<a href='webmostrarempleadossession.jsp?eliminar=";
                html += emp.getIdEmpleado() + "'>ELIMINAR </a>";
                html += "<td>";
                html += "</tr>";
            }
            return html;
        }
    }*/
}
