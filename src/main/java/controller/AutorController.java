package controller;
import repository.AutorRepository;
import service.AutorService;
import domain.autor.DadosCadastroAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorService service;
    @Autowired
    private AutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroAutor dados){
        var autor = service.construir(dados);
        repository.save(autor);
        return ResponseEntity.ok(dados);
    }
}
