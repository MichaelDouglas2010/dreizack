 package painel;


import java.util.List;

import back.Projeto;

public interface ProjetoDAO {
    List<Projeto> consultarProjetos();
    void cadastrarProjeto(Projeto projeto);
    void editarProjeto(Projeto projeto);
    void excluirProjeto(Projeto projeto);
}