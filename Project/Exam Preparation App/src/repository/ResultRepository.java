package repository;

import model.QuizResult;
import java.util.ArrayList;
import java.util.List;

public class ResultRepository {

    private List<QuizResult> results = new ArrayList<>();

    public void save(QuizResult result) {
        results.add(result);
    }

    public List<QuizResult> findAll() {
        return new ArrayList<>(results);
    }
}
