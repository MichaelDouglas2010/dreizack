package back;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static BD bd = new BD();
    private static String sql, mensagem;

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param u O usuário a ser inserido.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String inserir(Usuario u) {
        sql = "INSERT INTO usuario (cpf, nome_usuario, login_usuario, senha, cargo)\r\n"
                + "VALUES \r\n"
                + "  (?, ?, ?, ?, ?);";
        bd.getConnection();
        mensagem = "Usuário inserido com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, u.getCpf());
            bd.st.setString(2, u.getNome());
            bd.st.setString(3, u.getLogin());
            bd.st.setString(4, u.getSenha());
            bd.st.setString(5, u.getCargo());

            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na inclusão do usuário, verifique se o CPF já existe!";
        } finally {
            bd.close();
        }
        return mensagem;
    }

    /**
     * Exclui um usuário do banco de dados com base no CPF.
     *
     * @param codigo O CPF do projeto a ser excluído.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String excluir(String codigo) {
        sql = "DELETE FROM usuario WHERE cpf = ?";
        bd.getConnection();
        mensagem = "Usuário excluído com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, codigo);
            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na exclusão do usuário!";
        } finally {
            bd.close();
        }
        return mensagem;
    }

    /**
     * Altera os dados de um usuário no banco de dados.
     *
     * @param u O usuário com os novos dados a serem atualizados.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String alterar(Usuario u) {
        sql = "UPDATE usuario SET nome_usuario = ?, login_usuario = ?, senha = ?, cargo = ? WHERE cpf = ?";
        bd.getConnection();
        mensagem = "Dados do projeto alterados com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(5, u.getCpf());
            bd.st.setString(1, u.getNome());
            bd.st.setString(2, u.getLogin());
            bd.st.setString(3, u.getSenha());
            bd.st.setString(4, u.getCargo());

            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na alteração dos dados do usuário!";
        } finally {
            bd.close();
        }
        return mensagem;
    }
    
    /**
     * Lista todos os usuarios presentes no banco de dados.
     *
     * @return Uma lista de objetos usuarios.
     */
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            bd.getConnection();
            String sql = "SELECT * FROM usuario";
            bd.st = bd.con.prepareStatement(sql);
            ResultSet rs = bd.st.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getInt("cpf"));
                usuario.setNome(rs.getString("nome_usuario"));
                usuario.setLogin(rs.getString("login_usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCargo(rs.getString("cargo"));

                // Convertendo as datas do banco para java.util.Date
   //             Date dataInicio = rs.getDate("dataInicio");
 //               Date dataConclusao = rs.getDate("dataConclusao");
//                projeto.setDataInicio(dataInicio);
  //              projeto.setDataConclusao(dataConclusao);

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close();
        }

        return usuarios;
    }
    
    /**
     * Consulta o login do usuário no banco de dados.
     *
     * @param id O ID do usuário.
     * @return O login do usuário.
     */
    public static String consultaLog(String id) {
        bd.getConnection();
        sql = "SELECT login_usuario FROM usuario WHERE senha = ?";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, id);
            ResultSet resultSet =  bd.st.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("login_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close();
        }
        return null; // Retornar null se não encontrar o usuário.
    }

    /**
     * Consulta a senha do usuário no banco de dados.
     *
     * @param senha O ID do usuário.
     * @return A senha do usuário.
     */
    public static String consultaSenha(String senha) {
        bd.getConnection();
        sql = "SELECT senha FROM usuario WHERE login_usuario = ?";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, new String(senha)); // Convertendo char[] para String
            ResultSet resultSet = bd.st.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("senha");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close();
        }
        return null; // Retornar null se não encontrar o usuário.
    }

}
