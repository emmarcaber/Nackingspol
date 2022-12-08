/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anaclita
 */
public class CashierPanel extends javax.swing.JInternalFrame {

    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public static HashMap<String, Integer> cashierMap = new HashMap<>();

    /**
     * Creates new form TableCashier
     */
    public CashierPanel() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        getCashiersFromDB();
    }

    public void getCashiersFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(FirstName, ' ', LastName) AS Name, ContactNumber, Username, Password FROM user WHERE UserType = 'Cashier'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("Name");
                String contactNumber = rs.getString("ContactNumber");
                String userName = rs.getString("Username");
                String password = rs.getString("Password");

                String[] data = {name, contactNumber, userName, password};

                DefaultTableModel tblModel = (DefaultTableModel) tblCashier.getModel();
                tblModel.addRow(data);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

        btnAddCashier = new javax.swing.JButton();
        btnEditCashier = new javax.swing.JButton();
        btnDeleteCashier = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashier = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        btnAddCashier.setBackground(new java.awt.Color(0, 153, 0));
        btnAddCashier.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAddCashier.setForeground(new java.awt.Color(255, 255, 255));
        btnAddCashier.setText("ADD");
        btnAddCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCashierActionPerformed(evt);
            }
        });

        btnEditCashier.setBackground(new java.awt.Color(255, 255, 0));
        btnEditCashier.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEditCashier.setText("EDIT");
        btnEditCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCashierActionPerformed(evt);
            }
        });

        btnDeleteCashier.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteCashier.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDeleteCashier.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteCashier.setText("DELETE");
        btnDeleteCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCashierActionPerformed(evt);
            }
        });

        tblCashier.setAutoCreateRowSorter(true);
        tblCashier.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblCashier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Contact Number", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCashier);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAddCashier)
                        .addGap(74, 74, 74)
                        .addComponent(btnEditCashier)
                        .addGap(68, 68, 68)
                        .addComponent(btnDeleteCashier)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCashier)
                    .addComponent(btnEditCashier)
                    .addComponent(btnDeleteCashier))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCashierActionPerformed
        // TODO add your handling code here:
        new AddCashierDialog(null, true);
    }//GEN-LAST:event_btnAddCashierActionPerformed

    private void btnEditCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCashierActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tblModel = (DefaultTableModel) tblCashier.getModel();

        if (tblCashier.getSelectedRowCount() == 1) {
            String userName = tblModel.getValueAt(tblCashier.getSelectedRow(), 2).toString();
            new EditCashierDialog(null, true, getCashierID(userName));
        } else {
            if (tblCashier.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single row!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditCashierActionPerformed


    private void btnDeleteCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCashierActionPerformed
        // TODO add your handling code here:

        if (tblCashier.getSelectedRowCount() == 1) {

            DefaultTableModel tblModel = (DefaultTableModel) tblCashier.getModel();

            String name = tblModel.getValueAt(tblCashier.getSelectedRow(), 0).toString();
            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + name + "?", "Delete", JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                String userName = tblModel.getValueAt(tblCashier.getSelectedRow(), 2).toString();

                int toDeleteID = getCashierID(userName);
                int deletedID = deleteCashierFromDB(toDeleteID);

                System.out.println(toDeleteID);
                if (deletedID > 0) {
                    tblModel.removeRow(tblCashier.getSelectedRow());

                    JOptionPane.showMessageDialog(null, "Cashier deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cashier not deleted successfully!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            if (tblCashier.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single row!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteCashierActionPerformed

    private int getCashierID(String userName) {
        int toDeleteID = 0;
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT UserID FROM user WHERE Username = '" + userName + "'";
            rs = stmt.executeQuery(sql);

            rs.next();
            toDeleteID = rs.getInt("UserID");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return toDeleteID;
    }

    private int deleteCashierFromDB(int deleteID) {
        int deletedRows = 0;
        try {
            String sql = "DELETE FROM user WHERE UserID = ?";

            pstmt = DBConnect.getInstance().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, deleteID);

            int rowAffected = pstmt.executeUpdate();
            deletedRows = rowAffected;

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

        return deletedRows;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCashier;
    private javax.swing.JButton btnDeleteCashier;
    private javax.swing.JButton btnEditCashier;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblCashier;
    // End of variables declaration//GEN-END:variables
}
