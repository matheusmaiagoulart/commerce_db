package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Estoque.CadastrarEstoqueDTO;
import matheusmaia.commerce.domain.Estoque.DadosListagemEstoqueDTO;
import matheusmaia.commerce.services.Estoque.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/criarEstoque/{id}")
    public ResponseEntity criarEstoque(@PathVariable UUID id, @Valid @RequestBody CadastrarEstoqueDTO DTO){
        return estoqueService.criarEstoque(DTO, id);
    }

    //Lista ativos e inativos
    @GetMapping("/listarTodoEstoque")
    public ResponseEntity listarTodoEstoque(DadosListagemEstoqueDTO dto){
        System.out.println("chegou na controller");
        return estoqueService.listarTodoEstoque(dto);
    }

    //Lista estoque ativo
    @GetMapping("/listarEstoqueAtivo")
    public ResponseEntity listarEstoqueAtivo(DadosListagemEstoqueDTO dto){
        System.out.println("chegou na controller");
        return estoqueService.listarEstoqueAtivo(dto);
    }


}


