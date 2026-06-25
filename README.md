# -_cliente_web_oracle_- :.

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/2ffd9b94-1637-42b6-afdc-7ccc3d680d8d" />  

<img width="2551" height="1034" alt="image" src="https://github.com/user-attachments/assets/8780c4dd-7af3-42aa-a8d1-62a54ac5da6d" /> 

````markdown
# principal

Aplicación Web MVC extremadamente básica desarrollada con **Java 21**, **Spring Boot**, **Thymeleaf**, **JDBC** y **Oracle Database 19c**.

El objetivo es mostrar la arquitectura MVC y el procesamiento de datos utilizando Oracle, manteniendo el código lo más reducido posible.

---

# Tecnologías

- Java 21
- Spring Boot 3
- Spring MVC
- Thymeleaf
- JDBC
- Oracle Database 19c
- Bootstrap 5

---

# Arquitectura

```
 Navegador

      │

      ▼

 index.html

      │

      ▼

 ClienteController

      │

      ▼

 ClienteService

      │

      ▼

 ClienteDAO

      │

      ▼

 ConexionOracle

      │

      ▼

 Oracle Database 19c
```

---

# Estructura

```
principal

│

├── pom.xml

│

├── src

│   └── main

│       ├── java

│       │

│       ├── principal

│       │      ClienteWebOracleApplication.java

│       │

│       ├── conexion

│       │      ConexionOracle.java

│       │

│       ├── modelo

│       │      Cliente.java

│       │

│       ├── dao

│       │      ClienteDAO.java

│       │

│       ├── servicio

│       │      ClienteService.java

│       │

│       └── controlador

│              ClienteController.java

│

│       └── resources

│

│           ├── templates

│           │      index.html

│           │      formulario.html

│           │

│           ├── static

│           │

│           └── application.properties

│

└── README.md
```

---

# Base de datos Oracle 19c

```sql
CREATE TABLE CLIENTE(

ID NUMBER GENERATED ALWAYS AS IDENTITY,

NOMBRE VARCHAR2(100),

EMAIL VARCHAR2(100),

PRIMARY KEY(ID)

);

COMMIT;
```

---

# Funcionalidades

- Agregar clientes.
- Consultar clientes.
- Guardar datos en Oracle.
- Mostrar clientes registrados.

---

# Interfaz

```
+----------------------------------------------------+

                  CLIENTES

Nombre

[__________________________]

Email

[__________________________]

        [ Guardar ]

------------------------------------------------------

ID     Nombre           Email

1      Juan             juan@gmail.com

2      Ana              ana@gmail.com

3      Pedro            pedro@gmail.com

+----------------------------------------------------+
```

---

# Archivo: pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.0</version>
        <relativePath/>
    </parent>

    <groupId>com.ejemplo</groupId>
    <artifactId>principal</artifactId>
    <version>1.0.0</version>
    <name>principal</name>
    <description>Aplicación Web MVC con Oracle 19c</description>

    <properties>
        <java.version>21</java.version>
    </properties>

    <dependencies>

        <!-- Spring Boot MVC -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- Thymeleaf -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>

        <!-- JDBC -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <!-- Oracle JDBC Driver -->
        <dependency>
            <groupId>com.oracle.database.jdbc</groupId>
            <artifactId>ojdbc11</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- DevTools (opcional) -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- Pruebas -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>

        <plugins>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

        </plugins>

    </build>

</project>
```

---

# Archivo: application.properties

```
#################################################
# APLICACION
#################################################

spring.application.name=principal

server.port=8080

#################################################
# THYMELEAF
#################################################

spring.thymeleaf.cache=false

#################################################
# ORACLE DATABASE 19c
#################################################

spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=SYSTEM
spring.datasource.password=oracle

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

#################################################
# JDBC
#################################################

spring.datasource.hikari.maximum-pool-size=5

#################################################
# LOGS
#################################################

logging.level.root=INFO
```

---

# Archivo: ClienteWebOracleApplication.java

```
package principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación.
 */
@SpringBootApplication
public class ClienteWebOracleApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                ClienteWebOracleApplication.class,
                args
        );

    }

}
```

---

# Archivo: ConexionOracle.java

```
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase encargada de obtener la conexión con Oracle.
 */
@Component
public class ConexionOracle {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String usuario;

    @Value("${spring.datasource.password}")
    private String password;

    /**
     * Devuelve una conexión a Oracle.
     */
    public Connection conectar() throws SQLException {

        return DriverManager.getConnection(
                url,
                usuario,
                password
        );

    }

}
```

---

# Archivo: Cliente.java

```
package modelo;

/**
 * Modelo Cliente.
 */
public class Cliente {

