package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.DetailsViewModel;

public class DetailsViewController
{
  @FXML private TextField countField;
  @FXML private TextField maxField;
  @FXML private TextField minField;
  @FXML private TextField averageField;
  @FXML private Label errorLabel;

  private Region root;
  private DetailsViewModel viewModel;
  private ViewHandler viewHandler;

  public DetailsViewController()
  {
    // Called by FXMLLoader
  }

  public void init(ViewHandler viewHandler, DetailsViewModel viewModel,
      Region root)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;

    countField.textProperty().bind(viewModel.getCountProperty());
    maxField.textProperty().bind(viewModel.getMaxProperty());
    minField.textProperty().bind(viewModel.getMinProperty());
    averageField.textProperty().bind(viewModel.getAverageProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    reset();
  }

  public void reset()
  {
    viewModel.getAll();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void backButtonPressed()
  {
    viewHandler.openView("list");
  }
}
