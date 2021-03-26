package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Grade;
import model.GradeListModel;
import view.TableRowData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class GradeListViewModel implements PropertyChangeListener
{
  private GradeListModel model;
  private ObservableList<TableRowData> list;
  private ObjectProperty<TableRowData> selected;
  private BooleanProperty removeConfirmation;
  private StringProperty error;

  public GradeListViewModel(GradeListModel model)
  {
    this.model = model;
    this.model.addListener(this);
    this.list = createList();
    this.selected = new SimpleObjectProperty<>();
    this.removeConfirmation = new SimpleBooleanProperty(false);
    this.error = new SimpleStringProperty();
  }

  private synchronized ObservableList<TableRowData> createList()
  {
    ObservableList<TableRowData> obsList = FXCollections.observableArrayList();

    ArrayList<Grade> grades = new ArrayList<>();
    for (int i = 0; i < model.gradeListSize(); i++)
    {
      grades.add(model.getGrade(i));
    }
    for (int i = 0; i < grades.size(); i++)
    {
      obsList.add(new TableRowData(grades.get(i)));
    }
    return obsList;
  }

  public void clear()
  {
    error.set(null);
  }

  public String getSelected()
  {
    String s = "";
    if (selected.get() != null)
    {
      s += selected.get().getGrade();
    }
    return s;
  }

  public void remove()
  {
    if (selected.get() == null)
    {
      error.set("No selection");
      return;
    }
    error.set("");
    removeConfirmation.set(true);
    if (removeConfirmation.get())
    {
      try
      {
        Grade grade = selected.get().getGrade();
        model.removeGrade(grade);
      }
      catch (Exception e)
      {
        error.set("Error: " + e.getMessage());
      }
    }
    removeConfirmation.set(false);
  }

  public void confirm(boolean b)
  {
    removeConfirmation.set(b);
  }

  public ObservableList<TableRowData> getList()
  {
    return list;
  }

  public ObjectProperty<TableRowData> getSelectedProperty()
  {
    return selected;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }

  public BooleanProperty isRemoveConfirmationProperty()
  {
    return removeConfirmation;
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "add":
          addToTheList((Grade) evt.getNewValue()); break;
        case "remove":
          removeFromTheList((Grade) evt.getOldValue()); break;
      }
    });
  }

  private void removeFromTheList(Grade grade)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getCourseProperty().get().equals(grade.getCourse())
          && list.get(i).getGradeProperty().get() == grade.getGrade())
      {
        list.remove(i);
        break;
      }
    }
  }

  private void addToTheList(Grade grade)
  {
    list.add(new TableRowData(grade));
  }

}
