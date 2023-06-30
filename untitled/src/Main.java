import javax.swing.*;

public class Main {
    public static GestionProfesor gestorProfesor = new GestionProfesor();

    /* El método main() permite ejecutar el sistema de gestión de profesores, solicitando al usuario que ingrese una opción.
     * Es importante destacar que el sistema controla errores en tiempo de ejecución, como por ejemplo la excepción NumberFormatException.
     * */
    public static void main(String[] args) {

        int opcion;

        do {
            opcion = menu();
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
                    despedida();
                    break;
                default:
                    opcionInvalida();
                    break;
            }

        } while (opcion != 6);

    }

    //////////////////////////////FUNCIONES////////////////////////////////////
    public static int menu() {

        String input = InterfazGrafica.mensajeMenu("Bienvenido al sistema de gestión de profesores" +
                        "\nPor favor seleccione una opción: " +
                        "\n1. Agregar profesor" +
                        "\n2. Mostrar profesor" +
                        "\n3. Actualizar profesor" +
                        "\n4. Eliminar profesor" +
                        "\n5. Buscar profesor" +
                        "\n6. Salir",
                "MENU PINRCIPAL");
        if (input == null) {
            System.exit(0); //para salir del programa si se cierra la ventana
        }

        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            //si ingresa algun caracter no numericos o una cadena vacia
            return -1;
        }

    }

    /* El método agregarProfesor() permite agregar un profesor al sistema solicitando al usuario que ingrese su nombre, apellido, email, DNI y materia,
     * validando cada uno de ellos antes de cargar al profesor en el sistema.
     */
    public static void agregarProfesor() {

        Profesor profe = new Profesor();
        String dni;

        do {

            profe.setNombre(InterfazGrafica.mensajeIngreso("Por favor ingrese el nombre del profesor", "NOMBRE"));
            if (profe.getNombre() == null) {
                InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                return;
            }
        } while (!gestorProfesor.validarNombre(profe.getNombre()));

        do {
            profe.setApellido(InterfazGrafica.mensajeIngreso("Por favor ingrese el apellido del profesor", "APELLIDO"));
            if (profe.getApellido() == null) {
                InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                return;
            }

        } while (!gestorProfesor.validarApellido(profe.getApellido()));

        do {
            profe.setEmail(InterfazGrafica.mensajeIngreso("Por favor ingrese el e-mail del profesor", "E-MAIL"));
            if (profe.getEmail() == null) {
                InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                return;
            }

        } while (!gestorProfesor.validarEmail(profe.getEmail()));

        do {
            try {
                dni = InterfazGrafica.mensajeIngreso("Por favor ingrese el DNI del profesor", "DNI");

                if (dni == null) {//el usuarion oprimio el boton de cancela/cerrar ventana
                    InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    return;
                }

                profe.setDni(Integer.valueOf(dni));//pasar la cadena a numero

            } catch (NumberFormatException e) {//error al pasar la cade a numero, el usuario ingreso letras
                profe.setDni(-1);
            }

        } while (!gestorProfesor.validarDni(profe.getDni()));

        do {
            profe.setMateria(InterfazGrafica.mensajeIngreso("Por favor ingrese la Materia del profesor", "MATERIA"));
            if (profe.getMateria() == null) {//el usuarion oprimio el boton de cancela/cerrar ventana
                InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                return;
            }

        } while (!gestorProfesor.validarMateria(profe.getMateria()));

        if (gestorProfesor.getProfesores().contains(profe)) {
            InterfazGrafica.mensajeCancelar("El profesor ya existe en el sistema", "CANCELADO");
        } else {
            gestorProfesor.cargarProfesores(profe);
        }
    }


    /* El método mostrarProfesor() muestra en una ventana de diálogo
     * todos los profesores que se encuentran en el sistema.
     * la lista de profesores almacenados en el sistema.
     */
    public static void mostrarProfesor() {

        //Tabla.mostrarRegistrosEnTabla(gestorProfesor.getProfesores());//tabla para mostrar los profesores

        //mostrar los profesores en un JOptionPane (descomentar y comentar la linea donde se invoca a la tabla )

        if (gestorProfesor.getProfesores().size() == 0) {
            InterfazGrafica.mensajeCancelar("No hay profesores en el sistema", "CANCELADO");
            return;
        }

        InterfazGrafica.mensajeExito(gestorProfesor.listaString(), "LISTA DE PROFESORES");


    }

    /* El método actualizarProfesor() permite actualizar los datos de un profesor existente en el sistema.
     * Solicita al usuario que ingrese el índice del profesor a actualizar y luego invoca al método correspondiente en gestorProfesor.
     * */
    public static void actualizarProfesor() {

        if (gestorProfesor.getProfesores().size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay profesores en el sistema");
            return;
        }

        int indice;
        String input;

        do {
            input = InterfazGrafica.mensajeIngreso(gestorProfesor.listaString() + "Por favor ingrese el indice del profesor a actualizar", "ACTUALIZAR");

            //salir selecciona el boton de cancelar/cerrar ventana
            if (input == null) {
                InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                return;
            }

            try {
                indice = Integer.valueOf(input);
            } catch (NumberFormatException e) {//error si se ingresa una cadena vacia o letras
                indice = -1;
            }

        } while (!gestorProfesor.validarIndice(indice));

        gestorProfesor.actualizarProfesor(indice - 1);


    }

    /*
     * El método eliminarProfesor() permite eliminar un profesor existente en el sistema.
     *  Solicita al usuario que ingrese el índice del profesor a eliminar y luego invoca al método correspondiente en gestorProfesor.
     * */
    public static void eliminarProfesor() {

        if (gestorProfesor.getProfesores().size() == 0) {
            InterfazGrafica.mensajeCancelar("No hay profesores en el sistema", "CANCELADO");
        } else {
            int indice;
            String input;

            do {

                input = InterfazGrafica.mensajeIngreso(gestorProfesor.listaString() + "Por favor ingrese el indice del profesor a eliminar", "ELIMINAR");

                //salir selecciona el boton de cancelar/cerrar ventana
                if (input == null) {
                    InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    return;
                }

                try {
                    indice = Integer.valueOf(input);//error si se ingresa una cadena vacia o letras
                } catch (NumberFormatException e) {
                    indice = -1;
                }

            } while (!gestorProfesor.validarIndice(indice));

            gestorProfesor.eliminarProfesor(indice - 1);
        }
    }

    /*
     * El método buscarProfesor() permite buscar un profesor en el sistema según el criterio seleccionado por el usuario (nombre o DNI).
     *  Solicita al usuario que elija el criterio de búsqueda y luego ingrese los datos correspondientes, invocando al método de búsqueda apropiado en gestorProfesor.
     * */
    public static void buscarProfesor() {
        //si no hay objetos profesores en la lista
        if (gestorProfesor.getProfesores().size() == 0) {
            InterfazGrafica.mensajeCancelar("No hay profesores en el sistema", "ERROR");
            return;
        }
        String input = InterfazGrafica.mensajeIngreso("Por favor ingrese nombre o numero de DNI del profesor", "BUSCAR");

        try {
            int dni = Integer.valueOf(input);
            gestorProfesor.buscarProfesorDni(dni);
        } catch (NumberFormatException e) {
            gestorProfesor.buscarProfesorNombre(input);
        }

    }

    public static void despedida() {
        //mensaje de despedida version profesor
        InterfazGrafica.mensajeProfe("Gracias por utilizar el sistema\n   Saddy Pacheco S.A.", "HASTA LUEGO");

        //mensaje de despedida version administrador
        //InterfazGrafica.mensajeExito("Gracias por utilizar el sistema","HASTA LUEGO");
    }

    public static void opcionInvalida() {
        InterfazGrafica.mensajeAdvertencia("Opción no válida", "CUIDADO");
    }

}