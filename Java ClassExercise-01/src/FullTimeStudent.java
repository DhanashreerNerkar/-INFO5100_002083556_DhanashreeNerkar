public class FullTimeStudent extends Student{
    private int examScore1;
    private int examScore2;

    public FullTimeStudent(String name)
    {
        super(name);
        this.examScore1=(int)(Math.random()*100);
        this.examScore2=(int)(Math.random()*100);
    }

    public void printExamScores() {
        System.out.println("Exam Scores: " + examScore1 + ", " + examScore2+"\n");
    }
}
