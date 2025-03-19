package matheusmaia.commerce.utils;

import matheusmaia.commerce.infra.Exceptions.ValidacaoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TratamentoDeDados {
    //Classe para trataemento de dados de entrada

    private final int TAMANHO_MINIMO_LOGIN = 5;
    private final int TAMANHO_MINIMO_SENHA = 5;

    public String tratamentoLogin(String login){

        if(login == null){
            throw new ValidacaoException("Seu campo Login não pode ser Nulo!");
        }
        login.replaceAll("\\s", "");

        if(login.length() <= TAMANHO_MINIMO_LOGIN){
           throw new ValidacaoException("Seu campo Login precisa ter pelo menos " + TAMANHO_MINIMO_LOGIN + " caracteres");
        }
        return login;

    }

    public String tratamentoSenha(String senha){

        if(senha == null){
            throw new ValidacaoException("Seu campo Senha não pode ser Nulo!");
        }
        senha.replaceAll("\\s", "");

        if(senha.length() <= TAMANHO_MINIMO_SENHA){
            throw new ValidacaoException("Seu campo Senha precisa ter pelo menos " + TAMANHO_MINIMO_LOGIN + " caracteres");
        }
        return senha;

    }


}
