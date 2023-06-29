import javax.swing.*;
import java.util.List;

public class GestionProfesor {

    private List<Profesor> profesores;
    private GestionArchivo miArchivoProfesor = new GestionArchivo();

    public GestionProfesor() {
        profesores = miArchivoProfesor.leerProfesoresArchivo();
    }

    /*
     * El método cargarProfesores(Profesor profesor) permite cargar un profesor en la lista profesores si no existe previamente.
     * guarda el profesor en un archivo y muestra un mensaje de éxito o de que el profesor ya existe.
     * */
    public void cargarProfesores(Profesor profesor) {

        if (profesores.contains(profesor)) {

            JOptionPane.showMessageDialog(null, "El profesor ya existe en el sistema");

        } else {
            profesores.add(profesor);
            miArchivoProfesor.altaProfesorArchivo(profesor);
            InterfazGrafica.mensajeExito("Se agrego un profesor exitosamente", "OPERACIÓN EXITOSA");
        }
    }

    /*
     * El método actualizarProfesor(int indice) permite actualizar los datos de un profesor en la lista profesores.
     * Solicita al usuario que elija qué dato desea actualizar (nombre, apellido, email, DNI o materia) y realiza la actualización correspondiente en el profesor seleccionado.
     *  Luego, guarda la lista actualizada en el archivo.
     * */
    public void actualizarProfesor(int indice) {
        String input;
        int opcion, dni;
        Profesor profe = new Profesor(profesores.get(indice));

        do {
            input = InterfazGrafica.mensajeIngreso(
                     profe+
                            "\n\nPor favor seleccione la opción a actualizar" +
                            "\n1. Nombre" +
                            "\n2. Apellido" +
                            "\n3. E-mail" +
                            "\n4. DNI" +
                            "\n5. Materia" +
                            "\n6. Salir y guardar"
                            ,"MENU ACTUALIZAR");

            if (input == null) {
                InterfazGrafica.mensajeCancelar("Operación cancelada\n no se actualizo ningun profesor", "Cancelado");
                return;
            }

            try {
                opcion = Integer.valueOf(input);
            } catch (NumberFormatException e) {// si ingresa letras o una cadena vacia
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    input = InterfazGrafica.mensajeIngreso("Por favor ingrese el nuevo nombre", "NOMBRE");

                    if (input == null) {
                        InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    } else {
                        if (validarNombre(input)) {
                            profe.setNombre(input);
                        }
                    }
                    break;
                case 2:
                    //String apellido = InterfazGrafica.mensajeIngreso("Por favor ingrese el nuevo apellido", "APELLIDO");
                    input = InterfazGrafica.mensajeIngreso("Por favor ingrese el nuevo apellido", "APELLIDO");
                    if (input == null) {
                        InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    } else {
                        if (validarApellido(input)) {
                            profe.setApellido(input);
                        }
                    }
                    break;
                case 3:
                    input = InterfazGrafica.mensajeIngreso("Por favor ingrese el nuevo E-mail", "E-MAIL");
                    if (input == null) {
                        InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    } else {
                        if (validarEmail(input)) {
                            profe.setEmail(input);
                        }
                    }
                    break;
                case 4:
                    input = InterfazGrafica.mensajeIngreso("Por favor ingrese el nuevo DNI", "DNI");
                    if (input == null) {
                        InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    }
                    try {
                        dni = Integer.valueOf(input);
                        if (validarDni(dni)) {
                            profe.setDni(dni);
                        }
                    } catch (NumberFormatException e) {
                        dni = -1;
                        InterfazGrafica.mensajeAdvertencia("Ingrese numeros", "CUIDADO");
                    }

                    break;
                case 5:
                    input = InterfazGrafica.mensajeIngreso("Por favor ingrese la nueva materia", "MATERIA");
                    if (input == null) {
                        InterfazGrafica.mensajeCancelar("Operación cancelada", "CANCELADO");
                    }
                    if (validarMateria(input)) {
                        profe.setMateria(input);
                    }
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }

        } while (opcion != 6);

        if (profesores.contains(profe)) {
            InterfazGrafica.mensajeCancelar("El profesor ya existe en la lista, no se puede actualizar", "ERROR");
        } else {
            InterfazGrafica.mensajeExito("Profesor actualizado con éxito", "CONFIRMADO");
            profesores.set(indice, profe);// actualiza el profesor en la lista
            miArchivoProfesor.actualizarProfesorArchivo(profesores);
        }
    }

    /*
     * El método eliminarProfesor(int indice) elimina un profesor de la lista profesores según el índice proporcionado.
     * Posteriormente, guarda la lista actualizada en el archivo.
     * */

    public void eliminarProfesor(int indice) {
        profesores.remove(indice);
        miArchivoProfesor.actualizarProfesorArchivo(profesores);
    }

