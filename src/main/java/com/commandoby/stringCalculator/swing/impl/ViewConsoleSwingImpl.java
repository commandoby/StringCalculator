package com.commandoby.stringCalculator.swing.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ViewConsoleSwingImpl  implements ActionListener {
	JFrame jfrm;
	JTextField jtf;
	
	public ViewConsoleSwingImpl() {
		jfrm = new JFrame("String calculator");
		jfrm.setSize(350, 150);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jtf = new JTextField();
		jtf.setActionCommand("myTF");
		jtf.addActionListener(this);
		jfrm.add(jtf, BorderLayout.SOUTH);
		
		jfrm.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("myTF")) {
			System.out.println("here");
			jfrm.add(new JLabel(jtf.getText()), BorderLayout.SOUTH);
		}
	}

}
