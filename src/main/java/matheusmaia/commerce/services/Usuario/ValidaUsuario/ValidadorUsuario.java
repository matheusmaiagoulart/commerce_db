package matheusmaia.commerce.services.Usuario.ValidaUsuario;

import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;

public interface ValidadorUsuario {

    void validaUsuarioCadastro(CadastrarUsuarioDTO dados);
}
