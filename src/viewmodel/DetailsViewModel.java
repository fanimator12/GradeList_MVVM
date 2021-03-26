package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.GradeListModel;

public class DetailsViewModel
{
  private GradeListModel model;
  private StringProperty count;
  private StringProperty max;
  private StringProperty min;
  private StringProperty average;
  private StringProperty error;

  public DetailsViewModel(GradeListModel model)
  {
    this.model = model;
    this.count = new SimpleStringProperty();
    this.max = new SimpleStringProperty();
    this.min = new SimpleStringProperty();
    this.average = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
    getAll();
  }

  public void getAll()
  {
    this.count.set("");
    this.max.set("");
    this.min.set("");
    this.average.set("");
    try
    {
      this.count.set(model.gradeListSize() + "");
      this.max.set(model.getMaxGrade() + "");
      this.min.set(model.getMinGrade() + "");
      this.average.set(model.getAverageGrade() + "");
      this.error.set("");
    }
    catch (Exception e)
    {
      this.error.set(e.getMessage());
    }
  }

  public StringProperty getCountProperty()
  {
    return count;
  }
  public StringProperty getMaxProperty()
  {
    return max;
  }
  public StringProperty getMinProperty()
  {
    return min;
  }
  public StringProperty getAverageProperty()
  {
    return average;
  }
  public StringProperty getErrorProperty()
  {
    return error;
  }
}
