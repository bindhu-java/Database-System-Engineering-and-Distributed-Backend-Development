package model;

public class Question {

    private int id;
    private String topic;
    private String difficulty;
    private String question;
    private String answer;
    private String[] options;

    public Question(
            int id,
            String topic,
            String difficulty,
            String question,
            String answer) {

        this.id = id;
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
    }

    public Question(
            int id,
            String topic,
            String difficulty,
            String question,
            String[] options,
            String answer) {

        this.id = id;
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}