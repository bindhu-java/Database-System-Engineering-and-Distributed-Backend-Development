package service;

import model.Question;
import java.util.*;

public class AdaptiveQuizService {

    private final List<Question> questions;

    public AdaptiveQuizService() {
        questions = new ArrayList<>();

        addJavaQuestions();
        addPythonQuestions();
        addCQuestions();
        addOSQuestions();
        addMLQuestions();
    }

    private void addJavaQuestions() {

        questions.add(new Question(
                1,
                "Java",
                "Easy",
                "Which keyword is used to create a class in Java?",
                new String[]{"class", "object", "new", "create"},
                "class"
        ));

        questions.add(new Question(
                2,
                "Java",
                "Easy",
                "Which method is the starting point of a Java program?",
                new String[]{"start()", "main()", "run()", "execute()"},
                "main()"
        ));

        questions.add(new Question(
                3,
                "Java",
                "Medium",
                "Which concept allows one class to acquire properties of another class?",
                new String[]{"Encapsulation", "Inheritance", "Polymorphism", "Abstraction"},
                "Inheritance"
        ));

        questions.add(new Question(
                4,
                "Java",
                "Medium",
                "Which keyword is used to inherit a class?",
                new String[]{"this", "super", "extends", "implements"},
                "extends"
        ));

        questions.add(new Question(
                5,
                "Java",
                "Hard",
                "Which collection does not allow duplicate elements?",
                new String[]{"List", "ArrayList", "Set", "Vector"},
                "Set"
        ));
    }

    private void addPythonQuestions() {

        questions.add(new Question(
                6,
                "Python",
                "Easy",
                "Which symbol is used for comments in Python?",
                new String[]{"//", "#", "/*", "--"},
                "#"
        ));

        questions.add(new Question(
                7,
                "Python",
                "Easy",
                "Which function is used to display output in Python?",
                new String[]{"display()", "show()", "print()", "output()"},
                "print()"
        ));

        questions.add(new Question(
                8,
                "Python",
                "Medium",
                "Which data type stores key-value pairs?",
                new String[]{"List", "Tuple", "Dictionary", "Set"},
                "Dictionary"
        ));

        questions.add(new Question(
                9,
                "Python",
                "Medium",
                "Which keyword is used to define a function?",
                new String[]{"function", "define", "def", "fun"},
                "def"
        ));

        questions.add(new Question(
                10,
                "Python",
                "Hard",
                "Which keyword is used to handle exceptions?",
                new String[]{"try", "check", "error", "catch"},
                "try"
        ));
    }

    private void addCQuestions() {

        questions.add(new Question(
                11,
                "C",
                "Easy",
                "Which function is used to print output in C?",
                new String[]{"print()", "printf()", "display()", "cout"},
                "printf()"
        ));

        questions.add(new Question(
                12,
                "C",
                "Easy",
                "Which symbol is used to end a C statement?",
                new String[]{".", ":", ";", ","},
                ";"
        ));

        questions.add(new Question(
                13,
                "C",
                "Medium",
                "Which data type is used to store a single character?",
                new String[]{"string", "char", "character", "text"},
                "char"
        ));

        questions.add(new Question(
                14,
                "C",
                "Medium",
                "Which operator is used to get the address of a variable?",
                new String[]{"*", "&", "#", "@"},
                "&"
        ));

        questions.add(new Question(
                15,
                "C",
                "Hard",
                "Which function is used to allocate dynamic memory?",
                new String[]{"malloc()", "alloc()", "memory()", "new()"},
                "malloc()"
        ));
    }

