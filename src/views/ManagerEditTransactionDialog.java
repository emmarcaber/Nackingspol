/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import static views.ManagerTransactionPanel.tblTransaction;

/**
 *
 * @author Anaclita
 */
public class ManagerEditTransactionDialog extends javax.swing.JDialog {

    ResultSet rs = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;

    private int transactionID = 0;

    HashMap<String, Integer> productsMap = new HashMap<>();
    HashMap<String, Float> pricesMap = new HashMap<>();
    HashMap<String, Integer> customersMap = new HashMap<>();
    HashMap<String, Integer> cashiersMap = new HashMap<>();

    /**
     * Creates new form AddCashierDialog
     */
    public ManagerEditTransactionDialog(java.awt.Frame parent, boolean modal, int transactionID) {
        super(parent, modal);
        initComponents();

        bgTransactionType.add(rbWalk);
        bgTransactionType.add(rbDelivery);
        getProductsFromDB();
        getCustomersFromDB();
        getCashiersFromDB();

        this.transactionID = transactionID;
        txtQuantity.setText("0");

        loadToComponents(transactionID);
        disableFields();
        this.setVisible(true);
    }

    private void disableFields() {
        cbCustomer.setEnabled(false);
        cbCashier.setEnabled(false);
        dcDateOfTransaction.setEnabled(false);
    }

