package servicio;

import dao.ClienteDAO;
import modelo.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Capa de servicio .
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