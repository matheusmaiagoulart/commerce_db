package matheusmaia.commerce.services;

import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.domain.Produto.Produto;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.utils.TratamentoDeDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TratamentoDeDados tratamentoDeDados;

    @Transactional
    public ResponseEntity criarProduto(CadastrarProdutoDTO dados) {

        String produtoNome = dados.nomeProduto().trim();
        if(dados.validade().isBefore(LocalDate.now())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de validade precisa ser maior que a Data de hoje");
        }
        if(dados.preco() == null || (dados.preco().compareTo(BigDecimal.ZERO) < 0)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O preço precisa ser maior que 0!");
        }
        var produto = new Produto(dados);
        produto.setNomeProduto(produtoNome); //setando nome tratado
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
}
