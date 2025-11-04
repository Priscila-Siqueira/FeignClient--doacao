package com.senac.doacao.clientFeign;

import com.senac.doacao.dto.DoadorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// URL pode vir do application.properties: doador.service.url=http://localhost:8081
@Component
@FeignClient(name = "doador", url = "10.136.64.34:8081", path = "api/doador")
public interface DoadorFeignClient {

    // Endpoint remoto do Aluno A (cadastra um novo doador)
    @PostMapping(value = "/cadastrarDoador")
    DoadorDTO cadastrarDoador(@RequestBody DoadorDTO doador);

    // Endpoint remoto do Aluno A (buscar doador por ID)
    @GetMapping("/listarDoadorPorId/{id}")
    DoadorDTO getDoadorById(@PathVariable("id") Integer id);
}
