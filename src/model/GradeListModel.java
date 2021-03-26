package model;

import utility.UnnamedPropertyChangeSubject;

public interface GradeListModel extends UnnamedPropertyChangeSubject
{
   public void addGrade(Grade grade);

   public void removeGrade(Grade grade);

   public int gradeListSize();

   public Grade getGrade(int index);

   public Grade getMaxGrade();

   public Grade getMinGrade();

   public double getAverageGrade();
}
