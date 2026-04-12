/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.registroPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import presentacion.PanelImagenCircular;

/**
 *
 * @author demib
 */
public class PnlFoto extends JPanel {

    private PanelImagenCircular foto;

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
    }

    // metodo para cambiar la imagen desde fuera
    public void setImagen(String ruta) {
        foto.setImagen(ruta);
    }
}
