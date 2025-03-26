package matheusmaia.commerce.infra.Exceptions;

import matheusmaia.commerce.infra.Exceptions.Estoque.QuantidadeInsuficienteException;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.infra.Exceptions.Usuario.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(QuantidadeInsuficienteException.class)
    public ResponseEntity<String> handleQuantidadeInsufieciente(QuantidadeInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }


    }
