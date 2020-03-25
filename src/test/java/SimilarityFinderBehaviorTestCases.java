import edu.iis.mto.search.SequenceSearcher;
import edu.iis.mto.similarity.SimilarityFinder;
import helpers.SequenceSearcherMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimilarityFinderBehaviorTestCases {

    private SimilarityFinder finder = null;
    private SequenceSearcherMock searchAlgorithm = null;

    @BeforeEach
    void init(){
        searchAlgorithm = new SequenceSearcherMock();
        finder = new SimilarityFinder(searchAlgorithm);
    }


    @Test
    void checkSimilarityFinderBehaviorIfBothSequencesAreNullTest(){

    }

    @Test
    void checkSimilarityFinderBehaviorIfAppliedInterfaceIsNullTest(){

    }

    @Test
    void checkIfSequenceSearcherIsReferencedBySimilarityTest(){

    }

    @Test
    void checkSimilarityFinderBehaviorIfFirstSequenceIsNullTest(){

    }

    @Test
    void checkSimilarityFinderBehaviorIfSecondSequenceIsNullTest(){

    }

}
