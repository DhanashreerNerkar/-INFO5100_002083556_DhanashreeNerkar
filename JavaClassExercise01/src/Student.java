import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Integer> quizScores = new ArrayList<>(15);

    public Student(String name) {
        this.name=name;
        for (int i = 0; i < 15; i++) {
            quizScores.add((int) (Math.random() * 100)); // Random scores between 0 and 100
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getQuizScores() {
        return quizScores;
    }

    public double getAverageQuizScore() {
        int sum = 0;
        for (int score : quizScores) {
            sum += score;
        }
        return sum / (double) quizScores.size();
    }
}
