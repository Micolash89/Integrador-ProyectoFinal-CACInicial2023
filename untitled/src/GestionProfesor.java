import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GestionProfesor {

    private List<Profesor> profesores ;
    private GestionArchivo miArchivoProfesor = new GestionArchivo();

    public GestionProfesor() {
        profesores = miArchivoProfesor.leerProfesoresArchivo();
    }

    /*
    * El método cargarProfesores(Profesor profesor) permite cargar un profesor en la lista profesores si no existe previamente.
    *  Además, guarda el profesor en un archivo y muestra un mensaje de éxito o de que el profesor ya existe.
    * */
    public void cargarProfesores(Profesor profesor){
        if(profesores.contains(profesor)){

            JOptionPane.showMessageDialog(null, "El profesor ya existe");

        }else {
            profesores.add(profesor);
            miArchivoProfesor.altaProfesorArchivo(profesor);
            JOptionPane.showMessageDialog(null, "Profesor cargado correctamente");
        }
    }
    /*
    * El método actualizarProfesor(int indice) permite actualizar los datos de un profesor en la lista profesores.
    * Solicita al usuario que elija qué dato desea actualizar (nombre, apellido, email, DNI o materia) y realiza la actualización correspondiente en el profesor seleccionado.
    *  Luego, guarda la lista actualizada en el archivo.
    * */
    public void actualizarProfesor(int indice){
        int opcion;

        do{
            opcion = Integer.valueOf(JOptionPane.showInputDialog(
                    "Por favor seleccione la opción a actualizar" +
                    "\n Profesor:" + profesores.get(indice)+
                    "\n1. Nombre"+
                    "\n2. Apellido"+
                    "\n3. E-mail"+
                    "\n4. DNI"+
                    "\n5. Materia"+
                    "\n6. Salir"));
            switch (opcion){
                case 1:
                    String nombre = JOptionPane.showInputDialog("Por favor ingrese el nuevo nombre");
                    if(validarNombre(nombre)){
                        profesores.get(indice).setNombre(nombre);
                    }
                    break;
                case 2:
                    String apellido = JOptionPane.showInputDialog("Por favor ingrese el nuevo apellido");
                    if(validarApellido(apellido)){
                        profesores.get(indice).setApellido(apellido);
                    }
                    break;
                case 3:
                    String email = JOptionPane.showInputDialog("Por favor ingrese el nuevo E-mail");
                    if(validarEmail(email)){
                        profesores.get(indice).setEmail(email);
                    }
                    break;
                case 4:
                    int dni = Integer.valueOf(JOptionPane.showInputDialog("Por favor ingrese el nuevo dni"));
                    if(validarDni(dni)){
                        profesores.get(indice).setDni(dni);
                    }
                    break;
                case 5:
                    String materia = JOptionPane.showInputDialog("Por favor ingrese la nueva materia");
                    if(validarMateria(materia)){
                        profesores.get(indice).setMateria(materia);
                    }
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta");
            }

        }while (opcion != 6);

        miArchivoProfesor.actualizarProfesorArchivo(profesores);
    }

    /*
    * El método eliminarProfesor(int indice) elimina un profesor de la lista profesores según el índice proporcionado.
    * Posteriormente, guarda la lista actualizada en el archivo.
    * */

    public void eliminarProfesor(int indice){
        profesores.remove(indice);
        miArchivoProfesor.actualizarProfesorArchivo(profesores);
    }

    /*
    * El método buscarProfesorDni(int dni) busca un profesor en la lista profesores por su número de DNI.
    * Si lo encuentra, muestra los detalles del profesor en una ventana de diálogo;
    *  de lo contrario, muestra un mensaje indicando que el profesor no existe.
    * */
    public void buscarProfesorDni(int dni){

        int bandera =0;

        for(Profesor p : profesores){
            if(p.getDni() == dni){
                JOptionPane.showMessageDialog(null,"Profesor encontrado:\n"+ p.toString());
                bandera = 1;
            }
        }
        if(bandera == 0){
            JOptionPane.showMessageDialog(null, "El profesor no existe");
        }
    }
    /*
    * El método buscarProfesorNombre(String nombre) busca profesores en la lista profesores por su nombre.
    *  Si encuentra uno o más profesores con el nombre dado, muestra una lista con los detalles de los profesores encontrados;
    *  de lo contrario, muestra un mensaje indicando que el profesor no existe.
    * */
    public void buscarProfesorNombre(String nombre){
        if(nombre==null){
            JOptionPane.showMessageDialog(null, "Operación cancelada");
        }else {
            int bandera = 0;
            int i = 0;
            String lista = "";
            for (Profesor p : profesores) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    lista += ++i + ". " + p.toString() + "\n";
                    bandera = 1;
                }
            }
            if (bandera == 0) {
                JOptionPane.showMessageDialog(null, "El profesor no existe");
            } else {
                JOptionPane.showMessageDialog(null, "Se encontraron " + i + " Profesor/es con el nombre : " + nombre + "\n" + lista);
            }
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
    * */
    public String listaString(){

        String lista = "";
        int i = 0;
        for(Profesor p : profesores){
            lista += ++i +". "+ p.toString() + "\n";
        }
        return lista+'\n';
    }

    ///////////////////VALIDACIONES ///////////////////////////

    /*
    * El método validarNombre(String nombre) valida que el nombre ingresado sea correcto.
    * El nombre debe tener entre 2 y 40 caracteres.
    * */
    public boolean validarNombre(String nombre){
        if(nombre.trim().length() >= 2 && nombre.trim().length() <= 40){
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "El nombre debe tener al menos 2 caracteres y no debe exceder los 40 caracteres");
            return false;
        }
    }

    /*
    * El método validarApellido(String apellido) valida que el apellido ingresado sea correcto.
    * El apellido debe tener entre 2 y 40 caracteres.
    * */
    public boolean validarApellido(String apellido){
        if(apellido.trim().length() >= 2 && apellido.trim().length() <= 40){
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "El apellido debe tener al menos 2 caracteres y no debe exceder los 40 caracteres");
            return false;
        }
    }
    /*
    * El método validarEmail(String email) valida que el email ingresado sea correcto.
    * El email debe tener entre 3 y 40 caracteres y contener un @".
    * */
    public boolean validarEmail(String email){
        if(email.trim().length() >= 11 && email.trim().length() <= 40){

            if(email.contains("@")){
                return true;
            }else {
                JOptionPane.showMessageDialog(null, "El email debe contener @ y no debe exceder los 40 caracteres");
                return false;
            }

        }else {
            JOptionPane.showMessageDialog(null, "El email debe tener al menos 11 caracteres y no debe exceder los 40 caracteres");
            return false;
        }
    }
    /*
    * El método validarDni(int dni) valida que el dni ingresado sea correcto.
    * El dni debe ser mayor a 0 y menor a 99999999.
    * */
    public boolean validarDni(int dni) {
        if (dni > 99999999 && dni < 10000000 ) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "El dni debe ser menor a 8 digitos y mayor a 0");
            return false;
        }
    }
    /*
    * * El método validarMateria(String materia) valida que la materia ingresada sea correcta.
    * La materia debe tener entre 2 y 60 caracteres.
    * */
    public boolean validarMateria(String materia){
        if(materia.trim().length() >= 2 && materia.trim().length() <= 60){
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "La materia debe tener al menos 2 caracteres y no debe exceder los 60 caracteres");
            return false;
        }
    }

    /*
    * El método validarIndice(int indice) valida que el indice ingresado sea correcto.
    * El indice debe ser mayor a 0 y menor o igual a la cantidad de profesores en la lista.
    * */
    public boolean validarIndice(int indice){
        if(indice >= 1 && indice <= profesores.size()){
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "El indice no valido");
            return false;
        }
    }

    /*
    * El método validarBuscar(int opcion) valida que la opcion ingresada sea correcta.
    * La opcion debe ser mayor a 0 y menor o igual a 2.
    * */
    public boolean validarBuscar(int opcion){
        if(opcion >= 1 && opcion <= 2){
            return true;
        }else {
            JOptionPane.showMessageDialog(null, "Opción no valida");
            return false;
        }
    }
}
