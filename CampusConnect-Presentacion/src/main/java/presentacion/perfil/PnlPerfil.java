package presentacion.perfil;

import dominio.InfoAdicional;
import dominio.Perfil;
import dominio.Sesion;
import dominio.TipoInfo;
import presentacion.registroPanel.PanelImagenCircular;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel que muestra el perfil del usuario actual Contiene: foto, nombre,
 * carrera, gustos, hobbies, intereses Botones: EDITAR y ELIMINAR CUENTA
 *
 * @author Equipo 2 - "Azul"
 */
public class PnlPerfil extends JPanel {

    private PanelImagenCircular fotoPerfil;
    private JLabel lblNombre;
    private JLabel lblCarrera;
    private JTextArea txtAreaGustos;
    private JTextArea txtAreaHobbies;
    private JTextArea txtAreaIntereses;
    private JButton btnEditar;
    private JButton btnEliminar;

    private Perfil perfilActual;

    // Interface para notificar cambios al frame principal
    public interface OnAccionListener {

        void onEditar();

        void onEliminar();
    }
    private OnAccionListener accionListener;

    public PnlPerfil(OnAccionListener listener) {
        this.accionListener = listener;
        inicializarComponentes();
        construirLayout();
        cargarDatosPerfil();
    }

    private void inicializarComponentes() {
        fotoPerfil = new PanelImagenCircular();
        fotoPerfil.setPreferredSize(new Dimension(100, 100));
        fotoPerfil.setMaximumSize(new Dimension(100, 100));

        lblNombre = new JLabel();
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNombre.setForeground(Color.BLACK);

        lblCarrera = new JLabel();
        lblCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCarrera.setForeground(new Color(80, 80, 80));

        // Áreas de texto para gustos, hobbies, intereses (no editables)
        txtAreaGustos = new JTextArea();
        txtAreaGustos.setLineWrap(true);
        txtAreaGustos.setWrapStyleWord(true);
        txtAreaGustos.setEditable(false);
        txtAreaGustos.setOpaque(false);
        txtAreaGustos.setBorder(null);

        txtAreaHobbies = new JTextArea();
        txtAreaHobbies.setLineWrap(true);
        txtAreaHobbies.setWrapStyleWord(true);
        txtAreaHobbies.setEditable(false);
        txtAreaHobbies.setOpaque(false);
        txtAreaHobbies.setBorder(null);

        txtAreaIntereses = new JTextArea();
        txtAreaIntereses.setLineWrap(true);
        txtAreaIntereses.setWrapStyleWord(true);
        txtAreaIntereses.setEditable(false);
        txtAreaIntereses.setOpaque(false);
        txtAreaIntereses.setBorder(null);

        // Botones
        btnEditar = new JButton("EDITAR PERFIL");
        btnEditar.setBackground(Color.BLACK);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnEditar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnEditar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditar.addActionListener(e -> {
            if (accionListener != null) {
                accionListener.onEditar();
            }
        });

        btnEliminar = new JButton("ELIMINAR CUENTA");
        btnEliminar.setBackground(new Color(220, 20, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnEliminar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEliminar.addActionListener(e -> {
            if (accionListener != null) {
                accionListener.onEliminar();
            }
        });
    }

    private void construirLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        // ===== FOTO =====
        JPanel pnlFoto = new JPanel();
        pnlFoto.setLayout(new BoxLayout(pnlFoto, BoxLayout.X_AXIS));
        pnlFoto.setBackground(Color.WHITE);
        pnlFoto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        pnlFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlFoto.add(Box.createHorizontalGlue());
        pnlFoto.add(fotoPerfil);
        pnlFoto.add(Box.createHorizontalGlue());
        add(pnlFoto);
        add(Box.createVerticalStrut(20));

        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblNombre);
        add(Box.createVerticalStrut(5));

        lblCarrera.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(lblCarrera);
        add(Box.createVerticalStrut(20));

        JLabel lblGustos = new JLabel("Gustos:");
        lblGustos.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblGustos.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblGustos);
        add(Box.createVerticalStrut(5));
        add(crearScrollPane(txtAreaGustos));
        add(Box.createVerticalStrut(15));

        JLabel lblHobbies = new JLabel("Hobbies:");
        lblHobbies.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblHobbies.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblHobbies);
        add(Box.createVerticalStrut(5));
        add(crearScrollPane(txtAreaHobbies));
        add(Box.createVerticalStrut(15));

        JLabel lblIntereses = new JLabel("Intereses:");
        lblIntereses.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblIntereses.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblIntereses);
        add(Box.createVerticalStrut(5));
        add(crearScrollPane(txtAreaIntereses));
        add(Box.createVerticalStrut(30));

        add(btnEditar);
        add(Box.createVerticalStrut(10));
        add(btnEliminar);
        add(Box.createVerticalGlue());
    }

    private JScrollPane crearScrollPane(JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        return scrollPane;
    }

    private void cargarDatosPerfil() {
        perfilActual = Sesion.getPerfilActivo();

        if (perfilActual == null) {
            lblNombre.setText("No hay perfil activo");
            return;
        }

        // Nombre
        lblNombre.setText(perfilActual.getNombre());

        // Carrera
        lblCarrera.setText("Carrera: " + perfilActual.getCarrera().toString());

        // Foto
        String rutaFoto = perfilActual.getFotoPerfil();
        if (rutaFoto != null && !rutaFoto.isEmpty()) {
            fotoPerfil.setImagen(rutaFoto);
        }

        // Separar gustos, hobbies e intereses
        String gustos = "";
        String hobbies = "";
        String intereses = "";

        if (perfilActual.getPerfilInfoAdicional() != null) {
            for (InfoAdicional info : perfilActual.getPerfilInfoAdicional()) {
                if (info.getTipo() == TipoInfo.GUSTO) {
                    gustos = info.getNombre();
                } else if (info.getTipo() == TipoInfo.HOBBIE) {
                    hobbies = info.getNombre();
                } else if (info.getTipo() == TipoInfo.INTERES) {
                    intereses = info.getNombre();
                }
            }
        }

        txtAreaGustos.setText(gustos);
        txtAreaHobbies.setText(hobbies);
        txtAreaIntereses.setText(intereses);
    }

    /**
     * Actualiza el panel con los datos más recientes de la sesión Se llama
     * después de guardar cambios en el perfil
     */
    public void actualizarDatos() {
        cargarDatosPerfil();
    }
}
