package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.GradeListViewModel;

import java.util.Optional;

public class GradeListViewController
{
  @FXML private TableView<TableRowData> gradeListTable;
  @FXML private TableColumn<TableRowData, Number> gradeColumn;
  @FXML private TableColumn<TableRowData, String> courseColumn;
  @FXML private Label errorLabel;

  private Region root;
  private ViewHandler viewHandler;
  private GradeListViewModel viewModel;

  public GradeListViewController()
  {
    // Called by FXMLLoader
  }

  public void init(ViewHandler viewHandler, GradeListViewModel viewModel,
      Region root)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;

    gradeColumn.setCellValueFactory(
        cellData -> cellData.getValue().getGradeProperty());
    courseColumn.setCellValueFactory(
        cellData -> cellData.getValue().getCourseProperty());
    gradeListTable.setItems(viewModel.getList());

    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    viewModel.getSelectedProperty()
        .bind(gradeListTable.getSelectionModel().selectedItemProperty());
    viewModel.isRemoveConfirmationProperty()
        .addListener((obs, oldb, b) -> confirmation(b));
  }

  public void reset()
  {
    viewModel.clear();
    gradeListTable.getSelectionModel().clearSelection();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void addGradeButtonPressed()
  {
    viewHandler.openView("manage");
  }

  @FXML private void removeGradeButtonPressed()
  {
    viewModel.remove();
    gradeListTable.getSelectionModel().clearSelection();
  }

  @FXML private void showGradeDetailsButtonPressed()
  {
    viewHandler.openView("details");
  }

  private void confirmation(boolean confirm)
  {
    if (!confirm)
    {
      return;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Removing grade: " + viewModel.getSelected());
    Optional<ButtonType> result = alert.showAndWait();
    viewModel.confirm((result.isPresent()) && (result.get() == ButtonType.OK));
  }
}
