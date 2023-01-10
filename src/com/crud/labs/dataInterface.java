package com.crud.labs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dataInterface {

    public dataInterface() {
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama,nim,kelas;
                nama = JTextFieldNama.getText();
                nim = JTextFieldNIM.getText();
                kelas = JTextFieldKelas.getText();

                try{
                    preparedStatement = Connector.ConnectDB().prepareStatement("INSERT INTO mahasiswa (nama,nim,kelas) VALUES(?,?,?);");
                    preparedStatement.setString(1,nama);
                    preparedStatement.setString(2,nim);
                    preparedStatement.setString(3,kelas);
                    preparedStatement.executeUpdate();
                    showData();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Di Update");
                }catch (SQLException err){
                    Logger.getLogger(dataInterface.class.getName()).log(Level.SEVERE, null,err);
                }
                JTextFieldNama.setText("");
                JTextFieldNIM.setText("");
                JTextFieldKelas.setText("");
            }
        });
        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(dataInterface::createUpdateGUI);
            }
        });
        HapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(dataInterface::createDeleteGUI);
            }
        });
        buttonRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showData();
            }
        });
    }

    public static void createUpdateGUI(){
        updatePanel updatePanelUI = new updatePanel();
        JPanel updateRoot  = updatePanelUI.getUpdatePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(updateRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void createDeleteGUI(){
        deletePanel deletePanelUI = new deletePanel();
        JPanel deleteRoot = deletePanelUI.getDeletePanel();

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(deleteRoot);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public JPanel getMainPanel(){
        showData();
        return mainPanel;
    }

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private void showData(){
        try{
            Object[] columnTitle = {"Nama","Nim","Kelas"};
            tableModel = new DefaultTableModel(null,columnTitle);
            jTable.setModel(tableModel);

            //connect to DB
            Connection connection = Connector.ConnectDB();
            Statement statement = connection.createStatement();
            tableModel.getDataVector().removeAllElements();

            //inisialisasi result with SQL Query
            resultSet = statement.executeQuery("SELECT * FROM mahasiswa");

            //loop for insert data from SQL
            while (resultSet.next()){
                Object[] data ={
                        resultSet.getString("nama"),
                        resultSet.getString("nim"),
                        resultSet.getString("kelas")
                };
                tableModel.addRow(data);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private JPanel mainPanel;
    private JLabel JtittlePanel;
    private JTextField JTextFieldNama;
    private JTextField JTextFieldNIM;
    private JTextField JTextFieldKelas;
    private JButton AddButton;
    private JButton EditButton;
    private JButton HapusButton;
    private JPanel JFirstPAnel;
    private JPanel JSecondPanel;
    private JPanel JThirdPanel;
    private JLabel JLabelNama;
    private JLabel JLabelNim;
    private JLabel JLabelKelas;
    private JTable jTable;
    private JButton buttonRefresh;
}
