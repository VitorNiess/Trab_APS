package visao;

/* Bibliotecas que serão necessárias*/
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import controle.*;
import modelo.*;
import persistencia.*;

public class VisaoUsuario extends JFrame {
    /* Cria atributos do tipo modelo e controle */
    private Aluno aluno = new Aluno();
    private Professor professor = new Professor();

    private PersistenciaAluno pAluno = new PersistenciaAluno();
    private PersistenciaProfessor pProfessor = new PersistenciaProfessor();

    private ControleUsuario cUsuario;
    //private ControleAluno cAluno;
    //private ControleProfessor cProfessor;
    

    /* Variaveis que serao usadas */
    String[] papelVetor = {"","Aluno","Professor"};
    String nome, sobrenome, email, dia, mes, ano, papel, id, senha, cSenha;
    int idAux = 0;
    boolean condicao;

    /* Atributo que vai guardar a única instância da interface */
    private static VisaoUsuario uniqueInstance;

    /* Paineis */
    JPanel jpanel_cabecalho = new JPanel();
    JPanel jpanel_fundo = new JPanel();
    JPanel jpanel_login = new JPanel();
    JPanel jpanel_cadastro = new JPanel();

    /* Botões */
    JButton bt_login = new JButton("LOGIN");
    JButton bt_juntese = new JButton("JUNTE-SE");
    JButton bt_continuar_cadastro = new JButton("CONTINUAR");
    JButton bt_continuar_login = new JButton("CONTINUAR");
    JButton bt_continuar_recupera = new JButton("CONTINUAR");
    JButton bt_perdi = new JButton("perdi o id");

    /* Labels */
    JLabel projeto = new JLabel("PROJETO");
    JLabel label_cadatro = new JLabel("CADASTRO");
    JLabel label_login = new JLabel("LOGIN");
    JLabel label_recuperar = new JLabel("RECUPERAR ID");
    JLabel label_nome = new JLabel("NOME:");
    JLabel label_sobrenome = new JLabel("SOBRENOME:");
    JLabel label_email = new JLabel("EMAIL:");
    JLabel label_dataNasc = new JLabel("DATA DE NASCIMENTO:");
    JLabel label_papel = new JLabel("FUNCAO:");
    JLabel label_id = new JLabel("ID:");
    JLabel label_senha = new JLabel("SENHA:");
    JLabel label_cSenha = new JLabel("CONFIRME A SENHA:");

    /* Caixas de Texto */
    JTextArea tArea_nome = new JTextArea();
    JTextArea tArea_sobrenome = new JTextArea();
    JTextArea tArea_email = new JTextArea();
    JTextArea tArea_id = new JTextArea();
    JTextArea tArea_senha = new JTextArea();
    JTextArea tArea_cSenha = new JTextArea();

    /*ComboBox */
    JComboBox <String> cbox_dia = new JComboBox<>(preencheVetor(32, 1, true));
    JComboBox <String>cbox_mes = new JComboBox<>(preencheVetor(13, 1, true));
    JComboBox <String> cbox_ano = new JComboBox<>(preencheVetor(151, 1874, false));
    JComboBox <String> cbox_papel = new JComboBox <String> (papelVetor);

    /* Fonte e Cores */
    Font texto_padrao = new Font("ARIAL",Font.BOLD,12);
    Font texto_singelo = new Font("ARIAL",Font.BOLD,12);
    Font texto_titulo = new Font("ARIAL",Font.BOLD,30);
    Font texto_sub_titulo = new Font("ARIAL",Font.BOLD,20);
    Color cor_fundo = new Color(194,255,240);
    Color cor_cabecalho = new Color(0,204,155);
    Color cor_textos = new Color(163, 184, 204);

