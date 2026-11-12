package edu.utj.dsm.poo.gym.controlador;

import edu.utj.dsm.poo.gym.modelo.SesionUsuario;
import edu.utj.dsm.poo.gym.persistencia.UsuarioDAO;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblError;
    @FXML private Button btnLogin;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void handleLogin() {
        String user = txtUsuario.getText().trim();
        String pass = txtPassword.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            lblError.setText("Por favor ingresa usuario y contraseña.");
            return;
        }

        if (usuarioDAO.autenticar(user, pass)) {
            SesionUsuario sesion = SesionUsuario.getInstancia();
            String rol = sesion.getRol();

            try {
                switch (rol.toLowerCase()) {
                    case "administrador":
                    case "gerente":
                        App.setRoot("Administrador");
                        break;
                    case "propietario":
                        App.setRoot("Propietario");
                        break;
                    case "cliente":
                        App.setRoot("Cliente");
                        break;
                    case "entrenador":
                        App.setRoot("Entrenador");
                        break;
                    default:
                        lblError.setText("Rol no configurado: " + rol);
                        break;
                }
            } catch (IOException e) {
                lblError.setText("Error al cargar la interfaz de " + rol);
                e.printStackTrace();
            }
        } else {
            lblError.setText("Credenciales incorrectas o cuenta INACTIVA.");
        }
    }
}