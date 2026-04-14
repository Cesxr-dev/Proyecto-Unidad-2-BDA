package presentacion.registroPanel;

import dominio.Perfil;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import presentacion.inicioSesion.InicioSesionFrm;
import servicios.IPerfilService;
import servicios.PerfilService;

/**
 *
 * @author Equipo 2 - "Azul"
 */
public class RegistroUsuarioFrm extends javax.swing.JFrame {

    IPerfilService perfilService = new PerfilService();

    PnlCampos pnlCampos;
    PnlFoto pnlFoto;

    public RegistroUsuarioFrm() {
        pnlFoto = new PnlFoto();
        pnlCampos = new PnlCampos();

        initComponents();
        inicializarComponentes();
        asignarCompALayout();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        crearCuentaLbl = new javax.swing.JLabel();
        regresarBtn = new javax.swing.JButton();
        pnlFormRegistro = new javax.swing.JPanel();
        pnlBottom = new javax.swing.JPanel();
        btnContinuar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(390, 844));
        setResizable(false);
        setSize(new java.awt.Dimension(390, 844));

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setMaximumSize(null);

        crearCuentaLbl.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        crearCuentaLbl.setForeground(new java.awt.Color(0, 0, 0));
        crearCuentaLbl.setText("Registro");

        regresarBtn.setBackground(new java.awt.Color(255, 255, 255));
        regresarBtn.setForeground(new java.awt.Color(0, 0, 0));
        regresarBtn.setText("<-");
        regresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeaderLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(regresarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(crearCuentaLbl)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crearCuentaLbl)
                    .addComponent(regresarBtn))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlFormRegistro.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlFormRegistroLayout = new javax.swing.GroupLayout(pnlFormRegistro);
        pnlFormRegistro.setLayout(pnlFormRegistroLayout);
        pnlFormRegistroLayout.setHorizontalGroup(
            pnlFormRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        pnlFormRegistroLayout.setVerticalGroup(
            pnlFormRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );

        getContentPane().add(pnlFormRegistro, java.awt.BorderLayout.CENTER);

        pnlBottom.setBackground(new java.awt.Color(0, 120, 194));
        pnlBottom.setMaximumSize(null);

        btnContinuar.setBackground(new java.awt.Color(0, 0, 0));
        btnContinuar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnContinuar.setForeground(new java.awt.Color(255, 255, 255));
        btnContinuar.setText("CONTINUAR");
        btnContinuar.setBorderPainted(false);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(pnlBottom, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:
        //Falta hacer validaciones en pnlCampos y despues manejarla aqui con true or false

        System.out.println("Botón CONTINUAR presionado");  // Línea de depuración

        Perfil perfil = pnlCampos.obtenerDatos();
        System.out.println("Perfil obtenido: " + perfil);  // Línea de depuración

        perfilService.guardar(perfil);
        System.out.println("Perfil guardado");  // Línea de depuración
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void regresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarBtnActionPerformed
        // TODO add your handling code here:
        InicioSesionFrm inicioSesionFrame = new InicioSesionFrm();
        inicioSesionFrame.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_regresarBtnActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuarioFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroUsuarioFrm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel crearCuentaLbl;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlFormRegistro;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JButton regresarBtn;
    // End of variables declaration//GEN-END:variables

    //DECLARACION DE VARIABLES MANUAL
    //INICIALIZACION DE COMPONENTES
    private void inicializarComponentes() {

    }

    private void cargarCombos() {

    }

    //MANEJO DE LAYOUTS
    private void asignarCompALayout() {

        pnlFormRegistro.removeAll(); // limpiar lo que hizo NetBeans

        pnlFormRegistro.setLayout(new BoxLayout(pnlFormRegistro, BoxLayout.Y_AXIS));

        pnlFormRegistro.setBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20)
        );

        pnlFormRegistro.setBackground(Color.decode("#0078C2"));

        // Centrar foto
        pnlFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlCampos.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlFormRegistro.add(Box.createVerticalStrut(20));
        pnlFormRegistro.add(pnlFoto);

        pnlFormRegistro.add(Box.createVerticalStrut(20));
        pnlFormRegistro.add(pnlCampos);
    }
}
