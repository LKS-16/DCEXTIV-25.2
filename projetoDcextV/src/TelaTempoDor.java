import javax.swing.*;
import java.awt.*;

public class TelaTempoDor extends TelaBase {

    private String convenio;
    private String regiao;

    public TelaTempoDor(String convenio, String regiao) {
        super("Tempo da Dor");

        this.convenio = convenio;
        this.regiao = regiao;

        JPanel panel = criarPainelGradiente();
        setContentPane(panel);
        panel.setLayout(new BorderLayout(0, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));

        // Título
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Há quanto tempo a dor começou?");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 48));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descricao = new JLabel("Selecione uma opção que melhor descreve a duração da dor.");
        descricao.setFont(new Font("Montserrat", Font.PLAIN, 20));
        descricao.setForeground(new Color(220, 220, 240));
        descricao.setAlignmentX(Component.CENTER_ALIGNMENT);

        textPanel.add(titulo);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(descricao);
        panel.add(textPanel, BorderLayout.NORTH);

        // Opções de tempo
        String[] tempos = {
                "Menos de 24 horas", "1 a 3 dias", "4 a 7 dias",
                "1 a 2 semanas", "Mais de 15 dias", "Mais de 1 mês"
        };

        JPanel botoesPanel = new JPanel(new GridBagLayout());
        botoesPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        for (int i = 0; i < tempos.length; i++) {
            String tempo = tempos[i];

            RoundedButton btn = new RoundedButton(
                    tempo,
                    new Color(55, 48, 163, 180),
                    new Color(94, 23, 235, 120),
                    new Color(150, 100, 250, 150)
            );

            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFont(new Font("Montserrat", Font.BOLD, 20));
            btn.setPreferredSize(new Dimension(280, 110));

            btn.addActionListener(e -> {
                new TelaSintomas(convenio, regiao).setVisible(true);
                dispose();
            });

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            gbc.insets = new Insets(12, 22, 12, 22);
            gbc.fill = GridBagConstraints.BOTH;

            botoesPanel.add(btn, gbc);
        }

        panel.add(botoesPanel, BorderLayout.CENTER);

        adicionarNavegacao(panel, true, new TelaDor(convenio));
    }
}
