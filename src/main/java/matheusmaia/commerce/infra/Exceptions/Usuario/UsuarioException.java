package matheusmaia.commerce.infra.Exceptions.Usuario;

public class UsuarioException extends RuntimeException{
    public UsuarioException(String mensagem) {
        super(mensagem);
    }
}
