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