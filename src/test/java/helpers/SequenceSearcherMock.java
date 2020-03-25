package helpers;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.Optional;
import java.util.stream.IntStream;

public class SequenceSearcherMock implements SequenceSearcher {

    public int searchMethodCalls = 0;

    @Override
    public SearchResult search(int key, int[] sequence) {

        ++searchMethodCalls;

        if (sequence == null) {
            throw new IllegalArgumentException("Provided sequence cannot be null");
        }

        Optional<Integer> result = IntStream.range(0, sequence.length).filter(pos -> key == sequence[pos]).boxed().findFirst();
        int position = result.orElse(-1);
        SearchResult.Builder resultBuilder = SearchResult.builder()
                .withPosition(position)
                .withFound(position != -1);

        return resultBuilder.build();
    }
}
