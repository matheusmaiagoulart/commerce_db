package matheusmaia.commerce.infra.Exceptions.Usuario;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
