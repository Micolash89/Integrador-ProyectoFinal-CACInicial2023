import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionArchivo {
    private static final String NOMBRE_ARCHIVO = "profesores.txt";

    /*
     * El método altaProfesorArchivo(Profesor profesor) permite almacenar un profesor en el archivo.
     *  Además, guarda el profesor en un archivo y muestra un mensaje de éxito o de que el profesor ya existe.
     * */
    public void altaProfesorArchivo(Profesor profesor) {

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true))) {
            buffer.write(profesor.toString());
            buffer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo" + e.getMessage());
        }
    }

    /*
     * El método leerProfesoresArchivo() permite leer los profesores almacenados en el archivo.
     *  Devuelve una lista con todos los profesores.
     * */
    public List<Profesor> leerProfesoresArchivo() {
        List<Profesor> profesores = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] listado = linea.split(",");
                Profesor profesor = new Profesor(listado[0], listado[1], listado[2], Integer.valueOf(listado[3]), listado[4]);
                profesores.add(profesor);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo" + e.getMessage());
        }
        return profesores;
    }

    /*
     * El método actualizarProfesorArchivo(List<Profesor> profesores) permite actualizar los profesores almacenados en el archivo.
     *  Además, guarda la lista actualizada en el archivo.
     * */
    public void actualizarProfesorArchivo(List<Profesor> profesores) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, false))) {
            for (Profesor profesor : profesores) {
                buffer.write(profesor.toString());
                buffer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir en el archivo" + e.getMessage());
        }
    }
}