package br.com.zup.transacoes.transacao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String idCartao;
    @NotBlank
    @Email
    private String email;
    @OneToMany(mappedBy = "cartao", fetch = FetchType.EAGER)
    private List<Transacao> transacoes = new ArrayList<>();

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, String email) {
        this.idCartao = id;
        this.email = email;
    }

    public String getId() {
        return idCartao;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "idCartao='" + idCartao + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void associaTransacao(Transacao transacao) {
        this.transacoes.add(transacao);
    }
}
