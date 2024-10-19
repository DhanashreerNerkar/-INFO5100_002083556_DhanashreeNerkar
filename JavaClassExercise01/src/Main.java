import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Session s=new Session();

        for(Integer i=0;i<10;i++)
        {
            s.addStudent(new PartTimeStudent("PartTimeStudent-"+ (i+1)));
        }
        for(Integer i=10;i<20;i++)
        {
            s.addStudent(new FullTimeStudent("FullTimeStudent-"+ (i+1)));
        }

        /*
        This below method covers following :
        * 1. Calculate average score per students in class
        * 2. Arrange the average score in ascending order
        * 3. Prints name of part-time students
        * 4. prints exam scores of full time students
        */
       s.printStudentDetails();

    }
}


