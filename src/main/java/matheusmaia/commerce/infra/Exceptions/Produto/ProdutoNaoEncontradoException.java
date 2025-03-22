package matheusmaia.commerce.infra.Exceptions.Produto;

public class ProdutoNaoEncontradoException extends RuntimeException {

        public ProdutoNaoEncontradoException(String message) {
            super(message);
        }
}
