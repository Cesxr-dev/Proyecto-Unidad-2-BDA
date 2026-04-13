
package presentacion.registroPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class PanelImagenCircular extends JPanel {
    
    private BufferedImage imagen;
    
    public void setImagen(String ruta) {
        try {
            imagen = ImageIO.read(new File(ruta));
            this.repaint();
        } catch (IOException e) {
            e.printStackTrace();
            imagen = null;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            
            int diametro = Math.min(this.getWidth(), this.getHeight());
            int x = (this.getWidth() - diametro) / 2;
            int y = (this.getHeight() - diametro) / 2;
            Ellipse2D circulo = new Ellipse2D.Float(x, y, diametro, diametro);
            
            g2d.setClip(circulo);
        
        if (imagen != null) {
            int anchoImagen = imagen.getWidth();
            int altoImagen = imagen.getHeight();
            double escala = Math.max((double)diametro / anchoImagen, 
                    (double)diametro / altoImagen);
            int anchoEscalado = (int) (anchoImagen * escala);
            int altoEscalado = (int) (altoImagen * escala);
            int dx = (this.getWidth() - anchoEscalado) / 2;
            int dy = (this.getHeight() - altoEscalado) / 2;
            
            g2d.drawImage(imagen, dx, dy, anchoEscalado, altoEscalado, this);
            
        } else {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fill(circulo);
            g2d.setColor(Color.GRAY);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, diametro / 3));
            FontMetrics fm = g2d.getFontMetrics();
            String texto = "?";
            int anchoTexto = fm.stringWidth(texto);
            g2d.drawString(texto, x + (diametro - anchoTexto) / 2, 
                    (y - 5) + (diametro + fm.getAscent()) / 2);
        }
        g2d.dispose();
    }
    
}
