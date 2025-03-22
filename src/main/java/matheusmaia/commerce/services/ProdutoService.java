package matheusmaia.commerce.services;

import matheusmaia.commerce.domain.Estoque.Estoque;
import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.domain.Produto.DadosListagemProdutosDTO;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.domain.Produto.editarProdutoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.utils.TratamentoDeDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TratamentoDeDados tratamentoDeDados;
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public ResponseEntity criarProduto(CadastrarProdutoDTO dados) {
        String produtoNome = dados.nomeProduto().trim();
        if (dados.validade().isBefore(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de validade precisa ser maior que a Data de hoje");
        }
        if (dados.preco() == null || (dados.preco().compareTo(BigDecimal.ZERO) < 0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O preço precisa ser maior que 0!");
        }
        var produto = new Produto(dados);
        produto.setNomeProduto(produtoNome); //setando nome tratado
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }


    public ResponseEntity listarProdutos(DadosListagemProdutosDTO dto) {
        var allProducts = produtoRepository.findAll().stream().map(DadosListagemProdutosDTO::new).toList();
        if (allProducts == null || allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("A requisição foi bem sucedida! Porém, não há registros para mostrar!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(allProducts);
        }
    }

    @Transactional
    public ResponseEntity editarProduto(UUID id, editarProdutoDTO editarProduto) {
        Produto produto = produtoRepository.findById(id);

        if (produto == null) {
            throw new ProdutoNaoEncontradoException("O produto não foi encontado!");
        }
        produto.setNomeProduto(editarProduto.nome_produto());
        produto.setPreco(editarProduto.preco());
        produto.setValidade(editarProduto.validade());
        produtoRepository.save(produto);
        var Produto = produtoRepository.findById(id);

        //Atualizando a tabela estoque com os novos dados do Produto, que foram alterados
        Optional<Estoque> estoque = estoqueRepository.findById(Produto.getId());
        if(estoque.isEmpty()){
            throw new ProdutoNaoEncontradoException("Estoque não encontrado");
        }
        Estoque estoque1 = estoque.get();
        estoque1.setNomeProduto(Produto.getNomeProduto());
        estoque1.setValidade(Produto.getValidade());
        estoqueRepository.save(estoque1);

        return ResponseEntity.status(HttpStatus.OK).body("Produto e Estoque atualizados com sucesso!" + Produto);
    }
}
