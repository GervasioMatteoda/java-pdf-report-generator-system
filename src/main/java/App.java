import gui.PrintForm;
import javax.swing.JFrame;

public class App {
    
    public static void main(String[] args) {
        inicializarGUI();
    }
    
    private static void inicializarGUI() {
        JFrame pantalla;
        pantalla = new PrintForm();
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
    }
}