    private void getCustomersFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(FirstName, ' ', LastName) AS 'Name', CustomerID FROM customer";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customersMap.put(rs.getString("Name"), rs.getInt("CustomerID"));
                cbCustomer.addItem(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getCashiersFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(FirstName, ' ', LastName) AS 'Name', UserID FROM `user` WHERE UserType = 'Cashier'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                cashiersMap.put(rs.getString("Name"), rs.getInt("UserID"));
                cbCashier.addItem(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getProductsFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(ContainerType, ' ', WaterType) AS 'Name', ProductID, Price FROM product";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                productsMap.put(rs.getString("Name"), rs.getInt("ProductID"));
                pricesMap.put(rs.getString("Name"), rs.getFloat("Price"));
                cbProduct.addItem(rs.getString("Name"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadToComponents(int transactionID) {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(customer.FirstName, ' ', customer.Lastname) AS 'CustomerName', \n"
                    + "CONCAT(`user`.FirstName, ' ', `user`.LastName) AS 'CashierName', \n"
                    + "CONCAT(ContainerType, ' ', WaterType) AS 'Product', Quantity, Total, TransactionType,\n"
                    + "TransactionDate FROM transactions\n"
                    + "INNER JOIN customer ON transactions.CustomerID = customer.CustomerID\n"
                    + "INNER JOIN `user` ON transactions.CashierID = `user`.UserID\n"
                    + "INNER JOIN product ON transactions.ProductID = product.ProductID"
                    + " WHERE TransactionID = " + transactionID;
            rs = stmt.executeQuery(sql);

            rs.next();
            cbCustomer.setSelectedItem(rs.getString("CustomerName"));
            cbCashier.setSelectedItem(rs.getString("CashierName"));
            cbProduct.setSelectedItem(rs.getString("Product"));

            txtQuantity.setText(String.valueOf(rs.getInt("Quantity")));

            String typeOfTransaction = rs.getString("TransactionType");

            if (typeOfTransaction.equals("Walk-In")) {
                rbWalk.setSelected(true);
            } else {
                rbDelivery.setSelected(true);
            }

            txtTotal.setText(rs.getString("Total"));
            dcDateOfTransaction.setDate(rs.getDate("TransactionDate"));

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

        bgTransactionType = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cbCustomer = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbCashier = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbProduct = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        rbWalk = new javax.swing.JRadioButton();
        rbDelivery = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        txtTotal1 = new javax.swing.JLabel();
        dcDateOfTransaction = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 22)); // NOI18N
        jLabel1.setText("EDIT TRANSACTION");

        btnEdit.setBackground(new java.awt.Color(255, 255, 0));
        btnEdit.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnCancel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cbCustomer.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbCustomer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Customer" }));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Customer");

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Cashier");

        cbCashier.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbCashier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Cashier" }));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Date of Transaction");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Quantity");

        txtQuantity.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantityKeyTyped(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Product");

        cbProduct.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cbProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Product" }));
        cbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Transaction Type");

        rbWalk.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbWalk.setSelected(true);
        rbWalk.setText("Walk-In");
        rbWalk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbWalkActionPerformed(evt);
            }
        });

        rbDelivery.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbDelivery.setText("Delivery");
        rbDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDeliveryActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Total");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtTotal.setText("0.00");

        txtTotal1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtTotal1.setForeground(new java.awt.Color(255, 0, 0));
        txtTotal1.setText("Php");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(rbWalk)))
                        .addGap(28, 28, 28)
                        .addComponent(rbDelivery)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rbWalk)
                    .addComponent(rbDelivery))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal)
                        .addComponent(txtTotal1))
                    .addComponent(jLabel4))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(92, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(86, 86, 86)
                        .addComponent(cbCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90))
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(btnCancel)
                .addGap(54, 54, 54)
                .addComponent(btnEdit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(dcDateOfTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbCashier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dcDateOfTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnCancel))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        String selectedProduct = cbProduct.getSelectedItem().toString();
        String quantity = txtQuantity.getText();
        String total = txtTotal.getText();
        String transactionType = "";

        if (rbWalk.isSelected()) {
            transactionType = "Walk-In";
        } else if (rbDelivery.isSelected()) {
            transactionType = "Delivery";
        }

        if (cbProduct.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please select a product!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (quantity.equals("") || quantity.equals("0")) {
            JOptionPane.showMessageDialog(null, "Please enter a quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (quantity.equals("0")) {
            JOptionPane.showMessageDialog(null, "Quantity is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int rowAffected = updateTransactionToDB(
                    productsMap.get(selectedProduct), Integer.parseInt(quantity),
                    Float.parseFloat(total), transactionType, transactionID
            );

            if (rowAffected == 0) {
                JOptionPane.showMessageDialog(null, "Transaction not edited successfully!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                WriteLogs.writeLog("Edited Transaction ID: " + transactionID);
                System.out.println("Edited Transction ID: " + transactionID);
                JOptionPane.showMessageDialog(null, "Transaction edited successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();
                
                String [] data = {selectedProduct, quantity, "Php " + total, transactionType};
                tblModel.setValueAt(data[0], tblTransaction.getSelectedRow(), 2);
                tblModel.setValueAt(data[1], tblTransaction.getSelectedRow(), 3);
                tblModel.setValueAt(data[2], tblTransaction.getSelectedRow(), 4);
                tblModel.setValueAt(data[3], tblTransaction.getSelectedRow(), 5);

                this.dispose();
            }

        }

    }//GEN-LAST:event_btnEditActionPerformed

    private int updateTransactionToDB(int productID, int quantity, float total, String transactionType, int transactionID) {
        int updatedRows = 0;

        try {
            String sql = "UPDATE transactions SET ProductID = ?, Quantity = ?, Total = ?, TransactionType = ?"
                    + " WHERE TransactionID = ?";

            pstmt = DBConnect.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, productID);
            pstmt.setInt(2, quantity);
            pstmt.setFloat(3, total);
            pstmt.setString(4, transactionType);
            pstmt.setInt(5, transactionID);

            updatedRows = pstmt.executeUpdate();

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

        return updatedRows;
    }

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtQuantityActionPerformed

    private void cbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductActionPerformed
        // TODO add your handling code here:
        float price = 0f;
        int quantity = 0;
        int deliveryAdditions = 0;
        float total = 0f;

        if (rbDelivery.isSelected()) {
            deliveryAdditions = quantity * 5;
        }
        if (cbProduct.getSelectedIndex() != 0 && !txtQuantity.getText().equals("")) {
            price = pricesMap.get(cbProduct.getSelectedItem().toString());
            quantity = Integer.parseInt(txtQuantity.getText());
            total = price * quantity + deliveryAdditions;
        }
        
        txtTotal.setText(String.valueOf(total) + "0");
    }//GEN-LAST:event_cbProductActionPerformed

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        // TODO add your handling code here:

        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }

        float price = 0f;
        int quantity = 0;
        int deliveryAdditions = 0;
        float total = 0f;

        if (rbDelivery.isSelected()) {
            deliveryAdditions = quantity * 5;
        }
        if (cbProduct.getSelectedIndex() != 0 && !txtQuantity.getText().equals("")) {
            price = pricesMap.get(cbProduct.getSelectedItem().toString());
            quantity = Integer.parseInt(txtQuantity.getText());
            total = price * quantity + deliveryAdditions;
        }
        
        txtTotal.setText(String.valueOf(total) + "0");
    }//GEN-LAST:event_txtQuantityKeyTyped

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        // TODO add your handling code here:
        float price = 0f;
        int quantity = 0;
        int deliveryAdditions = 0;
        float total = 0f;

        if (rbDelivery.isSelected()) {
            deliveryAdditions = quantity * 5;
        }
        if (cbProduct.getSelectedIndex() != 0 && !txtQuantity.getText().equals("")) {
            price = pricesMap.get(cbProduct.getSelectedItem().toString());
            quantity = Integer.parseInt(txtQuantity.getText());
            total = price * quantity + deliveryAdditions;
        }
        
        txtTotal.setText(String.valueOf(total) + "0");
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void rbDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeliveryActionPerformed
        // TODO add your handling code here:
        float price = 0f;
        int quantity = 0;
        int deliveryAdditions = 0;
        float total = 0f;

        if (cbProduct.getSelectedIndex() != 0 && !txtQuantity.getText().equals("")) {
            price = pricesMap.get(cbProduct.getSelectedItem().toString());
            quantity = Integer.parseInt(txtQuantity.getText());
            total = price * quantity + (quantity * 5);
        }
        
        txtTotal.setText(String.valueOf(total) + "0");
    }//GEN-LAST:event_rbDeliveryActionPerformed

    private void rbWalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbWalkActionPerformed
        // TODO add your handling code here:
        float price = 0f;
        int quantity = 0;
        float total = 0f;

        if (cbProduct.getSelectedIndex() != 0 && !txtQuantity.getText().equals("")) {
            price = pricesMap.get(cbProduct.getSelectedItem().toString());
            quantity = Integer.parseInt(txtQuantity.getText());
            total = price * quantity;
        }
        
        txtTotal.setText(String.valueOf(total) + "0");
    }//GEN-LAST:event_rbWalkActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
        float price = 0f;
        int quantity = 0;
        int deliveryAdditions = 0;
        float total = 0f;

        if (rbDelivery.isSelected()) {
            deliveryAdditions = quantity * 5;
        }
        if (cbProduct.getSelectedIndex() != 0 && !txtQuantity.getText().equals("")) {
            price = pricesMap.get(cbProduct.getSelectedItem().toString());
            quantity = Integer.parseInt(txtQuantity.getText());
            total = price * quantity + deliveryAdditions;
        }
        
        txtTotal.setText(String.valueOf(total) + "0");
    }//GEN-LAST:event_txtQuantityKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTransactionType;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cbCashier;
    private javax.swing.JComboBox<String> cbCustomer;
    private javax.swing.JComboBox<String> cbProduct;
    private com.toedter.calendar.JDateChooser dcDateOfTransaction;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbDelivery;
    private javax.swing.JRadioButton rbWalk;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotal1;
    // End of variables declaration//GEN-END:variables
}
