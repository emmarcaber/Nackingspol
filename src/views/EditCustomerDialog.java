/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anaclita
 */
public class EditCustomerDialog extends javax.swing.JDialog {

    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    private int customerID = 0;

    /**
     * Creates new form AddCashierDialog
     */
    public EditCustomerDialog(java.awt.Frame parent, boolean modal, int customerID) {
        super(parent, modal);
        initComponents();

        this.customerID = customerID;
        loadToComponents(customerID);
        
        disabledFields();
        
        if (cbMunicity.getSelectedIndex() == 0) {
            cbBarangay.setEnabled(false);
            txtStreet.setEnabled(false);
        }

        this.setVisible(true);
    }
    
    private void disabledFields() {
        txtFirstName.setEnabled(false);
        txtLastName.setEnabled(false);
    }
    
    private void loadToComponents(int customerID) {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT FirstName, LastName, ContactNumber, Municity, Barangay, Street FROM customer INNER JOIN address ON customer.AddressID = address.AddressID WHERE CustomerID = " + customerID;
            rs = stmt.executeQuery(sql);

            rs.next();
            txtFirstName.setText(rs.getString("FirstName"));
            txtLastName.setText(rs.getString("LastName"));
            txtContactNumber.setText(rs.getString("ContactNumber"));

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtContactNumber = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cbBarangay = new javax.swing.JComboBox<>();
        cbMunicity = new javax.swing.JComboBox<>();
        txtStreet = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 22)); // NOI18N
        jLabel1.setText("EDIT CUSTOMER");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setText("First Name");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel4.setText("Last Name");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setText("Contact Number");

        btnAdd.setBackground(new java.awt.Color(255, 255, 0));
        btnAdd.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAdd.setText("EDIT");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        txtFirstName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtContactNumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14))); // NOI18N

        cbBarangay.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cbBarangay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Barangay" }));

        cbMunicity.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        cbMunicity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose City/Municipality", "Iriga City", "Baao" }));
        cbMunicity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMunicityActionPerformed(evt);
            }
        });

        txtStreet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setText("City/Municipality");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setText("Barangay");

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setText("Street");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cbMunicity, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbBarangay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtStreet))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMunicity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBarangay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContactNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(btnCancel)
                .addGap(53, 53, 53)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(229, 229, 229))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnAdd))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String contactNumber = txtContactNumber.getText();
        String municity = cbMunicity.getSelectedItem().toString();
        String barangay = cbBarangay.getSelectedItem().toString();
        String street = txtStreet.getText();
        
        String address = street + ", " + barangay + ", " + municity;

        if (firstName.equals("")) {
            JOptionPane.showMessageDialog(null, "First name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lastName.equals("")) {
            JOptionPane.showMessageDialog(null, "Last name is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (contactNumber.equals("")) {
            JOptionPane.showMessageDialog(null, "Contact Number is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (municity.equals("Choose City/Municipality")) {
            JOptionPane.showMessageDialog(null, "Please select a city or municipality!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (barangay.equals("Choose Barangay")) {
            JOptionPane.showMessageDialog(null, "Please select a barangay!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (street.equals("")) {
            JOptionPane.showMessageDialog(null, "Street is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int insertedAddressID = insertAddressToDB(street, barangay, municity);
            int insertedCustomerID = insertCustomerToDB(firstName, lastName, contactNumber, insertedAddressID);
            
            if (insertedCustomerID > 0) { 
                DefaultTableModel tblModel = (DefaultTableModel) CashierCustomerPanel.tblCustomer.getModel();
                
                String [] data = {firstName + " " + lastName, contactNumber, address};
                tblModel.addRow(data);
                
                JOptionPane.showMessageDialog(null, "Customer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(null, "Customer not added successfully!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private int insertAddressToDB(String street, String barangay, String municity) {
        int insertedAddressID = 0;

        try {
            String sql = "INSERT INTO address(Street, Barangay, Municity) "
                    + "VALUES(?, ?, ?)";

            pstmt = DBConnect.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, street);
            pstmt.setString(2, barangay);
            pstmt.setString(3, municity);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    insertedAddressID = rs.getInt(1);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return insertedAddressID;
    }


    private void cbMunicityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMunicityActionPerformed
        // TODO add your handling code here:
        String selectedMunicity = cbMunicity.getSelectedItem().toString();

        if (cbMunicity.getSelectedIndex() == 0) {
            cbBarangay.setEnabled(false);
            txtStreet.setEnabled(false);
        } else if (selectedMunicity.equals("Iriga City")) {
            cbBarangay.setEnabled(true);
            txtStreet.setEnabled(true);
            String[] barangays = {"Choose Barangay", "Antipolo", "Cristo Rey", "Del Rosario", "Francia", "La Anunciacion", "La Medalla", "La Purisima", "La Trinidad", "Niño Jesus", "Perpetual Help", "Sagrada", "Salvacion", "San Agustin", "San Andres", "San Antonio", "San Francisco", "San Isidro", "San Jose", "San Juan", "San Miguel", "San Nicolas", "San Pedro", "San Rafael", "San Ramon", "San Roque", "Santiago", "San Vicente Norte", "San Vicente Sur", "Sta. Cruz Norte", "Sta. Cruz Sur", "Sta. Elena", "Sta. Isabel", "Sta. Maria", "Sta. Teresita", "Sto. Domingo", "Sto. Niño"};

            cbBarangay.setModel(new DefaultComboBoxModel(barangays));
        } else if (selectedMunicity.equals("Baao")) {
            cbBarangay.setEnabled(true);
            txtStreet.setEnabled(true);

            String[] barangays = {"Choose Barangay", "Agdangan Poblacion", "Antipolo", "Bagumbayan", "Buluang", "Caranday", "Cristo Rey", "Del Pilar", "Del Rosario", "Iyagan", "La Medalla", "Lourdes", "Nababarera", "Pugay", "Sagrada", "Salvacion", "San Francisco", "San Isidro", "San Jose", "San Juan", "San Nicolas", "San Rafael", "San Ramon", "San Roque", "San Vicente", "Santa Cruz", "Santa Eulalia", "Santa Isabel", "Santa Teresa", "Santa Teresita", "Tapol"};

            cbBarangay.setModel(new DefaultComboBoxModel(barangays));
        }


    }//GEN-LAST:event_cbMunicityActionPerformed

    private int insertCustomerToDB(String firstName, String lastName, String contactNumber, int addressID) {
        int insertedCustomerID = 0;
        try {
            String sql = "INSERT INTO customer(FirstName, LastName, ContactNumber, AddressID) "
                    + "VALUES(?, ?, ?, ?)";

            pstmt = DBConnect.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, contactNumber);
            pstmt.setInt(4, addressID);

            int rowAffected = pstmt.executeUpdate();
            if (rowAffected == 1) {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    insertedCustomerID = rs.getInt(1);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return insertedCustomerID;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cbBarangay;
    private javax.swing.JComboBox<String> cbMunicity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtContactNumber;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtStreet;
    // End of variables declaration//GEN-END:variables
}
