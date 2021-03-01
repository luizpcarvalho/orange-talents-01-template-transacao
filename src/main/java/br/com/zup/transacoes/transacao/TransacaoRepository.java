package br.com.zup.transacoes.transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findTop10ByCartaoOrderByEfetivadaEmDesc(Cartao cartao);
}
