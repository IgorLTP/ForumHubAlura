package Alura.ForumHub.controller;

import Alura.ForumHub.model.Topico;
import Alura.ForumHub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @GetMapping
    public List<Topico> listar() {
        return repository.findAll();
    }

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody Topico topico) {
        if (repository.existsByTituloAndMensagem(topico.getTitulo(), topico.getMensagem())) {
            return "Tópico duplicado!";
        }
        repository.save(topico);
        return "Tópico criado com sucesso!";
    }
}
