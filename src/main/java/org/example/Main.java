package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        * c- create
        * r-read
        * u- update
        * d- delete
        * */


        String url = "jdbc:mysql://localhost:3309/ad";
        String username = "user";
        String password = "pwd";
        String sqlSelect = "select * from Equipo";
        String sqlInsert= "insert into Equipo (nombre, categoria, disponible)" +
                "values (?,?,?)";

        String sqlUpdate = "update Equipo set nombre = ?, categoria = ?, disponible = ? where id = ?";

        String sqlDelete = "delete from Equipo where id = ?";

        try(Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = conn.prepareStatement(sqlInsert) )
        {
            pst.setString(1,"Nuevo equipo");
            pst.setString(2,"Nueva categoria");
            pst.setBoolean(3,true);
            int result = pst.executeUpdate(); //devuelve el número de filas afectadas
            System.out.println("Filas afectadas : "+result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = conn.prepareStatement(sqlUpdate) )
        {
            pst.setString(1,"Equipo actualizado");
            pst.setString(2,"Categoria actualizada");
            pst.setBoolean(3,false);
            pst.setInt(4,1);
            int result = pst.executeUpdate(); //devuelve el numero de filas afectadas, si no hay
            //filas afectadas, devuelve 0
            System.out.println("Filas afectadas : "+result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = conn.prepareStatement(sqlDelete) )
        {
            pst.setInt(1,1);//busca el equipo con ID1
            int result = pst.executeUpdate(); //devuelve 1 o 0, dependiendo si encontró el id
            System.out.println("Filas afectadas : "+result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // execute(): devuelve boolean (true si el primer resultado es un ResultSet)
        // executeQuery(): solo para SELECT, devuelve un ResultSet
        // executeUpdate(): solo para INSERT/UPDATE/DELETE, devuelve el numero de filas afectadas

        try(Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement pst = conn.prepareStatement(sqlSelect);
            ResultSet rs = pst.executeQuery())
        {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nombre") + " | " +
                                rs.getString("categoria") + " | " +
                                (rs.getBoolean("disponible")? "disponible" : "no disponible")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