    private Integer id;
    private String nombre;
    private String email;

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(Integer id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
```

---

# Archivo: ClienteDAO.java

```
package dao;

import conexion.ConexionMySQL;
import modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado del acceso a Oracle.
 */
@Repository
public class ClienteDAO {

    @Autowired
    private ConexionOracle conexionOracle;

    /**
     * Guarda un cliente.
     */
    public void guardar(Cliente cliente) {

        String sql =
                "INSERT INTO CLIENTE (NOMBRE, EMAIL) VALUES (?, ?)";

        try (
                Connection con = conexionOracle.conectar();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**
     * Devuelve todos los clientes.
     */
    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

        String sql =
                "SELECT ID, NOMBRE, EMAIL FROM CLIENTE ORDER BY ID";

        try (
                Connection con = conexionOracle.conectar();
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
```

---

# Archivo: ClienteService.java

```
package servicio;

import dao.ClienteDAO;
import modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de servicio.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    /**
     * Guarda un cliente.
     */
    public void guardar(Cliente cliente) {

        clienteDAO.guardar(cliente);

    }

    /**
     * Devuelve la lista de clientes.
     */
    public List<Cliente> listar() {

        return clienteDAO.listar();

    }

} 
```

---

# Archivo: ClienteController.java

```
package controlador;

import modelo.Cliente;
import servicio.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controlador MVC.
 */
@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * Página principal.
     */
    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute("cliente", new Cliente());

        model.addAttribute(
                "clientes",
                clienteService.listar()
        );

        return "index";

    }

    /**
     * Guarda un cliente.
     */
    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Cliente cliente) {

        clienteService.guardar(cliente);

        return "redirect:/";

    }

}
```

---

# Archivo: index.html

```
<!DOCTYPE html>
<html lang="es"
      xmlns:th="http://www.thymeleaf.org">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>principal</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<!-- Barra superior -->

<nav class="navbar navbar-dark bg-primary">

    <div class="container">

        <span class="navbar-brand mb-0 h1">

            principal

        </span>

    </div>

</nav>

<!-- Contenido -->

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-8">

            <div class="card shadow rounded">

                <div class="card-header text-center">

                    <h3>CLIENTES</h3>

                </div>

                <div class="card-body">

                    <!-- Formulario -->

                    <form th:action="@{/guardar}"
                          th:object="${cliente}"
                          method="post">

                        <div class="mb-3">

                            <label class="form-label">

                                Nombre

                            </label>

                            <input
                                    type="text"
                                    class="form-control"
                                    th:field="*{nombre}"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label class="form-label">

                                Email

                            </label>

                            <input
                                    type="email"
                                    class="form-control"
                                    th:field="*{email}"
                                    required>

                        </div>

                        <div class="text-center">

                            <button
                                    class="btn btn-success">

                                Guardar

                            </button>

                        </div>

                    </form>

                    <hr>

                    <!-- Tabla -->

                    <table
                            class="table table-hover table-bordered">

                        <thead class="table-primary">

                        <tr>

                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Email</th>

                        </tr>

                        </thead>

                        <tbody>

                        <tr th:each="c : ${clientes}">

                            <td th:text="${c.id}"></td>

                            <td th:text="${c.nombre}"></td>

                            <td th:text="${c.email}"></td>

                        </tr>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </div>

</div>

</body>

</html>
```

---

# Archivo: formulario.html

```
<!DOCTYPE html>
<html lang="es"
      xmlns:th="http://www.thymeleaf.org">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1">

    <title>Nuevo Cliente</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

</head>

<body class="bg-light">

<nav class="navbar navbar-dark bg-primary">

    <div class="container">

        <a class="navbar-brand"
           th:href="@{/}">

            principal

        </a>

    </div>

</nav>

<div class="container mt-5">

    <div class="row justify-content-center">

        <div class="col-md-6">

            <div class="card shadow rounded">

                <div class="card-header text-center">

                    <h3>Nuevo Cliente</h3>

                </div>

                <div class="card-body">

                    <form th:action="@{/guardar}"
                          th:object="${cliente}"
                          method="post">

                        <div class="mb-3">

                            <label class="form-label">
                                Nombre
                            </label>

                            <input
                                    type="text"
                                    class="form-control"
                                    th:field="*{nombre}"
                                    placeholder="Ingrese el nombre"
                                    required>

                        </div>

                        <div class="mb-3">

                            <label class="form-label">
                                Email
                            </label>

                            <input
                                    type="email"
                                    class="form-control"
                                    th:field="*{email}"
                                    placeholder="Ingrese el email"
                                    required>

                        </div>

                        <div class="d-grid gap-2">

                            <button type="submit"
                                    class="btn btn-success">

                                Guardar

                            </button>

                            <a th:href="@{/}"
                               class="btn btn-secondary">

                                Volver

                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </div>

</div>

</body>

</html>
```

---

# Ejecución

1. Crear la tabla `CLIENTE` en Oracle Database 19c.
2. Configurar el usuario y la contraseña en `application.properties`.
3. Ejecutar `ClienteWebOracleApplication`.
4. Abrir el navegador en:

```
http://localhost:8080
```

---

# Resultado esperado

La aplicación permitirá registrar clientes y visualizar el listado almacenado en Oracle Database 19c mediante una interfaz
web sencilla desarrollada con Bootstrap 5, implementando la arquitectura MVC con JDBC puro.
````
:. . / .
