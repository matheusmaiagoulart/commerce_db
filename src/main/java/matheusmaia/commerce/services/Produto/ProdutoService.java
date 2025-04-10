package matheusmaia.commerce.services.Produto;


import matheusmaia.commerce.domain.Estoque.Estoque;
import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.domain.Produto.DadosListagemProdutosDTO;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.domain.Produto.editarProdutoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.services.Produto.validacoesProduto.ValidadorProduto;
import matheusmaia.commerce.utils.TratamentoDeDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TratamentoDeDados tratamentoDeDados;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    List<ValidadorProduto> validador;
    @Transactional
    public ResponseEntity criarProduto(CadastrarProdutoDTO dados) {

        log.info("/Produto - Iniciando validações para cadastrar Produto");
        //Tira espacos do dado
        String produtoNome = dados.nomeProduto().trim();

        validador.forEach(validador -> validador.validar(dados));

        //Salvo o produto com as informações tratadas
        var produto = new Produto(dados);
        produto.setNomeProduto(produtoNome);
        produtoRepository.save(produto);

        log.info("/Produto - Produto criado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @Transactional(readOnly = true)
    public ResponseEntity listarProdutosAtivos(DadosListagemProdutosDTO dto) {

        var allProducts = produtoRepository.findAllByAtivoTrue()
                .stream()
                .map(DadosListagemProdutosDTO::new)
                .toList();

        if (allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProdutoException("Não há produtos ativos!"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(allProducts);
        }
    }


    @Transactional
    public ResponseEntity atualizarProduto(UUID id, editarProdutoDTO editarProduto) {

        //Buscando produto no banco
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        //Buscando Estoque no banco
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("Estoque do Produto não encontrado!"));

        //Atualizacoes na tabela Produtos e Estoque

        //Nome produto
        if (editarProduto.nome_produto() != null) {
            produto.setNomeProduto(editarProduto.nome_produto());
            estoque.setNomeProduto(editarProduto.nome_produto());
        }
        //Validade
        if (editarProduto.validade() != null) {
            produto.setValidade(editarProduto.validade());
            estoque.setValidade(editarProduto.validade());
        }
        //Preco
        if (editarProduto.preco() != null) {
            produto.setPreco(editarProduto.preco());
            estoque.setPreco(editarProduto.preco());
        }
        //Ativo
        if (editarProduto.ativo() != null) {
            produto.setAtivo(editarProduto.ativo());
            estoque.setAtivo(editarProduto.ativo());
        }

        produtoRepository.save(produto);
        estoqueRepository.save(estoque);

        return ResponseEntity.status(HttpStatus.OK).body("Produto e Estoque atualizados com sucesso!");
    }


    @Transactional(readOnly = true)
    public ResponseEntity listarTodosProdutos(DadosListagemProdutosDTO dto) {

        var allProducts = produtoRepository.findAll()
                .stream()
                .map(DadosListagemProdutosDTO::new).
                toList();
        if (allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("A requisição foi bem sucedida! Porém, não há registros para mostrar!");
        }
            return ResponseEntity.status(HttpStatus.OK).body(allProducts);

    }

    @Transactional(readOnly = true)
    public ResponseEntity getProdutoById(UUID id){

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoException("O produto não foi encontrado!"));

        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }


}
