import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import helpers.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderStateTestCases {

    private static SimilarityFinder finder = null;
    private static SequenceSearcher searchAlgorithm = null;

    private static final double FULL_MATCHING_SETS = 1.0;
    private static final double FULL_MISMATCHING_SETS = 0.0;
    private static final double TWENTY_PERCENT = 0.2;
    private static final double EIGHTY_PERCENT = 0.8;

    @BeforeAll
    static void init(){
        searchAlgorithm = new SequenceSearcherMock();
        finder = new SimilarityFinder(searchAlgorithm);
    }

    @Test
    void calculateJackardSimilarityForTwoEmptySetsTest(){
        int[] collection1 = new int[]{};
        int[] collection2 = new int[]{};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForTwoDisjointSetsTest(){
        int[] collection1 = new int[]{15,24,33};
        int[] collection2 = new int[]{66,75,43};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MISMATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForFirstEmptySetAndSecondNotEmptyTest(){
        int[] collection1 = new int[]{};
        int[] collection2 = new int[]{66,75,43};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MISMATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForFirstNotEmptySetAndSecondEmptyTest(){
        int[] collection1 = new int[]{15,24,33};
        int[] collection2 = new int[]{};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MISMATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForTwoEqualNotEmptySetsTest(){
        int[] collection1 = new int[]{15,24,33};
        int[] collection2 = new int[]{15,24,33};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(FULL_MATCHING_SETS));
    }

    @Test
    void calculateJackardSimilarityForTwoSetsThatHaveOneElementInCommonTest(){
        int[] collection1 = new int[]{11,24,313};
        int[] collection2 = new int[]{15,24,33};

        double result = finder.calculateJackardSimilarity(collection1, collection2);
        assertThat(result, is(TWENTY_PERCENT));
    }

    @Test
    void calculateJackardSimilarityForTwoSetsThatDifferWithOnlyOneElementTest(){

    }

}

