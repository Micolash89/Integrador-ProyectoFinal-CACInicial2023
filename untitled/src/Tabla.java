import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Tabla {


    public static void mostrarRegistrosEnTabla(List <Profesor> misProfesores) {
        //obtiene registros
        List<String> registros = GestionArchivo.leerRegistros();

        //creo el modelo de la tabla
        DefaultTableModel miTabla = new DefaultTableModel();
        miTabla.addColumn("Id");
        miTabla.addColumn("Nombre");
        miTabla.addColumn("Apellido");
        miTabla.addColumn("DNI");
        miTabla.addColumn("Email");
        miTabla.addColumn("Materia");

        //agrega registros ak modelo de la tabla
        for (int i = 0; i < registros.size(); i++) {
            String registro = registros.get(i);
            String[] datosRegistro = registro.split(",");
            miTabla.addRow(datosRegistro);
        }

        //crea la tabla
        JTable table = new JTable(miTabla);//creo un objeto tabla
        JScrollPane scrollPane = new JScrollPane(table); //permite agregar barras de desplazamiento
        JDialog dialog = new JDialog();
        dialog.setTitle("Lista de Profesores");// titulo de la ventana
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setSize(500, 300);//tamaño de la ventana
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
