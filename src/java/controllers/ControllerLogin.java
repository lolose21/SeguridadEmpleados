/*

 */
package controllers;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import models.Empleado;
import repositories.RepositoryEmpleados;

public class ControllerLogin {

    HttpSession session;
    RepositoryEmpleados repo;

    public ControllerLogin() {
        this.repo = new RepositoryEmpleados();
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean existeEmpleado(String apellido, int idempleado) throws SQLException {
        //existe?
        Empleado emp = this.repo.existeEmpleado(apellido, idempleado);
        if (emp == null) {
            //no existe
            return false;
        } else {
            //existe el empleado y se ha validado
            session.setAttribute("EMPLEADO", emp);
            return true;
        }
    }

    public void cerrarSesion() {
        //recuperamos la sesion y la ponemos a 0
        session.setAttribute("EMPLEADO", null);
        session.invalidate();
    }
}
