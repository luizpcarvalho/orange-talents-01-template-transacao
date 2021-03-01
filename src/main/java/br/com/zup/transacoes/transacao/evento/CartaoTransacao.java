package br.com.zup.transacoes.transacao.evento;

import br.com.zup.transacoes.transacao.Cartao;
import br.com.zup.transacoes.transacao.CartaoRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CartaoTransacao {

    @NotBlank
    private String id;
    @NotBlank
    @Email
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao paraCartao(CartaoRepository cartaoRepository) {
        Optional<Cartao> optional = cartaoRepository.findByIdCartao(this.id);
        if(optional.isPresent()) {
            return optional.get();
        }
        Cartao cartao = new Cartao(this.id, this.email);
        cartao = cartaoRepository.save(cartao);
        return cartao;
    }
}
