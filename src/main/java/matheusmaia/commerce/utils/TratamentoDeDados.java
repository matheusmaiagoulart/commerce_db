package matheusmaia.commerce.utils;


import matheusmaia.commerce.infra.Exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TratamentoDeDados {
    //Classe para trataemento de dados de entrada


    public String tratamentoDado(String dado, String nomeDado) {

        if (dado == null) {
            throw new ValidacaoException(nomeDado + " não pode ser Nulo!");
        }
        return dado.replaceAll("\\s", "");

    }
}
