import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class TelaResumo extends TelaBase {

    // ---------- Construtor original (tempo como String) ----------
    public TelaResumo(String convenio, String regiaoDor, String tempoDor) {
        super("Resumo do Atendimento");
        criarInterfaceBasica(convenio, regiaoDor);

        // adiciona o painel com o tempo (mantendo compatibilidade)
        JPanel infoContainer = criarConteudoInfo(convenio, regiaoDor);
        infoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        infoContainer.add(criarPainelInfo("Tempo da Dor:", tempoDor));

        getContentPane().add(infoContainer, BorderLayout.CENTER);

        // botões inferiores
        adicionarBotoes(convenio, regiaoDor, tempoDor);
    }

    // ---------- Novo construtor (lista de sintomas) ----------
    public TelaResumo(String convenio, String regiaoDor, List<String> sintomasSelecionados) {
        super("Resumo do Atendimento");
        criarInterfaceBasica(convenio, regiaoDor);

        // adiciona o painel com os sintomas
        JPanel infoContainer = criarConteudoInfo(convenio, regiaoDor);
        infoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        infoContainer.add(criarPainelSintomas("Sintomas reportados:", sintomasSelecionados));

        getContentPane().add(infoContainer, BorderLayout.CENTER);

        // botões inferiores — envia a concatenação dos sintomas como string para o comprovante
        String sintomasConcat = String.join(", ", sintomasSelecionados);
        adicionarBotoes(convenio, regiaoDor, sintomasConcat);
    }

    // ---------- Método que cria a estrutura básica da janela ----------
    private void criarInterfaceBasica(String convenio, String regiaoDor) {
        JPanel panel = criarPainelGradiente();
        setContentPane(panel);
        panel.setLayout(new BorderLayout(0, 40));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 200, 0, 200));

        // cabeçalho
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Resumo do Atendimento");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 60));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Revise as informações fornecidas antes de gerar sua senha.");
        descricao.setFont(new Font("Montserrat", Font.PLAIN, 18));
        descricao.setForeground(new Color(220, 220, 240));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(titulo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        textPanel.add(descricao);
        panel.add(textPanel, BorderLayout.NORTH);
    }

    // ---------- Cria o container central com informações básicas ----------
    private JPanel criarConteudoInfo(String convenio, String regiaoDor) {
        JPanel infoContainer = new JPanel();
        infoContainer.setOpaque(false);
        infoContainer.setLayout(new BoxLayout(infoContainer, BoxLayout.Y_AXIS));

        infoContainer.add(criarPainelInfo("Convênio:", convenio));
        infoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        infoContainer.add(criarPainelInfo("Região da Dor:", regiaoDor));
        infoContainer.add(Box.createRigidArea(new Dimension(0, 20)));

        return infoContainer;
    }

    // ---------- Adiciona botões Editar / Concluir (reutilizável) ----------
    private void adicionarBotoes(String convenio, String regiaoDor, String tempoOuSintomas) {
        JPanel botoesAcaoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        botoesAcaoPanel.setOpaque(false);
        botoesAcaoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        RoundedButton editarBtn = new RoundedButton(
                "Editar Informações",
                new Color(220, 38, 38),
                new Color(239, 68, 68),
                new Color(252, 165, 165)
        );
        editarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            editarBtn.setIcon(new ImageIcon(
                    new ImageIcon(getClass().getResource("/resources/edit.png"))
                            .getImage()
                            .getScaledInstance(24, 24, Image.SCALE_SMOOTH)
            ));
        } catch (Exception ignored) {}

        editarBtn.addActionListener(e -> {
            // leva de volta ao começo do fluxo
            new TelaConvenio().setVisible(true);
            dispose();
        });

        RoundedButton concluirBtn = new RoundedButton(
                "Concluir e Gerar Senha",
                new Color(22, 163, 74),
                new Color(34, 197, 94),
                new Color(134, 239, 172)
        );
        concluirBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        try {
            concluirBtn.setIcon(new ImageIcon(
                    new ImageIcon(getClass().getResource("/resources/checkmark.png"))
                            .getImage()
                            .getScaledInstance(24, 24, Image.SCALE_SMOOTH)
            ));
        } catch (Exception ignored) {}

        // Ao concluir, passa os detalhes para TelaComprovante
        concluirBtn.addActionListener(e -> {
            new TelaComprovante(convenio, regiaoDor, tempoOuSintomas).setVisible(true);
            dispose();
        });

        botoesAcaoPanel.add(editarBtn);
        botoesAcaoPanel.add(concluirBtn);

        JPanel southContainer = new JPanel(new BorderLayout());
        southContainer.setOpaque(false);
        southContainer.add(botoesAcaoPanel, BorderLayout.NORTH);

        // adiciona ao painel root (que foi setado em criarInterfaceBasica)
        getContentPane().add(southContainer, BorderLayout.SOUTH);

        // navegação: voltar para TelaTempoDor (mantém compatibilidade)
        adicionarNavegacao(southContainer, true, new TelaTempoDor(convenio, regiaoDor));
    }

    // ---------- Painel de informação (Título + Valor) ----------
    private JPanel criarPainelInfo(String titulo, String valor) {
        JPanel infoPanel = new JPanel(new BorderLayout(20, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(55, 48, 163, 150));
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
                g2.setColor(new Color(150, 100, 250, 150));
                g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 30, 30));
                g2.dispose();
            }
        };
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        tituloLabel.setForeground(Color.WHITE);

        JLabel valorLabel = new JLabel(valor);
        valorLabel.setFont(new Font("Montserrat", Font.PLAIN, 24));
        valorLabel.setForeground(new Color(220, 220, 240));

        infoPanel.add(tituloLabel, BorderLayout.WEST);
        infoPanel.add(valorLabel, BorderLayout.CENTER);
        return infoPanel;
    }

    // ---------- Painel que mostra a lista de sintomas ----------
    private JPanel criarPainelSintomas(String titulo, List<String> sintomas) {
        JPanel sintPanel = new JPanel(new BorderLayout(20, 0)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(55, 48, 163, 150));
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
                g2.setColor(new Color(150, 100, 250, 150));
                g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 30, 30));
                g2.dispose();
            }
        };

        sintPanel.setOpaque(false);
        sintPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Montserrat", Font.BOLD, 24));
        tituloLabel.setForeground(Color.WHITE);

        JPanel lista = new JPanel();
        lista.setOpaque(false);
        lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));

        for (String s : sintomas) {
            JLabel item = new JLabel("• " + s);
            item.setFont(new Font("Montserrat", Font.PLAIN, 22));
            item.setForeground(new Color(220, 220, 240));
            lista.add(item);
        }

        sintPanel.add(tituloLabel, BorderLayout.NORTH);
        sintPanel.add(lista, BorderLayout.CENTER);

        return sintPanel;
    }
}
