package matheusmaia.commerce.services.Estoque;

import matheusmaia.commerce.domain.Estoque.CadastrarEstoqueDTO;
import matheusmaia.commerce.domain.Estoque.DadosListagemEstoqueDTO;
import matheusmaia.commerce.domain.Estoque.Estoque;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
public class EstoqueService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public ResponseEntity criarEstoque(CadastrarEstoqueDTO DTO, UUID id){

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("O produto não foi encontrado na base de Produtos! Verifique o ID e tente novamente!"));
        Estoque estoque = new Estoque(DTO, produto);
        estoqueRepository.save(estoque);
        return ResponseEntity.ok().body(estoque);
    }

    @Transactional(readOnly = true)
    public ResponseEntity listarTodoEstoque(DadosListagemEstoqueDTO listagemEstoqueDTO){

       var listaEstoque = estoqueRepository.findAll()
               .stream()
               .map(DadosListagemEstoqueDTO::new)
               .toList();
        if (listaEstoque == null || listaEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A requisição foi bem sucedida! Porém, não há registros para mostrar!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(listaEstoque);
        }

    }

    @Transactional(readOnly = true)
    public ResponseEntity listarEstoqueAtivo(DadosListagemEstoqueDTO listagemEstoqueDTO){

        var listaEstoque = estoqueRepository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemEstoqueDTO::new)
                .toList();

        if (listaEstoque.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A requisição foi bem sucedida! Porém, não há registros para mostrar!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(listaEstoque);
        }
    }


}
