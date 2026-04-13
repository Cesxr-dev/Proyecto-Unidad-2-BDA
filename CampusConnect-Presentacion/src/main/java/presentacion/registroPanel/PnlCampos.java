/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.registroPanel;

import dominio.Carrera;
import dominio.InfoAdicional;
import dominio.Perfil;
import dominio.TipoInfo;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author demib
 */
public class PnlCampos extends JPanel {
    
    

    private JTextField txtNombre;
    private com.toedter.calendar.JDateChooser dateChooser;
    private JComboBox<Carrera> comboCarrera;
    private JTextField txtCorreoInstitucional;
    private JTextField txtContrasenia;
    
    
    private JScrollPane scrollGustos;
    private JScrollPane scrollHobbies;
    private JScrollPane scrollIntereses;
    
    private JTextArea txtAreaGustos;
    
    private JTextArea txtAreaHobbies;
    
    private JTextArea txtAreaIntereses;
    
    // Colores de borde como constantes de instancia
    private final Border bordeError = BorderFactory.createLineBorder(Color.RED);
    private final Border bordeOk = BorderFactory.createLineBorder(new Color(200, 200, 200)); // gris suave

    public PnlCampos() {
        
        
        inicializarComponentes();
        construirLayout();
        setPreferredSize(new Dimension(324, 602));
        this.setVisible(true);
    }
    
    

    private void inicializarComponentes() {
        txtNombre = new JTextField();
        dateChooser = new com.toedter.calendar.JDateChooser();
        comboCarrera = new JComboBox<>(Carrera.values());
        txtCorreoInstitucional = new JTextField();
        txtContrasenia = new JTextField();
        
        txtAreaGustos = new JTextArea();
        txtAreaHobbies = new JTextArea();
        txtAreaIntereses = new JTextArea();
        
        txtAreaGustos.setLineWrap(true);    // va hacer que el texto haga salto de linea
        txtAreaGustos.setWrapStyleWord(true);
        txtAreaHobbies.setLineWrap(true);
        txtAreaHobbies.setWrapStyleWord(true);
        txtAreaIntereses.setLineWrap(true);
        txtAreaIntereses.setWrapStyleWord(true);
        
        // Envolver en JScrollPane con tamaño fijo
        scrollGustos = new JScrollPane(txtAreaGustos);
        scrollHobbies = new JScrollPane(txtAreaHobbies);
        scrollIntereses = new JScrollPane(txtAreaIntereses);
        
        Dimension tamanoArea = new Dimension(Integer.MAX_VALUE, 80); // altura fija 80px
        scrollGustos.setMaximumSize(tamanoArea);
        scrollHobbies.setMaximumSize(tamanoArea);
        scrollIntereses.setMaximumSize(tamanoArea);

        scrollGustos.setAlignmentX(LEFT_ALIGNMENT);
        scrollHobbies.setAlignmentX(LEFT_ALIGNMENT);
        scrollIntereses.setAlignmentX(LEFT_ALIGNMENT);
        

        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        dateChooser.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboCarrera.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtCorreoInstitucional.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        // Alinea todo a la izquierda
        txtNombre.setAlignmentX(LEFT_ALIGNMENT);
        dateChooser.setAlignmentX(LEFT_ALIGNMENT);
        comboCarrera.setAlignmentX(LEFT_ALIGNMENT);
        txtCorreoInstitucional.setAlignmentX(LEFT_ALIGNMENT);
        txtContrasenia.setAlignmentX(LEFT_ALIGNMENT);
    }

