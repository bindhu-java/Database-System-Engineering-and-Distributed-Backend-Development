package model;

public class QuizResult {

    private String studentName;
    private String topic;
    private String difficulty;
    private int score;

    public QuizResult(String studentName,
                      String topic,
                      String difficulty,
                      int score) {

        this.studentName = studentName;
        this.topic = topic;
        this.difficulty = difficulty;
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTopic() {
        return topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }
}