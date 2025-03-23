package matheusmaia.commerce.services;

import matheusmaia.commerce.domain.Estoque.CadastrarEstoqueDTO;
import matheusmaia.commerce.domain.Estoque.DadosListagemEstoqueDTO;
import matheusmaia.commerce.domain.Estoque.Estoque;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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


    public ResponseEntity listarEstoque(DadosListagemEstoqueDTO listagemEstoqueDTO){
        System.out.println(
                "chegou na service"
        );
       var listaEstoque = estoqueRepository.findAll().stream().map(DadosListagemEstoqueDTO::new).toList();
        System.out.println(listagemEstoqueDTO.id_produto());
        System.out.println(listagemEstoqueDTO.quantidade());
        System.out.println(listagemEstoqueDTO.validade());
        System.out.println(listagemEstoqueDTO.nome_produto());
        if (listaEstoque == null || listaEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("A requisição foi bem sucedida! Porém, não há registros para mostrar!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(listaEstoque);
        }

    }
}
