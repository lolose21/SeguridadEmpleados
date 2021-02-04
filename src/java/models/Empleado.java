package models;

public class Empleado {

    private int idEmpleado;
    private String apellido;
    private String oficio;
    private int salario;
    private int departamento;

    public Empleado() {
    }

    //llamamos al constructor
    public Empleado(int id, String ape, String ofi, int sal, int dept) {
        this.idEmpleado = id;
        this.apellido = ape;
        this.oficio = ofi;
        this.salario = sal;
        this.departamento = dept;
    }

    //<editor-fold defaultstate="collapsed" desc="getter and setter">
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
//</editor-fold>

}
