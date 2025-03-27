package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import lombok.extern.java.Log;
import matheusmaia.commerce.domain.Transacao.Transacao;
import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity transacao(@Valid @RequestBody TransacaoDTO dto){
        System.out.println("Chegou na controller");
        return transacaoService.transacao(dto);
    }

    @GetMapping("/{horasBusca}")
    public List<Transacao> buscarTransacoes(@PathVariable Integer horasBusca){
        System.out.println("Buscando Transações");
        return transacaoService.buscarTransacoes(horasBusca);
    }

    @GetMapping()
    public List<Transacao> buscarTodasTransacoesDia(){
        Integer horasBusca = 24;
        System.out.println("Buscando Transações");
        return transacaoService.buscarTransacoes(horasBusca);
    }


}
