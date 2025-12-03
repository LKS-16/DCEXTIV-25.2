import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TelaDor extends TelaBase {

    public TelaDor(String convenio) {
        super("Região da Dor");

        JPanel panel = criarPainelGradiente();
        setContentPane(panel);
        panel.setLayout(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 150, 0, 150));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Onde está a sua dor ou desconforto?");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 55));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Toque na região do corpo onde sente desconforto ou utilize a câmera.");
        descricao.setFont(new Font("Montserrat", Font.PLAIN, 22));
        descricao.setForeground(new Color(220, 220, 240));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(titulo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        textPanel.add(descricao);
        panel.add(textPanel, BorderLayout.NORTH);

        JPanel botoesPanel = new JPanel(new GridBagLayout());
        botoesPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        String[] regioes = {"Cabeça", "Pescoço", "Ombros", "Peito", "Abdômen", "Costas", "Pernas", "Pés", "Corpo Inteiro"};
        for (int i = 0; i < regioes.length; i++) {
            String regiao = regioes[i];
            RoundedButton btn = new RoundedButton(regiao, new Color(55, 48, 163, 150), new Color(94, 23, 235, 100), new Color(150, 100, 250, 150));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            String nomeArquivo = regiao.toLowerCase().replace(" ", "");
            try {
                Image img = new ImageIcon(getClass().getResource("/resources/" + nomeArquivo + ".png")).getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(img));
            } catch (Exception e) { System.err.println("Imagem não encontrada: " + nomeArquivo + ".png"); }

            btn.setVerticalTextPosition(SwingConstants.BOTTOM);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setIconTextGap(15);
            btn.setFont(new Font("Montserrat", Font.BOLD, 20));

            btn.addActionListener(e -> {
                new TelaTempoDor(convenio, regiao).setVisible(true);
                dispose();
            });

            gbc.gridx = i % 3;
            gbc.gridy = i / 3;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10, 15, 10, 15);

            botoesPanel.add(btn, gbc);
        }
        panel.add(botoesPanel, BorderLayout.CENTER);

        adicionarNavegacao(panel, true, new TelaConvenio());
    }
}