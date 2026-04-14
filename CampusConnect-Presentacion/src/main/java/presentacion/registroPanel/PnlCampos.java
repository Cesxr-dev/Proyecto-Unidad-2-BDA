/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.registroPanel;

import dominio.Carrera;
import dominio.Perfil;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author demib
 */
public class PnlCampos extends JPanel {
    
    private PnlFoto pnlFoto;

    private JTextField txtNombre;
    private com.toedter.calendar.JDateChooser dateChooser;
    private JComboBox<Carrera> comboCarrera;
    private JTextField txtCorreoInstitucional;
    private JTextField txtContrasenia;

    public PnlCampos() {
        pnlFoto = new PnlFoto();
        
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
        
    }
    
    //Pendiente
    public void validarCampos(){
        
    }
    
    //Obtener datos para crear perfil
    public Perfil obtenerDatos(){
        Perfil perfil = new Perfil();
        
        //RUTA FOTO DE PERFIL
        perfil.setFotoPerfil(pnlFoto.getRutaImagen());
        
        //NOMBRE DE ESTUDIANTE
        
        perfil.setNombre(txtNombre.getText());
        
        //FECHA NACIMIENTO
        //.getDate() es de tipo Date
        Date date = dateChooser.getDate();
        //CONVERTIR DE Date a LocalDate
        LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();

        perfil.setFechaNacimiento(fecha);
        
        //CARRERA
        Carrera carrera = (Carrera) comboCarrera.getSelectedItem();
        
        perfil.setCarrera(carrera);
        
        perfil.setCorreoInstitucional(txtCorreoInstitucional.getText());
        
        //CONTRASEÑA
        perfil.setContrasena(txtContrasenia.getText());
        
        return perfil;
    }
}
