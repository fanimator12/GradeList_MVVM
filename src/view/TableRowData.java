package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Grade;

public class TableRowData
{
  private StringProperty courseProperty;
  private IntegerProperty gradeProperty;

  public TableRowData(Grade grade)
  {
    courseProperty = new SimpleStringProperty(grade.getCourse());
    gradeProperty = new SimpleIntegerProperty(grade.getGrade());
  }

  public StringProperty getCourseProperty()
  {
    return courseProperty;
  }

  public IntegerProperty getGradeProperty()
  {
    return gradeProperty;
  }

  public Grade getGrade()
  {
    return new Grade(gradeProperty.get(), courseProperty.get());
  }

}
