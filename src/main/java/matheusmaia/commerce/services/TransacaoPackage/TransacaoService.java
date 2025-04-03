package matheusmaia.commerce.services.TransacaoPackage;

import matheusmaia.commerce.domain.Transacao.Transacao;
import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Transacao.TransacoesNaoEncontradasException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.repositories.TransacaoRepository;
import matheusmaia.commerce.services.TransacaoPackage.validacoesTransacao.ValidadorTransacao;
import org.springframework.beans.factory.annotation.Autowired;
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

        //validacao das classes que implementaram a interface ValidadorTransacao
        validadores.forEach(validadorTransacao -> validadorTransacao.validar(dados));

        //Pegando valor do produto
        var precoProduto = produtoRepository.findById(dados.idProduto()).get().getPreco();

        //Transforma para o valor final
        var valor = BigDecimal.valueOf(dados.quantidade()).multiply(precoProduto);

        //Adicina os novos valores atualizados no obj
        var Transacao = new Transacao(dados);
        Transacao.setValor(valor);
        Transacao.setDataHora(OffsetDateTime.now());

        transacaoRepository.save(Transacao);

        //Atualiza a quantidade no banco
        var estoqueProduto = estoqueRepository.findEstoqueById(dados.idProduto());
        estoqueProduto.setQuantidade(estoqueProduto.getQuantidade() - dados.quantidade());
        estoqueRepository.save(estoqueProduto);

    return ResponseEntity.status(HttpStatus.CREATED).body(Transacao);
    }


    @Transactional(readOnly = true)
    public List<Transacao> buscarTransacoes(Integer horasBusca){

        //Transforma a hora da busca para Offsetdatetime
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusHours(horasBusca);

        //lista filtrada com dados de X horas atrás até o momento
        List<Transacao> listaTransacoes = transacaoRepository.findByHora(dataHoraIntervalo);

        if(listaTransacoes.isEmpty()){
            throw new TransacoesNaoEncontradasException("Não foram encontradas transações para o intervalo definido!");

        }

        return listaTransacoes;


    }

}
