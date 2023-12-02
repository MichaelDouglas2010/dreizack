package back;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private static BD bd = new BD();
    private static String sql, mensagem;

    /**
     * Insere uma nova tarefa no banco de dados.
     *
     * @param t A tarefa a ser inserida.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String inserir(Tarefa t) {
        sql = "INSERT INTO tarefa (nome_tarefa, descricao_tarefa, responsavel_tarefa, status_tarefa, id_projeto)\r\n"
                + "VALUES (?, ?, ?, ?, ?); -- Associada ao projeto com id_projeto";
        bd.getConnection();
        mensagem = "Tarefa inserida com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            // bd.st.setInt(1, t.getId()); // Não está sendo utilizado, removido
            bd.st.setString(1, t.getNome());
            bd.st.setString(2, t.getDescricao());
            bd.st.setString(3, t.getResponsavel());
            bd.st.setDouble(4, t.getStatus());
            bd.st.setInt(5, t.getIdProjeto());

            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na inclusão da tarefa, verifique se o código já existe!";
        } finally {
            bd.close();
        }
        return mensagem;
    }

    /**
     * Exclui uma tarefa do banco de dados com base no código.
     *
     * @param codigo O código da tarefa a ser excluída.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String excluir(int codigo) {
        sql = "DELETE FROM tarefa WHERE id_tarefa = ?";
        bd.getConnection();
        mensagem = "Tarefa excluída com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, codigo);
            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na exclusão da tarefa!";
        } finally {
            bd.close();
        }
        return mensagem;
    }

    /**
     * Altera os dados de uma tarefa no banco de dados.
     *
     * @param t A tarefa com os novos dados a serem atualizados.
     * @return Uma mensagem indicando o resultado da operação.
     */
    public static String alterar(Tarefa t) {
        sql = "UPDATE tarefa SET nome_tarefa = ?, descricao_tarefa = ?, responsavel_tarefa = ?, status_tarefa = ?, id_projeto = ? WHERE id_tarefa = ?";
        bd.getConnection();
        mensagem = "Dados da tarefa alterados com sucesso!";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(6, t.getId());
            bd.st.setString(1, t.getNome());
            bd.st.setString(2, t.getDescricao());
            bd.st.setString(3, t.getResponsavel());
            bd.st.setDouble(4, t.getStatus());
            bd.st.setInt(5, t.getIdProjeto());

            // bd.st.setDate(6, new java.sql.Date(t.getDataInicio().getTime())); // Corrigido o índice para 6 e convertido a data
            // bd.st.setDate(7, new java.sql.Date(t.getDataConclusao().getTime())); // Corrigido o índice para 7 e convertido a data

            bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            mensagem = "Falha na alteração dos dados da tarefa!";
        } finally {
            bd.close();
        }
        return mensagem;
    }
    
    /**
     * Lista todos os tarefas presentes no banco de dados.
     *
     * @return Uma lista de objetos tarefa.
     */
    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();

        try {
            bd.getConnection();
            String sql = "SELECT * FROM tarefa";
            bd.st = bd.con.prepareStatement(sql);
            ResultSet rs = bd.st.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id_tarefa"));
                tarefa.setNome(rs.getString("nome_tarefa"));
                tarefa.setDescricao(rs.getString("descricao_tarefa"));
                tarefa.setResponsavel(rs.getString("responsavel_tarefa"));
                tarefa.setStatus(rs.getDouble("status_tarefa"));

                // Convertendo as datas do banco para java.util.Date
   //             Date dataInicio = rs.getDate("dataInicio");
 //               Date dataConclusao = rs.getDate("dataConclusao");
//                projeto.setDataInicio(dataInicio);
  //              projeto.setDataConclusao(dataConclusao);

                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            bd.close();
        }

        return tarefas;
    }
    
}
