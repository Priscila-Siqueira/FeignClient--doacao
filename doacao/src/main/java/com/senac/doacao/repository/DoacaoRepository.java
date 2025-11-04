package com.senac.doacao.repository;

import com.senac.doacao.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {
    List<Doacao> findByDoadorId(Integer doadorId);

}
