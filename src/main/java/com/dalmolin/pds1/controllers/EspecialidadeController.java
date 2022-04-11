package com.dalmolin.pds1.controllers;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dalmolin.pds1.db.DB;
import com.dalmolin.pds1.models.Especialidade;
public class EspecialidadeController {
    public static List<Especialidade> getEspecialidades() {
        try {
            Statement stmt = DB.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("select id, descricao from especialidade order by id");
            
            List<Especialidade> especiliadadesList = new ArrayList<Especialidade>();
            Especialidade especialidade = null;
            while(rs.next()) {
                especialidade = new Especialidade(
                    rs.getInt("id"),
                    rs.getString("descricao")
                );
                especiliadadesList.add(especialidade);
            }
            return especiliadadesList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
