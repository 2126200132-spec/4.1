package edu.utj.dsm.poo.gym.controlador;

import edu.utj.dsm.poo.gym.modelo.Administrador;
import edu.utj.dsm.poo.gym.modelo.SesionUsuario;
import edu.utj.dsm.poo.gym.persistencia.AdministradorArrayList;
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

public class AdministradorController implements Initializable {

    @FXML private Label lblSesion;
    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> cmbRol;
    @FXML private ComboBox<String> cmbEstado;

    @FXML private Label lblErrorId;
    @FXML private Label lblErrorNombre;
    @FXML private Label lblErrorEmail;
    @FXML private Label lblErrorRol;
    @FXML private Label lblErrorEstado;

    @FXML private TableView<Administrador> tblAdministradores;
    @FXML private TableColumn<Administrador, Integer> colId;
    @FXML private TableColumn<Administrador, String> colNombre;
    @FXML private TableColumn<Administrador, String> colEmail;
    @FXML private TableColumn<Administrador, String> colRol;
    @FXML private TableColumn<Administrador, String> colEstado;

    @FXML private Button btnNuevo;
    @FXML private Button btnValidar;
    @FXML private Button btnGuardar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnCerrarSesion;

    private final AdministradorArrayList dao = new AdministradorArrayList();
    private final ObservableList<Administrador> listaObservable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarInformacionSesion();

        if (colId != null) colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (colNombre != null) colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        if (colEmail != null) colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        if (colRol != null) colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        if (colEstado != null) colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        if (cmbRol != null) {
            cmbRol.setItems(FXCollections.observableArrayList("Administrador", "Gerente", "Recepcionista", "Soporte"));
        }
        if (cmbEstado != null) {
            cmbEstado.setItems(FXCollections.observableArrayList("Activo", "Inactivo"));
        }

        if (tblAdministradores != null) {
            tblAdministradores.setItems(listaObservable);

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
                aplicarPermisosPorRol();
            });
        }

        configurarListenersValidacion();
        actualizarTabla();
        restablecerEstadoBotones();
    }

    private void mostrarInformacionSesion() {
        SesionUsuario sesion = SesionUsuario.getInstancia();
        if (sesion != null && lblSesion != null) {
            lblSesion.setText("Usuario: " + sesion.getNombre() + " | Rol: " + sesion.getRol());
        }
    }

    private void aplicarPermisosPorRol() {
        SesionUsuario sesion = SesionUsuario.getInstancia();
        if (sesion == null) return;

        String rol = sesion.getRol().toLowerCase();

        if (!rol.equals("administrador")) {
            if (btnNuevo != null) btnNuevo.setDisable(true);
            if (btnGuardar != null) btnGuardar.setDisable(true);
            if (btnEditar != null) btnEditar.setDisable(true);
            if (btnEliminar != null) btnEliminar.setDisable(true);
        }
    }

    @FXML
    private void handleCerrarSesion() {
        SesionUsuario.cerrarSesion();
        try {
            App.setRoot("Login");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if (val.isEmpty()) { lblErrorId.setText("Obligatorio"); esValido = false; }
            else if (!val.matches("\\d+")) { lblErrorId.setText("Solo números"); esValido = false; }
            else { lblErrorId.setText(""); }
        }

        if (txtNombre != null && lblErrorNombre != null) {
            String val = txtNombre.getText().trim();
            if (val.isEmpty()) { lblErrorNombre.setText("Obligatorio"); esValido = false; }
            else { lblErrorNombre.setText(""); }
        }

        if (txtEmail != null && lblErrorEmail != null) {
            String val = txtEmail.getText().trim();
            if (val.isEmpty()) { lblErrorEmail.setText("Obligatorio"); esValido = false; }
            else if (!val.matches("^[A-Za-z0-9+_.-]+@(.+)$")) { lblErrorEmail.setText("Formato inválido"); esValido = false; }
            else { lblErrorEmail.setText(""); }
        }

        if (cmbRol != null && lblErrorRol != null) {
            if (cmbRol.getValue() == null) { lblErrorRol.setText("Selecciona rol"); esValido = false; }
            else { lblErrorRol.setText(""); }
        }

        if (cmbEstado != null && lblErrorEstado != null) {
            if (cmbEstado.getValue() == null) { lblErrorEstado.setText("Selecciona estado"); esValido = false; }
            else { lblErrorEstado.setText(""); }
        }

        if (btnGuardar != null) btnGuardar.setDisable(!esValido);
        aplicarPermisosPorRol();
        return esValido;
    }

    @FXML private void handleNuevo() { handleLimpiar(); }

    @FXML
    private void handleValidar() {
        if (validarFormularioEnTiempoReal()) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Validación Exitosa", "Los datos del administrador son válidos.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de Validación", "Por favor revisa los campos señalados.");
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

            Administrador nuevoAdmin = new Administrador(id, nombre, email, rol, estado);

            if (dao.agregar(nuevoAdmin)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador registrado correctamente.");
                actualizarTabla();
                handleLimpiar();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo guardar el registro.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", "El ID debe ser un número entero.");
        }
    }

    @FXML
    private void handleEditar() {
        int indiceSeleccionado = tblAdministradores.getSelectionModel().getSelectedIndex();
        if (indiceSeleccionado < 0) {
            mostrarAlerta(Alert.AlertType.WARNING, "Selección requerida", "Selecciona un elemento de la tabla para editar.");
            return;
        }

        if (!validarFormularioEnTiempoReal()) return;

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String nombre = txtNombre.getText().trim();
            String email = txtEmail.getText().trim();
            String rol = cmbRol.getValue();
            String estado = (cmbEstado != null && cmbEstado.getValue() != null) ? cmbEstado.getValue() : "Activo";

            Administrador adminActualizado = new Administrador(id, nombre, email, rol, estado);
            Administrador adminExistente = tblAdministradores.getSelectionModel().getSelectedItem();

            if (dao.actualizar(adminActualizado, indiceSeleccionado) || (dao.eliminar(adminExistente) && dao.agregar(adminActualizado))) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador actualizado correctamente.");
                actualizarTabla();
                handleLimpiar();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo actualizar el registro.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de Datos", "El ID debe ser un número entero.");
        }
    }

    @FXML
    private void handleEliminar() {
        Administrador seleccionado = tblAdministradores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            if (dao.eliminar(seleccionado)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Administrador eliminado.");
                actualizarTabla();
                handleLimpiar();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo eliminar el administrador.");
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

    private void actualizarTabla() {
        listaObservable.setAll(dao.listar());
    }

    private void restablecerEstadoBotones() {
        if (btnNuevo != null) btnNuevo.setDisable(false);
        if (btnValidar != null) btnValidar.setDisable(false);
        if (btnGuardar != null) btnGuardar.setDisable(true);
        if (btnEditar != null) btnEditar.setDisable(true);
        if (btnEliminar != null) btnEliminar.setDisable(true);
        if (btnLimpiar != null) btnLimpiar.setDisable(false);

        aplicarPermisosPorRol();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}