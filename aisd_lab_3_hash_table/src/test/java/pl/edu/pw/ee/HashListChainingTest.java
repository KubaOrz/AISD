package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashListChainingTest {

    private final int DELTA = 0;
    private HashListChaining<String> hashList;
    private HashListChaining<String> hashListSizeOne;

    @Before
    public void setUp() {
        hashList = new HashListChaining<>(10);
        hashListSizeOne = new HashListChaining<>(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitWithZero() {

        //when
        HashListChaining<String> hashWithZero = new HashListChaining<>(0);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInitWithNegative() {

        //when
        HashListChaining<String> hashWithZero = new HashListChaining<>(-1);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddNull() {

        //when
        hashList.add(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGetNull() {

        //when
        hashList.get(null);

        //then
        assert false;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDeleteNull() {

        //when
        hashList.delete(null);

        //then
        assert false;
    }

    @Test
    public void shouldReturnCountWhenAddElem() {

        //given
        String word = "hash";

        //when
        hashList.add(word);
        int expected = 1;
        int result = hashList.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenAddTwoDiffElem() {

        //given
        String word1 = "hash";
        String word2 = "bash";

        //when
        hashList.add(word1);
        hashList.add(word2);
        int expected = 2;
        int result = hashList.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenAddTwoEqualElem() {

        //given
        String word1 = "hash";
        String word2 = word1;

        //when
        hashList.add(word1);
        hashList.add(word2);
        int expected = 1;
        int result = hashList.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenAddSomeElem() {

        //given
        String[] words = {"Iza", "z", "Matiza", "Agata", "z", "Fiata"};

        //when
        for (String word : words) {
            hashList.add(word);
        }
        int expected = 5;
        int result = hashList.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenAddElemAtOneIndex() {

        //given
        String word1 = "nie";
        String word2 = "wiem";

        //when
        hashListSizeOne.add(word1);
        hashListSizeOne.add(word2);
        int expected = 2;
        int result = hashListSizeOne.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnWordWhenWordInHash() {

        //given
        String word = "topinambur";

        //when
        hashList.add(word);
        String expected = word;
        String result = hashList.get(word);

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnNullWhenWordNotInHash() {

        //given
        String word = "topinambur";

        //when
        String result = hashList.get(word);
        String expected = null;

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldPassWhenDeleteWordNotInHash() {

        //when
        hashList.delete("abc");

        //then
        assert true;
    }

    @Test
    public void shouldPassWhenDoubleDeleteWord() {

        //when
        hashList.add("abc");
        hashList.delete("abc");
        hashList.delete("abc");

        //then
        assert true;
    }

    @Test
    public void shouldReturnWordWhenAddTwoEqualWords() {

        //given
        String word1 = "Alcatraz";
        String word2 = word1;

        //when
        hashList.add(word1);
        hashList.add(word2);
        String expected = word1;
        String result = hashList.get(word1);

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnWordWhenAddElemAtOneIndex() {

        //given
        String word1 = "nie";
        String word2 = "wiem";
        String word3 = "jak";

        //when
        hashListSizeOne.add(word1);
        hashListSizeOne.add(word2);
        hashListSizeOne.add(word3);
        String expected = word3;
        String result = hashListSizeOne.get(word3);

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenAddAndDeleteElem() {

        //given
        String word = "Alcatraz";

        //when
        hashList.add(word);
        hashList.delete(word);
        int expected = 0;
        int result = hashList.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenAddAndDeleteSomeElem() {

        //given
        String[] words = {"Iza", "z", "Matiza", "Agata", "z", "Fiata"};

        //when
        for (String word : words) {
            hashList.add(word);
        }
        hashList.delete("z");
        hashList.delete("Fiata");
        hashList.delete("Tego nie ma w haszu");
        int expected = 3;
        int result = hashList.countElements();

        //then
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCountWhenDeleteFromOneIndex() {

        //given
        String[] words = {"Gwiazda", "w", "górze", "lśni"};

        //when
        for (String word : words) {
            hashListSizeOne.add(word);
        }
        hashListSizeOne.delete("Gwiazda");
        hashListSizeOne.delete("lśni");
        int expectedSize = 2;
        int resultSize = hashListSizeOne.countElements();
        String expectedWord = null;
        String resultWord1 = hashListSizeOne.get("Gwiazda");
        String resultWord2 = hashListSizeOne.get("lśni");

        //then
        Assert.assertEquals(expectedSize, resultSize);
        Assert.assertEquals(expectedWord, resultWord1);
        Assert.assertEquals(expectedWord, resultWord2);
    }

    @Test
    public void shouldReturnLoadFactorWhenAddOneElem() {

        //given
        String word = "alabaster";

        //when
        hashList.add(word);
        double expected = 0.1;
        double result = hashList.countLoadFactor();

        //then
        Assert.assertEquals(expected, result, DELTA);
    }

    @Test
    public void shouldReturnLoadFactorWhenAddSomeElem() {

        //given
        String[] words = {"Gwiazda", "w", "górze", "lśni"};

        //when
        for (String word : words) {
            hashList.add(word);
        }
        double expected = 0.4;
        double result = hashList.countLoadFactor();

        //then
        Assert.assertEquals(expected, result, DELTA);
    }

}
