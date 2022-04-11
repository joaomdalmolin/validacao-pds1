package com.dalmolin.pds1.views.tables;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.dalmolin.pds1.models.Medico;

public class MedicoTableModel extends AbstractTableModel {

    private List<Medico> listaMedicos;
    private String[] columnNames = {"ID", "Nome", "Especialidade", "Telefone", "E-mail"};

    public MedicoTableModel(List<Medico> listaMedicos) {
        this.listaMedicos = listaMedicos;
    }

    public int getColumnCount() {
        return this.columnNames.length;
    }

    public int getRowCount() {
        return this.listaMedicos.size();
    }

    @Override
    public String getColumnName(int index) {
        return this.columnNames[index];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Medico medico = this.listaMedicos.get(rowIndex);
        
        switch (columnIndex) {
            default: case 0: return medico.getId();
            case 1: return medico.getNome();
            case 2: return medico.getEspecialidade().getDescricao();
            case 3: return medico.getTelefone();
            case 4: return medico.getEmail();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void addRow(Medico medico) {
        this.listaMedicos.add(medico);
    }

}