    /*
     * El método buscarProfesorDni(int dni) busca un profesor en la lista profesores por su número de DNI.
     * Si lo encuentra, muestra los detalles del profesor en una ventana de diálogo;
     *  de lo contrario, muestra un mensaje indicando que el profesor no existe.
     * */
    public void buscarProfesorDni(int dni) {

        for (Profesor p : profesores) {
            if (p.getDni() == dni) {
                JOptionPane.showMessageDialog(null, "Profesor encontrado con el DNI: " + dni + "\nDetalles del profesor:\n" + p.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "El profesor no existe");

    }

    /*
     * El método buscarProfesorNombre(String nombre) busca profesores en la lista profesores por su nombre.
     * Si encuentra uno o más profesores con el nombre dado, muestra una lista con los detalles de los profesores encontrados;
     * de lo contrario, muestra un mensaje indicando que el profesor no existe.
     * */
    public void buscarProfesorNombre(String nombre) {
        int i = 0;

        String lista = "";
        for (Profesor p : profesores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                lista += ++i + ". " + p.toString() + "\n";
            }
        }
        if (lista.equals("")) {
            InterfazGrafica.mensajeCancelar("No se encontraron profesores", "BUSCAR");
        } else {
            InterfazGrafica.mensajeMostrar("Se encontraron " + i + " Profesor/es con el nombre : " + nombre + "\n" + lista, "BUSCAR");
        }

    }

    /*
     * El método getProfesores() devuelve la lista de profesores.
     * */
    public List<Profesor> getProfesores() {
        return profesores;
    }

    /*
     * El método listaString() genera una representación en forma de cadena de texto de la lista de profesores, numerándolos.
     * El método devuelve una cadena de texto con la lista de profesores.
     * */
    public String listaString() {

        String lista = "";
        int i = 0;
        for (Profesor p : profesores) {
            lista += ++i + " - " + p.toString() + "\n";
        }
        return lista + '\n';
    }

    ///////////////////VALIDACIONES ///////////////////////////
    /*
     * El método validarNombre(String nombre) valida que el nombre ingresado sea correcto.
     * El nombre debe tener entre 2 y 40 caracteres.
     * */
    public boolean validarNombre(String nombre) {
        if (nombre.trim().length() >= 2 && nombre.trim().length() <= 40) {
            return true;
        } else {
            InterfazGrafica.mensajeAdvertencia("El nombre debe tener al menos 2 caracteres y no debe exceder los 40 caracteres", "CUIDADO");
            return false;
        }
    }

    /*
     * El método validarApellido(String apellido) valida que el apellido ingresado sea correcto.
     * El apellido debe tener entre 2 y 40 caracteres.
     * */
    public boolean validarApellido(String apellido) {
        if (apellido.trim().length() >= 2 && apellido.trim().length() <= 40) {
            return true;
        } else {
            InterfazGrafica.mensajeAdvertencia("El apellido debe tener al menos 2 caracteres y no debe exceder los 40 caracteres", "CUIDADO");
            return false;
        }
    }

    /*
     * El método validarEmail(String email) valida que el email ingresado sea correcto.
     * El email debe tener entre 3 y 40 caracteres y contener un @".
     * */
    public boolean validarEmail(String email) {
        if (email.trim().length() >= 11 && email.trim().length() <= 40) {

            if (email.contains("@")) {
                return true;
            } else {
                InterfazGrafica.mensajeAdvertencia("El email debe contener @ y no debe exceder los 40 caracteres", "CUIDADO");
                return false;
            }

        } else {
            InterfazGrafica.mensajeAdvertencia("El email debe tener al menos 11 caracteres y no debe exceder los 40 caracteres", "CUIDADO");
            return false;
        }
    }

    /*
     * El método validarDni(int dni) valida que el dni ingresado sea correcto.
     * El dni debe tener una cantidad de 8 digitos.
     * */
    public boolean validarDni(int dni) {
        if (dni > 9999999 && dni < 100000000) {
            return true;
        } else {

            InterfazGrafica.mensajeAdvertencia("El dni debe tener una cantidad de 8 digitos", "CUIDADO");
            return false;
        }
    }

    /*
     * * El método validarMateria(String materia) valida que la materia ingresada sea correcta.
     * La materia debe tener entre 2 y 60 caracteres.
     * */
    public boolean validarMateria(String materia) {
        if (materia.trim().length() >= 2 && materia.trim().length() <= 60) {
            return true;
        } else {
            InterfazGrafica.mensajeAdvertencia("La materia debe tener al menos 2 caracteres y no debe exceder los 60 caracteres", "CUIDADO");
            return false;
        }
    }

    /*
     * El método validarIndice(int indice) valida que el indice ingresado sea correcto.
     * El indice debe ser mayor a 0 y menor o igual a la cantidad de profesores en la lista.
     * */
    public boolean validarIndice(int indice) {
        if (indice >= 1 && indice <= profesores.size()) {
            return true;
        } else {
            InterfazGrafica.mensajeAdvertencia("El indice no valido", "CUIDADO");
            return false;
        }
    }

    /*
     * El método validarBuscar(int opcion) valida que la opcion ingresada sea correcta.
     * La opcion debe ser mayor a 0 y menor o igual a 2.
     * */
    public boolean validarBuscar(int opcion) {
        if (opcion >= 1 && opcion <= 2) {
            return true;
        } else {
            InterfazGrafica.mensajeAdvertencia("Opción no valida", "CUIDADO");
            return false;
        }
    }
}