    /* Contrução do JFrame que será usado */
    public VisaoUsuario(){
        setSize(750,600);
        setTitle("TP Analise e Projeto de Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /* Cria uma instancia única para essa interface (Padrao Singleton) */
    public static VisaoUsuario getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new VisaoUsuario();
		return uniqueInstance;
	}

    /* Interface do cabecalho */
    public void cabecalho(){
        /* Botões */
        bt_login.setFont(texto_padrao);
        bt_login.setBounds(520, 30,100,40);
        bt_login.setBackground(Color.white);
		bt_login.setForeground(Color.black);
        bt_login.addActionListener(this::vaiPLogin);

        bt_juntese.setFont(texto_padrao);
        bt_juntese.setBounds(625, 30,100,40);
        bt_juntese.setBackground(Color.white);
		bt_juntese.setForeground(Color.black);
        bt_juntese.addActionListener(this::vaiPCadastro);

        /* Labels */
        projeto.setFont(texto_titulo);
        projeto.setBounds(20, 30,150,50);

        /* Paineis */
        jpanel_cabecalho.setLayout(null);
        jpanel_cabecalho.setBackground(cor_cabecalho);
        jpanel_cabecalho.setSize(750, 100);
        jpanel_cabecalho.setLocation(0, 0);
        jpanel_cabecalho.setVisible(true);

        jpanel_fundo.setLayout(null);
        jpanel_fundo.setBackground(cor_fundo);
        jpanel_fundo.setSize(750, 600);
        jpanel_fundo.setLocation(0, 0);
        jpanel_fundo.setVisible(true);
        
        /* Adiciona os elementos no cabecalho, em seguida adiciona-o no fundo e adiciona o fundo */
        jpanel_cabecalho.add(bt_login);
        jpanel_cabecalho.add(bt_juntese);
        jpanel_cabecalho.add(projeto);
        
        add(jpanel_cabecalho);
        add(jpanel_fundo);
    }

    /* Interface para o cadastro */
    public void cadastro(Controle controle){
        setVisible(true);

        cabecalho();

        /* Botões */
        bt_continuar_cadastro.setFont(texto_padrao);
        bt_continuar_cadastro.setBounds(0,0,125,40);
		bt_continuar_cadastro.setLocation(187, 375);
        bt_continuar_cadastro.setBackground(Color.white);
		bt_continuar_cadastro.setForeground(Color.black);
        bt_continuar_cadastro.addActionListener(this::continuarCadastro);

        /* Labels */
        label_cadatro.setFont(texto_sub_titulo);
        label_cadatro.setBounds(187, 25,125,50);

        label_nome.setFont(texto_padrao);
        label_nome.setBounds(37, 110,175,50);

        label_sobrenome.setFont(texto_padrao);
        label_sobrenome.setBounds(37, 140,175,50);

        label_email.setFont(texto_padrao);
        label_email.setBounds(37, 170,175,50);

        label_dataNasc.setText("DATA DE NASCIMENTO");
        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(37, 200,175,50);

        label_papel.setFont(texto_padrao);
        label_papel.setBounds(37, 230,175,50);

        label_senha.setFont(texto_padrao);
        label_senha.setBounds(37, 260,175,50);

        label_cSenha.setFont(texto_padrao);
        label_cSenha.setBounds(37, 290,175,50);

        /* Caixas de texto */
        tArea_nome.setFont(texto_padrao);
        tArea_nome.setBounds(218, 125,250,25);
        tArea_nome.setBackground(cor_textos);

        tArea_sobrenome.setFont(texto_padrao);
        tArea_sobrenome.setBounds(218, 155,250,25);
        tArea_sobrenome.setBackground(cor_textos);

        tArea_email.setFont(texto_padrao);
        tArea_email.setBounds(218, 185,250,25);
        tArea_email.setBackground(cor_textos);

        tArea_senha.setFont(texto_padrao);
        tArea_senha.setBounds(218, 275,250,25);
        tArea_senha.setBackground(cor_textos);

        tArea_cSenha.setFont(texto_padrao);
        tArea_cSenha.setBounds(218, 305,250,25);
        tArea_cSenha.setBackground(cor_textos);

        /* ComboBOXes */
        cbox_dia.setBounds(218, 215,80,25);

        cbox_mes.setBounds(303, 215,80,25);

        cbox_ano.setBounds(388, 215,80,25);

        cbox_papel.setBounds(218, 245,250,25);

        /* Painel */   
        jpanel_cadastro.setLayout(null);
        jpanel_cadastro.setBackground(Color.WHITE);
        jpanel_cadastro.setSize(500, 500);
        jpanel_cadastro.setLocation(125, 100);
        jpanel_cadastro.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_cadastro.add(label_cadatro);
        jpanel_cadastro.add(label_nome);
        jpanel_cadastro.add(tArea_nome);
        jpanel_cadastro.add(label_sobrenome);
        jpanel_cadastro.add(tArea_sobrenome);
        jpanel_cadastro.add(label_email);
        jpanel_cadastro.add(tArea_email);
        jpanel_cadastro.add(label_dataNasc);
        jpanel_cadastro.add(cbox_dia);
        jpanel_cadastro.add(cbox_mes);
        jpanel_cadastro.add(cbox_ano);
        jpanel_cadastro.add(label_papel);
        jpanel_cadastro.add(cbox_papel);
        jpanel_cadastro.add(label_senha);
        jpanel_cadastro.add(tArea_senha);
        jpanel_cadastro.add(label_cSenha);
        jpanel_cadastro.add(tArea_cSenha);
        jpanel_cadastro.add(bt_continuar_cadastro);

        
        jpanel_fundo.add(jpanel_cadastro);
    }


    /* Interface para o cadastro */
    public void login(Controle controle){
        setVisible(true);

        cabecalho();

        /* Botões */
        bt_continuar_login.setFont(texto_padrao);
        bt_continuar_login.setBounds(125, 325,125,25);
        bt_continuar_login.setBackground(Color.white);
		bt_continuar_login.setForeground(Color.black);
        bt_continuar_login.addActionListener(this::continuarLogin);

        bt_perdi.setFont(texto_singelo);
        bt_perdi.setBounds(125, 360,125,25);
        bt_perdi.setBorderPainted(false);;
        bt_perdi.setBackground(Color.white);
		bt_perdi.setForeground(cor_cabecalho);
        bt_perdi.addActionListener(this::perdiId);

        /* Labels */
        label_login.setFont(texto_sub_titulo);
        label_login.setBounds(152, 100,70,50);

        label_id.setFont(texto_padrao);
        label_id.setBounds(62, 175,100,50);

        label_senha.setFont(texto_padrao);
        label_senha.setBounds(62, 210,100,50);

        label_papel.setFont(texto_padrao);
        label_papel.setBounds(62, 245,100,50);

        /* Caixas de texto */
        tArea_id.setFont(texto_padrao);
        tArea_id.setBounds(162, 185,150,25);
        tArea_id.setBackground(cor_textos);

        tArea_senha.setFont(texto_padrao);
        tArea_senha.setBounds(162, 220,150,25);
        tArea_senha.setBackground(cor_textos);

        /* ComboBoxes */
        cbox_papel.setBounds(162, 255,150,25);

        /* Paineis */
        jpanel_login.setLayout(null);
        jpanel_login.setBackground(Color.WHITE);
        jpanel_login.setSize(375, 500);
        jpanel_login.setLocation(375, 100);
        jpanel_login.setVisible(true);

        /* Adiciona elementos no painel */
        jpanel_login.add(bt_continuar_login);
        jpanel_login.add(bt_perdi);
        jpanel_login.add(label_login);
        jpanel_login.add(label_id);
        jpanel_login.add(tArea_id);
        jpanel_login.add(label_senha);
        jpanel_login.add(tArea_senha);
        jpanel_login.add(label_papel);
        jpanel_login.add(cbox_papel);

        jpanel_fundo.add(jpanel_login);

    }



    /* Ações dos botões */
    /* Acoes do botao continuar do cadastro */
    private void continuarCadastro(ActionEvent actionEvent){
        /* Atribuicao  dos valores no texto para salvar nas variaveis locais*/
        nome = tArea_nome.getText();
        sobrenome = tArea_sobrenome.getText();
        email = tArea_email.getText();
        dia = cbox_dia.getSelectedItem()+"";
        mes = cbox_mes.getSelectedItem()+"";
        ano = cbox_ano.getSelectedItem()+"";
        senha = tArea_senha.getText();
        papel = cbox_papel.getSelectedItem()+"";
        cSenha = tArea_cSenha.getText();

        /* expressão para saber se os dados do usuario fotam preenchidas */
        condicao = nome.equals("") || sobrenome.equals("") || email.equals("") || dia.equals("") ||
        mes.equals("") || ano.equals("") || senha.equals("") || papel.equals("") || cSenha.equals("");
        if(condicao)
            /* Exibe a mensagem de erro */
            JOptionPane.showMessageDialog(null,"Informação faltando", "ERRO",JOptionPane.ERROR_MESSAGE);
        else{
            /* Compara se as senhas digitas são iguais. Em caso de serem diferentes, imprime uma 
             * mensagem de erro. Do contrário prossegue com a inserção */
            if(!(senha.equals(cSenha) && !(senha.equals(""))))
                JOptionPane.showMessageDialog(null,"Senhas divergem", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{

                /* Insere o usuário de acordo com a funcao selecionada */
                if(papel.equals("Aluno")){
                    /* Pega os dados digitados e insere o usuário no sistema */
                    aluno.setNome(nome);
                    aluno.setSobrenome(sobrenome);
                    aluno.setEmail(email);
                    aluno.setDiaNasc(Integer.parseInt(dia));
                    aluno.setMesNasc(Integer.parseInt(mes));
                    aluno.setAnoNasc(Integer.parseInt(ano));
                    aluno.setSenha(senha);

                    /* Insere o aluno no sistema */
                    pAluno.insere(aluno);

                    /* Uma mensagem de sucesso aparece e apresenta o id do usuário */
                    JOptionPane.showMessageDialog(null,"Parabéns!! Você foi cadastrado com sucesso.\nSeu id é: "+ pAluno.devolveMaiorID(), "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    /* Pega os dados digitados e insere o usuário no sistema */
                    professor.setNome(nome);
                    professor.setSobrenome(sobrenome);
                    professor.setEmail(email);
                    professor.setDiaNasc(Integer.parseInt(dia));
                    professor.setMesNasc(Integer.parseInt(mes));
                    professor.setAnoNasc(Integer.parseInt(ano));
                    professor.setSenha(senha);

                    /* Insere o professor no sistema */
                    pProfessor.insere(professor);

                    /* Uma mensagem de sucesso aparece e apresenta o id do usuário */
                    JOptionPane.showMessageDialog(null,"Parabéns!! Você foi cadastrado com sucesso.\nSeu id é: "+ pProfessor.devolveMaiorID(), "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                }
        
                
                /* Deixa as caixas de texto em branco */
                tArea_nome.setText("");
                tArea_nome.requestFocus();
                tArea_sobrenome.setText("");
                tArea_sobrenome.requestFocus();
                tArea_email.setText("");
                tArea_email.requestFocus();
                cbox_dia.setSelectedItem("");
                cbox_mes.setSelectedItem("");
                cbox_ano.setSelectedItem("");
                cbox_papel.setSelectedItem("");
                tArea_senha.setText("");
                tArea_senha.requestFocus();
                tArea_cSenha.setText("");
                tArea_cSenha.requestFocus();

                /* Chama a funcao que redireciona para o login */
                vaiPLogin(actionEvent);
            }
        }
    }

    /* Acoes do botao continuar do login */
    public void continuarLogin(ActionEvent actionEvent){
        /* Armazena o id e a senha em uma variavel local, que sera usada para comparacao */
        id = tArea_id.getText();
        senha = tArea_senha.getText();

        /* Variável que converte o item do ComboBox para texto, especificamente da funcao */
        String aux = cbox_papel.getSelectedItem()+"";

        if(id.equals(""))
            id = "0";

        /* Insere o usuário de acordo com a funcao selecionada */
        if(aux.equals("Aluno")){
            aluno = pAluno.buscaID(Integer.parseInt(id));
            
            /* Se o id for igual a 0, o usuario nao existe e uma mensagem de erro eh exibida */
            if(aluno.getId()==0)
                JOptionPane.showMessageDialog(null,"ID não encontrado", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Caso a senha digitada nao seja igual a armazenada pelo usuario, exibe mensagem de erro.
                 * Caso seja igual imprime uma mensagem de sucesso e redireciona para a pagina do usuario. */
                if(!(aluno.getSenha().equals(senha)))
                    JOptionPane.showMessageDialog(null,"Senha incorreta", "ERRO",JOptionPane.ERROR_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(null,"Seja bem-vinde " + aluno.getNome(), "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    continuarUsuario(true, aluno);
                }
            }
                
        }
        else{
           professor = pProfessor.buscaID(Integer.parseInt(id));
            /* Se o id for igual a 0, o usuario nao existe e uma mensagem de erro eh exibida */
            if(professor.getId()==0)
                JOptionPane.showMessageDialog(null,"ID não encontrado", "ERRO",JOptionPane.ERROR_MESSAGE);
            else{
                /* Caso a senha digitada nao seja igual a armazenada pelo usuario, exibe mensagem de erro.
                 * Caso seja igual imprime uma mensagem de sucesso e redireciona para a pagina do usuario. */
                if(professor.getSenha().equals(senha)){
                    JOptionPane.showMessageDialog(null,"Seja bem-vinde " + professor.getNome(), "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    continuarUsuario(false, professor);
                }
                else
                    JOptionPane.showMessageDialog(null,"Senha incorreta", "ERRO",JOptionPane.ERROR_MESSAGE);
            } 
        }
    }

    /* Funcao que redireciona o usuario para a sua pagina apos o login */
    public void continuarUsuario(Boolean tipoUsuario, Entidade entidade){
        tArea_id.setText("");
        tArea_id.requestFocus();
        tArea_senha.setText("");
        tArea_senha.requestFocus();
        cbox_papel.setSelectedItem("");

        /* Exclui o layout do cadastro e do login */
        jpanel_login.remove(bt_continuar_login);
        jpanel_login.remove(label_login);
        jpanel_login.remove(label_id);
        jpanel_login.remove(tArea_id);
        jpanel_login.remove(label_senha);
        jpanel_login.remove(tArea_senha);
        jpanel_login.remove(label_papel);
        jpanel_login.remove(cbox_papel);

        jpanel_fundo.remove(jpanel_login);

        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(projeto);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        setVisible(false);

        /* Considerando true para Aluno e false para professor, uma comparacao eh feita e 
         * leva o usuario para sua pagina correspondente de acordo com seu papel */
        if(tipoUsuario)
            VisaoAluno.getInstance().paginaAluno(entidade);
        else
            VisaoProfessor.getInstance().paginaProfessor(entidade);
    }

    public void perdiId(ActionEvent actionEvent){
        setVisible(false);
        /* Deixa as caixas de texto em branco */
        tArea_id.setText("");
        tArea_id.requestFocus();
        tArea_senha.setText("");
        tArea_senha.requestFocus();
        cbox_papel.setSelectedItem("");

        /* Apaga tudo do painel */
		jpanel_login.remove(bt_continuar_login);
		jpanel_login.remove(bt_perdi);
        jpanel_login.remove(label_login);
        jpanel_login.remove(label_id);
        jpanel_login.remove(tArea_id);
        jpanel_login.remove(label_senha);
        jpanel_login.remove(tArea_senha);
        jpanel_login.remove(label_papel);
        jpanel_login.remove(cbox_papel);

        /* cria novo layout */

        /* Botoes*/
        bt_continuar_recupera.setFont(texto_padrao);
        bt_continuar_recupera.setBounds(125, 325,125,25);
        bt_continuar_recupera.setBackground(Color.white);
		bt_continuar_recupera.setForeground(Color.black);
        bt_continuar_recupera.addActionListener(this::continuarRecuperacao);

        /* Labels */
        label_recuperar.setFont(texto_sub_titulo);
        label_recuperar.setBounds(100, 100,175,50);

        label_email.setFont(texto_padrao);
        label_email.setBounds(40, 175,100,50);

        label_dataNasc.setText("DATA NASC.:");
        label_dataNasc.setFont(texto_padrao);
        label_dataNasc.setBounds(40, 210,100,50);

        label_papel.setFont(texto_padrao);
        label_papel.setBounds(40, 245,100,50);

        /* Caixas de texto */
        tArea_email.setFont(texto_padrao);
        tArea_email.setBounds(140, 185,190,25);
        tArea_email.setBackground(cor_textos);

        /* ComboBoxes */
        cbox_papel.setBounds(140, 255,190,25);

        cbox_dia.setBounds(140, 220,60,25);

        cbox_mes.setBounds(205, 220,60,25);

        cbox_ano.setBounds(270, 220,60,25);
        
        /* Adicionar no painel */
        jpanel_login.add(label_recuperar);
        jpanel_login.add(label_email);
        jpanel_login.add(label_dataNasc);
        jpanel_login.add(label_papel);
        jpanel_login.add(tArea_email);
        jpanel_login.add(cbox_papel);
        jpanel_login.add(cbox_dia);
        jpanel_login.add(cbox_mes);
        jpanel_login.add(cbox_ano);
        jpanel_login.add(bt_continuar_recupera);
        setVisible(true);

    }

    /* Funcao do botao que prossegue na recuperacao da senha */
    public void continuarRecuperacao(ActionEvent actionEvent){
        idAux = 0;
        /* Pega os dados digitados */
        email = tArea_email.getText();
        dia = cbox_dia.getSelectedItem()+"";
        mes = cbox_mes.getSelectedItem()+"";
        ano = cbox_ano.getSelectedItem()+"";
        papel = cbox_papel.getSelectedItem()+"";

        /* Se algum dado estiver em branco, retorna mensagem de erro */
        if(email.equals("") || dia.equals("") || mes.equals("") || ano.equals("") || papel.equals(""))
            JOptionPane.showMessageDialog(null,"Informação faltando", "ERRO",JOptionPane.ERROR_MESSAGE);
        else{
            if(papel.equals("Aluno")){
                idAux = pAluno.devolveIdPerdido(email, Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano));
                if(idAux ==0 )
                    JOptionPane.showMessageDialog(null,"Dados divergem", "ERRO",JOptionPane.ERROR_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(null,"Seu id é: "+ idAux + "\nFavor nao perde-lo de novo", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    /* exlui td */
                    limpaRecuperacao();
                }
            }
            else{
                idAux = pProfessor.devolveIdPerdido(email, Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano));
                if(idAux ==0 )
                    JOptionPane.showMessageDialog(null,"i", "ERRO",JOptionPane.ERROR_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(null,"Seu id é: "+ idAux + "\nFavor nao perde-lo de novo", "SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                    /* exlui td */
                    limpaRecuperacao();
                }
            }
        }

            
    }

   

    /* Funcao que remove tudo do painel e vai para o cdastro */    
    public void vaiPLogin(ActionEvent actionEvent){
        /* Deixa a caixa de texto de algumas das caixas de texto */
        tArea_email.setText("");
        tArea_email.requestFocus();
        cbox_dia.setSelectedItem("");
        cbox_mes.setSelectedItem("");
        cbox_ano.setSelectedItem("");
        cbox_papel.setSelectedItem("");
        tArea_senha.setText("");
        tArea_senha.requestFocus();
        tArea_cSenha.setText("");
        tArea_cSenha.requestFocus();

        /* remove tudo do painel */
        jpanel_cadastro.remove(label_cadatro);
        jpanel_cadastro.remove(label_nome);
        jpanel_cadastro.remove(tArea_nome);
        jpanel_cadastro.remove(label_sobrenome);
        jpanel_cadastro.remove(tArea_sobrenome);
        jpanel_cadastro.remove(label_email);
        jpanel_cadastro.remove(tArea_email);
        jpanel_cadastro.remove(label_dataNasc);
        jpanel_cadastro.remove(cbox_dia);
        jpanel_cadastro.remove(cbox_mes);
        jpanel_cadastro.remove(cbox_ano);
        jpanel_cadastro.remove(label_papel);
        jpanel_cadastro.remove(cbox_papel);
        jpanel_cadastro.remove(label_senha);
        jpanel_cadastro.remove(tArea_senha);
        jpanel_cadastro.remove(label_cSenha);
        jpanel_cadastro.remove(tArea_cSenha);
        jpanel_cadastro.remove(bt_continuar_cadastro);

        jpanel_fundo.remove(jpanel_cadastro);

        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(projeto);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        setVisible(false);

        /* Manda pro login */
        login(cUsuario);
    }

    /* Funcao que remove tudo do painel e vai para o cdastro */
    public void vaiPCadastro(ActionEvent actionEvent){
        /* Deixa as caixas de texto em branco */
        tArea_id.setText("");
        tArea_id.requestFocus();
        tArea_senha.setText("");
        tArea_senha.requestFocus();
        cbox_papel.setSelectedItem("");

        /* Apaga tudo do painel */
		jpanel_login.remove(bt_continuar_login);
		jpanel_login.remove(bt_perdi);
        jpanel_login.remove(label_login);
        jpanel_login.remove(label_id);
        jpanel_login.remove(tArea_id);
        jpanel_login.remove(label_senha);
        jpanel_login.remove(tArea_senha);
        jpanel_login.remove(label_papel);
        jpanel_login.remove(cbox_papel);

        jpanel_fundo.remove(jpanel_login);

        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(projeto);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        setVisible(false);

        /* Manda pro cadastro */
        cadastro(cUsuario);
    }

    /* Funcao que remove o Layout da recuperacao e volta pro login */
    public void limpaRecuperacao(){
        /* Deixa as caixas de texto em branco */
        tArea_email.setText("");
        tArea_email.requestFocus();
        cbox_dia.setSelectedItem("");
        cbox_mes.setSelectedItem("");
        cbox_ano.setSelectedItem("");
        cbox_papel.setSelectedItem("");

        /* Remove Layout da recuperacao */
        jpanel_login.remove(label_recuperar);
        jpanel_login.remove(label_email);
        jpanel_login.remove(label_dataNasc);
        jpanel_login.remove(label_papel);
        jpanel_login.remove(tArea_email);
        jpanel_login.remove(cbox_papel);
        jpanel_login.remove(cbox_dia);
        jpanel_login.remove(cbox_mes);
        jpanel_login.remove(cbox_ano);
        jpanel_login.remove(bt_continuar_recupera);

        jpanel_fundo.remove(jpanel_cadastro);

        jpanel_cabecalho.remove(bt_login);
        jpanel_cabecalho.remove(bt_juntese);
        jpanel_cabecalho.remove(projeto);

        remove(jpanel_cabecalho);
        remove(jpanel_fundo);

        setVisible(false);

        /* Manda pro login */
        login(cUsuario);
    }


    /* Tamanho dos vetores para o dia, mês e ano */
    public String[] preencheVetor(int tamanho, int comeco, boolean orientacao) {

        /* Cria um vetor que vai armazenar os tempo correspondente e coloca o primeiro elemento*/
        String[] vetor = new String[tamanho];

        /* Deixa a primeira posição como null */
        vetor[0]= "";

        /* Preeche os elemetos seguintes até o fim do tamanho se acordo com a orientacao dada
         * se for TRUE é crescente, se for FALSE é descrescente*/
        if(orientacao){
            for(int i = 1; i<tamanho ;i++){
                vetor[i] = Integer.toString(comeco + i - 1);
            }
        }
        else{
            for(int i = 1; i<tamanho ;i++){
                vetor[i] = Integer.toString(comeco + tamanho - i - 1);
            }
        }
        /* Retorna o vetor */
        return vetor;
    }
}