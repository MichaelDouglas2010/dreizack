package inicial;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

/**
 * Classe que representa a tela inicial da aplicação.
 */
public class TelaInicial extends JFrame {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Método principal que inicia a aplicação.
	 * 
	 * @param args os argumentos da linha de comando
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Cria uma instância da TelaInicial e a torna visível
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Cria a tela inicial.
	 */
	public TelaInicial() {
		/*
		 * Configurações básicas do JFrame
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 347);
		
		/*
		 * Configuração do painel de conteúdo
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		/*
		 * Botões da tela inicial
		 */
		JButton btnUsuario = new JButton("Usuario");
		btnUsuario.setBounds(50, 175, 220, 35);
		JButton btnTarefas = new JButton("Tarefas");
		btnTarefas.setBounds(50, 220, 220, 35);
		JButton btnProjetos = new JButton("Projetos");
		btnProjetos.setBounds(50, 130, 220, 35);
		contentPane.setLayout(null);
		contentPane.add(btnUsuario);
		contentPane.add(btnTarefas);
		contentPane.add(btnProjetos);

		/*
		 * ActionListener para o botão Usuario
		 */
		btnUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ao clicar, cria uma instância da TelaCadastroUsuario e a torna visível
				TelaCadastroUsuario q = new TelaCadastroUsuario();
				dispose(); // Fecha a tela atual
				q.setVisible(true);
			}
		});

		/*
		 * ActionListener para o botão Projetos
		 */
		btnProjetos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ao clicar, cria uma instância da TelaCadastroProjeto e a torna visível
				TelaCadastroProjeto q = new TelaCadastroProjeto();
				dispose(); // Fecha a tela atual
				q.setVisible(true);
			}
		});
		
		/*
		 * ActionListener para o botão Tarefas
		 */
		btnTarefas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ao clicar, cria uma instância da TelaCadastroTarefa e a torna visível
				TelaCadastroTarefa q = new TelaCadastroTarefa();
				dispose(); // Fecha a tela atual
				q.setVisible(true);
			}
		});
	}
}
