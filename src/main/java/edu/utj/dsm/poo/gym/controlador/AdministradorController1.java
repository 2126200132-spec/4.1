package edu.utj.dsm.poo.gym.controlador;

import edu.utj.dsm.poo.gym.modelo.Administrador;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdministradorController1 implements Initializable {

    // Componentes FXML del Formulario
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> cmbRol;
    @FXML private ComboBox<String> cmbEstado;

    // Mensajes de Validación
    @FXML private Label lblErrorId;
    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorEmail;
    @FXML private Label lblErrorRol;
    @FXML private Label lblErrorEstado;

    // Tabla de Datos y Columnas
    @FXML private TableView<Administrador> tblAdministradores;
    @FXML private TableColumn<Administrador, Integer> colId;
    @FXML private TableColumn<Administrador, String> colNombre;
    @FXML private TableColumn<Administrador, String> colEmail;
    @FXML private TableColumn<Administrador, String> colRol;
    @FXML private TableColumn<Administrador, String> colEstado;

    // Botones de Acción
    @FXML private Button btnNuevo;
    @FXML private Button btnValidar;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;

    // Lista Observable para la tabla
    private final ObservableList<Administrador> listaAdministradores = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Enlace de las columnas de la tabla
        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colNombre != null) colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        if (colEmail != null) colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        if (colRol != null) colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        if (colEstado != null) colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Opciones de los ComboBox
        if (cmbRol != null) {
            cmbRol.setItems(FXCollections.observableArrayList("Administrador", "Gerente", "Recepcionista", "Soporte"));
        }
        if (cmbEstado != null) {
            cmbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        }

        if (tblAdministradores != null) {
            tblAdministradores.setItems(listaAdministradores);

            // Listener de selección
            tblAdministradores.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                boolean seleccionado = (newVal != null);
                if (btnEditar != null) btnEditar.setDisable(!seleccionado);
                if (btnEliminar != null) btnEliminar.setDisable(!seleccionado);

                if (newVal != null) {
                    if (txtId != null) txtId.setText(String.valueOf(newVal.getId()));
                    if (txtNombre != null) txtNombre.setText(newVal.getNombre());
                    if (txtEmail != null) txtEmail.setText(newVal.getEmail());
                    if (cmbRol != null) cmbRol.setValue(newVal.getRol());
                    if (cmbEstado != null) cmbEstado.setValue(newVal.getEstado());
                }
            });
        }

        configurarListenersValidacion();
        restablecerEstadoBotones();
    }

    private void configurarListenersValidacion() {
        if (txtId != null) txtId.textProperty().addListener((obs, oldVal, newVal) -> validarFormularioEnTiempoReal());
        if (txtNombre != null) txtNombre.textProperty().addListener((obs, oldVal, newVal) -> validarFormularioEnTiempoReal());
        if (txtEmail != null) txtEmail.textProperty().addListener((obs, oldVal, newVal) -> validarFormularioEnTiempoReal());
        if (cmbRol != null) cmbRol.valueProperty().addListener((obs, oldVal, newVal) -> validarFormularioEnTiempoReal());
        if (cmbEstado != null) cmbEstado.valueProperty().addListener((obs, oldVal, newVal) -> validarFormularioEnTiempoReal());
    }

    private boolean validarFormularioEnTiempoReal() {
        boolean esValido = true;

        if (txtId != null && lblErrorId != null) {
            String val = txtId.getText().trim();
            if (val.isEmpty()) {
                lblErrorId.setText("Obligatorio");
                esValido = false;
            } else if (!val.matches("\\d+")) {
                lblErrorId.setText("Solo números");
                esValido = false;
            } else {
                lblErrorId.setText("");
            }
        }

        if (txtNombre != null && lblErrorNombre != null) {
            String val = txtNombre.getText().trim();
            if (val.isEmpty()) {
                lblErrorNombre.setText("Obligatorio");
                esValido = false;
            } else {
                lblErrorNombre.setText("");
            }
        }

        if (txtEmail != null && lblErrorEmail != null) {
            String val = txtEmail.getText().trim();
            if (val.isEmpty()) {
                lblErrorEmail.setText("Obligatorio");
                esValido = false;
            } else if (!val.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                lblErrorEmail.setText("Formato inválido");
                esValido = false;
            } else {
                lblErrorEmail.setText("");
            }
        }

        if (cmbRol != null && lblErrorRol != null) {
            if (cmbRol.getValue() == null) {
                lblErrorRol.setText("Selecciona un rol");
                esValido = false;
            } else {
                lblErrorRol.setText("");
            }
        }

        if (cmbEstado != null && lblErrorEstado != null) {
            if (cmbEstado.getValue() == null) {
                lblErrorEstado.setText("Selecciona estado");
                esValido = false;
            } else {
                lblErrorEstado.setText("");
            }
        }

        if (btnGuardar != null) btnGuardar.setDisable(!esValido);
        return esValido;
    }

    @FXML
    private void handleNuevo() {
        handleLimpiar();
    }

    @FXML
    private void handleValidar() {
        if (validarFormularioEnTiempoReal()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Validación Exitosa", "Los datos del administrador son válidos.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de Validación", "Por favor revisa los mensajes indicados.");
        }
    }

    @FXML
    private void handleGuardar() {
        if (!validarFormularioEnTiempoReal()) return;
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();
            String rol = cmbRol.getValue();
            String estado = (cmbEstado != null && cmbEstado.getValue() != null) ? cmbEstado.getValue() : "Activo";

            listaAdministradores.add(new Administrador(id, nombre, email, rol, estado));
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador registrado correctamente.");
            handleLimpiar();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ocurrió un error al procesar el registro.");
        }
    }

    @FXML
    private void handleEditar() {
        int selIndex = tblAdministradores.getSelectionModel().getSelectedIndex();
        if (selIndex >= 0 && validarFormularioEnTiempoReal()) {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();
            String rol = cmbRol.getValue();
            String estado = (cmbEstado != null && cmbEstado.getValue() != null) ? cmbEstado.getValue() : "Activo";

            listaAdministradores.set(selIndex, new Administrador(id, nombre, email, rol, estado));
            mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador actualizado correctamente.");
            handleLimpiar();
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Edición", "Selecciona un registro válido para editar.");
        }
    }

    @FXML
    private void handleEliminar() {
        if (tblAdministradores != null) {
            Administrador sel = tblAdministradores.getSelectionModel().getSelectedItem();
            if (sel != null) {
                listaAdministradores.remove(sel);
                handleLimpiar();
            }
        }
    }

    @FXML
    private void handleLimpiar() {
        if (txtId != null) txtId.clear();
        if (txtNombre != null) txtNombre.clear();
        if (txtEmail != null) txtEmail.clear();
        if (cmbRol != null) cmbRol.setValue(null);
        if (cmbEstado != null) cmbEstado.setValue(null);

        if (lblErrorId != null) lblErrorId.setText("");
        if (lblErrorNombre != null) lblErrorNombre.setText("");
        if (lblErrorEmail != null) lblErrorEmail.setText("");
        if (lblErrorRol != null) lblErrorRol.setText("");
        if (lblErrorEstado != null) lblErrorEstado.setText("");

        if (tblAdministradores != null) tblAdministradores.getSelectionModel().clearSelection();
        restablecerEstadoBotones();
    }

    private void restablecerEstadoBotones() {
        if (btnNuevo != null) btnNuevo.setDisable(false);
        if (btnValidar != null) btnValidar.setDisable(false);
        if (btnGuardar != null) btnGuardar.setDisable(true);
        if (btnEditar != null) btnEditar.setDisable(true);
        if (btnEliminar != null) btnEliminar.setDisable(true);
        if (btnLimpiar != null) btnLimpiar.setDisable(false);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}