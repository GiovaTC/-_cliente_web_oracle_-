package conexion;

import com.sun.jdi.connect.spi.Connection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Clase encargada de obtener la conexión con Mysql .
 */
@Component
public class ConexionMySQL {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usuario;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Devuelve una conexion a Mysql .
     */
    public Connection conectar() throws SQLException {

        return (Connection) DriverManager.getConnection(
                url,
                usuario,
                password
        );
    }
}
