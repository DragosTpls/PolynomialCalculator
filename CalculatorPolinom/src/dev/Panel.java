package dev;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel controlLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    public JTextField polinom;

    public Panel(){
        prepareGUI();
    }

    public static void main(String[] args){
        Panel panel = new Panel();
        panel.showEventDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER );
        controlLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        polinom = new JTextField(25);

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo(){
        headerLabel.setText("Control in action: Button");
        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ButtonClickListener());
        controlPanel.add(okButton);
        controlPanel.add(polinom);
        controlPanel.add(controlLabel);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if( command.equals( "OK" ))  {
                statusLabel.setText("Ok Button clicked.");
                controlLabel.setText(polinom.getText());
            }
        }
    }
}