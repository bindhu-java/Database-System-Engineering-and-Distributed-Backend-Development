package repository;

import model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    private List<Question> questions = new ArrayList<>();

    public QuestionRepository() {

        questions.add(new Question(
                1,
                "Java",
                "Simple",
                "Which keyword is used to create a class in Java?",
                "class"
        ));

        questions.add(new Question(
                2,
                "Java",
                "Moderate",
                "Which collection stores unique elements?",
                "Set"
        ));

        questions.add(new Question(
                3,
                "Java",
                "Hard",
                "Which concept allows one interface to have multiple implementations?",
                "Polymorphism"
        ));

        questions.add(new Question(
                4,
                "Python",
                "Simple",
                "Which keyword is used to define a function?",
                "def"
        ));

        questions.add(new Question(
                5,
                "Python",
                "Moderate",
                "Which data structure stores key-value pairs?",
                "dictionary"
        ));

        questions.add(new Question(
                6,
                "C",
                "Simple",
                "Which function is the entry point of a C program?",
                "main"
        ));

        questions.add(new Question(
                7,
                "Operating Systems",
                "Hard",
                "Which scheduling algorithm uses the shortest remaining time?",
                "SRTF"
        ));

        questions.add(new Question(
                8,
                "Machine Learning",
                "Moderate",
                "Which technique reduces overfitting in a decision tree?",
                "Pruning"
        ));
    }

    public List<Question> findByTopic(String topic) {

        List<Question> result = new ArrayList<>();

        for (Question question : questions) {

            if (question.getTopic().equalsIgnoreCase(topic)) {
                result.add(question);
            }
        }

        return result;
    }
}