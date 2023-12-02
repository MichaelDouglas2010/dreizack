package painel;


import java.util.List;

import back.Usuario;

public interface UsuarioDAO {
    List<Usuario> consultarUsuarios();
    void cadastrarUsuario(Usuario usuario);
    Usuario logarUsuario(String login, String senha);
    void editarUsuario(Usuario usuario);
    void excluirUsuario(Usuario usuario);
}
