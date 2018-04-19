/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GiaHieu
 */
public class MajorDAO {

    public MajorDAO() {
    }
    
     public static List<Major> listMajor(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=SOF302_TestASM";
            Connection con = DriverManager.getConnection(url, "sa", "123");
            String sql = "select * from Majors";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            List<Major> list = new ArrayList<>();
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                Major mj = new Major(id, name);
                list.add(mj);
            }
            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
