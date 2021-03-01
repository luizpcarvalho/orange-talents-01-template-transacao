package br.com.zup.transacoes.transacao;

import br.com.zup.transacoes.transacao.evento.EventoDeTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoDeTransacao eventoDeTransacao) {
        Transacao transacao = eventoDeTransacao.paraTransacao(cartaoRepository);
        transacaoRepository.save(transacao);
        System.out.println("Transação " + transacao.getId() + " recebido.");
    }

    public List<Transacao> transacoes() {
        return transacaoRepository.findAll();
    }

    public ResponseEntity<List<Transacao>> transacoesRecentes(Long id) {
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<Transacao> transacoes = transacaoRepository.findTop10ByCartaoOrderByEfetivadaEmDesc(cartao.get());
        return ResponseEntity.ok().body(transacoes);
    }

}
