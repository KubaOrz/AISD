package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pw.ee.exceptions.ElementNotFoundException;
import pl.edu.pw.ee.services.HashTable;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class HashQuadraticProbingTest {

    private HashOpenAdressing<Double> hash;

    @Before
    public void setUp() {
        this.hash = new HashQuadraticProbing<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> unusedHash = new HashQuadraticProbing<>(initialSize, 0.5, 0.5);

        // then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenBothConstantsEqualToZero() {
        //when
        HashTable<Double> unusedHash = new HashQuadraticProbing<>(10, 0, 0);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashQuadraticProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenPutNull() {
        //given
        Double elem = null;

        //when
        hash.put(elem);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyPutElems_WhenElemsNotInHash() {
        //given
        Double[] elems = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};

        //when
        for (Double elem : elems) {
            hash.put(elem);
        }
        int expectedNumberOfElems = 9;
        int actualNUmberOfElems = getNumOfElems(hash);

        //then
        Assert.assertEquals(expectedNumberOfElems, actualNUmberOfElems);
    }

    @Test
    public void should_CorrectlyPuElems_WhenLowHashInitSize() {
        //given
        HashOpenAdressing<Integer> hash3 = new HashQuadraticProbing<>(3, 0.5, 0.5);
        int expectedNumOfElems = 100;
        int expectedHashSize = 192;

        //when
        for (int i = 0; i < expectedNumOfElems; i++) {
            hash3.put(i);
        }
        int actualNumOfElems = getNumOfElems(hash3);
        int actualHashSize = hash3.getSize();

        //then
        Assert.assertEquals(expectedNumOfElems, actualNumOfElems);
        Assert.assertEquals(expectedHashSize, actualHashSize);
    }

    @Test
    public void should_CorrectlyPutElems_WhenSomeElemsInHash() {
        //given
        Double[] elems = {1.0, 2.0, 1.0, 4.0, 2.0, 6.0, 7.0, 1.0, 9.0};

        //when
        for (Double elem : elems) {
            hash.put(elem);
        }
        int expectedNumberOfElems = 6;
        int actualNumberOfElems = getNumOfElems(hash);

        //then
        Assert.assertEquals(expectedNumberOfElems, actualNumberOfElems);
    }

    @Test
    public void should_CorrectlyPutElems_WhenElemsHaveEqualHashCodes() {
        //given
        final int numberOfElems = 20;
        HashOpenAdressing<ElemEqualHashCode> hashEqualCodes = new HashQuadraticProbing<>(30, 0.5, 0.5);
        int expectedNumOfElems = numberOfElems;

        //when
        for (int i = 0; i < numberOfElems; i++) {
            hashEqualCodes.put(new ElemEqualHashCode(i));
        }
        int actualNumOfElems = getNumOfElems(hashEqualCodes);

        //then
        Assert.assertEquals(expectedNumOfElems, actualNumOfElems);
    }

    @Test
    public void should_CorrectlyPutElem_WhenElemCodeEqualsHashSize() {
        //given
        ElemEqualHashCode elem = new ElemEqualHashCode(839);
        HashOpenAdressing<ElemEqualHashCode> hash17 = new HashQuadraticProbing<>(17, 0.5, 0.5);
        int expectedNumOfElems = 1;

        //when
        hash17.put(elem);
        int actualNumOfElems = getNumOfElems(hash17);

        //then
        Assert.assertEquals(expectedNumOfElems, actualNumOfElems);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenGetNull() {
        //given
        Double elem = null;

        //when
        hash.get(elem);

        //then
        assert false;
    }

    @Test(expected = ElementNotFoundException.class)
    public void should_ThrowException_WhenGetElemNotInHash() {
        //given
        Double elem = 0.0;

        //when
        hash.get(elem);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyGetElem_WhenElemInHash() {
        //given
        Double elem = 1.3;
        Double expectedElem = elem;

        //when
        hash.put(elem);
        Double actualElem = hash.get(elem);

        //then
        Assert.assertEquals(expectedElem, actualElem);
    }

    @Test
    public void should_CorrectlyPutAndGetElems_WhenElemsHaveEqualHashCodes() {
        //given
        final int numberOfElems = 20;
        HashOpenAdressing<ElemEqualHashCode> hashEqualCodes = new HashQuadraticProbing<>(30, 0.5, 0.5);
        ElemEqualHashCode[] elems = new ElemEqualHashCode[numberOfElems];
        ElemEqualHashCode[] takenElems = new ElemEqualHashCode[numberOfElems];
        for (int i = 0; i < numberOfElems; i++) {
            elems[i] = new ElemEqualHashCode(i);
        }

        //when
        for (int i = 0; i < numberOfElems; i++) {
            hashEqualCodes.put(elems[i]);
        }

        for (int i = 0; i < numberOfElems; i++) {
            takenElems[i] = hashEqualCodes.get(elems[i]);
        }

        //then
        Assert.assertArrayEquals(elems, takenElems);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenDeleteNull() {
        //given
        Double elem = null;

        //when
        hash.delete(elem);

        //then
        assert false;
    }

    @Test(expected = ElementNotFoundException.class)
    public void should_ThrowException_WhenDeleteElemNotInHash() {
        //given
        Double elem = 0.0;

        //when
        hash.delete(elem);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyDeleteElem_WhenElemInHash() {
        //given
        Double elem = 0.1;
        int expectedNumOfElems = 0;

        //when
        hash.put(elem);
        hash.delete(elem);
        int actualNumOfElems = getNumOfElems(hash);

        //then
        Assert.assertEquals(expectedNumOfElems, actualNumOfElems);
    }

    @Test(expected = ElementNotFoundException.class)
    public void should_ThrowException_WhenGetDeletedElem() {
        //given
        Double elem = 0.1;

        //when
        hash.put(elem);
        hash.delete(elem);
        hash.get(elem);

        //then
        assert false;
    }

    @Test
    public void should_CorrectlyDoubleResize_WhenNeeded() {
        //given
        int hashSize = hash.getSize();
        int expectedHashSize = hashSize * 2;
        int expectedNumberOfElems = hashSize;

        //when
        for (int i = 0; i < hashSize; i++) {
            hash.put((double) i);
        }
        int actualHashSize = hash.getSize();
        int actualNumberOfElems = getNumOfElems(hash);

        //then
        Assert.assertEquals(expectedHashSize, actualHashSize);
        Assert.assertEquals(expectedNumberOfElems, actualNumberOfElems);
    }

    @Test
    public void should_CorrectlyPutAndDeleteAllStringElems() {
        //given
        int length = 200;
        HashOpenAdressing<String> hashWithStrings = new HashQuadraticProbing<>();
        String[] elems = new String[length];
        for (int i = 0; i < length; i++) {
            elems[i] = "String" + i;
        }
        int expectedNumOfElemsBeforeDel = length;
        int expectedNumOfElemsAfterDel = 0;

        //when
        for (int i = 0; i < length; i++) {
            hashWithStrings.put(elems[i]);
        }
        int actualNumOfElemsBeforeDel = getNumOfElems(hashWithStrings);

        for (int i = 0; i < length; i++) {
            hashWithStrings.delete(elems[i]);
        }
        int actualNumOfElemsAfterDel = getNumOfElems(hashWithStrings);

        //then
        Assert.assertEquals(expectedNumOfElemsBeforeDel, actualNumOfElemsBeforeDel);
        Assert.assertEquals(expectedNumOfElemsAfterDel, actualNumOfElemsAfterDel);

    }

    @Test
    public void should_CorrectlyPutAndDeleteElems_WhenElemsHaveEqualHashCodes() {
        //given
        final int numberOfElems = 20;
        HashOpenAdressing<ElemEqualHashCode> hashEqualCodes = new HashQuadraticProbing<>(30, 0.5, 0.5);
        ElemEqualHashCode[] elems = new ElemEqualHashCode[numberOfElems];
        for (int i = 0; i < numberOfElems; i++) {
            elems[i] = new ElemEqualHashCode(i);
        }
        int expectedNumOfElemsAfterDel = 0;

        //when
        for (int i = 0; i < numberOfElems; i++) {
            hashEqualCodes.put(elems[i]);
        }

        for (int i = 0; i < numberOfElems; i++) {
            hashEqualCodes.delete(elems[i]);
        }
        int actualNumOfElemsAfterDel = getNumOfElems(hashEqualCodes);

        //then
        Assert.assertEquals(expectedNumOfElemsAfterDel, actualNumOfElemsAfterDel);
    }

    @Test
    public void should_CorrectlyDeleteFirstElem_WhenElemsHaveEqualHashCodes() {
        //given
        HashOpenAdressing<ElemEqualHashCode> hashEqualElems = new HashQuadraticProbing<>(10, 0.5, 0.5);
        ElemEqualHashCode elem1 = new ElemEqualHashCode(10);
        ElemEqualHashCode elem2 = new ElemEqualHashCode(20);
        ElemEqualHashCode elem3 = new ElemEqualHashCode(30);
        int expectedNumOfElemsAfterDel = 2;

        //when
        hashEqualElems.put(elem1);
        hashEqualElems.put(elem2);
        hashEqualElems.put(elem3);
        hashEqualElems.delete(elem1);
        int actualNumOfElemsAfterDel = getNumOfElems(hashEqualElems);

        //then
        Assert.assertEquals(expectedNumOfElemsAfterDel, actualNumOfElemsAfterDel);
    }

    @Test
    public void should_CorrectlyDeleteSecondElem_WhenElemsHaveEqualHashCodes() {
        //given
        HashOpenAdressing<ElemEqualHashCode> hashEqualElems = new HashQuadraticProbing<>(10, 0.5, 0.5);
        ElemEqualHashCode elem1 = new ElemEqualHashCode(10);
        ElemEqualHashCode elem2 = new ElemEqualHashCode(20);
        ElemEqualHashCode elem3 = new ElemEqualHashCode(30);
        int expectedNumOfElemsAfterDel = 2;

        //when
        hashEqualElems.put(elem1);
        hashEqualElems.put(elem2);
        hashEqualElems.put(elem3);
        hashEqualElems.delete(elem2);
        int actualNumOfElemsAfterDel = getNumOfElems(hashEqualElems);

        //then
        Assert.assertEquals(expectedNumOfElemsAfterDel, actualNumOfElemsAfterDel);
    }

    @Test
    public void should_CorrectlyDeleteLastElem_WhenElemsHaveEqualHashCodes() {
        //given
        HashOpenAdressing<ElemEqualHashCode> hashEqualElems = new HashQuadraticProbing<>(10, 0.5, 0.5);
        ElemEqualHashCode elem1 = new ElemEqualHashCode(10);
        ElemEqualHashCode elem2 = new ElemEqualHashCode(20);
        ElemEqualHashCode elem3 = new ElemEqualHashCode(30);
        int expectedNumOfElemsAfterDel = 2;

        //when
        hashEqualElems.put(elem1);
        hashEqualElems.put(elem2);
        hashEqualElems.put(elem3);
        hashEqualElems.delete(elem3);
        int actualNumOfElemsAfterDel = getNumOfElems(hashEqualElems);

        //then
        Assert.assertEquals(expectedNumOfElemsAfterDel, actualNumOfElemsAfterDel);
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
