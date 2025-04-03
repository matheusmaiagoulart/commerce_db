package matheusmaia.commerce.services.UsuarioPackage.ValidaUsuario;

import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;

public interface ValidadorUsuario {

    void validaUsuarioCadastro(CadastrarUsuarioDTO dados);
}
