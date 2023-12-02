package back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * ProjetoDAO:
 * Essa classe realiza operações no banco de dados relacionadas aos projetos.
 */

public class ProjetoDAO {

    private static BD bd = new BD();
    private static String sql, mensagem;

    /**
     * Insere um novo projeto no banco de dados.
     *
     * @param p O projeto a ser inserido.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String inserir(Projeto p) {
        sql = "INSERT INTO projeto (nome_projeto, descricao_projeto, orcamento_projeto, status_projeto)\r\n"
                + "VALUES \r\n"
                + "  (?, ?, ?, ?); -- Associada ao projeto com id_projeto";
        bd.getConnection();
        mensagem = "Projeto inserido com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);

            bd.st.setString(1, p.getNome());
            bd.st.setString(2, p.getDescricao());
            bd.st.setDouble(3, p.getOrcamento());
            bd.st.setDouble(4, p.getStatus());

            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na inclusão do projeto!";
        } finally {
            bd.close();
        }
        return mensagem;
    }

    /**
     * Exclui um projeto do banco de dados com base no código.
     *
     * @param codigo O código do projeto a ser excluído.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String excluir(int codigo) {
        sql = "DELETE FROM projeto WHERE id_projeto = ?";
        bd.getConnection();
        mensagem = "Produto excluído com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, codigo);
            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na exclusão do produto!";
        } finally {
            bd.close();
        }
        return mensagem;
    }

    /**
     * Altera os dados de um projeto no banco de dados.
     *
     * @param p O projeto com os novos dados a serem atualizados.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String alterar(Projeto p) {
        sql = "UPDATE projeto SET nome_projeto = ?, descricao_projeto = ?, orcamento_projeto = ?, status_projeto = ? WHERE id_projeto = ?";
        bd.getConnection();
        mensagem = "Dados do projeto alterados com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(5, p.getId());
            bd.st.setString(1, p.getNome());
            bd.st.setString(2, p.getDescricao());
            bd.st.setDouble(3, p.getOrcamento());
            bd.st.setDouble(4, p.getStatus());

            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na alteração dos dados do projeto!";
        } finally {
            bd.close();
        }
        return mensagem;
    }
    
 // ... (seu código existente)

    /**
     * Lista todos os projetos presentes no banco de dados.
     *
     * @return Uma lista de objetos Projeto.
     */
    public List<Projeto> listarProjetos() {
        List<Projeto> projetos = new ArrayList<>();

        try {
            bd.getConnection();
            String sql = "SELECT * FROM projeto";
            bd.st = bd.con.prepareStatement(sql);
            ResultSet rs = bd.st.executeQuery();

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id_projeto"));
                projeto.setNome(rs.getString("nome_projeto"));
                projeto.setDescricao(rs.getString("descricao_projeto"));
                projeto.setOrcamento(rs.getDouble("orcamento_projeto"));
                projeto.setStatus(rs.getDouble("status_projeto"));

                // Convertendo as datas do banco para java.util.Date
   //             Date dataInicio = rs.getDate("dataInicio");
 //               Date dataConclusao = rs.getDate("dataConclusao");
//                projeto.setDataInicio(dataInicio);
  //              projeto.setDataConclusao(dataConclusao);

                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close();
        }

        return projetos;
    }

}
