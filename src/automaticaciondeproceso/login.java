/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automaticaciondeproceso;

import automaticaciondeproceso.conections.Conection;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author wwwca
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        
       this.setSize(new Dimension(296, 315));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        jLabel3.setText("jLabel3");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        getContentPane().setLayout(null);

        jLabel1.setText("Usuario:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 60, 50, 14);
        getContentPane().add(txtusuario);
        txtusuario.setBounds(80, 50, 190, 35);

        jLabel2.setText("Password");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 120, 60, 14);
        getContentPane().add(txtpassword);
        txtpassword.setBounds(80, 110, 190, 38);

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(180, 160, 90, 23);

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(80, 160, 80, 23);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/automaticaciondeproceso/fondo3.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(-130, -30, 560, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
         try{ 
             
           if(txtusuario.getText().length()>0 && txtpassword.getText().length()>0){
                     Conection conec=new Conection();
          // ResultSet rs= conec.select("select * from catedratico where primernombre like '%"+txtprimnombre.getText()+"%' and segundonombre like '%"+txtsegundonombre.getText()+"%' and primerapellido like '%"+txtprimapellido.getText()+"%' and segundoapellido like '%"+txtsegunapellido.getText()+"%'");
           ResultSet rs= conec.select("select * from usuario where usuario='"+txtusuario.getText()+"' and password=password('"+txtpassword.getText()+"')");
           //  System.out.println("select * from usuario where usuario='"+txtusuario.getText()+"' and password=password('"+txtpassword.getText()+"')");
            
            boolean issomething=false;
           if(rs!=null){
               
               while (rs.next())
               {
                   issomething=true;
                    MenuPrincipal menu=new MenuPrincipal();
                    menu.setAlwaysOnTop(true);
                    menu.setVisible(true);  
                    this.dispose();
                    
                }
               if(!issomething){
                    JOptionPane.showMessageDialog(this,"usuario o contraseña incorrecta"  );
                }
               
                    
                }else{
                    JOptionPane.showMessageDialog(this, "Error al conectar "  );
               }
           
           
           }
           
          
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(this, "Error al conectar "  );
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            txtpassword.setText("");
            txtusuario.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("window".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
