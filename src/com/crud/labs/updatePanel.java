package com.crud.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class updatePanel {

    public updatePanel() {
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama,nim,kelas;

                nama = JTextFieldNama.getText();
                nim = JTextFieldNim.getText();
                kelas = JTextFieldKelas.getText();
                if(!Objects.equals(nim, "") && !Objects.equals(nama, "") && !Objects.equals(kelas, "")){
                    try{
                        preparedStatement = Connector.ConnectDB().prepareStatement("UPDATE mahasiswa SET nama=?, kelas=? WHERE nim=?;");
                        preparedStatement.setString(1,nama);
                        preparedStatement.setString(2,kelas);
                        preparedStatement.setString(3,nim);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Di UPDATE");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();
                    }catch (SQLException err){
                        err.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Data Yang Diinput Tidak Boleh Kosong");
                }

            }
        });
        CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getUpdatePanel(){
        return MainUpdatePanel;
    }

    private PreparedStatement preparedStatement;
    private JPanel MainUpdatePanel;
    private JLabel JTittleUpdatePanel;
    private JTextField JTextFieldNim;
    private JTextField JTextFieldNama;
    private JTextField JTextFieldKelas;
    private JButton CancelButton;
    private JButton UpdateButton;
    private JPanel JPanelNimTarger;
    private JLabel JTittleNIM;
    private JPanel JPanelUpdateData;
    private JLabel JTittleNama;
    private JLabel JTittleKelas;
}
