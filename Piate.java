import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class Piate {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zawieszone UI");
        JButton startBtn = new JButton("Start");
        JLabel statusLabel = new JLabel("Gotowe");

        startBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void, String>() {
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i < 5; i++) {
                            Thread.sleep(1000);
                            publish("Pracuję... " + (i + 1) + "s");
                        }
                        return null;
                    }

                    protected void process(List<String> chunks) {
                        statusLabel.setText(chunks.get(chunks.size() - 1));
                    }

                    protected void done() {
                        statusLabel.setText("Zakończono pomyślnie!");
                    }
                }.execute();
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(startBtn);
        frame.add(statusLabel);
        frame.setSize(300,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}