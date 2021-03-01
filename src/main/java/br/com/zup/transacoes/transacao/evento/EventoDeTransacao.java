package br.com.zup.transacoes.transacao.evento;

import br.com.zup.transacoes.transacao.Cartao;
import br.com.zup.transacoes.transacao.CartaoRepository;
import br.com.zup.transacoes.transacao.Estabelecimento;
import br.com.zup.transacoes.transacao.Transacao;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoDeTransacao {

    @NotBlank
    private String id;
    @NotNull
    private BigDecimal valor;
    @Valid
    @Embedded
    private EstabelecimentoTransacao estabelecimento;
    @Valid
    @Embedded
    private CartaoTransacao cartao;
    @NotNull
    private LocalDateTime efetivadaEm;

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoTransacao getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoTransacao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao paraTransacao(CartaoRepository cartaoRepository) {
        Estabelecimento estabelecimento = this.estabelecimento.paraEstabelecimento();
        Cartao cartao = this.cartao.paraCartao(cartaoRepository);
        Transacao transacao = new Transacao(this.id, this.valor, estabelecimento, cartao, this.efetivadaEm);
        cartao.associaTransacao(transacao);
        return transacao;
    }
}
