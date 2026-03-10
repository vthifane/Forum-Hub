package service;

import domain.curso.Curso;
import domain.curso.DadosCadastroCurso;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    public Curso construir(DadosCadastroCurso dados){
        return new Curso(null, dados.nome(), dados.categoria());
    }
}
