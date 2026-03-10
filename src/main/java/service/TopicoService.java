package service;

import domain.topico.DadosAtualizacaoTopico;
import domain.topico.DadosCadastroTopico;
import domain.topico.Status;
import domain.topico.Topico;
import repository.AutorRepository;
import repository.CursoRepository;
import domain.topico.validadores.ValidadorAtualizacaoTopico;
import domain.topico.validadores.ValidadorCadastroTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TopicoRepository;
import security.ValidacaoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorCadastroTopico> validadoresDeCadastro;

    @Autowired
    private List<ValidadorAtualizacaoTopico> validadoresDeAtualizacao;

    public Topico construir(DadosCadastroTopico dados){
        if(!autorRepository.existsById(dados.idAutor())){
            throw new ValidacaoException("Não existe autor com o id informado!");
        }
        if(!cursoRepository.existsById(dados.idCurso())){
            throw new ValidacaoException("Não existe curso com o id informado!");
        }

        validadoresDeCadastro.forEach(v -> v.validar(dados));

        return new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), Status.ABERTO,
                autorRepository.getReferenceById(dados.idAutor()), cursoRepository.getReferenceById(dados.idCurso()),
                new ArrayList<>());
    }

    public Topico retornar(Long id) {
        if(!topicoRepository.existsById(id)){
            throw new ValidacaoException("Tópico não encontrado");
        }
        return topicoRepository.findById(id).get();
    }

    public void atualizar(Long id, @Valid DadosAtualizacaoTopico dados) {

        if(topicoRepository.findById(id).isEmpty()){
            throw new ValidacaoException("Tópido não encontrado para este id");
        }
        if(!cursoRepository.existsById(dados.idCurso())){
            throw new ValidacaoException("Curso não encontrado");
        }
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        validadoresDeAtualizacao.forEach(v -> v.validar(dados));

        var topico = topicoRepository.getReferenceById(id);
        topico.atualizar(dados, curso);


    }
}
