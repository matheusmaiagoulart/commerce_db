package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Venda.TransacaoDTO;
import matheusmaia.commerce.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
