package dao;

import conexion.ConexionMySQL;
import modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado del acceso a Mysql .
 */
@Repository
public class ClienteDAO {

    @Autowired
    private ConexionMySQL conexionMySQL;

    /**
     * Guardar un cliente .
     */
    public void guardar(Cliente cliente) {

        String sql =
                "INSERT INTO CLIENTE (NOMBRE, EMAIL) VALUES (?,?)";

        try (
                Connection con = (Connection) conexionMySQL.conectar();
                PreparedStatement ps =  con.prepareStatement(sql)
        ) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * Devuelver todos los clientes .
     */
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();

        String  sql =
                "SELECT ID, NOMBRE, EMAIL FROM CLIENTE ORDER BY ID";
        try (
                Connection con = (Connection) conexionMySQL.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("ID"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setEmail(rs.getString("EMAIL"));

                lista.add(cliente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }   
}
