package com.dalmolin.pds1.views.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dalmolin.pds1.controllers.EspecialidadeController;
import com.dalmolin.pds1.controllers.MedicoController;
import com.dalmolin.pds1.models.Especialidade;
import com.dalmolin.pds1.models.Medico;
import com.dalmolin.pds1.views.Frame;

public class MedicoForm extends JPanel{

    private Frame frame;
    private JButton buttonCancelar;
    private JButton buttonSalvar;
    private JLabel labelNome;
    private JLabel labelEspecialidade;
    private JLabel labelTelefone;
    private JLabel labelEmail;
    private JTextField inputNome;
    private JComboBox<Especialidade> inputEspecilidade;
    private JTextField inputTelefone;
    private JTextField inputEmail;
    private Medico medico;


    public MedicoForm(Frame frame) {
        this(frame, null);
    }

    public MedicoForm(Frame frame, Medico medico) {
        this.frame = frame;
        this.medico = medico;

        // Customize panel
        this.setSize(500, 400);
        this.setLayout(null);

        // Instatiate components 
        this.buttonCancelar = new JButton("Cancelar");
        this.buttonSalvar = new JButton("Salvar");

        this.labelNome = new JLabel("Nome:");
        this.labelEspecialidade = new JLabel("Especialidade:");
        this.labelTelefone = new JLabel("Telefone:");
        this.labelEmail = new JLabel("Email:");
        
        this.inputNome = new JTextField();
        this.inputEspecilidade = new JComboBox<Especialidade>();
        this.inputTelefone = new JTextField();
        this.inputEmail = new JTextField();
        
        this.fillInputEspecialidadeItems();

        // Set components position and size
        this.buttonCancelar.setBounds(20, 20, 120, 30);
        this.buttonSalvar.setBounds(150, 20, 120, 30);

        this.labelNome.setBounds(20, 70, 120, 30);
        this.labelEspecialidade.setBounds(20, 120, 120, 30);
        this.labelTelefone.setBounds(20, 170, 120, 30);
        this.labelEmail.setBounds(20, 220, 120, 30);

        this.inputNome.setBounds(150, 70, 200, 30);
        this.inputEspecilidade.setBounds(150, 120, 200, 30);
        this.inputTelefone.setBounds(150, 170, 200, 30);
        this.inputEmail.setBounds(150, 220, 200, 30);

        // add button click listeners
        this.buttonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                MedicoForm panel = (MedicoForm) button.getParent();
                panel.getFrame().openMedicoList();
            }
        });
        
        buttonSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                MedicoForm panel = (MedicoForm) button.getParent();

                Medico newMedico = new Medico();
                if (panel.medico != null) {
                    newMedico = panel.medico;
                }

                Especialidade especialidade = (Especialidade) panel.inputEspecilidade.getSelectedItem();
                System.out.println(especialidade);
                
                newMedico.setNome(panel.inputNome.getText());
                newMedico.setEspecialidade(especialidade);
                newMedico.setTelefone(panel.inputTelefone.getText());
                newMedico.setEmail(panel.inputEmail.getText());
                
                if (newMedico.getId() == null){
                    MedicoController.createMedico(newMedico);
                } else {
                    MedicoController.editMedico(newMedico);
                }
                panel.getFrame().openMedicoList();
            }
        });

        // Add components to panel
        this.add(buttonCancelar);
        this.add(buttonSalvar);

        this.add(labelEspecialidade);
        this.add(labelNome);
        this.add(labelTelefone);
        this.add(labelEmail);

        this.add(inputNome);
        this.add(inputEspecilidade);
        this.add(inputTelefone);
        this.add(inputEmail);

        // Fill if objects was provided
        if (this.medico != null) {
            this.fillInputFields(this.medico);
        }
    }

    private void fillInputEspecialidadeItems() {
        this.inputEspecilidade.removeAllItems();
        for (Especialidade e : EspecialidadeController.getEspecialidades()) {
            this.inputEspecilidade.addItem(e);
        }
    }

    private void fillInputFields(Medico medico) {
        this.inputNome.setText(medico.getNome());
        this.inputEspecilidade.setSelectedItem(medico.getEspecialidade());
        this.inputTelefone.setText(medico.getTelefone());
        this.inputEmail.setText(medico.getEmail());
    }

    public Frame getFrame() {
        return this.frame;
    }
}
