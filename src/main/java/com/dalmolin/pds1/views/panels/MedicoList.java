package com.dalmolin.pds1.views.panels;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dalmolin.pds1.controllers.MedicoController;
import com.dalmolin.pds1.models.Medico;
import com.dalmolin.pds1.views.Frame;
import com.dalmolin.pds1.views.tables.MedicoTableModel;

public class MedicoList extends JPanel {
    private Frame frame;
    private JButton buttonAdicionar;
    private JButton buttonEditar;
    private JButton buttonRemover;
    private JTable tableMedicos;
    private MedicoTableModel medicosTableModel;
    private List<Medico> medicosList;


    public MedicoList(Frame frame) {
        this.frame = frame;

        // Customize panel
        this.setLayout(null);


        // Instatiate components 
        this.buttonAdicionar = new JButton("Adicionar");
        this.buttonEditar = new JButton("Editar");
        this.buttonRemover = new JButton("Remover");
        
        
        this.medicosList = MedicoController.getMedicos();
        this.medicosTableModel = new MedicoTableModel(this.medicosList);
        this.tableMedicos = new JTable(medicosTableModel);
        this.tableMedicos.setFillsViewportHeight(true);

        JTableHeader tableHeader = this.tableMedicos.getTableHeader();

        this.buttonAdicionar.setBounds(20, 20, 120, 30);
        this.buttonEditar.setBounds(160, 20, 120, 30);
        this.buttonRemover.setBounds(300, 20, 120, 30);
        tableHeader.setBounds(20, 80, 460, 30);
        this.tableMedicos.setBounds(20, 110, 460, 200);

        
        this.buttonAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                MedicoList panel = (MedicoList) button.getParent();
                panel.getFrame().openMedicoForm();
            }
        });


        this.buttonEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                MedicoList panel = (MedicoList) button.getParent();
                int selectedIndex = panel.tableMedicos.getSelectedRow();
                if (selectedIndex >= 0) {
                    Integer idMedico = (Integer) panel.tableMedicos.getValueAt(selectedIndex, 0);
                    Medico medico = MedicoController.getMedicoByID(idMedico);
                    panel.getFrame().openMedicoForm(medico);
                }
            }
        });

        this.buttonRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                MedicoList panel = (MedicoList) button.getParent();
                int selectedIndex = panel.tableMedicos.getSelectedRow();
                if (selectedIndex >= 0) {
                    Integer idMedico = (Integer) panel.tableMedicos.getValueAt(selectedIndex, 0);
                    MedicoController.deleteMedico(idMedico);
                    panel.getFrame().openMedicoList();
                }
            }
        });

        this.add(this.buttonAdicionar);
        this.add(this.buttonEditar);
        this.add(this.buttonRemover);
        this.add(tableHeader);
        this.add(this.tableMedicos);
    }

    public Frame getFrame() {
        return this.frame;
    }
}
