package com.github.tncrazvan.arcanotech.controller;

import com.github.tncrazvan.arcanotech.database.MySQL;
import com.github.tncrazvan.arcano.Http.HttpController;
import static com.github.tncrazvan.arcano.Tool.Http.Status.STATUS_BAD_REQUEST;

import com.google.gson.JsonArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.github.tncrazvan.arcano.Bean.Web.HttpPath;

public class Documentation extends HttpController{
    @HttpPath(name = "/documentation/explore")
    public JsonArray explore() throws SQLException{
        if(!issetRequestQueryString("id")){
            setResponseStatus(STATUS_BAD_REQUEST);
            return null;
        }
        String id = getRequestQueryString("id");
        JsonArray array;
        try (Connection conn = new MySQL("127.0.0.1", "root", "", "arcanotech").connect()) {
            PreparedStatement st = conn.prepareStatement("select * from visibility where visibility_id like ?");
            st.setString(1, id);
            ResultSet result = st.executeQuery();
            array = new JsonArray();
            while(result.next()){
                array.add("assssd:"+result.getString("visibility_id"));
            }
        }
        return array;
    }
}
