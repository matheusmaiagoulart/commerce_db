package matheusmaia.commerce.services.Transacao;

import matheusmaia.commerce.domain.Transacao.Transacao;
import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Transacao.TransacoesNaoEncontradasException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import matheusmaia.commerce.repositories.ProdutoRepository;
import matheusmaia.commerce.repositories.TransacaoRepository;
import matheusmaia.commerce.services.Transacao.validacoesTransacao.ValidadorTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(TransacaoService.class);
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
        log.info("/Transacao - Iniciando validações para realizar a Transação");
        validadores.forEach(validadorTransacao -> validadorTransacao.validar(dados));
        log.info("Buscando Informações do Produto");
        var precoProduto = produtoRepository.findById(dados.idProduto()).get().getPreco();

        var valor = BigDecimal.valueOf(dados.quantidade()).multiply(precoProduto);

        //Adicina os novos valores atualizados no obj
        var Transacao = new Transacao(dados);
        Transacao.setValor(valor);
        Transacao.setDataHora(OffsetDateTime.now());

        transacaoRepository.save(Transacao);
        log.info("Sua transação foi concluída!");

        var estoqueProduto = estoqueRepository.findEstoqueById(dados.idProduto());
        estoqueProduto.setQuantidade(estoqueProduto.getQuantidade() - dados.quantidade());
        estoqueRepository.save(estoqueProduto);

    return ResponseEntity.status(HttpStatus.CREATED).body(Transacao);
    }


    @Transactional(readOnly = true)
    public List<Transacao> buscarTransacoes(Integer horasBusca){

        log.info("/Transacoes - Buscando pelas transações...");
        //Transforma a hora da busca para Offsetdatetime
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusHours(horasBusca);

        //lista filtrada com dados de X horas atrás até o momento
        List<Transacao> listaTransacoes = transacaoRepository.findByHora(dataHoraIntervalo);

        if(listaTransacoes.isEmpty()){
            log.info("/Transacoes - Nenhuma transação foi encontrada neste intervalo!");
            throw new TransacoesNaoEncontradasException("Não foram encontradas transações para o intervalo definido!");
        }
        log.info("/Transacoes - Transações encontradas com sucesso!");
        return listaTransacoes;


    }

}
