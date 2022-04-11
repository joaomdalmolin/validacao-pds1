package com.dalmolin.pds1.views;

import javax.swing.JFrame;

import com.dalmolin.pds1.models.Medico;
import com.dalmolin.pds1.views.panels.MedicoForm;
import com.dalmolin.pds1.views.panels.MedicoList;

public class Frame extends JFrame {
    
    public Frame() {
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setTitle("Clinica");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.openMedicoList();
    }

    public void openMedicoForm() {
        MedicoForm medicoForm = new MedicoForm(this);
        this.setContentPane(medicoForm);
        this.repaint();
    }
    public void openMedicoForm(Medico medico) {
        MedicoForm medicoForm = new MedicoForm(this, medico);
        this.setContentPane(medicoForm);
        this.repaint();
    }

    public void openMedicoList() {
        MedicoList medicoList = new MedicoList(this);
        this.setContentPane(medicoList);
        this.repaint();
    }
}
