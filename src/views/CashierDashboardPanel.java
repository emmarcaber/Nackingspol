/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package views;

import java.awt.Color;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.sql.*;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static views.CashierDashboard.Customers;
import static views.CashierDashboard.Transactions;
import static views.CashierDashboard.customerLabel;
import static views.CashierDashboard.mainPane;
import static views.CashierDashboard.transactionLabel;

/**
 *
 * @author Anaclita
 */
public class CashierDashboardPanel extends javax.swing.JInternalFrame {

    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    Color DefaultColor,ClickedColor;
    private String name = "";

    public static HashMap<String, Integer> cashiersMap = new HashMap<>();

    /**
     * Creates new form TableCashier
     */
    public CashierDashboardPanel(String name) {
        initComponents();
        getCashiersFromDB();
        txtTotalTransactions.setText(String.valueOf(getTotalTransactions(name)));
        txtTotalCustomers.setText(String.valueOf(getTotalCustomers()));
        
        this.name = name;
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        
        DefaultColor=new Color(25,118,211);
        ClickedColor=new Color (255,255,255);
        
        ui.setNorthPane(null);
    }
    
    private void getCashiersFromDB() {
        try {
            stmt = DBConnect.getInstance().createStatement();
            
            String sql = "SELECT CONCAT(user.FirstName, ' ', user.LastName) AS 'Name', UserID FROM user WHERE UserType = 'Cashier'";
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
    
    
    private int getTotalTransactions(String name) {
        int totalCashier = 0;
        try {
            stmt = DBConnect.getInstance().createStatement();
            
            String sql = "SELECT COUNT(*) AS 'Transactions' FROM transactions WHERE " +
                    "CashierID = " + cashiersMap.get(name)
                    ;
            rs = stmt.executeQuery(sql);
            
            rs.next();
            totalCashier = rs.getInt("Transactions");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalCashier;
    }
    
    private int getTotalCustomers() {
        int totalManager = 0;
        try {
            stmt = DBConnect.getInstance().createStatement();
            
            String sql = "SELECT COUNT(*) AS 'Customers' FROM customer";
            rs = stmt.executeQuery(sql);
            
            rs.next();
            totalManager = rs.getInt("Customers");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalManager;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transactionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalTransactions = new javax.swing.JLabel();
        customerPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelCustomer = new javax.swing.JLabel();
        txtTotalCustomers = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setVisible(true);

        transactionPanel.setBackground(new java.awt.Color(255, 255, 51));
        transactionPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        transactionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transactionPanelMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/transactionLogo.jpg"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setText("TRANSACTIONS");

        txtTotalTransactions.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        txtTotalTransactions.setText("0");

        javax.swing.GroupLayout transactionPanelLayout = new javax.swing.GroupLayout(transactionPanel);
        transactionPanel.setLayout(transactionPanelLayout);
        transactionPanelLayout.setHorizontalGroup(
            transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transactionPanelLayout.createSequentialGroup()
                        .addComponent(txtTotalTransactions)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transactionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(67, 67, 67))))
        );
        transactionPanelLayout.setVerticalGroup(
            transactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactionPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(txtTotalTransactions)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        customerPanel.setBackground(new java.awt.Color(0, 51, 204));
        customerPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerPanelMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/customerLogo.jpg"))); // NOI18N

        labelCustomer.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        labelCustomer.setForeground(new java.awt.Color(255, 255, 255));
        labelCustomer.setText("CUSTOMERS");

        txtTotalCustomers.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        txtTotalCustomers.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalCustomers.setText("0");

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(86, 86, 86))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelLayout.createSequentialGroup()
                        .addComponent(txtTotalCustomers)
                        .addGap(143, 143, 143))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelLayout.createSequentialGroup()
                        .addComponent(labelCustomer)
                        .addGap(91, 91, 91))))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(37, 37, 37)
                .addComponent(txtTotalCustomers)
                .addGap(30, 30, 30)
                .addComponent(labelCustomer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(transactionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(customerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(transactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(584, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transactionPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionPanelMouseClicked
        // TODO add your handling code here:
        Transactions.setBackground(DefaultColor);
        Customers.setBackground(ClickedColor);
        
        transactionLabel.setForeground(Color.WHITE);
        customerLabel.setForeground(Color.BLACK);
        
        CashierTransactionPanel Transactions = new CashierTransactionPanel(name);
        mainPane.removeAll();
        mainPane.add(Transactions).setVisible(true);
        
    }//GEN-LAST:event_transactionPanelMouseClicked

    private void customerPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerPanelMouseClicked
        // TODO add your handling code here:
        Transactions.setBackground(ClickedColor);
        Customers.setBackground(DefaultColor);
        
        transactionLabel.setForeground(Color.BLACK);
        customerLabel.setForeground(Color.WHITE);
        
        CashierCustomerPanel Customers = new CashierCustomerPanel();
        mainPane.removeAll();
        mainPane.add(Customers).setVisible(true);
    }//GEN-LAST:event_customerPanelMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel customerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel labelCustomer;
    private javax.swing.JPanel transactionPanel;
    private javax.swing.JLabel txtTotalCustomers;
    private javax.swing.JLabel txtTotalTransactions;
    // End of variables declaration//GEN-END:variables
}
