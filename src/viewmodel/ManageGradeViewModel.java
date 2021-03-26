package viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Grade;
import model.GradeListModel;

public class ManageGradeViewModel
{
  public static final int EMPTY_GRADE = -9;
  private GradeListModel model;
  private StringProperty course;
  private IntegerProperty grade;
  private StringProperty error;

  public ManageGradeViewModel(GradeListModel model)
  {
    this.model = model;
    this.course = new SimpleStringProperty();
    this.grade = new SimpleIntegerProperty(EMPTY_GRADE);
    this.error = new SimpleStringProperty();
  }

  public void clear()
  {
    error.set(null);
    course.set(null);
    grade.set(EMPTY_GRADE);
  }

  public boolean add()
  {
    error.set(null);
    try
    {
      Grade g = new Grade(grade.get(), course.get());
      model.addGrade(g);
      return true;
    }
    catch (Exception e)
    {
      if (grade.get() == EMPTY_GRADE)
      {
        error.set("Illegal grade");
      }
      else
      {
        error.set(e.getMessage());
      }
    }
    return false;
  }

  public StringProperty getCourseProperty()
  {
    return course;
  }

  public IntegerProperty getGradeProperty()
  {
    return grade;
  }

  public StringProperty getErrorProperty()
  {
    return error;
  }
}
