package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import viewmodel.ManageGradeViewModel;

public class ManageGradeViewController
{
  @FXML private TextField courseTextField;
  @FXML private TextField gradeTextField;
  @FXML private Label errorLabel;

  private Region root;
  private ManageGradeViewModel viewModel;
  private ViewHandler viewHandler;

  public ManageGradeViewController()
  {
    // Called by FXMLLoader
  }

  public void init(ViewHandler viewHandler, ManageGradeViewModel viewModel,
      Region root)
  {
    this.viewModel = viewModel;
    this.viewHandler = viewHandler;
    this.root = root;

    courseTextField.textProperty().bindBidirectional(viewModel.getCourseProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());
    Bindings.bindBidirectional(gradeTextField.textProperty(),
        viewModel.getGradeProperty(), new StringConverter<>()
        {
          @Override public String toString(Number n)
          {
            if (n == null || n.intValue() == viewModel.EMPTY_GRADE)
            {
              return "";
            }
            return n.toString();
          }

          @Override public Number fromString(String s)
          {
            try
            {
              return Integer.parseInt(s);
            }
            catch (Exception e)
            {
              return viewModel.EMPTY_GRADE;
            }
          }
        });
  }

  public void reset()
  {
    viewModel.clear();
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void addGradeSubmitButton()
  {
    boolean added = viewModel.add();
    if (added)
    {
      viewHandler.openView("list");
    }
  }

  @FXML private void addGradeCancelButton()
  {
    viewHandler.openView("list");
  }

}
