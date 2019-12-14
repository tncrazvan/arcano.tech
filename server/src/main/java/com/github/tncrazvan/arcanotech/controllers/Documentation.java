/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.tncrazvan.arcanotech.controllers;

import com.github.tncrazvan.arcano.Bean.WebMethod;
import com.github.tncrazvan.arcano.Bean.WebPath;
import com.github.tncrazvan.arcano.Http.HttpController;
import com.github.tncrazvan.arcanotech.MySQL;
import com.google.gson.JsonArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
@WebPath(name = "/documentation")
public class Documentation extends HttpController{
    
    @WebPath(name = "/explore")
    public JsonArray explore() throws SQLException{
        if(!e.issetUrlQuery("id")){
            e.setStatus(STATUS_BAD_REQUEST);
            return null;
        }
        String id = e.getUrlQuery("id");
        Connection conn = new MySQL("127.0.0.1", "root", "", "arcanotech").connect();
        PreparedStatement st = conn.prepareStatement("select * from visibility where visibility_id like ?");
        st.setString(1, id);
        ResultSet result = st.executeQuery();
        JsonArray array = new JsonArray();
        while(result.next()){
            array.add(result.getString("visibility_id"));
        }
        conn.close();
        return array;
    }
}
