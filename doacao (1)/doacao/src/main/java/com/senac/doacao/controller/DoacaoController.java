package com.senac.doacao.controller;

import com.senac.doacao.dto.DoadorDTO;
import com.senac.doacao.dto.DoacaoDtoRequest;
import com.senac.doacao.entity.Doacao;
import com.senac.doacao.service.DoacaoService;
import com.senac.doacao.clientFeign.DoadorFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doacoes")
@CrossOrigin("*")
public class DoacaoController {

    private final DoacaoService doacaoService;
    private final DoadorFeignClient doadorClient;

    public DoacaoController(DoacaoService doacaoService, DoadorFeignClient doadorClient) {
        this.doacaoService = doacaoService;
        this.doadorClient = doadorClient;
    }

    /**
     * 1️⃣ Endpoint para cadastrar um novo doador (via Feign Client)
     * POST /api/doacoes/cadastrardoador
     */
    @PostMapping("/cadastrardoador")
    public ResponseEntity<DoadorDTO> cadastrarDoador(@RequestBody DoadorDTO doadorDTO) {
        try {
            DoadorDTO novoDoador = doadorClient.cadastrarDoador(doadorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoDoador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 2️⃣ Endpoint para gravar uma nova doação para um doador existente
     * POST /api/doacoes/{doadorId}/nova
     */
    @PostMapping("/{doadorId}/nova")
    public ResponseEntity<Doacao> gravarNovaDoacao(
            @PathVariable Integer doadorId,
            @RequestBody DoacaoDtoRequest request) {
        try {
            // Aqui usamos o service que salva a doação local
            Doacao novaDoacao = doacaoService.criarDoacao(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 3️⃣ Endpoint para obter todas as doações de um doador
     * GET /api/doacoes/{doadorId}
     */
    @GetMapping("/{doadorId}")
    public ResponseEntity<Map<String, Object>> obterDoacoesDoUsuario(@PathVariable Integer doadorId) {
        try {
            // Busca o doador remoto via Feign
            DoadorDTO doador = doadorClient.getDoadorById(doadorId);

            // Busca as doações locais pelo ID
            List<Doacao> doacoes = doacaoService.listarDoacoesPorUsuario(doadorId);

            // Monta a DTO de resposta
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("idDoador", doador.getId());
            resposta.put("nomeDoador", doador.getNome());
            resposta.put("emailDoador", doador.getEmail());
            resposta.put("doacoes", doacoes);

            return ResponseEntity.ok(resposta);

        } catch (feign.FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
