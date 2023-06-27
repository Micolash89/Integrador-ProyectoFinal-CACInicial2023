import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Color;

public class InterfazGrafica {

    public static String mensajeMenu(String mensaje, String titulo) {

        ImageIcon icono = new ImageIcon("images/icono.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        //UIManager.put("Panel.background", Color.yellow);

        return (String) JOptionPane.showInputDialog(null,mensaje, titulo,JOptionPane.QUESTION_MESSAGE, icono, null, null);

    }

    public static String mensajeIngreso(String cadena,String TITULO) {

        return JOptionPane.showInputDialog(null,cadena,TITULO,JOptionPane.QUESTION_MESSAGE);


    }

    public static void mensajeMostrar(String cadena) {

        JOptionPane.showMessageDialog(null, cadena);

    }

    public static void mensajeExito(String cadena,String titulo) {
        ImageIcon icono = new ImageIcon("images/ok.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

        //UIManager.put("Panel.background", Color.yellow);
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.INFORMATION_MESSAGE, icono);

    }

    public static void mensajeCancelar(String cadena, String titulo) {

        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.ERROR_MESSAGE);

    }

    public static void mensajeAdvertencia(String cadena, String titulo) {

        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.WARNING_MESSAGE);

    }

    public static void mensajeProfe(String cadena, String titulo) {

        ImageIcon icono = new ImageIcon("images/profe.png");
        icono = new ImageIcon(icono.getImage().getScaledInstance(70, 80, java.awt.Image.SCALE_SMOOTH));
        JOptionPane.showMessageDialog(null, cadena, titulo, JOptionPane.WARNING_MESSAGE,icono);

    }

}
