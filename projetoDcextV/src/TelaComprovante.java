import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaComprovante extends TelaBase {

    public TelaComprovante(String convenio, String regiaoDor, String tempoDor) {
        super("Comprovante de Atendimento");

        setSize(800, 700);
        setLocationRelativeTo(null);

        String senhaAtendimento = "A-" + String.format("%03d", (int)(Math.random() * 999) + 1);

        JPanel panel = criarPainelGradiente();
        setContentPane(panel);

        JPanel contentContainer = new JPanel();
        contentContainer.setOpaque(false);
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Comprovante de atendimento");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 40));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        String detalhes = "<html>"
                + "<body style='font-family: Montserrat; font-size: 14pt; color: #DDDDFF;'>"
                + "<b>Data/Hora:</b> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")) + "<br><br>"
                + "<b style='font-size: 16pt;'>Detalhes do Atendimento:</b><br>"
                + "<b>Convênio:</b> " + convenio + "<br>"
                + "<b>Região da Dor:</b> " + regiaoDor + "<br>"
                + "<b>Tempo da Dor:</b> " + tempoDor + "<br><br>"
                + "Prezado(a) paciente, seu atendimento foi registrado e você será encaminhado(a) ao profissional responsável."
                + "</body></html>";
        JLabel detalhesLabel = new JLabel(detalhes);
        detalhesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel senhaLabel = new JLabel("Sua Senha:");
        senhaLabel.setFont(new Font("Montserrat", Font.BOLD, 30));
        senhaLabel.setForeground(Color.WHITE);
        senhaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel senhaValor = new JLabel(senhaAtendimento);
        senhaValor.setFont(new Font("Montserrat", Font.BOLD, 80));
        senhaValor.setForeground(Color.WHITE);
        senhaValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        RoundedButton fecharBtn = new RoundedButton("Fechar", new Color(55, 48, 163), new Color(94, 23, 235), new Color(150, 100, 250));
        fecharBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fecharBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        fecharBtn.addActionListener(e -> {
            new TelaInicial().setVisible(true);
            dispose();
        });

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);

        contentContainer.add(titulo);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 40)));
        contentContainer.add(detalhesLabel);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 40)));
        contentContainer.add(senhaLabel);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        contentContainer.add(senhaValor);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 50)));
        contentContainer.add(fecharBtn);

        centerWrapper.add(contentContainer);

        panel.add(centerWrapper, BorderLayout.CENTER);
        adicionarNavegacao(panel, false, null);
    }
}