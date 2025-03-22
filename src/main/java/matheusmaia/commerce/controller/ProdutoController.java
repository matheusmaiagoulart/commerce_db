package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.domain.Produto.DadosListagemProdutosDTO;
import matheusmaia.commerce.domain.Produto.editarProdutoDTO;
import matheusmaia.commerce.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listarProdutos")
    public ResponseEntity listarProdutos(DadosListagemProdutosDTO dto){
        return produtoService.listarProdutos(dto);
    }

    @PostMapping("/criarProduto")
    public ResponseEntity criarProduto(@Valid @RequestBody CadastrarProdutoDTO dados){
         var Produto = produtoService.criarProduto(dados);
        return ResponseEntity.ok().body(Produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarProduto(@PathVariable UUID id, @Valid @RequestBody editarProdutoDTO dto){
        return produtoService.editarProduto(id, dto);
    }
}
