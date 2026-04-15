/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.homepagePanel;

/**
 *
 * @author demib
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javax.swing.JPanel;



/**
 * Panel tipo "card" para mostrar el perfil de un usuario.
 * este incluye imagen, nombre, carrera, intereses y btns.
 */
public class PnlPerfilCard extends JPanel {

    
    // COLORES
    
    private static final Color COLOR_CARD_BG       = Color.WHITE;
    private static final Color COLOR_FONDO          = new Color(245, 245, 245);
    private static final Color COLOR_OVERLAY        = new Color(0, 0, 0, 120);   // negro semitransparente
    private static final Color COLOR_NOMBRE         = Color.WHITE;
    private static final Color COLOR_CARRERA        = new Color(220, 220, 220);
    private static final Color COLOR_INTERESES_TIT  = new Color(80, 80, 80);
    private static final Color COLOR_INTERESES_TXT  = new Color(100, 100, 100);
    private static final Color COLOR_BTN_MEGUSTA    = new Color(0, 120, 194);    // azul
    private static final Color COLOR_BTN_NO         = new Color(245, 245, 245);  // gris claro
    private static final Color COLOR_BTN_NO_TEXTO   = new Color(80, 80, 80);
    private static final Color COLOR_BTN_BORDE_NO   = new Color(200, 200, 200);

    
    // FUENTES
    
    private static final Font FUENTE_NOMBRE    = new Font("Segoe UI", Font.BOLD,  18);
    private static final Font FUENTE_CARRERA   = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FUENTE_INT_TIT   = new Font("Segoe UI", Font.BOLD,  12);
    private static final Font FUENTE_INT_TXT   = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FUENTE_BTN       = new Font("Segoe UI", Font.BOLD,  13);

    
    // COMPONENTES
    
    private PnlImagen    pnlImagen;
    private JLabel       lblInteresesTitulo;
    private JLabel       lblIntereses;
    private JButton      btnMeGusta;
    private JButton      btnNoMeInteresa;

    
    // DATOS
    
    private String nombre;
    private String carrera;
    private String intereses;
    private String rutaImagen;

    
    // CONSTRUCTOR
    
    public PnlPerfilCard(String nombre, String carrera, String intereses, String rutaImagen) {
        this.nombre     = nombre;
        this.carrera    = carrera;
        this.intereses  = intereses;
        this.rutaImagen = rutaImagen;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(COLOR_FONDO);
        setBorder(new EmptyBorder(20, 20, 20, 20));

        inicializarComponentes();
        construirLayout();
    }

    
    // INICIALIZAR
    
