import java.util.*;

public class Session
{
    private ArrayList<Student> students=new ArrayList<Student>(20);

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public void printStudentDetails()
    {
        //Sort Students by their average score
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // Compare average quiz scores, returns in ascending order
                return Double.compare(s1.getAverageQuizScore(), s2.getAverageQuizScore());
            }
        });

        //print student details
        for(Student s: students)
        {
            if(s instanceof PartTimeStudent)
            {
                System.out.println("Name: "+s.getName()+"\nScores: "+s.getQuizScores()+"\nAvg Score: "+s.getAverageQuizScore()+"\n");
            }
            else
            {
                if(s instanceof FullTimeStudent) {
                    System.out.println("Name: " + s.getName() +
                            "\nScores: " + s.getQuizScores() +
                            "\nAvg Score: " + s.getAverageQuizScore());

                    ((FullTimeStudent) s).printExamScores();
                }
            }
        }
    }
}
