/*
 * TelaCadastroProjeto é uma classe que representa a interface gráfica para cadastro,
 * alteração, localização e exclusão de projetos. Esta tela interage com o banco de dados
 * e utiliza a classe ProjetoDAO para realizar operações no banco.
 *
 * @author Michael Douglas
 * @version 1.0
 */

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
import back.Projeto;
import back.ProjetoDAO;

public class TelaCadastroProjeto extends JFrame {

    private static final long serialVersionUID = 1L;

    // Componentes da interface
    private JLabel jcomp1;
    private JLabel txtId;
    private JLabel txtNome;
    private JLabel txtDescricao;
    private JLabel txtOrcamento;
    private JLabel txtStatus;

    private JTextField compId;
    private JTextField compNome;
    private JTextField compDescricao;
    private JTextField compOrcamento;
    private JTextField compStatus;

    private JButton btnMenuInicial;
    private JButton btnEstoque;
    private JButton btnAlterar;
    private JButton btnLocalizar;
    private JButton btnNovoCadastro;
    private JButton btnExcluir;
    private JTextField textCampoFeed;

    /**
     * Construtor da classe TelaCadastroProjeto que inicializa e configura todos os componentes da interface.
     */
    public TelaCadastroProjeto() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        // Labels
        jcomp1 = new JLabel("Cadastro / Alteração de Projeto");
        txtId = new JLabel("Id:");
        txtNome = new JLabel("Nome:");
        txtOrcamento = new JLabel("Orcamento:");
        txtDescricao = new JLabel("Descrição:");
        txtStatus = new JLabel("Status:");

        // Campos de texto
        compId = new JTextField(5);
        compNome = new JTextField(5);
        compDescricao = new JTextField(5);
        compOrcamento = new JTextField(5);
        compStatus = new JTextField(5);

        // Botões
        btnMenuInicial = new JButton("Menu Inicial");
        btnEstoque = new JButton("Estoque");
        btnAlterar = new JButton("Alterar");
        btnLocalizar = new JButton("Localizar");
        btnNovoCadastro = new JButton("Cadastrar Projeto");
        btnExcluir = new JButton("Excluir");

        // Ajustar o tamanho e definir o layout
        setPreferredSize(new Dimension(600, 300));
        contentPane.setLayout(null);

        // Adicionar componentes
        contentPane.add(jcomp1);
        contentPane.add(txtId);
        contentPane.add(txtNome);
        contentPane.add(txtOrcamento);
        contentPane.add(txtDescricao);
        contentPane.add(txtStatus);

        contentPane.add(compId);
        contentPane.add(compNome);
        contentPane.add(compDescricao);
        contentPane.add(compOrcamento);
        contentPane.add(compStatus);

        contentPane.add(btnMenuInicial);
        contentPane.add(btnEstoque);
        contentPane.add(btnAlterar);
        contentPane.add(btnLocalizar);
        contentPane.add(btnNovoCadastro);
        contentPane.add(btnExcluir);

        // Definir as posições dos componentes
        jcomp1.setBounds(35, 25, 190, 50);

        txtId.setBounds(35, 90, 100, 25);
        txtNome.setBounds(35, 120, 100, 25);
        txtDescricao.setBounds(35, 165, 100, 25);
        txtOrcamento.setBounds(35, 195, 100, 25);
        txtStatus.setBounds(35, 225, 100, 25);

        compId.setBounds(105, 90, 162, 25);
        compNome.setBounds(105, 120, 162, 25);
        compDescricao.setBounds(105, 165, 162, 25);
        compOrcamento.setBounds(105, 195, 162, 25);
        compStatus.setBounds(105, 225, 162, 25);

        btnMenuInicial.setBounds(35, 295, 120, 30);
        btnEstoque.setBounds(165, 295, 120, 30);

        btnAlterar.setBounds(280, 170, 150, 30);
        btnLocalizar.setBounds(280, 130, 150, 30);
        btnNovoCadastro.setBounds(280, 90, 150, 30);
        btnExcluir.setBounds(280, 210, 150, 30);

        textCampoFeed = new JTextField();
        textCampoFeed.setBounds(56, 367, 420, 86);
        contentPane.add(textCampoFeed);

        textCampoFeed.setColumns(10);

        // Ações dos botões
        btnLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BD bd = new BD();
                bd.getConnection();
                String s = compId.getText();
                String sql = "select * from projeto where id_projeto like ?";
                try {
                    bd.st = bd.con.prepareStatement(sql);
                    bd.st.setString(1, "%" + s + "%");
                    bd.rs = bd.st.executeQuery();

                    // Limpar os campos antes de exibir novos resultados
                    limparCampos();

                    // Adicionar os resultados aos campos
                    while (bd.rs.next()) {
                        compId.setText(Integer.toString(bd.rs.getInt(1)));
                        compNome.setText(bd.rs.getString(2));
                        compDescricao.setText(bd.rs.getString(3));
                        compOrcamento.setText(Double.toString(bd.rs.getDouble(4)));
                        compStatus.setText(Double.toString(bd.rs.getDouble(5)));
                    }
                } catch (SQLException erro) {
                    System.out.println(erro);
                }
            }

            // Método para limpar os campos antes de exibir novos resultados
            private void limparCampos() {
                compId.setText("");
                compNome.setText("");
                compDescricao.setText("");
                compOrcamento.setText("");
                compStatus.setText("");
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjetoDAO.excluir(Integer.parseInt(compId.getText()));
                String a = "Projeto Excluido com Sucesso";
                textCampoFeed.setText(a);
            }
        });

        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Projeto p = new Projeto();

                p.setId(Integer.parseInt(compId.getText()));
                p.setNome(compNome.getText());
                p.setDescricao(compDescricao.getText());
                p.setOrcamento(Double.parseDouble(compOrcamento.getText()));
                p.setStatus(Double.parseDouble(compStatus.getText()));

                ProjetoDAO.alterar(p);
                textCampoFeed.setText("Projeto alterado com Sucesso");
            }
        });

        btnMenuInicial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaInicial q = new TelaInicial();
                q.setVisible(true);
                dispose();
            }
        });

        btnNovoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Projeto p = new Projeto();
                p.setNome(compNome.getText());
                p.setDescricao(compDescricao.getText());
                p.setOrcamento(Double.parseDouble(compOrcamento.getText()));
                p.setStatus(Double.parseDouble(compStatus.getText()));

                ProjetoDAO.inserir(p);
                textCampoFeed.setText("Projeto cadastrado com Sucesso");
            }
        });
    }

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando (não utilizado neste caso).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastroProjeto frame = new TelaCadastroProjeto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