    private void addOSQuestions() {

        questions.add(new Question(
                16,
                "Operating Systems",
                "Easy",
                "What is the main function of an operating system?",
                new String[]{
                        "Manage computer resources",
                        "Create websites",
                        "Design hardware",
                        "Write programs"
                },
                "Manage computer resources"
        ));

        questions.add(new Question(
                17,
                "Operating Systems",
                "Easy",
                "Which scheduling algorithm executes processes in arrival order?",
                new String[]{
                        "SJF",
                        "FCFS",
                        "Round Robin",
                        "Priority"
                },
                "FCFS"
        ));

        questions.add(new Question(
                18,
                "Operating Systems",
                "Medium",
                "Which scheduling algorithm uses a time quantum?",
                new String[]{
                        "FCFS",
                        "SJF",
                        "Round Robin",
                        "Priority"
                },
                "Round Robin"
        ));

        questions.add(new Question(
                19,
                "Operating Systems",
                "Medium",
                "Which component manages communication between user programs and hardware?",
                new String[]{
                        "Compiler",
                        "Kernel",
                        "Browser",
                        "Database"
                },
                "Kernel"
        ));

        questions.add(new Question(
                20,
                "Operating Systems",
                "Hard",
                "Which IPC mechanism uses a shared memory area?",
                new String[]{
                        "Pipe",
                        "Signal",
                        "Shared Memory",
                        "File"
                },
                "Shared Memory"
        ));
    }

    private void addMLQuestions() {

        questions.add(new Question(
                21,
                "Machine Learning",
                "Easy",
                "What does ML stand for?",
                new String[]{
                        "Machine Learning",
                        "Manual Learning",
                        "Machine Logic",
                        "Model Learning"
                },
                "Machine Learning"
        ));

        questions.add(new Question(
                22,
                "Machine Learning",
                "Easy",
                "Which algorithm is commonly used for classification?",
                new String[]{
                        "Decision Tree",
                        "Linear Search",
                        "Binary Search",
                        "Sorting"
                },
                "Decision Tree"
        ));

        questions.add(new Question(
                23,
                "Machine Learning",
                "Medium",
                "Which metric is commonly used to measure classification accuracy?",
                new String[]{
                        "Accuracy",
                        "Temperature",
                        "Memory",
                        "Frequency"
                },
                "Accuracy"
        ));

        questions.add(new Question(
                24,
                "Machine Learning",
                "Medium",
                "Which regularization method can make some coefficients exactly zero?",
                new String[]{
                        "Ridge",
                        "Lasso",
                        "KNN",
                        "PCA"
                },
                "Lasso"
        ));

        questions.add(new Question(
                25,
                "Machine Learning",
                "Hard",
                "Which measure is used in a decision tree to select a split?",
                new String[]{
                        "Entropy",
                        "Voltage",
                        "Frequency",
                        "Latency"
                },
                "Entropy"
        ));
    }

    public List<Question> getQuestions(String topic, int count) {

        List<Question> filtered = new ArrayList<>();

        for (Question q : questions) {
            if (q.getTopic().equalsIgnoreCase(topic)) {
                filtered.add(q);
            }
        }

        Collections.shuffle(filtered);

        if (count > filtered.size()) {
            count = filtered.size();
        }

        return new ArrayList<>(filtered.subList(0, count));
    }

    public List<Question> getAdaptiveQuestions(
            String topic,
            int count,
            String difficulty) {

        List<Question> filtered = new ArrayList<>();

        for (Question q : questions) {

            if (!q.getTopic().equalsIgnoreCase(topic)) {
                continue;
            }

            if (difficulty == null ||
                    difficulty.equalsIgnoreCase("Adaptive") ||
                    q.getDifficulty().equalsIgnoreCase(difficulty)) {

                filtered.add(q);
            }
        }

        Collections.shuffle(filtered);

        if (count > filtered.size()) {
            count = filtered.size();
        }

        return new ArrayList<>(filtered.subList(0, count));
    }

    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    public int calculateScore(
            List<Question> quizQuestions,
            Map<Integer, String> userAnswers) {

        int score = 0;

        for (Question q : quizQuestions) {

            String userAnswer = userAnswers.get(q.getId());

            if (userAnswer != null &&
                    userAnswer.equalsIgnoreCase(q.getAnswer())) {

                score++;
            }
        }

        return score;
    }
}