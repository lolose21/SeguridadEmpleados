/*
.
 */
package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Empleado;
import oracle.jdbc.OracleDriver;

public class RepositoryEmpleados {

    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        String cadena = "jdbc:oracle:thin:@LOCALHOST:1521:XE";
        Connection cn
                = DriverManager.getConnection(cadena, "system", "oracle");
        return cn;

    }

    public ArrayList<Empleado> getEmpleados() throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from emp";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Empleado> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("emp_no");
            String ape = rs.getString("apellido");
            String ofi = rs.getString("oficio");
            int sal = rs.getInt("salario");
            int dept = rs.getInt("dept_no");
            Empleado emp = new Empleado(id, ape, ofi, sal, dept);
            lista.add(emp);
        }
        rs.close();
        cn.close();
        return lista;
    }

    public ArrayList<Empleado> getEmpleadosSession(ArrayList<String> idsempleados) throws SQLException {
        //convertimos la coleccion de ides a String separados por comas

        String datos = "";
        for (String id : idsempleados) {
            datos += id + ",";
        }
        int ultimacoma = datos.lastIndexOf(",");
        datos = datos.substring(0, ultimacoma);
        Connection cn = this.getConnection();
        String sql = "select * from emp where emp_no in(" + datos + ")";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        ArrayList<Empleado> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("emp_no");
            String ape = rs.getString("apellido");
            String ofi = rs.getString("oficio");
            int sal = rs.getInt("salario");
            int deptno = rs.getInt("dept_no");
            Empleado emp = new Empleado(id, ape, ofi, sal, deptno);
            lista.add(emp);
        }
        rs.close();
        cn.close();
        return lista;
    }

    public Empleado existeEmpleado(String apellido, int idempleado) throws SQLException {
        Connection cn = this.getConnection();
        String sql = "select * from emp where upper(apellido)=upper(?) and emp_no=?";
        PreparedStatement pst = cn.prepareStatement(sql);
        pst.setString(1, apellido);
        pst.setInt(2, idempleado);
        ResultSet rs = pst.executeQuery();
        //puede q exista o no exista
        if (rs.next()) {
            //existe el empleado
            int id = rs.getInt("emp_no");
            String ape = rs.getString("apellido");
            String ofi = rs.getString("oficio");
            int sal = rs.getInt("salario");
            int deptno = rs.getInt("dept_no");
            Empleado emp = new Empleado(id, ape, ofi, sal, deptno);
            rs.close();
            cn.close();
            return emp;
        } else {
            //no existe
            rs.close();
            cn.close();
            return null;
        }

    }
}