    private void inicializarComponentes() {

        // Panel imagen con overlay de nombre/carrera 
        pnlImagen = new PnlImagen(rutaImagen, nombre, carrera);
        pnlImagen.setAlignmentX(LEFT_ALIGNMENT);
        pnlImagen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));
        pnlImagen.setPreferredSize(new Dimension(350, 220));

        // Titulo intereses 
        lblInteresesTitulo = new JLabel("Intereses");
        lblInteresesTitulo.setFont(FUENTE_INT_TIT);
        lblInteresesTitulo.setForeground(COLOR_INTERESES_TIT);
        lblInteresesTitulo.setAlignmentX(LEFT_ALIGNMENT);

        // Texto intereses
        lblIntereses = new JLabel("<html>" + intereses + "</html>");
        lblIntereses.setFont(FUENTE_INT_TXT);
        lblIntereses.setForeground(COLOR_INTERESES_TXT);
        lblIntereses.setAlignmentX(LEFT_ALIGNMENT);

        //  Botón Me gusta 
        btnMeGusta = crearBoton("Me gusta", COLOR_BTN_MEGUSTA, Color.WHITE, null);
        btnMeGusta.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btnMeGusta.setBackground(COLOR_BTN_MEGUSTA.darker());
            }
            @Override public void mouseExited(MouseEvent e) {
                btnMeGusta.setBackground(COLOR_BTN_MEGUSTA);
            }    
        });
        
        // --- Botón No me interesa ---
        btnNoMeInteresa = crearBoton("No me interesa", COLOR_BTN_NO, COLOR_BTN_NO_TEXTO, COLOR_BTN_BORDE_NO);
        btnNoMeInteresa.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btnNoMeInteresa.setBackground(new Color(230, 230, 230));
            }
            @Override public void mouseExited(MouseEvent e) {
                btnNoMeInteresa.setBackground(COLOR_BTN_NO);
            }
        });
    }

    
    // LAYOUT
    
    private void construirLayout() {

        // Card blanca redondeada
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_CARD_BG);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(0, 0, 15, 0));
        card.setAlignmentX(LEFT_ALIGNMENT);

        // Imagen arriba del card
        card.add(pnlImagen);

        // Seccion intereses
        JPanel pnlIntereses = new JPanel();
        pnlIntereses.setLayout(new BoxLayout(pnlIntereses, BoxLayout.Y_AXIS));
        pnlIntereses.setOpaque(false);
        pnlIntereses.setBorder(new EmptyBorder(12, 15, 12, 15));
        pnlIntereses.setAlignmentX(LEFT_ALIGNMENT);

        lblInteresesTitulo.setAlignmentX(LEFT_ALIGNMENT);
        lblIntereses.setAlignmentX(LEFT_ALIGNMENT);

        pnlIntereses.add(lblInteresesTitulo);
        pnlIntereses.add(Box.createVerticalStrut(4));
        pnlIntereses.add(lblIntereses);

        card.add(pnlIntereses);

        // Fila de btns
        JPanel pnlBotones = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlBotones.setOpaque(false);
        pnlBotones.setBorder(new EmptyBorder(0, 15, 15, 15));
        pnlBotones.setAlignmentX(LEFT_ALIGNMENT);
        pnlBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        pnlBotones.add(btnNoMeInteresa);
        pnlBotones.add(btnMeGusta);
        card.add(pnlBotones);

        add(card);
    }

    // 
    // crear boton estilizado
    // 
    private JButton crearBoton(String texto, Color fondo, Color textoColor, Color borde) {
        JButton btn = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
                if (borde != null) {
                    g2.setColor(borde);
                    g2.setStroke(new BasicStroke(1.5f));
                    g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, getWidth()-1, getHeight()-1, 30, 30));
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setText(texto);
        btn.setFont(FUENTE_BTN);
        btn.setForeground(textoColor);
        btn.setBackground(fondo);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 42));
        return btn;
    }

    // 
    // GETTERS DE BOTONES (para agregar listeners)
    // 
    public JButton getBtnMeGusta()      { return btnMeGusta; }
    public JButton getBtnNoMeInteresa() { return btnNoMeInteresa; }
    
     
    // ACTUALIZAR DATOS DEL CARD
    
    public void actualizarPerfil(String nombre, String carrera, String intereses, String rutaImagen) {
        this.nombre     = nombre;
        this.carrera    = carrera;
        this.intereses  = intereses;
        this.rutaImagen = rutaImagen;

        lblIntereses.setText("<html>" + intereses + "</html>");
        pnlImagen.actualizarDatos(rutaImagen, nombre, carrera);
        repaint();
    }

    // =========================================
    // PANEL IMAGEN INTERNO con overlay
    // =========================================
    private class PnlImagen extends JPanel {

        private String ruta;
        private String nombre;
        private String carrera;
        private BufferedImage imagen;

        public PnlImagen(String ruta, String nombre, String carrera) {
            this.ruta    = ruta;
            this.nombre  = nombre;
            this.carrera = carrera;
            setOpaque(false);
            cargarImagen();
        }

        private void cargarImagen() {
            if (ruta != null && !ruta.isEmpty()) {
                try {
                    imagen = ImageIO.read(new File(ruta));
                } catch (IOException e) {
                    imagen = null; // si no carga, muestra placeholder
                }
            }
        }

        public void actualizarDatos(String ruta, String nombre, String carrera) {
            this.ruta    = ruta;
            this.nombre  = nombre;
            this.carrera = carrera;
            cargarImagen();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int radio = 20;

            // Clip redondeado (solo esquinas superiores)
            g2.setClip(new RoundRectangle2D.Float(0, 0, w, h, radio, radio));

            // Imagen o placeholder
            if (imagen != null) {
                g2.drawImage(imagen, 0, 0, w, h, null);
            } else {
                // Placeholder gris con icono de persona
                g2.setColor(new Color(180, 180, 180));
                g2.fillRect(0, 0, w, h);
                g2.setColor(new Color(220, 220, 220));
                g2.setFont(new Font("Segoe UI", Font.BOLD, 60));
                FontMetrics fm = g2.getFontMetrics();
                String icono = "?";
                int ix = (w - fm.stringWidth(icono)) / 2;
                int iy = (h + fm.getAscent()) / 2 - 10;
                g2.drawString(icono, ix, iy);
            }

            // Overlay gradiente oscuro en la parte inferior
            GradientPaint gradiente = new GradientPaint(
                0, h * 0.45f, new Color(0, 0, 0, 0),
                0, h,         new Color(0, 0, 0, 190)
            );
            g2.setPaint(gradiente);
            g2.fillRect(0, 0, w, h);

            // Nombre
            g2.setColor(COLOR_NOMBRE);
            g2.setFont(FUENTE_NOMBRE);
            g2.drawString(nombre, 15, h - 30);

            // Carrera
            g2.setColor(COLOR_CARRERA);
            g2.setFont(FUENTE_CARRERA);
            g2.drawString(carrera, 15, h - 12);

            g2.dispose();
        }
    }
}
