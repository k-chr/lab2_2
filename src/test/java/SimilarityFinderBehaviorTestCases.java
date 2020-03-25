import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import helpers.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimilarityFinderBehaviorTestCases {

    private SimilarityFinder finder = null;
    private SequenceSearcherMock searchAlgorithm = null;
    private static final int ZERO = 0;

    @BeforeEach
    void init() {
        searchAlgorithm = new SequenceSearcherMock();
        finder = new SimilarityFinder(searchAlgorithm);
    }

    @Test
    void checkSimilarityFinderBehaviorIfBothSequencesAreNotNullTest() {
        int[] seq1, seq2;
        seq1 = new int[]{};
        seq2 = new int[]{};

        assertDoesNotThrow(()->finder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void checkSimilarityFinderBehaviorIfBothSequencesAreNullTest() {
        int[] seq1, seq2;
        seq1 = null;
        seq2 = null;

        assertThrows(NullPointerException.class, ()->finder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void checkSimilarityFinderBehaviorIfAppliedInterfaceIsNullTest() {
        int[] seq1, seq2;
        seq1 = new int[]{1,1,1};
        seq2 = new int[]{2,2,2};
        finder = new SimilarityFinder(null);

        assertThrows(NullPointerException.class, ()->finder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void checkIfSequenceSearcherIsReferencedBySimilarityTest() {
        int[] seq1, seq2;
        seq1 = new int[]{1, 2, 3};
        seq2 = new int[]{2, 3, 4};

        finder.calculateJackardSimilarity(seq1, seq2);

        assertThat(searchAlgorithm.searchMethodCalls, is(greaterThan(ZERO)));
    }

    @Test
    void checkSimilarityFinderBehaviorIfFirstSequenceIsNullTest() {
        int[] seq1, seq2;
        seq1 = null;
        seq2 = new int[]{2, 4};

        assertThrows(NullPointerException.class, ()->finder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void checkSimilarityFinderBehaviorIfSecondSequenceIsNullTest() {
        int[] seq1, seq2;
        seq1 = new int[]{4, 2};
        seq2 = null;

        assertThrows(IllegalArgumentException.class, ()->finder.calculateJackardSimilarity(seq1, seq2));
    }

}
