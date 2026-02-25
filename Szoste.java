import javax.swing.*;

class UzytkownikModel {
    public boolean walidujLogowanie(String user, String pass) {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {}
        if(user.equals("admin") && pass.equals("haslo123")) {
            return true;
        }
        else {
            return false;
        }
    }
}

class GlownyView {
    JFrame frame;
    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;
    JLabel statusLabel;

    public GlownyView() {
        frame = new JFrame("Logowanie");
        userField = new JTextField(10);
        passField = new JPasswordField(10);
        loginBtn = new JButton("Zaloguj");
        statusLabel = new JLabel("Panel Logowania");

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(new JLabel("Login:"));
        frame.add(userField);
        frame.add(new JLabel("Hasło:"));
        frame.add(passField);
        frame.add(loginBtn);
        frame.add(statusLabel);

        frame.setSize(500,250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class GlownyController {
    private UzytkownikModel model;
    private GlownyView view;

    public GlownyController(UzytkownikModel model, GlownyView view) {
        this.model = model;
        this.view = view;
        view.loginBtn.addActionListener(e -> zaloguj());
    }

    private void zaloguj() {
        view.loginBtn.setEnabled(false);
        view.statusLabel.setText("Trwa weryfikacja danych...");

        new SwingWorker<Boolean, Void>() {
            protected Boolean doInBackground() throws Exception {
                return model.walidujLogowanie(view.userField.getText(), new String(view.passField.getPassword()));
            }

            protected void done() {
                try {
                    boolean wynik = get();
                    if (wynik) view.statusLabel.setText("Logowanie pomyślne!");
                    else view.statusLabel.setText("Błędny login lub hasło!");
                } catch (Exception e) {
                    view.statusLabel.setText("Błąd logowania");
                }
                view.loginBtn.setEnabled(true);
            }
        }.execute();
    }
}

public class Szoste {
    public static void main(String[] args) {
        UzytkownikModel model = new UzytkownikModel();
        GlownyView view = new GlownyView();
        new GlownyController(model, view);
    }
}