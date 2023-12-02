package inicial;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import back.BD;
import back.TarefaDAO;
import back.Tarefa;

/**
 * Classe que representa a tela de cadastro e alteração de Tarefa.
 */
public class TelaCadastroTarefa extends JFrame {

    public static final long serialVersionUID = 1L;
    public JLabel jcomp1;
    public JLabel txtId;
    public JLabel txtName;
    public JLabel txtDescricao;
    public JLabel txtResponsavel;
    public JLabel txtStatus;
//    public JLabel txtDataInicio;
//    public JLabel txtDataConclusao;
    public JLabel txtIdProjeto;

    public static JTextField compId;
    public static JTextField compNome;
    public static JTextField compDescricao;
    public static JTextField compResponsavel;
    public static JTextField compStatus;
//    public static JTextField compDataInicio;
//    public static JTextField compDataConclusao;
    public static JTextField compIdProjeto;

    public JButton btnMenuInicial;
    public JButton btnEstoque;
    public JButton btnAlterar;
    public JButton btnLocalizar;
    public JButton btnNovoCadastro;
    private JButton btnExcluir;
    private JTextField textCampoFeed;

    /**
     * Construtor que cria a tela de cadastro de Tarefa.
     */
    public TelaCadastroTarefa() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        jcomp1 = new JLabel("Cadastro / Alteração de Tarefa");
        txtId = new JLabel("Id:");
        txtName = new JLabel("Nome:");
        txtDescricao = new JLabel("Descrição:");
        txtResponsavel = new JLabel("Responsavel:");
        txtStatus = new JLabel("Status:");
//        txtDataInicio = new JLabel("Data Inicio:");
//        txtDataConclusao = new JLabel("Data Conclusão:");
        txtIdProjeto = new JLabel("Id do projeto:");

        compId = new JTextField(5);
        compNome = new JTextField(5);
        compDescricao = new JTextField(5);
        compResponsavel = new JTextField(5);
        compStatus = new JTextField(5);
//        compDataInicio = new JTextField(5);
//        compDataConclusao = new JTextField(5);
        compIdProjeto = new JTextField(5);

        btnMenuInicial = new JButton("Menu Incial");
        btnEstoque = new JButton("Estoque");
        btnAlterar = new JButton("Alterar");
        btnLocalizar = new JButton("Localizar");
        btnNovoCadastro = new JButton("Cadastrar Tarefa");
        btnExcluir = new JButton("Excluir");

        // Adjust size and set layout
        setPreferredSize(new Dimension(600, 300));
        contentPane.setLayout(null);

        // Add components
        contentPane.add(jcomp1);
        contentPane.add(txtId);
        contentPane.add(txtName);
        contentPane.add(txtResponsavel);
        contentPane.add(txtDescricao);
        contentPane.add(txtStatus);
//        contentPane.add(txtDataInicio);
//        contentPane.add(txtDataConclusao);
        contentPane.add(txtIdProjeto);

        contentPane.add(compId);
        contentPane.add(compNome);
        contentPane.add(compDescricao);
        contentPane.add(compResponsavel);
        contentPane.add(compStatus);
//        contentPane.add(compDataInicio);
//        contentPane.add(compDataConclusao);
        contentPane.add(compIdProjeto);

        contentPane.add(btnMenuInicial);
        contentPane.add(btnEstoque);
        contentPane.add(btnAlterar);
        contentPane.add(btnLocalizar);
        contentPane.add(btnNovoCadastro);
        contentPane.add(btnExcluir);

        // Set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(35, 25, 190, 50);

//        txtId.setEditable(false);
        txtId.setBounds(35, 90, 100, 25);
        txtName.setBounds(35, 120, 100, 25);
        txtDescricao.setBounds(35, 165, 100, 25);
        txtResponsavel.setBounds(35, 195, 100, 25);
        txtStatus.setBounds(35, 225, 100, 25);
//        txtDataInicio.setBounds(35, 255, 100, 25);
//        txtDataConclusao.setBounds(35, 282, 100, 25);
        txtIdProjeto.setBounds(35, 255, 100, 25);

