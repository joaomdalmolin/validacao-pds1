package com.dalmolin.pds1.controllers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dalmolin.pds1.db.DB;
import com.dalmolin.pds1.models.Especialidade;
import com.dalmolin.pds1.models.Medico;

public class MedicoController {
    public static List<Medico> getMedicos() {
        try {
            Statement stmt = DB.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT m.id, m.nome, m.especialidade_id, e.descricao as especialidade_descricao, " +
                "m.telefone, m.email FROM clinica.medico as m LEFT JOIN clinica.especialidade e  ON e.id = m.especialidade_id  ORDER BY id;");
            
            List<Medico> medicosList = new ArrayList<Medico>();
            Medico medico = null;
            while(rs.next()) {
                medico = new Medico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    new Especialidade(rs.getInt("especialidade_id"), rs.getString("especialidade_descricao")),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
                medicosList.add(medico);
            }
            return medicosList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static Medico getMedicoByID(int id) {
        try {
            Statement stmt = DB.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT m.id, m.nome, m.especialidade_id, e.descricao as especialidade_descricao," +
            " m.telefone, m.email FROM clinica.medico AS m LEFT JOIN clinica.especialidade e  ON e.id = m.especialidade_id " +
            " WHERE m.id = " + id + ";");
            //TODO: fix sql injection

            if (rs.next()) {
                return new Medico(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    new Especialidade(rs.getInt("especialidade_id"), rs.getString("especialidade_descricao")),
                    rs.getString("telefone"),
                    rs.getString("email")
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    public static Medico editMedico(Medico medico) {
        try {
            Statement stmt = DB.getConnection().createStatement();
            stmt.executeUpdate("UPDATE clinica.medico SET nome='" + medico.getNome() +"', especialidade_id=" +
                medico.getEspecialidade().getId() + ", telefone='" + medico.getTelefone() +"', email='" + medico.getEmail() + 
                "' WHERE id=" + medico.getId() + ";");
                //TODO: fix sql injection
            
            return getMedicoByID(medico.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Medico createMedico(Medico medico) {
        try {
            Statement stmt = DB.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO clinica.medico (nome, especialidade_id, telefone, email) " +
                "VALUES('" + medico.getNome() +"', " + medico.getEspecialidade().getId() + ", '" + medico.getTelefone() +"', '"
                + medico.getEmail() + "');");
            //TODO: fix sql injection
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    

    public static void deleteMedico(int medicoId) {
        try {
            Statement stmt = DB.getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM clinica.medico WHERE id=" + medicoId + ";");
            //TODO: fix sql injection
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
