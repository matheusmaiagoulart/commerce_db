package matheusmaia.commerce.utils;

import matheusmaia.commerce.infra.Exceptions.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TratamentoDeDados {
    //Classe para trataemento de dados de entrada

    public String tratamentoDado(String dado, String nomeDado){

        if(dado == null){
            throw new ValidacaoException(nomeDado + " não pode ser Nulo!");
        }
        return dado.replaceAll("\\s", "");

    }



}
