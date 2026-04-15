package presentacion.perfil;

import dominio.Carrera;
import dominio.InfoAdicional;
import dominio.Perfil;
import dominio.Sesion;
import dominio.TipoInfo;
import presentacion.registroPanel.PnlFoto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * @author Equipo 2 - "Azul"
 */
public class PnlEditarPerfil extends JPanel {

    private JTextField txtNombre;
    private com.toedter.calendar.JDateChooser dateChooser;
    private JComboBox<Carrera> comboCarrera;
    private PnlFoto pnlFoto;

    private JScrollPane scrollGustos;
    private JScrollPane scrollHobbies;
    private JScrollPane scrollIntereses;

    private JTextArea txtAreaGustos;
    private JTextArea txtAreaHobbies;
    private JTextArea txtAreaIntereses;

    private JButton btnGuardar;
    private JButton btnCancelar;

    private Perfil perfilActual;

    // Colores de borde
    private final Border bordeError = BorderFactory.createLineBorder(Color.RED);
    private final Border bordeOk = BorderFactory.createLineBorder(new Color(200, 200, 200));

    // Interface para notificar cambios al frame principal
    public interface OnAccionListener {

        void onGuardar();

        void onCancelar();
    }
    private OnAccionListener accionListener;

    public PnlEditarPerfil(OnAccionListener listener) {
        this.accionListener = listener;
        inicializarComponentes();
        construirLayout();
        cargarDatosPerfil();
    }

    private void inicializarComponentes() {
        pnlFoto = new PnlFoto();

        txtNombre = new JTextField();
        dateChooser = new com.toedter.calendar.JDateChooser();
        comboCarrera = new JComboBox<>(Carrera.values());

        txtAreaGustos = new JTextArea();
        txtAreaHobbies = new JTextArea();
        txtAreaIntereses = new JTextArea();

        // Configurar áreas de texto
        txtAreaGustos.setLineWrap(true);
        txtAreaGustos.setWrapStyleWord(true);
        txtAreaHobbies.setLineWrap(true);
        txtAreaHobbies.setWrapStyleWord(true);
        txtAreaIntereses.setLineWrap(true);
        txtAreaIntereses.setWrapStyleWord(true);

        // Envolver en JScrollPane
        scrollGustos = new JScrollPane(txtAreaGustos);
        scrollHobbies = new JScrollPane(txtAreaHobbies);
        scrollIntereses = new JScrollPane(txtAreaIntereses);

        Dimension tamanoArea = new Dimension(Integer.MAX_VALUE, 80);
        scrollGustos.setMaximumSize(tamanoArea);
        scrollHobbies.setMaximumSize(tamanoArea);
        scrollIntereses.setMaximumSize(tamanoArea);

        scrollGustos.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollHobbies.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollIntereses.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Configurar campos de texto
        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        dateChooser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboCarrera.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        txtNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboCarrera.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Botones
        btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setBackground(new Color(34, 139, 34));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnGuardar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(e -> guardarCambios());

        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setBackground(new Color(169, 169, 169));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnCancelar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelar.addActionListener(e -> {
            if (accionListener != null) {
                accionListener.onCancelar();
            }
        });
    }

