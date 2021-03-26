package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GradeListModelManager implements GradeListModel
{
  private GradeList gradeList;
  private boolean loggedIn;
  private PropertyChangeSupport property;

  public GradeListModelManager()
  {
    this.gradeList = new GradeList(10);
    createDummyData();
    this.loggedIn = false;
    this.property = new PropertyChangeSupport(this);
  }

  private void createDummyData()
  {
    try
    {
      String[] courses = { "SDJ", "RWD", "SEP", "MSE", "DBS" };
      for (int i = 0; i < courses.length; i++)
      {
        int randomIndex = (int) (Math.random() * 7);
        int grade = Grade.LEGAL_GRADES[randomIndex];
        gradeList.addGrade(new Grade(grade, courses[i]));
      }
    }
    catch (Exception e)
    {
      // ok
    }
  }

  @Override public int gradeListSize()
  {
    return gradeList.size();
  }

  @Override public void addGrade(Grade grade)
  {
    gradeList.addGrade(grade);
    property.firePropertyChange("add", null, grade);
  }

  @Override public void removeGrade(Grade grade)
  {
    gradeList.removeGrade(grade);
    property.firePropertyChange("remove", grade, null);
  }

  @Override public Grade getGrade(int index)
  {
    return gradeList.getGrade(index);
  }

  @Override public Grade getMaxGrade()
  {
    return gradeList.getMaxGrade();
  }

  @Override public Grade getMinGrade()
  {
    return gradeList.getMinGrade();
  }

  @Override public double getAverageGrade()
  {
    return gradeList.getAverage();
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
