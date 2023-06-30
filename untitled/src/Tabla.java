import javax.swing.*;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Tabla {


    public static void mostrarRegistrosEnTabla(List<Profesor> misProfesores) {
        //obtiene registros
        List<String> registros = GestionArchivo.leerRegistros();

        //creo el modelo de la tabla
        DefaultTableModel miTabla = new DefaultTableModel();
        miTabla.addColumn("Id");
        miTabla.addColumn("Nombre");
        miTabla.addColumn("Apellido");
        miTabla.addColumn("Email");
        miTabla.addColumn("DNI");
        miTabla.addColumn("Materia");

        //agrega registros ak modelo de la tabla
        for (int i = 0; i < registros.size(); i++) {
            String registro = registros.get(i);
            String[] datosRegistro = registro.split(",");
            miTabla.addRow(datosRegistro);
        }

        //crea la tabla
        JTable table = new JTable(miTabla);//creo un objeto tabla
        table.setCellSelectionEnabled(false); // Desactiva la selección de celdas
        table.getTableHeader().setReorderingAllowed(false); // Desactiva el movimiento de columnas
        JScrollPane scrollPane = new JScrollPane(table); //permite agregar barras de desplazamiento
        JDialog dialog = new JDialog();
        dialog.setTitle("Lista de Profesores");// titulo de la ventana
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(scrollPane, BorderLayout.CENTER);
        //dialog.setSize(500, 300);//tamaño de la ventana
        //dialog.setLocationRelativeTo(null);
        //dialog.setVisible(true);


        // Modifica el ancho de las columnas
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // Ajusta el ancho de la columna "Número"
        columnModel.getColumn(3).setPreferredWidth(200); // Ajusta el ancho de la columna "Email"
        columnModel.getColumn(5).setPreferredWidth(200); // Ajusta el ancho de la columna "Materia"


        // Crea el botón "Cerrar"
        JButton closeButton = new JButton("Cerrar");

        //closeButton.setPreferredSize(new Dimension(100, 30)); // Establece las dimensiones deseadas del botón
        closeButton.setBounds(50, 50, 100, 30);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cierra el diálogo
            }
        });
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setSize(700, 350);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }


}
