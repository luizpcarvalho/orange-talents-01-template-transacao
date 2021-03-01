package br.com.zup.transacoes.transacao.evento;

import br.com.zup.transacoes.transacao.Estabelecimento;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoTransacao {

    @NotBlank
    private String nome;
    @NotBlank
    private String cidade;
    @NotBlank
    private String endereco;

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public Estabelecimento paraEstabelecimento() {
        return new Estabelecimento(this.nome, this.cidade, this.endereco);
    }
}
