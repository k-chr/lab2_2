import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import helpers.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimilarityFinderBehaviorTestCases {

    private SimilarityFinder finder = null;
    private SequenceSearcherMock searchAlgorithm = null;

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
        seq1 = new int[]{};
        seq2 = new int[]{};
        finder = new SimilarityFinder(null);

        assertThrows(NullPointerException.class, ()->finder.calculateJackardSimilarity(seq1, seq2));
    }

    @Test
    void checkIfSequenceSearcherIsReferencedBySimilarityTest() {

    }

    @Test
    void checkSimilarityFinderBehaviorIfFirstSequenceIsNullTest() {

    }

    @Test
    void checkSimilarityFinderBehaviorIfSecondSequenceIsNullTest() {

    }

}
