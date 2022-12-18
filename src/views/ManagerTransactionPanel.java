/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anaclita
 */
public final class ManagerTransactionPanel extends javax.swing.JInternalFrame {

    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    HashMap<String, Integer> productsMap = new HashMap<>();
    HashMap<String, Integer> customersMap = new HashMap<>();
    HashMap<String, Integer> cashiersMap = new HashMap<>();

    private void getCustomersFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(FirstName, ' ', LastName) AS 'Name', CustomerID FROM customer";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customersMap.put(rs.getString("Name"), rs.getInt("CustomerID"));
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
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates new form TableCashier
     */
    public ManagerTransactionPanel() {

        initComponents();

        getTransactionsFromDB();
        getCustomersFromDB();
        getCashiersFromDB();
        getProductsFromDB();

        bgTransactionType.add(rbWalk);
        bgTransactionType.add(rbDelivery);

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

    }

    private void removeAllRowsTableTransaction() {
        DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

        int rowsToRemove = tblModel.getRowCount();
        //remove rows from the bottom one by one
        for (int i = rowsToRemove - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    public void getTransactionsFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(customer.FirstName, ' ', customer.Lastname) AS 'CustomerName', \n"
                    + "CONCAT(`user`.FirstName, ' ', `user`.LastName) AS 'CashierName', \n"
                    + "CONCAT(ContainerType, ' ', WaterType) AS 'Product', Quantity, Total, TransactionType,\n"
                    + "TransactionDate FROM transactions\n"
                    + "INNER JOIN customer ON transactions.CustomerID = customer.CustomerID\n"
                    + "INNER JOIN `user` ON transactions.CashierID = `user`.UserID\n"
                    + "INNER JOIN product ON transactions.ProductID = product.ProductID";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                String cashierName = rs.getString("CashierName");
                String product = rs.getString("Product");
                String quantity = String.valueOf(rs.getInt("Quantity"));
                String total = "Php " + String.valueOf(rs.getFloat("Total")) + "0";
                String typeOfTransaction = rs.getString("TransactionType");
                String dateOfTransaction = DateFormat.getDateInstance().format(rs.getDate("TransactionDate"));

                String[] data = {customerName, cashierName, product, quantity, total, typeOfTransaction, dateOfTransaction};

                DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();
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

        bgTransactionType = new javax.swing.ButtonGroup();
        btnAddTransaction = new javax.swing.JButton();
        btnEditTransaction = new javax.swing.JButton();
        btnDeleteTransaction = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaction = new javax.swing.JTable();
        txtSearchCustomer = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        rbWalk = new javax.swing.JRadioButton();
        rbDelivery = new javax.swing.JRadioButton();
        dcDOT = new com.toedter.calendar.JDateChooser();
        btnRefreshSearch = new javax.swing.JButton();
        btnFilterDate = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setVisible(true);

        btnAddTransaction.setBackground(new java.awt.Color(0, 153, 0));
        btnAddTransaction.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnAddTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnAddTransaction.setText("ADD TRANSACTION");
        btnAddTransaction.setFocusable(false);
        btnAddTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTransactionActionPerformed(evt);
            }
        });

        btnEditTransaction.setBackground(new java.awt.Color(255, 255, 0));
        btnEditTransaction.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnEditTransaction.setText("EDIT TRANSACTION");
        btnEditTransaction.setFocusable(false);
        btnEditTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTransactionActionPerformed(evt);
            }
        });

        btnDeleteTransaction.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteTransaction.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnDeleteTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteTransaction.setText("DELETE TRANSACTION");
        btnDeleteTransaction.setFocusable(false);
        btnDeleteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTransactionActionPerformed(evt);
            }
        });

        tblTransaction.setAutoCreateRowSorter(true);
        tblTransaction.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblTransaction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Name", "Cashier Name", "Product", "Quantity", "Total", "Type", "Date Transacted"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransaction.setMinimumSize(new java.awt.Dimension(50, 0));
        jScrollPane1.setViewportView(tblTransaction);
        if (tblTransaction.getColumnModel().getColumnCount() > 0) {
            tblTransaction.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblTransaction.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblTransaction.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblTransaction.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblTransaction.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblTransaction.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblTransaction.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        txtSearchCustomer.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSearchCustomer.setMargin(new java.awt.Insets(3, 6, 3, 6));

        btnSearch.setBackground(new java.awt.Color(0, 102, 255));
        btnSearch.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setText("SEARCH CUSTOMER");
        btnSearch.setFocusable(false);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        rbWalk.setBackground(new java.awt.Color(255, 255, 255));
        rbWalk.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbWalk.setText("Walk-In");
        rbWalk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbWalkActionPerformed(evt);
            }
        });

        rbDelivery.setBackground(new java.awt.Color(255, 255, 255));
        rbDelivery.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rbDelivery.setText("Delivery");
        rbDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDeliveryActionPerformed(evt);
            }
        });

        btnRefreshSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refreshIcon.jpg"))); // NOI18N
        btnRefreshSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshSearchActionPerformed(evt);
            }
        });

        btnFilterDate.setBackground(new java.awt.Color(0, 102, 255));
        btnFilterDate.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnFilterDate.setForeground(new java.awt.Color(255, 255, 255));
        btnFilterDate.setText("FILTER BY DATE");
        btnFilterDate.setFocusable(false);
        btnFilterDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtSearchCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(rbWalk)
                        .addGap(18, 18, 18)
                        .addComponent(rbDelivery)
                        .addGap(18, 18, 18)
                        .addComponent(dcDOT, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFilterDate)
                            .addComponent(btnRefreshSearch)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddTransaction)
                        .addGap(88, 88, 88)
                        .addComponent(btnEditTransaction)
                        .addGap(72, 72, 72)
                        .addComponent(btnDeleteTransaction)))
                .addGap(126, 126, 126))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnRefreshSearch)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch)
                        .addComponent(rbWalk)
                        .addComponent(rbDelivery))
                    .addComponent(dcDOT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFilterDate)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteTransaction)
                    .addComponent(btnEditTransaction)
                    .addComponent(btnAddTransaction))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTransactionActionPerformed
        // TODO add your handling code here:
        new ManagerAddTransactionDialog(null, true);
    }//GEN-LAST:event_btnAddTransactionActionPerformed

    private void btnEditTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTransactionActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

        if (tblTransaction.getSelectedRowCount() == 1) {
            int customerID = customersMap.get(tblModel.getValueAt(tblTransaction.getSelectedRow(), 0).toString());
            int cashierID = cashiersMap.get(tblModel.getValueAt(tblTransaction.getSelectedRow(), 1).toString());
            int productID = productsMap.get(tblModel.getValueAt(tblTransaction.getSelectedRow(), 2).toString());
            int quantity = Integer.parseInt(tblModel.getValueAt(tblTransaction.getSelectedRow(), 3).toString());
            String typeOfTransaction = tblModel.getValueAt(tblTransaction.getSelectedRow(), 5).toString();
            new ManagerEditTransactionDialog(null, true, getTransactionID(customerID, cashierID, productID, quantity, typeOfTransaction));
        } else {
            if (tblTransaction.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single row!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditTransactionActionPerformed


    private void btnDeleteTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTransactionActionPerformed
        // TODO add your handling code here:

        if (tblTransaction.getSelectedRowCount() == 1) {

            DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

            String customerName = tblModel.getValueAt(tblTransaction.getSelectedRow(), 0).toString();
            String product = tblModel.getValueAt(tblTransaction.getSelectedRow(), 2).toString();
            String typeOfTransaction = tblModel.getValueAt(tblTransaction.getSelectedRow(), 5).toString();
            String deleteMessage = "Are you sure you want to delete " + customerName + "'s " + typeOfTransaction + " transaction of " + product + "?";
            int answer = JOptionPane.showConfirmDialog(null, deleteMessage, "Delete", JOptionPane.YES_NO_OPTION);

            if (answer == 0) {
                int customerID = customersMap.get(tblModel.getValueAt(tblTransaction.getSelectedRow(), 0).toString());
                int cashierID = cashiersMap.get(tblModel.getValueAt(tblTransaction.getSelectedRow(), 1).toString());
                int productID = productsMap.get(tblModel.getValueAt(tblTransaction.getSelectedRow(), 2).toString());
                int quantity = Integer.parseInt(tblModel.getValueAt(tblTransaction.getSelectedRow(), 3).toString());

                int toDeleteID = getTransactionID(customerID, cashierID, productID, quantity, typeOfTransaction);
                int deletedID = deleteTransactionFromDB(toDeleteID);

                if (deletedID > 0) {
                    WriteLogs.writeLog("Deleted TransactionID ID: " + toDeleteID);
                    System.out.println("Deleted TransactionID ID: " + toDeleteID);
                    tblModel.removeRow(tblTransaction.getSelectedRow());

                    JOptionPane.showMessageDialog(null, "Transaction deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Transaction not deleted successfully!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            if (tblTransaction.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Table is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a single row!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteTransactionActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String toSearch = txtSearchCustomer.getText();

        if (toSearch.equals("")) {
            removeAllRowsTableTransaction();
            getTransactionsFromDB();
        } else {
            DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

            removeAllRowsTableTransaction();

            try {
                stmt = DBConnect.getInstance().createStatement();

                String sql = "SELECT CONCAT(customer.FirstName, ' ', customer.Lastname) AS 'CustomerName', \n"
                        + "customer.LastName, "
                        + "CONCAT(`user`.FirstName, ' ', `user`.LastName) AS 'CashierName', \n"
                        + "CONCAT(ContainerType, ' ', WaterType) AS 'Product', Quantity, Total, TransactionType,\n"
                        + "TransactionDate FROM transactions\n"
                        + "INNER JOIN customer ON transactions.CustomerID = customer.CustomerID\n"
                        + "INNER JOIN `user` ON transactions.CashierID = `user`.UserID\n"
                        + "INNER JOIN product ON transactions.ProductID = product.ProductID WHERE customer.LastName LIKE '"
                        + toSearch + "%'";

                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String customerName = rs.getString("CustomerName");
                    String cashierName = rs.getString("CashierName");
                    String product = rs.getString("Product");
                    String quantity = String.valueOf(rs.getInt("Quantity"));
                    String total = "Php " + String.valueOf(rs.getFloat("Total")) + "0";
                    String typeOfTransaction = rs.getString("TransactionType");
                    String dateOfTransaction = DateFormat.getDateInstance().format(rs.getDate("TransactionDate"));

                    String[] data = {customerName, cashierName, product, quantity, total, typeOfTransaction, dateOfTransaction};

                    tblModel.addRow(data);
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnRefreshSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshSearchActionPerformed
        // TODO add your handling code here:
        removeAllRowsTableTransaction();

        txtSearchCustomer.setText("");
        bgTransactionType.clearSelection();
        dcDOT.setCalendar(null);

        getTransactionsFromDB();
    }//GEN-LAST:event_btnRefreshSearchActionPerformed

    private void rbWalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbWalkActionPerformed
        // TODO add your handling code here:
        String toSearch = txtSearchCustomer.getText();

        DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

        removeAllRowsTableTransaction();

        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(customer.FirstName, ' ', customer.Lastname) AS 'CustomerName', \n"
                    + "customer.LastName, "
                    + "CONCAT(`user`.FirstName, ' ', `user`.LastName) AS 'CashierName', \n"
                    + "CONCAT(ContainerType, ' ', WaterType) AS 'Product', Quantity, Total, TransactionType,\n"
                    + "TransactionDate FROM transactions\n"
                    + "INNER JOIN customer ON transactions.CustomerID = customer.CustomerID\n"
                    + "INNER JOIN `user` ON transactions.CashierID = `user`.UserID\n"
                    + "INNER JOIN product ON transactions.ProductID = product.ProductID WHERE customer.LastName"
                    + " LIKE '" + toSearch + "%'"
                    + " AND TransactionType = 'Walk-In'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                String cashierName = rs.getString("CashierName");
                String product = rs.getString("Product");
                String quantity = String.valueOf(rs.getInt("Quantity"));
                String total = "Php " + String.valueOf(rs.getFloat("Total")) + "0";
                String typeOfTransaction = rs.getString("TransactionType");
                String dateOfTransaction = DateFormat.getDateInstance().format(rs.getDate("TransactionDate"));

                String[] data = {customerName, cashierName, product, quantity, total, typeOfTransaction, dateOfTransaction};

                tblModel.addRow(data);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_rbWalkActionPerformed

    private void rbDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeliveryActionPerformed
        // TODO add your handling code here:
        String toSearch = txtSearchCustomer.getText();

        DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

        removeAllRowsTableTransaction();

        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(customer.FirstName, ' ', customer.Lastname) AS 'CustomerName', \n"
                    + "customer.LastName, "
                    + "CONCAT(`user`.FirstName, ' ', `user`.LastName) AS 'CashierName', \n"
                    + "CONCAT(ContainerType, ' ', WaterType) AS 'Product', Quantity, Total, TransactionType,\n"
                    + "TransactionDate FROM transactions\n"
                    + "INNER JOIN customer ON transactions.CustomerID = customer.CustomerID\n"
                    + "INNER JOIN `user` ON transactions.CashierID = `user`.UserID\n"
                    + "INNER JOIN product ON transactions.ProductID = product.ProductID WHERE customer.LastName"
                    + " LIKE '" + toSearch + "%'"
                    + " AND TransactionType = 'Delivery'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                String cashierName = rs.getString("CashierName");
                String product = rs.getString("Product");
                String quantity = String.valueOf(rs.getInt("Quantity"));
                String total = "Php " + String.valueOf(rs.getFloat("Total")) + "0";
                String typeOfTransaction = rs.getString("TransactionType");
                String dateOfTransaction = DateFormat.getDateInstance().format(rs.getDate("TransactionDate"));

                String[] data = {customerName, cashierName, product, quantity, total, typeOfTransaction, dateOfTransaction};

                tblModel.addRow(data);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_rbDeliveryActionPerformed

    private void btnFilterDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterDateActionPerformed
        // TODO add your handling code here:
        String toSearch = txtSearchCustomer.getText();
        String transactionType = "";

        String transactionDate = "";
        try {
            SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd");
            transactionDate = DateFor.format(dcDOT.getDate());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this, "No selected date to filter!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (rbWalk.isSelected()) {
            transactionType = "Walk-In";
        } else {
            transactionType = "Delivery";
        }

        DefaultTableModel tblModel = (DefaultTableModel) tblTransaction.getModel();

        removeAllRowsTableTransaction();

        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT CONCAT(customer.FirstName, ' ', customer.Lastname) AS 'CustomerName', \n"
                    + "customer.LastName, "
                    + "CONCAT(`user`.FirstName, ' ', `user`.LastName) AS 'CashierName', \n"
                    + "CONCAT(ContainerType, ' ', WaterType) AS 'Product', Quantity, Total, TransactionType,\n"
                    + "TransactionDate FROM transactions\n"
                    + "INNER JOIN customer ON transactions.CustomerID = customer.CustomerID\n"
                    + "INNER JOIN `user` ON transactions.CashierID = `user`.UserID\n"
                    + "INNER JOIN product ON transactions.ProductID = product.ProductID WHERE customer.LastName"
                    + " LIKE '" + toSearch + "%'"
                    + " AND TransactionType = '" + transactionType + "'"
                    + " AND TransactionDate = '" + transactionDate + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                String cashierName = rs.getString("CashierName");
                String product = rs.getString("Product");
                String quantity = String.valueOf(rs.getInt("Quantity"));
                String total = "Php " + String.valueOf(rs.getFloat("Total")) + "0";
                String typeOfTransaction = rs.getString("TransactionType");
                String dateOfTransaction = DateFormat.getDateInstance().format(rs.getDate("TransactionDate"));

                String[] data = {customerName, cashierName, product, quantity, total, typeOfTransaction, dateOfTransaction};

                tblModel.addRow(data);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnFilterDateActionPerformed

    private int getTransactionID(int customerID, int cashierID, int productID, int quantity, String typeOfTransaction) {
        int toDeleteID = 0;
        try {
            stmt = DBConnect.getInstance().createStatement();

            String sql = "SELECT TransactionID from transactions WHERE "
                    + "CustomerID = " + customerID
                    + " AND CashierID = " + cashierID
                    + " AND ProductID = " + productID
                    + " AND Quantity = " + quantity
                    + " AND TransactionType = '" + typeOfTransaction + "'";
            rs = stmt.executeQuery(sql);

            rs.next();
            toDeleteID = rs.getInt("TransactionID");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return toDeleteID;
    }

    private int deleteTransactionFromDB(int deleteID) {
        int deletedRows = 0;
        try {
            String sql = "DELETE FROM transactions WHERE TransactionID = ?";

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
    private javax.swing.ButtonGroup bgTransactionType;
    private javax.swing.JButton btnAddTransaction;
    private javax.swing.JButton btnDeleteTransaction;
    private javax.swing.JButton btnEditTransaction;
    private javax.swing.JButton btnFilterDate;
    private javax.swing.JButton btnRefreshSearch;
    private javax.swing.JButton btnSearch;
    private com.toedter.calendar.JDateChooser dcDOT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbDelivery;
    private javax.swing.JRadioButton rbWalk;
    public static javax.swing.JTable tblTransaction;
    private javax.swing.JTextField txtSearchCustomer;
    // End of variables declaration//GEN-END:variables
}
