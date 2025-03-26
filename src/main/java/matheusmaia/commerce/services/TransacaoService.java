package matheusmaia.commerce.services;

import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.domain.Venda.Transacao;
import matheusmaia.commerce.domain.Venda.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.repositories.TransacaoRepository;
import matheusmaia.commerce.services.validacoesTransacao.ValidadorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private List<ValidadorTransacao> validadores; //Spring identifica todas as classes que utilizam essa interface e vai instancia-las automatico

    @Transactional
    public ResponseEntity transacao(TransacaoDTO dados){

        validadores.forEach(validadorTransacao -> validadorTransacao.validar(dados)); //validacao das classes que implementaram a interface ValidadorTransacao

        var precoProduto = produtoRepository.findById(dados.idProduto()).get().getPreco();
        var valor = BigDecimal.valueOf(dados.quantidade()).multiply(precoProduto);
        var Transacao = new Transacao(dados);
        Transacao.setValor(valor);
        Transacao.setDataHora(OffsetDateTime.now());

        transacaoRepository.save(Transacao);

        var estoqueProduto = estoqueRepository.findEstoqueById(dados.idProduto());
        estoqueProduto.setQuantidade(estoqueProduto.getQuantidade() - dados.quantidade()); //atualizando a quantidade no banco
        estoqueRepository.save(estoqueProduto);

    return ResponseEntity.status(HttpStatus.CREATED).body(Transacao);
    }
}
