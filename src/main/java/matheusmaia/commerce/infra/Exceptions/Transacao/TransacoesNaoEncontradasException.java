package matheusmaia.commerce.infra.Exceptions.Transacao;

public class TransacoesNaoEncontradasException extends RuntimeException{
    public TransacoesNaoEncontradasException(String mensagem) {
        super(mensagem);
    }
}

