/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.registroPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author demib
 */
public class PnlFoto extends JPanel {

    private PanelImagenCircular foto;
    
    private String rutaImagen;

    public PnlFoto() {
        inicializar();
    }

    private void inicializar() {
        Dimension size = new Dimension(128, 125);
        setBackground(Color.decode("#0078C2"));
        

        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

        setLayout(new BorderLayout());

        foto = new PanelImagenCircular();
        foto.setPreferredSize(new Dimension(100, 100)); // tamaño del circulo
        foto.setOpaque(false);
        add(foto, BorderLayout.CENTER);
        
        foto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarImagen();
            }
        });

    }


    public String seleccionarImagen() {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selecciona una imagen");

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                setImagen(ruta); 
                return ruta;
            }else{
                return null;
            }
    }
    
    // metodo para obtener la ruta de la imagen
    public String getRutaImagen() {
        return rutaImagen;
    }
    
    // método para cambiar la imagen desde fuera

    public void setImagen(String ruta) {
        this.rutaImagen = ruta;
        foto.setImagen(ruta);
    }

    
}
