package matheusmaia.commerce.infra.Exceptions.Estoque;

public class QuantidadeInsuficienteException extends RuntimeException{

        public QuantidadeInsuficienteException(String message) {
            super(message);
        }

}
