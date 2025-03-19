package matheusmaia.commerce.services;

import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ResponseEntity<Produto> criarProduto(CadastrarProdutoDTO dados){
        var Produto = new Produto(dados);
        System.out.println(Produto.toString());
        this.produtoRepository.save(Produto);
        return ResponseEntity.ok(Produto);

    }
}