    private void construirLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        // ===== NOMBRE =====
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblNombre);
        add(Box.createVerticalStrut(5));
        add(txtNombre);
        add(Box.createVerticalStrut(15));

        // ===== FECHA =====
        JLabel lblFecha = new JLabel("Fecha de Nacimiento:");
        lblFecha.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblFecha);
        add(Box.createVerticalStrut(5));
        add(dateChooser);
        add(Box.createVerticalStrut(15));

        // ===== CARRERA =====
        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblCarrera);
        add(Box.createVerticalStrut(5));
        add(comboCarrera);
        add(Box.createVerticalStrut(15));
        
        // ===== CORREO INSTITUCIONAL =====
        JLabel lblCorreoInstitucional = new JLabel("Correo Institucional:");
        lblCarrera.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblCorreoInstitucional);
        add(Box.createVerticalStrut(5));
        add(txtCorreoInstitucional);
        add(Box.createVerticalStrut(15));
        
        
        // ===== CONTRASEÑA =====
        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblCarrera.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblContrasenia);
        add(Box.createVerticalStrut(5));
        add(txtContrasenia);
        
        // ===== gustos =====
        JLabel lblGustos = new JLabel("Gustos:");
        lblCarrera.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblGustos);
        add(Box.createVerticalStrut(5));
        add(scrollGustos); 
        add(Box.createVerticalStrut(15));
        
        // ===== hobbies =====
        JLabel lblHobbies = new JLabel("Hobbies:");
        lblCarrera.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblHobbies);
        add(Box.createVerticalStrut(5));
        add(scrollHobbies); 
        add(Box.createVerticalStrut(15));
        
        // ===== intereses =====
        JLabel lblIntereses = new JLabel("Intereses:");
        lblCarrera.setAlignmentX(LEFT_ALIGNMENT); 
        add(lblIntereses);
        add(Box.createVerticalStrut(5));
        add(scrollIntereses); 
        add(Box.createVerticalStrut(15));
        
    }
    
    //Pendiente
    public boolean validarCampos() {
        boolean valido = true;

        if (txtNombre.getText().trim().isEmpty()) {
            txtNombre.setBorder(bordeError);
            valido = false;
        } else {
            txtNombre.setBorder(bordeOk);
        }

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

        String correo = txtCorreoInstitucional.getText().trim();
        if (correo.isEmpty() || !correo.matches("^[\\w.]+@potros.itson\\.edu\\.mx$")) {
            txtCorreoInstitucional.setBorder(bordeError);
            valido = false;
        } else {
            txtCorreoInstitucional.setBorder(bordeOk);
        }

        if (txtContrasenia.getText().trim().length() < 6) {
            txtContrasenia.setBorder(bordeError);
            valido = false;
        } else {
            txtContrasenia.setBorder(bordeOk);
        }

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
    
    public void limpiarCampos() {
        // 1. Limpiar campos de texto
        txtNombre.setText("");
        txtCorreoInstitucional.setText("");
        txtContrasenia.setText("");
        
        // 2. Limpiar JDateChooser (se le asigna null para dejarlo en blanco)
        dateChooser.setDate(null);
        
        // 3. Reiniciar el JComboBox a la primera opcion (índice 0)
        // -1 para que no se vea ninguna opcioon
        comboCarrera.setSelectedIndex(0); 
        
        // 4. Limpiar areas de texto
        txtAreaGustos.setText("");
        txtAreaHobbies.setText("");
        txtAreaIntereses.setText("");
    }
    
    //Obtener datos para crear perfil
    public Perfil obtenerDatos(){
        Perfil perfil = new Perfil();
        
        
        
        //RUTA NOMBRE
        perfil.setNombre(txtNombre.getText());
        
        //FECHA NACIMIENTO
            //.getDate() es de tipo Date
            Date date = dateChooser.getDate();
            //CONVERTIR DE Date a LocalDate
            LocalDate fecha = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            perfil.setFechaNacimiento(fecha);
        
        //CARRERA
        Carrera carrera = (Carrera) comboCarrera.getSelectedItem();
        
        perfil.setCarrera(carrera);
        
        //CORREO
        perfil.setCorreoInstitucional(txtCorreoInstitucional.getText());
        
        //CONTRASEÑA
        perfil.setContrasena(txtContrasenia.getText());
        
        
        
        //GUSTOS-HOBBIES-INTERESES
        Set<InfoAdicional> infoAdicionalSet = new HashSet<>();

        InfoAdicional infoAdicionalGustos = new InfoAdicional();
        infoAdicionalGustos.setTipo(TipoInfo.GUSTO);
        infoAdicionalGustos.setNombre(txtAreaGustos.getText());
        
        infoAdicionalSet.add(infoAdicionalGustos);

        InfoAdicional infoAdicionalHobbies = new InfoAdicional();
        infoAdicionalHobbies.setTipo(TipoInfo.HOBBIE);
        infoAdicionalHobbies.setNombre(txtAreaHobbies.getText());
        
        infoAdicionalSet.add(infoAdicionalHobbies);

        InfoAdicional infoAdicionalIntereses = new InfoAdicional();
        infoAdicionalIntereses.setTipo(TipoInfo.INTERES);
        infoAdicionalIntereses.setNombre(txtAreaIntereses.getText());
        
        infoAdicionalSet.add(infoAdicionalIntereses);
        
        for (InfoAdicional info : infoAdicionalSet) {
            info.getPerfiles().add(perfil);
        }

        perfil.setPerfilInfoAdicional(infoAdicionalSet);

        return perfil;
    }
}
