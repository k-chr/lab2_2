import edu.iis.mto.similarity.SimilarityFinder;
import helpers.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderStateTestCases {

    private static final double FULL_MATCHING_SETS = 1.0;
    private static final double FULL_MISMATCHING_SETS = 0.0;
    private static final double TWENTY_PERCENT = 0.2;
    private static final double FIFTY_PERCENT = 0.5;
    private SimilarityFinder finder = null;
    private SequenceSearcherMock searchAlgorithm = null;

    @BeforeEach
    void init() {
        searchAlgorithm = new SequenceSearcherMock();
        finder = new SimilarityFinder(searchAlgorithm);
    }

    @Test
    void calculateJackardSimilarityForTwoEmptySetsTest() {
        int[] collection1 = new int[]{};
        int[] collection2 = new int[]{};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForTwoDisjointSetsTest() {
        int[] collection1 = new int[]{15, 24, 33};
        int[] collection2 = new int[]{66, 75, 43};

        HashMap<Integer, Integer> map = new HashMap<>() {{
            put(66, 0);
            put(75, 1);
            put(43, 2);
        }};

        searchAlgorithm.injectExpectedResults(map);
        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MISMATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForFirstEmptySetAndSecondNotEmptyTest() {
        int[] collection1 = new int[]{};
        int[] collection2 = new int[]{66, 75, 43};

        HashMap<Integer, Integer> map = new HashMap<>() {{
            put(66, 0);
            put(75, 1);
            put(43, 2);
        }};

        searchAlgorithm.injectExpectedResults(map);
        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MISMATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForFirstNotEmptySetAndSecondEmptyTest() {
        int[] collection1 = new int[]{15, 24, 33};
        int[] collection2 = new int[]{};

        HashMap<Integer, Integer> map = new HashMap<>() {{

        }};

        searchAlgorithm.injectExpectedResults(map);
        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MISMATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForTwoEqualNotEmptySetsTest() {
        int[] collection1 = new int[]{15, 24, 33};
        int[] collection2 = new int[]{15, 24, 33};

        HashMap<Integer, Integer> map = new HashMap<>() {{
            put(15, 0);
            put(24, 1);
            put(33, 2);
        }};

        searchAlgorithm.injectExpectedResults(map);
        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForTwoSetsThatHaveOneElementInCommonTest() {
        int[] collection1 = new int[]{11, 24, 313};
        int[] collection2 = new int[]{15, 24, 33};

        HashMap<Integer, Integer> map = new HashMap<>() {{
            put(24, 1);
            put(15, 0);
            put(33, 2);
        }};

        searchAlgorithm.injectExpectedResults(map);
        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(TWENTY_PERCENT));
    }

    @Test
    void calculateJackardSimilarityForTwoSetsThatDifferWithOnlyOneElementTest() {
        int[] collection1 = new int[]{15, 214, 33};
        int[] collection2 = new int[]{15, 24, 33};

        HashMap<Integer, Integer> map = new HashMap<>() {{
            put(15, 0);
            put(24, 1);
            put(33, 2);
        }};

        searchAlgorithm.injectExpectedResults(map);
        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FIFTY_PERCENT));
    }

}