    private void construirLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);

        // ===== FOTO =====
        JPanel pnlFotoContenedor = new JPanel();
        pnlFotoContenedor.setLayout(new BoxLayout(pnlFotoContenedor, BoxLayout.X_AXIS));
        pnlFotoContenedor.setBackground(Color.WHITE);
        pnlFotoContenedor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        pnlFotoContenedor.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlFotoContenedor.add(Box.createHorizontalGlue());
        pnlFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlFotoContenedor.add(pnlFoto);
        pnlFotoContenedor.add(Box.createHorizontalGlue());
        add(pnlFotoContenedor);
        add(Box.createVerticalStrut(15));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblNombre);
        add(Box.createVerticalStrut(5));
        add(txtNombre);
        add(Box.createVerticalStrut(15));

        JLabel lblFecha = new JLabel("Fecha de Nacimiento:");
        lblFecha.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblFecha);
        add(Box.createVerticalStrut(5));
        add(dateChooser);
        add(Box.createVerticalStrut(15));

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblCarrera);
        add(Box.createVerticalStrut(5));
        add(comboCarrera);
        add(Box.createVerticalStrut(15));

        JLabel lblGustos = new JLabel("Gustos:");
        lblGustos.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblGustos);
        add(Box.createVerticalStrut(5));
        add(scrollGustos);
        add(Box.createVerticalStrut(15));

        JLabel lblHobbies = new JLabel("Hobbies:");
        lblHobbies.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblHobbies);
        add(Box.createVerticalStrut(5));
        add(scrollHobbies);
        add(Box.createVerticalStrut(15));

        JLabel lblIntereses = new JLabel("Intereses:");
        lblIntereses.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(lblIntereses);
        add(Box.createVerticalStrut(5));
        add(scrollIntereses);
        add(Box.createVerticalStrut(20));

        add(btnGuardar);
        add(Box.createVerticalStrut(10));
        add(btnCancelar);
        add(Box.createVerticalGlue());
    }

    /**
     * Carga los datos actuales del perfil en los campos
     */
    private void cargarDatosPerfil() {
        perfilActual = Sesion.getPerfilActivo();

        if (perfilActual == null) {
            return;
        }

        // Nombre
        txtNombre.setText(perfilActual.getNombre());

        // Fecha de nacimiento
        LocalDate fecha = perfilActual.getFechaNacimiento();
        if (fecha != null) {
            Date date = java.sql.Date.valueOf(fecha);
            dateChooser.setDate(date);
        }

        // Carrera
        comboCarrera.setSelectedItem(perfilActual.getCarrera());
        
        // Foto
        String rutaFoto = perfilActual.getFotoPerfil();

        if (rutaFoto != null && !rutaFoto.isEmpty()) {
            if (rutaFoto.startsWith("/")) {
                // imagen desde resources
                java.net.URL url = getClass().getResource(rutaFoto);
                if (url != null) {
                    pnlFoto.setImagen(url.getPath());
                }
            } else {
                // ruta absoluta
                pnlFoto.setImagen(rutaFoto);
            }
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
     * Valida los campos del formulario
     */
    private boolean validarCampos() {
        boolean valido = true;

        // Validar nombre
        if (txtNombre.getText().trim().isEmpty()) {
            txtNombre.setBorder(bordeError);
            valido = false;
        } else {
            txtNombre.setBorder(bordeOk);
        }

        // Validar fecha
        if (dateChooser.getDate() == null) {
            dateChooser.setBorder(bordeError);
            valido = false;
        } else {
            LocalDate fecha = dateChooser.getDate().toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            if (fecha.isAfter(LocalDate.now().minusYears(18))) {
                dateChooser.setBorder(bordeError);
                valido = false;
            } else {
                dateChooser.setBorder(bordeOk);
            }
        }

        // Validar gustos, hobbies, intereses
        if (txtAreaGustos.getText().trim().isEmpty()) {
            scrollGustos.setBorder(bordeError);
            valido = false;
        } else {
            scrollGustos.setBorder(bordeOk);
        }

        if (txtAreaHobbies.getText().trim().isEmpty()) {
            scrollHobbies.setBorder(bordeError);
            valido = false;
        } else {
            scrollHobbies.setBorder(bordeOk);
        }

        if (txtAreaIntereses.getText().trim().isEmpty()) {
            scrollIntereses.setBorder(bordeError);
            valido = false;
        } else {
            scrollIntereses.setBorder(bordeOk);
        }

        return valido;
    }

    /**
     * Obtiene los datos editados
     */
    private Perfil obtenerDatosEditados() {
        Perfil perfil = new Perfil();
        perfil.setId(perfilActual.getId());

        // Nombre
        perfil.setNombre(txtNombre.getText());

        // Fecha
        Date date = dateChooser.getDate();
        LocalDate fecha = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        perfil.setFechaNacimiento(fecha);

        // Carrera
        Carrera carrera = (Carrera) comboCarrera.getSelectedItem();
        perfil.setCarrera(carrera);

        // Foto (si se seleccionó una nueva)
        String rutaFoto = pnlFoto.getRutaImagen();
        if (rutaFoto != null && !rutaFoto.isEmpty()) {
            perfil.setFotoPerfil(rutaFoto);
        } else {
            perfil.setFotoPerfil(perfilActual.getFotoPerfil());
        }

        // Mantener correo y contraseña del perfil actual
        perfil.setCorreoInstitucional(perfilActual.getCorreoInstitucional());
        perfil.setContrasena(perfilActual.getContrasena());

        // Gustos, hobbies, intereses
        Set<InfoAdicional> infoAdicionalSet = new HashSet<>();

        InfoAdicional infoGustos = new InfoAdicional();
        infoGustos.setTipo(TipoInfo.GUSTO);
        infoGustos.setNombre(txtAreaGustos.getText());
        infoAdicionalSet.add(infoGustos);

        InfoAdicional infoHobbies = new InfoAdicional();
        infoHobbies.setTipo(TipoInfo.HOBBIE);
        infoHobbies.setNombre(txtAreaHobbies.getText());
        infoAdicionalSet.add(infoHobbies);

        InfoAdicional infoIntereses = new InfoAdicional();
        infoIntereses.setTipo(TipoInfo.INTERES);
        infoIntereses.setNombre(txtAreaIntereses.getText());
        infoAdicionalSet.add(infoIntereses);

        for (InfoAdicional info : infoAdicionalSet) {
            info.getPerfiles().add(perfil);
        }

        perfil.setPerfilInfoAdicional(infoAdicionalSet);

        return perfil;
    }

    /**
     * Guarda los cambios realizados
     */
    private void guardarCambios() {
        if (!validarCampos()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "Por favor completa todos los campos correctamente",
                    "Error de Validación",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Mostrar confirmación
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas guardar los cambios?",
                "Confirmar cambios",
                javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            try {
                Perfil perfilEditado = obtenerDatosEditados();

                // Actualizar en BD
                servicios.PerfilService perfilService = new servicios.PerfilService();
                perfilService.actualizar(perfilEditado);

                // Actualizar en sesión
                Sesion.setPerfilActivo(perfilEditado);

                javax.swing.JOptionPane.showMessageDialog(this,
                        "¡Cambios guardados exitosamente!",
                        "Éxito",
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);

                // Notificar al listener
                if (accionListener != null) {
                    accionListener.onGuardar();
                }
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Error al guardar cambios: " + e.getMessage(),
                        "Error del Sistema",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Limpia los bordes de error
     */
    private void limpiarBordes() {
        txtNombre.setBorder(bordeOk);
        dateChooser.setBorder(bordeOk);
        scrollGustos.setBorder(bordeOk);
        scrollHobbies.setBorder(bordeOk);
        scrollIntereses.setBorder(bordeOk);
    }
}
