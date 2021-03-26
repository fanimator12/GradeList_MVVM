package model;

public class GradeList
{
  private Grade[] grades;
  private int actuaralNumberOfGrades;

  public GradeList(int maxNumberOfGrades)
  {
    this.grades = new Grade[maxNumberOfGrades];
    this.actuaralNumberOfGrades = 0;
  }

  public int size()
  {
    return actuaralNumberOfGrades;
  }

  public void addGrade(Grade grade)
  {
    addGrade(actuaralNumberOfGrades, grade);
  }

  public void addGrade(int index, Grade grade)
  {
    if (actuaralNumberOfGrades < grades.length)
    {
      for (int i = actuaralNumberOfGrades - 1; i >= index; i--)
      {
        grades[i + 1] = grades[i];
      }
      grades[index] = grade;
      actuaralNumberOfGrades++;
    }
  }

  public void removeGrade(int index)
  {
    for (int i = index; i < actuaralNumberOfGrades - 1; i++)
    {
      grades[i] = grades[i + 1];
    }
    actuaralNumberOfGrades--;
  }

  public Grade getGrade(int index)
  {
    return grades[index];
  }

  public Grade getMaxGrade()
  {
    String course = null;
    int max = Grade.LEGAL_GRADES[Grade.LEGAL_GRADES.length - 1] - 1;
    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      if (grades[i].getGrade() > max)
      {
        max = grades[i].getGrade();
        course = grades[i].getCourse();
      }
    }
    if (actuaralNumberOfGrades < 1)
    {
      throw new IllegalStateException("Empty grade list");
    }
    return new Grade(max, course);
  }

  public Grade getMinGrade()
  {
    String course = null;
    int min = Grade.LEGAL_GRADES[0] + 1;
    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      if (grades[i].getGrade() < min)
      {
        min = grades[i].getGrade();
        course = grades[i].getCourse();
      }
    }
    if (actuaralNumberOfGrades < 1)
    {
      throw new IllegalStateException("Empty grade list");
    }
    return new Grade(min, course);
  }

  public double getAverage()
  {
    int sum = 0;

    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      sum += grades[i].getGrade();
    }
    if (actuaralNumberOfGrades < 1)
    {
      throw new IllegalStateException("Empty grade list");
    }
    double average = (double) sum / actuaralNumberOfGrades;
    return average;
  }

  public int getGradeCount(int grade)
  {
    int count = 0;
    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      if (grades[i].getGrade() == grade)
      {
        count++;
      }
    }
    return count;
  }

  public String getGradeDistribution()
  {
    String s = "{";
    for (int i = 0; i < Grade.LEGAL_GRADES.length; i++)
    {
      s += Grade.LEGAL_GRADES[i] + " (" + getGradeCount(Grade.LEGAL_GRADES[i])
          + ")";
      if (i < Grade.LEGAL_GRADES.length - 1)
      {
        s += ", ";
      }
    }
    s += "}";
    return s;
  }

  public String toString()
  {
    String s = "{";
    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      s += grades[i];
      if (i < actuaralNumberOfGrades - 1)
      {
        s += ", ";
      }
    }
    s += "}";
    return s;
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof GradeList))
    {
      return false;
    }
    GradeList other = (GradeList) obj;
    if (actuaralNumberOfGrades != other.actuaralNumberOfGrades)
    {
      return false;
    }
    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      if (!grades[i].equals(other.grades[i]))
      {
        return false;
      }
    }
    return true;
  }

  public void removeGrade(Grade grade)
  {
    for (int i = 0; i < actuaralNumberOfGrades; i++)
    {
      if (grades[i].equals(grade))
      {
        for (int j = i; j < actuaralNumberOfGrades - 1; j++)
        {
          grades[j] = grades[j + 1];
        }
        actuaralNumberOfGrades--;
        break;
      }
    }
  }
}
