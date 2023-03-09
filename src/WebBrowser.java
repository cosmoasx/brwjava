import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class WebBrowser extends JFrame {

    private JTextField urlField;
    private JEditorPane displayPane;
    private JButton goButton;

    public WebBrowser() {
        super("fu");
        setSize(1920, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        urlField = new JTextField(40);
        displayPane = new JEditorPane();
        goButton = new JButton("Go");

        urlField.setText("https://google.com");
        displayPane.setEditable(false);

        urlField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPage(e.getActionCommand());
            }
        });

        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadPage(urlField.getText());
            }
        });

        displayPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    loadPage(e.getURL().toString());
                }
            }
        });

        JPanel topBar = new JPanel();

        topBar.add(urlField);
        topBar.add(goButton);

        add(topBar, BorderLayout.NORTH);
        add(new JScrollPane(displayPane), BorderLayout.CENTER);

        loadPage(urlField.getText());
    }

    private void loadPage(String url) {
        try {
            displayPane.setPage(url);
            urlField.setText(url);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid URL");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WebBrowser().setVisible(true);
            }
        });
    }
}