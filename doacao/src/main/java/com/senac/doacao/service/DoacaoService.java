package com.senac.doacao.service;

import com.senac.doacao.clientFeign.DoadorFeignClient;
import com.senac.doacao.dto.DoadorDTO;
import com.senac.doacao.dto.DoacaoDtoRequest;
import com.senac.doacao.entity.Doacao;
import com.senac.doacao.repository.DoacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final DoadorFeignClient doadorClient;

    public DoacaoService(DoacaoRepository doacaoRepository, DoadorFeignClient doadorClient) {
        this.doacaoRepository = doacaoRepository;
        this.doadorClient = doadorClient;
    }

    // 1) Criar uma nova doação
    public Doacao criarDoacao(DoacaoDtoRequest request) {
        // 1. Cadastrar o doador remotamente (Aluno A)
        DoadorDTO novoDoador = new DoadorDTO();
        novoDoador.setNome(request.getNomeDoador());
        novoDoador.setEmail(request.getEmailDoador());

        DoadorDTO doadorCadastrado = doadorClient.cadastrarDoador(novoDoador);

        // 2. Criar a doação local
        Doacao nova = new Doacao();
        nova.setData(LocalDate.now());
        nova.setValor(request.getDoacaoValor());
        nova.setStatus(1);

        return doacaoRepository.save(nova);
    }

    // 2) Buscar todas as doações de um usuário
    public List<Doacao> listarDoacoesPorUsuario(Integer doadorId) {
        return doacaoRepository.findByDoadorId(doadorId);
    }
}
