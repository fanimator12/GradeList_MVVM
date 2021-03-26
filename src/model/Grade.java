package model;

public class Grade
{
   private int grade;
   private String course;

   public final static int LEGAL_GRADES[] = { 12, 10, 7, 4, 2, 0, -3 };

   public Grade(int grade, String course)
   {
      this.course = course;
      if (isLegalGrade(grade))
      {
         this.grade = grade;
      }
      else
      {
         throw new IllegalArgumentException("Illegal grade: " + grade);
      }
   }

   public int getGrade()
   {
      return grade;
   }

   public String getCourse()
   {
      return course;
   }

   public String toString()
   {
      return "{" + course + ": " + grade + "}";
   }

   public boolean equals(Object obj)
   {
      if (!(obj instanceof Grade))
      {
         return false;
      }
      Grade other = (Grade) obj;
      return grade == other.grade && course.equals(other.course);
   }

   public static boolean isLegalGrade(int grade)
   {
      for (int i = 0; i < LEGAL_GRADES.length; i++)
      {
         if (grade == LEGAL_GRADES[i])
         {
            return true;
         }
      }
      return false;
   }

}
