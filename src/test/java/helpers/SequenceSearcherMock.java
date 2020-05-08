package helpers;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.HashMap;

public class SequenceSearcherMock implements SequenceSearcher {

    public int searchMethodCalls = 0;
    private HashMap<Integer, Integer> expectedAnswers = new HashMap<>();

    public void injectExpectedResults(HashMap<Integer, Integer> expected){
        expectedAnswers = expected;
    }

    @Override
    public SearchResult search(int key, int[] sequence) {

        ++searchMethodCalls;

        if (sequence == null) {
            throw new IllegalArgumentException("Provided sequence cannot be null");
        }

        int position = expectedAnswers.getOrDefault(key, -1);

        SearchResult.Builder resultBuilder = SearchResult.builder()
                .withPosition(position)
                .withFound(position != -1);

        return resultBuilder.build();
    }
}
