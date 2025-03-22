package matheusmaia.commerce.infra.Exceptions;

import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<String> handleValidacaoException(ValidacaoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage() + "Faltam informações! Campos vazios ou Nulos não são aceitos!");

    }

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
        public ResponseEntity<String> handleProdutoNaoEncontrado(ProdutoNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage() + "Produto não foi encontrado!");

    }
    }
