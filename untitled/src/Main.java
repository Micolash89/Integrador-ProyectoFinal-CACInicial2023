import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static GestionProfesor gestorProfesor = new GestionProfesor();

    public static void main(String[] args) {

        int opcion;
        String input;

        do {

                input = JOptionPane.showInputDialog("Bienvenido al sistema de gestión de profesores" +
                        "\nPor favor seleccione una opción: " +
                        "\n1. Agregar profesor" +
                        "\n2. Mostrar profesor" +
                        "\n3. Actualizar profesor" +
                        "\n4. Eliminar profesor" +
                        "\n5. Buscar profesor" +
                        "\n6. Salir");

if(input != null){
    if(input.length()>0){
            opcion = Integer.valueOf(input);
            switch (opcion) {
                case 1:
                    agregarProfesor();
                    break;
                case 2:
                    mostrarProfesor();
                    break;
                case 3:
                    actualizarProfesor();
                    break;
                case 4:
                    eliminarProfesor();
                    break;
                case 5:
                    buscarProfesor();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema");
                    break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                        break;
            }
    }else{
        JOptionPane.showMessageDialog(null, "Opción no válida");
        opcion = -1;
    }
            }else{

        JOptionPane.showMessageDialog(null, "Operación cancelada");
        opcion = 6;

}

        } while (opcion != 6);

    }

    /*
     El método agregarProfesor() permite agregar un profesor al sistema solicitando al usuario que ingrese su nombre, apellido, email, DNI y materia,
     validando cada uno de ellos antes de cargar al profesor en el sistema.
     */
    public static void agregarProfesor() {

        Profesor profesor = new Profesor();

        String nombre,apellido,email,materia,dni;

        do {

            nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre del profesor");
            if(nombre==null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }
            profesor.setNombre(nombre);

        }while(!gestorProfesor.validarNombre(profesor.getNombre()));

        do {
            apellido=JOptionPane.showInputDialog("Por favor ingrese el apellido del profesor");
            if(apellido==null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }
            profesor.setApellido(apellido);
        }while(!gestorProfesor.validarApellido(profesor.getApellido()));

        do {
            email=JOptionPane.showInputDialog("Por favor ingrese e-mail del profesor");
            if(email==null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }
            profesor.setEmail(email);
        }while(!gestorProfesor.validarEmail(profesor.getEmail()));

        do{
            dni=JOptionPane.showInputDialog("Por favor ingrese el DNI del profesor");
            if(dni==null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }
            profesor.setDni(Integer.valueOf(dni));
        }while(!gestorProfesor.validarDni(profesor.getDni()));

        do{
            materia =JOptionPane.showInputDialog("Por favor ingrese el Materia del profesor");
            if(materia==null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }
            profesor.setMateria(materia);
        }while(!gestorProfesor.validarMateria(profesor.getMateria()));

        gestorProfesor.cargarProfesores(profesor);

    }
    /*
    El método mostrarProfesor() muestra en una ventana de diálogo
     todos los profesores que se encuentran en el sistema.
     la lista de profesores almacenados en el sistema.
    */
    public static void mostrarProfesor() {
        if(gestorProfesor.getProfesores().size()==0){
            JOptionPane.showMessageDialog(null, "No hay profesores en el sistema");
        }else {
            JOptionPane.showMessageDialog(null, gestorProfesor.listaString());
        }
    }

    /*
    * El método actualizarProfesor() permite actualizar los datos de un profesor existente en el sistema.
    * Solicita al usuario que ingrese el índice del profesor a actualizar y luego invoca al método correspondiente en gestorProfesor.
    * */
    public static void actualizarProfesor() {

        if(gestorProfesor.getProfesores().size()==0){
            JOptionPane.showMessageDialog(null, "No hay profesores en el sistema");
        }else {
            int indice;
            String input;

            do {
                input = JOptionPane.showInputDialog(gestorProfesor.listaString() + "Por favor ingrese el indice del profesor a actualizar");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    return;
                } else {
                    if (input.equals("")) {
                        input = "-1";
                    }
                    indice = Integer.valueOf(input);
                }
            }while (!gestorProfesor.validarIndice(indice)) ;

                gestorProfesor.actualizarProfesor(indice - 1);

        }
    }
    /*
    * El método eliminarProfesor() permite eliminar un profesor existente en el sistema.
    *  Solicita al usuario que ingrese el índice del profesor a eliminar y luego invoca al método correspondiente en gestorProfesor.
    * */
    public static void eliminarProfesor() {

        if(gestorProfesor.getProfesores().size()==0){
            JOptionPane.showMessageDialog(null, "No hay profesores en el sistema");
        }else {
            int indice;
            String input;

            do {

                input = JOptionPane.showInputDialog(gestorProfesor.listaString() + "Por favor ingrese el indice del profesor a actualizar");
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    return;
                }
                if (input.equals("")) {
                    input = "-1";
                }

                indice = Integer.valueOf(input);

            } while (!gestorProfesor.validarIndice(indice));

            gestorProfesor.eliminarProfesor(indice - 1);
        }

    }

    /*
    * El método buscarProfesor() permite buscar un profesor en el sistema según el criterio seleccionado por el usuario (nombre o DNI).
    *  Solicita al usuario que elija el criterio de búsqueda y luego ingrese los datos correspondientes, invocando al método de búsqueda apropiado en gestorProfesor.
    * */
    public static void buscarProfesor() {
        if(gestorProfesor.getProfesores().size()==0){
            JOptionPane.showMessageDialog(null, "No hay profesores en el sistema");
        }else{
        int opcion;
        String input;

        do{

            input=JOptionPane.showInputDialog("Desea buscar por: "+
                    "\n1. Nombre" +
                    "\n2. DNI");
            if(input==null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                return;
            }
            if(input.equals("")){
                input="-1";
            }
            opcion = Integer.valueOf(input);

        }while(!gestorProfesor.validarBuscar(opcion));

        if(opcion == 1){
            gestorProfesor.buscarProfesorNombre(JOptionPane.showInputDialog("Ingrese nombre del profesor"));
        }else {
            do {
                    input = JOptionPane.showInputDialog("Ingrese DNI del profesor");
                    if (input == null) {
                        JOptionPane.showMessageDialog(null, "Operación cancelada");
                        return;
                    }
                    if (input.equals("")) {
                        input = "-1";
                    }
                }while(!gestorProfesor.validarDni(Integer.valueOf(input)));
            gestorProfesor.buscarProfesorDni(Integer.valueOf(Integer.valueOf(input)));
        }
    }

    }
}