        compId.setBounds(115, 90, 162, 25);
        compNome.setBounds(115, 120, 162, 40);
        compDescricao.setBounds(115, 165, 162, 25);
        compResponsavel.setBounds(115, 195, 162, 25);
        compStatus.setBounds(115, 225, 162, 25);
//        compDataInicio.setBounds(140, 255, 162, 25);
//        compDataConclusao.setBounds(140, 282, 162, 25);
        compIdProjeto.setBounds(115, 255, 162, 25);

        btnMenuInicial.setBounds(35, 340, 120, 30);
        btnEstoque.setBounds(165, 340, 120, 30);

        btnAlterar.setBounds(290, 170, 150, 30);
        btnLocalizar.setBounds(290, 130, 150, 30);
        btnNovoCadastro.setBounds(290, 90, 150, 30);
        btnExcluir.setBounds(290, 210, 150, 30);

        textCampoFeed = new JTextField();
        textCampoFeed.setBounds(50, 380, 380, 80);
        contentPane.add(textCampoFeed);
        textCampoFeed.setColumns(10);

        btnLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BD bd = new BD();
                bd.getConnection();
                String s = compId.getText();
                String sql = "SELECT * FROM tarefa WHERE id_tarefa LIKE ?";
                try {
                    bd.st = bd.con.prepareStatement(sql);
                    bd.st.setString(1, "%" + s + "%");
                    bd.rs = bd.st.executeQuery();

                    // Limpa os campos antes de exibir novos resultados
                    limparCampos();

                    // Adiciona os resultados aos campos
                    while (bd.rs.next()) {
                        compId.setText(Integer.toString(bd.rs.getInt(1)));
                        compNome.setText(bd.rs.getString(2));
                        compDescricao.setText(bd.rs.getString(3));
                        compResponsavel.setText(bd.rs.getString(4));
                        compStatus.setText(Double.toString(bd.rs.getDouble(5)));
                        compIdProjeto.setText(Integer.toString(bd.rs.getInt(6)));
                    }
                } catch (SQLException erro) {
                    System.out.println(erro);
                } finally {
                    bd.close();
                }
            }

            // Método para limpar os campos antes de exibir novos resultados
            private void limparCampos() {
                compId.setText("");
                compNome.setText("");
                compResponsavel.setText("");
                compDescricao.setText("");
                compStatus.setText("");
                compIdProjeto.setText("");
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TarefaDAO.excluir(Integer.parseInt(compId.getText()));
                String a = "Tarefa Excluido com Sucesso";
                textCampoFeed.setText(a);
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tarefa t = new Tarefa();

                t.setId(Integer.parseInt(compId.getText()));
                t.setNome(compNome.getText());
                t.setDescricao(compDescricao.getText());
                t.setResponsavel(compResponsavel.getText());
                t.setStatus(Double.parseDouble(compStatus.getText()));
//                t.setDataInicio(compDataInicio.getText());
//                t.setDataConclusao(compDataConclusao.getText());
                t.setIdProjeto(Integer.parseInt(compIdProjeto.getText()));

                TarefaDAO.alterar(t);
                textCampoFeed.setText("item alterado com Sucesso");
            }
        });

        btnMenuInicial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaInicial q = new TelaInicial();
                q.setVisible(true);
                dispose();
            }
        });

        btnNovoCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (compId == null) {
                    textCampoFeed.setText("Não colocar nada no ID, apenas preencha as outras informações!");
                } else {
                    Tarefa t = new Tarefa();

                    t.setNome(compNome.getText());
                    t.setDescricao(compDescricao.getText());
                    t.setResponsavel(compResponsavel.getText());
                    t.setStatus(Double.parseDouble(compStatus.getText()));
                    t.setIdProjeto(Integer.parseInt(compIdProjeto.getText()));

                    TarefaDAO.inserir(t);
                    textCampoFeed.setText("Tarefa Cadastrado com Sucesso");
                }
            }
        });
    }

    /**
     * Método principal para iniciar a aplicação.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastroTarefa frame = new TelaCadastroTarefa();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
