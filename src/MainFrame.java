import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
* @author Aracely Berrocal Huerta
* */
public class MainFrame extends JFrame {

    File file = new File("src/archivo.txt");

    private final JPanel contentPane;
    private final JTextField txtDescripcion, txtPrecio;
    private final JLabel lblDescripcion, iconDescription, lblPrecio, lblResultado, iconPrice, lblTitle;
    private final JButton btnAlta, btnProductos;
    private final JScrollPane scrollPane;
    private final JTextArea txtProductos;

    // EJECUCIÓN

    public static void main(String[] args) {
        try {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error en compilación");
            e.printStackTrace();
        }
    }
    /*Metodo para recoger los datos del usuario y alamacenarlos en archivo.txt,
    esto ocurrirá cuando se pulse el botón "Alta" */

    public void readFile() {
        try {
            Scanner scan = new Scanner(file);
            String fileContent = "";
            while (scan.hasNextLine()) {
                fileContent = fileContent.concat(scan.nextLine()) + "\n";
                txtProductos.setText(fileContent);
            }
            lblResultado.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Metodo para recoger los datos del archivo.txt e introducirlos en el text Area.
    Este ocurrirá cuando se pulse el botón "Visualizar productos" .*/


    private void writeFile() throws IOException {
        try {
            FileWriter myWriter = new FileWriter(file, true);
            myWriter.write(txtDescripcion.getText() + " -> " + txtPrecio.getText() + "€ \n");
            myWriter.close();
            lblResultado.setText("Guardado");
            lblResultado.setForeground(Color.green);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //DISEÑO DE LA VENTANA

    public MainFrame() {
        setTitle("Manejo de Archivos");
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 529, 600);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 249, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblDescripcion = new JLabel("Descripci\u00F3n del art\u00EDculo:");
        lblDescripcion.setForeground(new Color(56, 55, 55));
        lblDescripcion.setFont(new Font("Inter", Font.PLAIN, 12));
        lblDescripcion.setBounds(100, 132, 154, 18);
        contentPane.add(lblDescripcion);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(new Color(56, 55, 55));
        lblPrecio.setFont(new Font("Inter", Font.PLAIN, 12));
        lblPrecio.setBounds(100, 179, 140, 31);
        contentPane.add(lblPrecio);

        txtDescripcion = new JTextField();
        txtDescripcion.setBorder(new LineBorder(new Color(226, 232, 240), 1, true));
        txtDescripcion.setBounds(297, 126, 182, 31);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        txtPrecio = new JTextField();
        txtPrecio.setBorder(new LineBorder(new Color(226, 232, 240), 1, true));
        txtPrecio.setBounds(297, 172, 182, 31);
        contentPane.add(txtPrecio);
        txtPrecio.setColumns(10);

        btnAlta = new JButton("Alta");
        btnAlta.setBorder(new LineBorder(new Color(79, 209, 197), 1, true));
        btnAlta.setForeground(Color.WHITE);
        btnAlta.setBackground(new Color(79, 209, 197));
        btnAlta.setBounds(321, 229, 136, 34);
        btnAlta.addActionListener(arg0 -> {
            try {
                writeFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        contentPane.add(btnAlta);

        lblResultado = new JLabel("Resultado");
        lblResultado.setFont(new Font("Inter", Font.PLAIN, 11));
        lblResultado.setBounds(368, 275, 64, 19);
        contentPane.add(lblResultado);

        btnProductos = new JButton("Visualizar productos");
        btnProductos.setBorder(new LineBorder(new Color(79, 209, 197), 1, true));
        btnProductos.setForeground(Color.WHITE);
        btnProductos.setBackground(new Color(79, 209, 197));
        btnProductos.setBounds(97, 317, 357, 34);
        btnProductos.addActionListener(arg0 -> readFile());
        contentPane.add(btnProductos);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(97, 357, 357, 173);
        scrollPane.setBorder(new LineBorder(new Color(79, 209, 197), 1, true));
        contentPane.add(scrollPane);

        txtProductos = new JTextArea();
        txtProductos.setFont(new Font("Inter", Font.PLAIN, 13));
        scrollPane.setViewportView(txtProductos);

        iconDescription = new JLabel("");
        iconDescription.setIcon(new ImageIcon(MainFrame.class.getResource("/Icons/descriptionIcon.png")));
        iconDescription.setBounds(48, 126, 42, 41);
        contentPane.add(iconDescription);

        iconPrice = new JLabel("");
        iconPrice.setIcon(new ImageIcon(MainFrame.class.getResource("/Icons/priceIcon.png")));
        iconPrice.setBounds(48, 179, 42, 41);
        contentPane.add(iconPrice);

        lblTitle = new JLabel("Manejo de Archivos");
        lblTitle.setFont(new Font("Inter", Font.PLAIN, 36));
        lblTitle.setBounds(97, 51, 335, 34);
        contentPane.add(lblTitle);
    }
}
