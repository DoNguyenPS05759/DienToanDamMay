/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GiaHieu
 */
public class StudentDAO {

    public StudentDAO() {
    }

    public static List<Student> showStudent(String studentname) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SOF302_TestASM";
            Connection con = DriverManager.getConnection(url, "sa", "123");
            String sql = "select * from Students";
            if (studentname.length() > 0) {
                sql += " where Name like '%" + studentname + "%'";
            }
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            List<Student> list = new ArrayList<Student>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String mark = rs.getString("Mark");
                String major = rs.getString("Major");
                Student sp = new Student(id, name, mark, major);
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteStudent(int id) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SOF302_TestASM";
            Connection con = DriverManager.getConnection(url, "sa", "123");
            String sql = "delete from Students where Id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(Student student) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SOF302_TestASM";
            Connection con = DriverManager.getConnection(url, "sa", "123");
            String sql = "insert into Students values(?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, student.getId());
            stm.setString(2, student.getName());
            stm.setString(3, student.getMark());
            stm.setString(4, student.getMajor());
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(Student student) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SOF302_TestASM";
            Connection con = DriverManager.getConnection(url, "sa", "123");
            String sql = "update Students set Name=?, Mark=?, Major=? where Id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, student.getName());
            stm.setString(2, student.getMark());
            stm.setString(3, student.getMajor());
            stm.setInt(4, student.getId());
            stm.executeUpdate();
            stm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
