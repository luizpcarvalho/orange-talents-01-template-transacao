package br.com.zup.transacoes.transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<Transacao>> consulta() {
        return ResponseEntity.ok().body(transacaoService.transacoes());
    }

    @GetMapping("/{id}/recentes")
    public ResponseEntity<List<Transacao>> consultaRecentes(@PathVariable("id") Long id) {
        return  transacaoService.transacoesRecentes(id);
    }

}
