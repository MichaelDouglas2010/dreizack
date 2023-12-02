package back;


import java.util.List;

public class TarefaExportaCSV {

    /**
     * Converte uma lista de tarefas para uma string no formato CSV.
     *
     * @param tarefas A lista de tarefa a ser convertida.
     * @return Uma string no formato CSV representando os tarefas.
     */
    public static String toCSV(List<Tarefa> tarefas) {
        StringBuilder csv = new StringBuilder();

        // Adiciona cabeçalho
        csv.append("id,nome,descricao,responsavel,status\n");

        // Adiciona os dados de cada tarefa
        for (Tarefa tarefa : tarefas) {
            csv.append(tarefa.getId()).append(",");
            csv.append(tarefa.getNome()).append(",");
            csv.append(tarefa.getDescricao()).append(",");
            csv.append(tarefa.getResponsavel()).append(",");
            csv.append(tarefa.getStatus()).append("\n");
        }

        return csv.toString();
    }

    public static void main(String[] args) {
        TarefaDAO tarefaDAO = new TarefaDAO();
        List<Tarefa> tarefas = tarefaDAO.listarTarefas();
        System.out.println(toCSV(tarefas));
    }
}
