package painel;

import java.util.List;

import back.Tarefa;

public interface TarefaDAO {
    List<Tarefa> consultarTarefas();
    void cadastrarTarefa(Tarefa tarefa);
    void editarTarefa(Tarefa tarefa);
    void excluirTarefa(Tarefa tarefa);
}