package controller;

import domain.curso.Curso;
import repository.CursoRepository;
import service.CursoService;
import domain.curso.DadosCadastroCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService service;
    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroCurso dados){
        Curso curso = service.construir(dados);
        repository.save(curso);
        return ResponseEntity.ok(dados);
    }
}
