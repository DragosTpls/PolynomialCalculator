package dev;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class PolyInterpreter implements ActionListener {
	Panel panel;
	String[] monoame1;
	String[] monoame2;
	
	// Suntem dependenti de valorile existente pe panou
	public PolyInterpreter(Panel panel) {
		this.panel = panel;
	}
	
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        panel.x = (panel.valoare.getText().isEmpty()) ? 0 : Integer.parseInt(panel.valoare.getText());
        
        //panel.statusLabel.setPreferredSize(new Dimension(350,30));
        if(command.equals("ok1"))  {
            String buffer = panel.polinom.getText();
        	Polinom poli = this.createPoli(buffer);
        	panel.controlLabel.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Rezultat polinom 1: <b>"+Integer.toString(poli.valoare()) + "</b></p></html>");
        	panel.statusLabel.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Polinom 1: <b>" + poli.afisare() + "</b></p></html>");
        } else if(command.equals("ok2")) {
            String buffer = panel.polinom2.getText();
        	Polinom poli =  this.createPoli(buffer);
        	panel.controlLabel2.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Rezultat polinom 2: <b>"+Integer.toString(poli.valoare()) + "</b></p></html>");
        	panel.statusLabel2.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Polinom 2: <b>" + poli.afisare() + "</b></p></html>");
        } else if(command.equals("plus")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
        	Polinom poli3 = this.add(buffer1, buffer2);
        	panel.controlLabel3.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Rezultat suma polinoame: <b>"+Integer.toString(poli3.valoare()) + "</b></p></html>");
        	panel.statusLabel3.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Polinom 3: <b>" + poli3.afisare() + "</b></p></html>");
        } else if(command.equals("minus")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
        	Polinom poli3 = this.sub(buffer1, buffer2);
        	panel.controlLabel4.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Rezultat scadere polinoame: <b>"+Integer.toString(poli3.valoare()) + "</b></p></html>");
        	panel.statusLabel4.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Polinom 4: <b>" + poli3.afisare() + "</b></p></html>");
        } else if(command.equals("times")) {
            String buffer1 = panel.polinom.getText();
            String buffer2 = panel.polinom2.getText();
        	Polinom poli3 = this.times(buffer1, buffer2);
        	panel.controlLabel5.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Rezultat inmultire polinoame: <b>"+Integer.toString(poli3.valoare()) + "</b></p></html>");
        	panel.statusLabel5.setText("<html><p style='font-family:Arial;font-weight:100;line-height:15px;'>Polinom 5: <b>" + poli3.afisare() + "</b></p></html>");
        }
    }
    
    private Polinom createPoli(String buffer) {
    	String monoame[];
    	String finalBuffer;
    	String actualBuffer = (buffer.contains("-")) ? buffer.replace("-", "+-") : buffer;
    	finalBuffer = (actualBuffer.charAt(0) == '+') ? actualBuffer.substring(1) : actualBuffer;
    	monoame = finalBuffer.split("\\+"); //omg
    	Polinom poli = new Polinom(monoame, panel.x);
    	return poli;
    }
    
    private Polinom add(String buffer1, String buffer2) {
    	Polinom poli1, poli2, poli3;
    	poli1 = createPoli(buffer1);
    	poli2 = createPoli(buffer2);
    	poli3 = poli1.add(poli2);
    	return poli3;
    }
    
    private Polinom sub(String buffer1, String buffer2) {
    	Polinom poli1, poli2, poli3;
    	poli1 = createPoli(buffer1);
    	poli2 = createPoli(buffer2);
    	for(int i=0;i<poli2.monom.size();i++) {
    		poli2.monom.get(i).coeficient = poli2.monom.get(i).coeficient*(-1);
    	}
    	poli3 = poli1.add(poli2);
    	return poli3;
    }
    
    private Polinom times(String buffer1, String buffer2) {
    	Polinom poli1, poli2, poli3;
    	poli1 = createPoli(buffer1);
    	poli2 = createPoli(buffer2);
    	poli3 = poli1.times(poli2);
    	return poli3;
    }
	    
}
