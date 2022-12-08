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
import static views.AdminDashboard.Cashier;
import static views.AdminDashboard.Manager;
import static views.AdminDashboard.cashierLabel;
import static views.AdminDashboard.mainPane;
import static views.AdminDashboard.managerLabel;

/**
 *
 * @author Anaclita
 */
public class DashboardPanel extends javax.swing.JInternalFrame {

    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    Color DefaultColor,ClickedColor;

    public static HashMap<String, Integer> cashierMap = new HashMap<>();

    /**
     * Creates new form TableCashier
     */
    public DashboardPanel() {
        initComponents();
        txtTotalCashier.setText(String.valueOf(getTotalCashier()));
        txtTotalManager.setText(String.valueOf(getTotalManager()));
        
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        
        DefaultColor=new Color(25,118,211);
        ClickedColor=new Color (255,255,255);
        
        ui.setNorthPane(null);

        getCashiersFromDB();
    }
    
    private int getTotalCashier() {
        int totalCashier = 0;
        try {
            stmt = DBConnect.getInstance().createStatement();
            
            String sql = "SELECT COUNT(*) AS 'Cashiers' FROM user WHERE UserType = 'Cashier'";
            rs = stmt.executeQuery(sql);
            
            rs.next();
            totalCashier = rs.getInt("Cashiers");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalCashier;
    }
    
    private int getTotalManager() {
        int totalManager = 0;
        try {
            stmt = DBConnect.getInstance().createStatement();
            
            String sql = "SELECT COUNT(*) AS 'Manager' FROM user WHERE UserType = 'Manager'";
            rs = stmt.executeQuery(sql);
            
            rs.next();
            totalManager = rs.getInt("Manager");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return totalManager;
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

        cashierPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTotalCashier = new javax.swing.JLabel();
        managerPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotalManager = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setVisible(true);

        cashierPanel.setBackground(new java.awt.Color(255, 255, 51));
        cashierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cashierPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cashierPanelMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cashierLogo.jpg"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setText("CASHIER");

        txtTotalCashier.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        txtTotalCashier.setText("0");

        javax.swing.GroupLayout cashierPanelLayout = new javax.swing.GroupLayout(cashierPanel);
        cashierPanel.setLayout(cashierPanelLayout);
        cashierPanelLayout.setHorizontalGroup(
            cashierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashierPanelLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(cashierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cashierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cashierPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cashierPanelLayout.createSequentialGroup()
                        .addComponent(txtTotalCashier)
                        .addGap(134, 134, 134))))
        );
        cashierPanelLayout.setVerticalGroup(
            cashierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashierPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(txtTotalCashier)
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        managerPanel.setBackground(new java.awt.Color(0, 153, 0));
        managerPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managerPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managerPanelMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/managerLogo.jpg"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("MANAGER");

        txtTotalManager.setFont(new java.awt.Font("Century Gothic", 0, 48)); // NOI18N
        txtTotalManager.setForeground(new java.awt.Color(255, 255, 255));
        txtTotalManager.setText("0");

        javax.swing.GroupLayout managerPanelLayout = new javax.swing.GroupLayout(managerPanel);
        managerPanel.setLayout(managerPanelLayout);
        managerPanelLayout.setHorizontalGroup(
            managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managerPanelLayout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, managerPanelLayout.createSequentialGroup()
                        .addComponent(txtTotalManager)
                        .addGap(137, 137, 137))))
        );
        managerPanelLayout.setVerticalGroup(
            managerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managerPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addComponent(txtTotalManager)
                .addGap(28, 28, 28)
                .addComponent(jLabel7)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(cashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(managerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(managerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(596, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cashierPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cashierPanelMouseClicked
        // TODO add your handling code here:
        Manager.setBackground(DefaultColor);
        Cashier.setBackground(ClickedColor);
        
        managerLabel.setForeground(Color.WHITE);
        cashierLabel.setForeground(Color.BLACK);
        
        CashierPanel Cashier = new CashierPanel();
        mainPane.removeAll();
        mainPane.add(Cashier).setVisible(true);
        
    }//GEN-LAST:event_cashierPanelMouseClicked

    private void managerPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerPanelMouseClicked
        // TODO add your handling code here:
        Manager.setBackground(ClickedColor);
        Cashier.setBackground(DefaultColor);
        
        managerLabel.setForeground(Color.BLACK);
        cashierLabel.setForeground(Color.WHITE);
        
        ManagerPanel Manager = new ManagerPanel();
        mainPane.removeAll();
        mainPane.add(Manager).setVisible(true);
    }//GEN-LAST:event_managerPanelMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cashierPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel managerPanel;
    private javax.swing.JLabel txtTotalCashier;
    private javax.swing.JLabel txtTotalManager;
    // End of variables declaration//GEN-END:variables
}
