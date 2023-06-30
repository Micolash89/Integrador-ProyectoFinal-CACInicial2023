import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionArchivo {
    private static final String FILE_PATH = "profesores.txt";

    /*
     * El método altaProfesorArchivo(Profesor profesor) permite almacenar un profesor en el archivo.
     *  Además, guarda el profesor en un archivo y muestra un mensaje de éxito o de que el profesor ya existe.
     * */
    public void altaProfesorArchivo(Profesor profesor) {

        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            buffer.write(profesor.toString());
            buffer.newLine();
        } catch (IOException e) {
            InterfazGrafica.mensajeCancelar("Error al escribir en el archivo" + e.getMessage(), "ERROR");
        }
    }

    /*
     * El método leerProfesoresArchivo() permite leer los profesores almacenados en el archivo.
     *  Devuelve una lista con todos los profesores.
     * */
    public List<Profesor> leerProfesoresArchivo() {
        List<Profesor> profesores = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            while ((linea = buffer.readLine()) != null) {
                String[] listado = linea.split(",");
                Profesor profesor = new Profesor(listado[0], listado[1], listado[2], Integer.valueOf(listado[3]), listado[4]);
                profesores.add(profesor);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo" + e.getMessage());
        }
        return profesores;
    }

    /*
     * El método actualizarProfesorArchivo(List<Profesor> profesores) permite actualizar los profesores almacenados en el archivo.
     *  Además, guarda la lista actualizada en el archivo.
     * */
    public void actualizarProfesorArchivo(List<Profesor> profesores) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Profesor profesor : profesores) {
                buffer.write(profesor.toString());
                buffer.newLine();
            }
        } catch (IOException e) {
            InterfazGrafica.mensajeCancelar("Error al escribir en el archivo" + e.getMessage(), "ERROR");
        }
    }


    public static List<String> leerRegistros() {
        //registros se inicializa como una lista vacia donde se van a almacenar los registros leidos
        List<String> registros = new ArrayList<>();
        //ruta del archivo desde el cual se van a leer los registros

        try (BufferedReader leer = new BufferedReader(new FileReader(FILE_PATH))) {
            //linea almacena cada linea leida del archivo
            String linea;
            int indice = 0; // indice inicial
            //lee una linea de texto del archivo y la asigna a la variable... y verifica si el valor de linea es null(linea es null, significa que no hay más lineas)
            while ((linea = leer.readLine()) != null) {
                String registroConIndice = ++indice + "," + linea; //agrega el indice y una coma al registro
                registros.add(registroConIndice);
            }
        } catch (IOException e) {
            //muestra un mensaje de error si no se pudo leer el archivo
            InterfazGrafica.mensajeCancelar("No se pudo leer el archivo: " + e.getMessage(), "ERROR");
        }
        return registros;
    }

}