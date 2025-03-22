package matheusmaia.commerce.services;

import matheusmaia.commerce.domain.Estoque.CadastrarEstoqueDTO;
import matheusmaia.commerce.domain.Estoque.Estoque;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EstoqueService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

    public ResponseEntity criarEstoque(CadastrarEstoqueDTO DTO, UUID id){
        Produto produto = produtoRepository.findById(id);
        if(produto == null){
            throw new ProdutoNaoEncontradoException("O produto não foi encontrado na base de Produtos! Verifique o ID e tente novamente!");
        }
        Estoque estoque = new Estoque(DTO, produto);
        estoqueRepository.save(estoque);
        return ResponseEntity.ok().body(estoque);
    }
}
