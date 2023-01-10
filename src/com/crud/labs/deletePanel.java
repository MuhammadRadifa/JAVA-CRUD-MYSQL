package com.crud.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class deletePanel {

    public deletePanel() {
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim;
                nim = textField1.getText();
                System.out.println(nim);
                if (!Objects.equals(nim, "")){
                    try{
                        preparedStatement = Connector.ConnectDB().prepareStatement("DELETE FROM mahasiswa WHERE nim=?;");
                        preparedStatement.setString(1,nim);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Data Berhasil Di Hapus");
                        JComponent component = (JComponent) e.getSource();
                        Window window = SwingUtilities.getWindowAncestor(component);
                        window.dispose();


                    }catch (SQLException err){
                        err.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Nim Harus Di Isi");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent component = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(component);
                window.dispose();
            }
        });
    }

    public JPanel getDeletePanel(){
        return DeletePanel;
    }

    private PreparedStatement preparedStatement;
    private JPanel DeletePanel;
    private JLabel JTittleDelete;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTextField textField1;
    private JLabel JTextFieldNim;
    private JLabel JTittleNim;
    private JPanel JPanelNim;
    private JPanel JPanelButton;
}
