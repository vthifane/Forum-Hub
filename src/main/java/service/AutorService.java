package service;

import domain.autor.Autor;
import domain.autor.DadosCadastroAutor;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    public Autor construir(DadosCadastroAutor dados) {
        return new Autor(null, dados.nome(), dados.email(), dados.senha());
    }
}
