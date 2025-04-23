package testing;
import javax.swing.*;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class RentalForm extends javax.swing.JFrame {


    public RentalForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

public void saveRental() {
    Connection con = DatabaseConnection.getConnection();
    String sql = "INSERT INTO rental (customer_name, contact_number, address, car_model, start_date, end_date, payment_mode) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String updateCarSql = "UPDATE cars SET status = 'rented' WHERE name = ?";
    String checkCarSql = "SELECT status FROM cars WHERE name = ?";
    
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        PreparedStatement updateCarPst = con.prepareStatement(updateCarSql);
        PreparedStatement checkCarPst = con.prepareStatement(checkCarSql);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        String formattedStartDate = sdf.format(startDate);
        String formattedEndDate = sdf.format(endDate);
        String selectedCarModel = (String) carModelComboBox.getSelectedItem();
        
        System.out.println("Selected Car Model: " + selectedCarModel);
        
         checkCarPst.setString(1, selectedCarModel);
         ResultSet rs = checkCarPst.executeQuery();
            
         if (rs.next()) {
            String carStatus = rs.getString("status");

            if ("rented".equalsIgnoreCase(carStatus)) {
                JOptionPane.showMessageDialog(null, "This car is already rented. Choose another car.");
                return; 
            }

            // Proceed with rental insertion
            pst.setString(1, customerNameField.getText());
            pst.setString(2, contactNumberField.getText());
            pst.setString(3, addressField.getText());
            pst.setString(4, selectedCarModel);
            pst.setString(5, formattedStartDate);
            pst.setString(6, formattedEndDate);
            pst.setString(7, (String) paymentModeComboBox.getSelectedItem());

            int rentalRows = pst.executeUpdate();

            if (rentalRows > 0) {
                // Update the car status to 'rented'
                updateCarPst.setString(1, selectedCarModel);
                int updateRows = updateCarPst.executeUpdate();

                if (updateRows > 0) {
                    JOptionPane.showMessageDialog(null, "Rental saved successfully!\nCar marked as rented.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Rental saved, but failed to update car status.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save rental data.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selected car not found in database.");
        }

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

public void Reserve() {
    Connection con = DatabaseConnection.getConnection();
    String sql = "INSERT INTO rental (customer_name, contact_number, address, car_model, start_date, end_date, payment_mode, status) VALUES (?, ?, ?, ?, ?, ?, ?, 'Ongoing')";
    String updateCarSql = "UPDATE cars SET status = 'Reserved' WHERE name = ?";
    String checkCarSql = "SELECT status FROM cars WHERE name = ?";
    String updateRentalSql = "UPDATE rental SET status = 'Reserved' WHERE car_model = ? AND status = 'Ongoing'";
    
    try {
        PreparedStatement pst = con.prepareStatement(sql);
        PreparedStatement updateCarPst = con.prepareStatement(updateCarSql);
        PreparedStatement checkCarPst = con.prepareStatement(checkCarSql);
        PreparedStatement updateRentalPst = con.prepareStatement(updateRentalSql);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = (Date) startDateSpinner.getValue();
        Date endDate = (Date) endDateSpinner.getValue();

        String formattedStartDate = sdf.format(startDate);
        String formattedEndDate = sdf.format(endDate);
        String selectedCarModel = (String) carModelComboBox.getSelectedItem();
        
        System.out.println("Selected Car Model: " + selectedCarModel);
        
         checkCarPst.setString(1, selectedCarModel);
         ResultSet rs = checkCarPst.executeQuery();
            
         if (rs.next()) {
            String carStatus = rs.getString("status");

            if ("rented".equalsIgnoreCase(carStatus)) {
                JOptionPane.showMessageDialog(null, "This car is already reserved. Choose another car.");
                return; 
            }

            // Proceed with rental insertion
            pst.setString(1, customerNameField.getText());
            pst.setString(2, contactNumberField.getText());
            pst.setString(3, addressField.getText());
            pst.setString(4, selectedCarModel);
            pst.setString(5, formattedStartDate);
            pst.setString(6, formattedEndDate);
            pst.setString(7, (String) paymentModeComboBox.getSelectedItem());

            int rentalRows = pst.executeUpdate();

            if (rentalRows > 0) {
                // Update the car status to 'rented'
                updateCarPst.setString(1, selectedCarModel);
                int updateRows = updateCarPst.executeUpdate();
                
                 updateRentalPst.setString(1, selectedCarModel);
                 updateRentalPst.executeUpdate();

                if (updateRows > 0) {
                    JOptionPane.showMessageDialog(null, "Rental saved successfully!\nCar marked as reserved.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Rental saved, but failed to update car status.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save rental data.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selected car not found in database.");
        }

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



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        customerNameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        carModelComboBox = new javax.swing.JComboBox<>();
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
        jButton2 = new javax.swing.JButton();

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Customer Name : ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(249, 250, 251));
        jPanel1.setPreferredSize(new java.awt.Dimension(492, 450));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Customer Name : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, 30));

        customerNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(customerNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 260, 30));

        jLabel2.setText("Contact Number : ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, 30));

        jLabel5.setText("Start Date : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, 30));

        jLabel6.setText("End Date : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 20));

        jLabel7.setText("Payment Mode : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, 20));

        carModelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Honda Civic", "Toyota Vios", "Mitsubishi Mirage", "Toyota Innova", "Toyota Corolla", "Mitsubishi Montero", " " }));
        carModelComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carModelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carModelComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(carModelComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 260, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("RENT!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 130, -1));

        contactNumberField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactNumberFieldActionPerformed(evt);
            }
        });
        jPanel1.add(contactNumberField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 260, 30));

        paymentModeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash Payment", "Online Payment" }));
        paymentModeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paymentModeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentModeComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(paymentModeComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 315, -1));

        endDateSpinner.setModel(new javax.swing.SpinnerDateModel());
        jPanel1.add(endDateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 140, -1));

        startDateSpinner.setModel(new javax.swing.SpinnerDateModel());
        jPanel1.add(startDateSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 140, -1));

        jLabel8.setText("Address :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, 30));

        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });
        jPanel1.add(addressField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 260, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("RENTAL APPLICATION FORM");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 260, 60));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LOGO_BIG.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        jLabel11.setText("Car Model :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 70, -1));

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
        jPanel1.add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("RESERVE NOW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customerNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerNameFieldActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void contactNumberFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactNumberFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactNumberFieldActionPerformed

    private void carModelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carModelComboBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_carModelComboBoxActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveRental();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Reserve();
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RentalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RentalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RentalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RentalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RentalForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JComboBox<String> carModelComboBox;
    private javax.swing.JTextField contactNumberField;
    private javax.swing.JTextField customerNameField;
    private javax.swing.JSpinner endDateSpinner;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JComboBox<String> paymentModeComboBox;
    private javax.swing.JSpinner startDateSpinner;
    // End of variables declaration//GEN-END:variables
}
