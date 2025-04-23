/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package testing;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
public class FormNissanUrvan extends javax.swing.JFrame {

    /**
     * Creates new form FormNissanUrvan
     */
    public FormNissanUrvan() {
        initComponents();
        this.setLocationRelativeTo(null);
        staffInChargeField.setText(Session.staffName);
        staffInChargeField.setEditable(false);
    }

 public void saveRental() {
    
     if (customerNameField.getText().trim().isEmpty() ||
        contactNumberField.getText().trim().isEmpty() ||
        addressField.getText().trim().isEmpty() ||
        carModelField.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Connection con = DatabaseConnection.getConnection();
    String sql = "INSERT INTO rental (customer_name, contact_number, address, car_model, start_date, end_date, payment_mode, staff_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String updateCarSql = "UPDATE cars SET status = 'Rented', available = available - 1 WHERE name = ? AND available > 0";
    String checkCarSql = "SELECT status, available FROM cars WHERE name = ?";

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        PreparedStatement updateCarPst = con.prepareStatement(updateCarSql);
        PreparedStatement checkCarPst = con.prepareStatement(checkCarSql);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        String formattedStartDate = sdf.format(startDate);
        String formattedEndDate = sdf.format(endDate);

        // Hardcoded car model
        String selectedCarModel = carModelField.getText().trim();

        // Check car status
        checkCarPst.setString(1, selectedCarModel);
        ResultSet rs = checkCarPst.executeQuery();

        if (rs.next()) {
            String carStatus = rs.getString("status");
            int available = rs.getInt("available");
            
            if (available <= 0) {
            JOptionPane.showMessageDialog(null, "No more available units for this car.");
                return;
            }

            /*
            if ("rented".equalsIgnoreCase(carStatus)) {
                JOptionPane.showMessageDialog(null, "This car is already rented. Choose another car.");
                return;
            }
            */

            // Proceed with rental insertion
            pst.setString(1, customerNameField.getText());
            pst.setString(2, contactNumberField.getText());
            pst.setString(3, addressField.getText());
            pst.setString(4, selectedCarModel);
            pst.setString(5, formattedStartDate);
            pst.setString(6, formattedEndDate);
            pst.setString(7, (String) paymentModeComboBox.getSelectedItem());
            pst.setString(8, Session.staffName);

            int rentalRows = pst.executeUpdate();

            if (rentalRows > 0) {
                // Update the car status to 'rented'
                updateCarPst.setString(1, selectedCarModel);
                int updateRows = updateCarPst.executeUpdate();

                if (updateRows > 0) {
                    JOptionPane.showMessageDialog(null, "Rental saved successfully!\nCar marked as rented.");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Rental saved, but failed to update car status.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save rental data.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selected car not found in database.");
        }

        // Cleanup
        rs.close();
        checkCarPst.close();
        pst.close();
        updateCarPst.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rentalPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        customerNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        contactNumberField = new javax.swing.JTextField();
        paymentModeComboBox = new javax.swing.JComboBox<>();
        endDateSpinner = new javax.swing.JSpinner();
        startDateSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        carModelField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        staffInChargeField = new javax.swing.JTextField();
        btnSaveAsImage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        rentalPanel.setBackground(new java.awt.Color(245, 245, 245));
        rentalPanel.setPreferredSize(new java.awt.Dimension(492, 450));
        rentalPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Customer Name : ");
        rentalPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 30));

        customerNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameFieldActionPerformed(evt);
            }
        });
        rentalPanel.add(customerNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 260, 30));

        jLabel2.setText("Contact Number : ");
        rentalPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 30));

        jLabel5.setText("Start Date : ");
        rentalPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, 30));

        jLabel6.setText("End Date : ");
        rentalPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 20));

        jLabel7.setText("Payment Mode : ");
        rentalPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, 20));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("RENT!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        rentalPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 160, 40));

        contactNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactNumberFieldActionPerformed(evt);
            }
        });
        rentalPanel.add(contactNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 260, 30));

        paymentModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash Payment", "Online Payment" }));
        paymentModeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paymentModeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentModeComboBoxActionPerformed(evt);
            }
        });
        rentalPanel.add(paymentModeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 315, -1));

        endDateSpinner.setModel(new javax.swing.SpinnerDateModel());
        rentalPanel.add(endDateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 140, -1));

        startDateSpinner.setModel(new javax.swing.SpinnerDateModel());
        rentalPanel.add(startDateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 140, -1));

        jLabel8.setText("Address :");
        rentalPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 30));

        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });
        rentalPanel.add(addressField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 260, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("RENTAL APPLICATION FORM");
        rentalPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 260, 60));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LOGO_BIG.png"))); // NOI18N
        rentalPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        jLabel11.setText("Car Model :");
        rentalPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 70, -1));

        exitButton.setBackground(new java.awt.Color(245, 245, 245));
        exitButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitButton.setText("X");
        exitButton.setBorderPainted(false);
        exitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        rentalPanel.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        carModelField.setEditable(false);
        carModelField.setText("Nissan Urvan");
        rentalPanel.add(carModelField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 260, -1));

        jLabel12.setText("Staff In-Charge:");
        rentalPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 90, 30));

        staffInChargeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffInChargeFieldActionPerformed(evt);
            }
        });
        rentalPanel.add(staffInChargeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 260, 30));

        btnSaveAsImage.setText("Save as Image");
        btnSaveAsImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAsImageActionPerformed(evt);
            }
        });
        rentalPanel.add(btnSaveAsImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 450, -1, -1));
        btnSaveAsImage.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rentalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rentalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customerNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveRental();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void contactNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactNumberFieldActionPerformed

    private void paymentModeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentModeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paymentModeComboBoxActionPerformed

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldActionPerformed

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_exitButtonMouseClicked

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    private void staffInChargeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffInChargeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_staffInChargeFieldActionPerformed

    private void btnSaveAsImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAsImageActionPerformed
        jButton1.setVisible(false);
        btnSaveAsImage.setVisible(false);
        BufferedImage image = new BufferedImage(rentalPanel.getWidth(), rentalPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        rentalPanel.paint(g2);
        g2.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Form As Image");

        //default file name
        fileChooser.setSelectedFile(new File("rental_form.png"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".png")) {
                fileToSave = new File(filePath + ".png");
            }

            try {
                ImageIO.write(image, "png", fileToSave);
                JOptionPane.showMessageDialog(this, "Form saved to: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving image: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnSaveAsImageActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormNissanUrvan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormNissanUrvan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormNissanUrvan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormNissanUrvan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormNissanUrvan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JButton btnSaveAsImage;
    private javax.swing.JTextField carModelField;
    private javax.swing.JTextField contactNumberField;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JSpinner endDateSpinner;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> paymentModeComboBox;
    private javax.swing.JPanel rentalPanel;
    private javax.swing.JTextField staffInChargeField;
    private javax.swing.JSpinner startDateSpinner;
    // End of variables declaration//GEN-END:variables
}
