package controller;

import domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import repository.TopicoRepository;
import security.ValidacaoException;
import service.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService service;
    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados){
        var topico = service.construir(dados);
        repository.save(topico);
        return ResponseEntity.ok(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"titulo"})Pageable paginacao){
        var page = repository.findAll(paginacao)
                .map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = service.retornar(id);
        var dto = new DadosListagemTopico(topico);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados){
        service.atualizar(id, dados);
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var topico = repository.findById(id);
        if(topico.isEmpty()){
            throw new ValidacaoException("Tópico não encontrado");
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
