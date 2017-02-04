package ru.pravvich.map.userModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReferenceBookTest {
    @Test
    public void whenAddOneElementThenSizeEqualOne() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");

        int result = book.size();
        assertThat(result, is(1));
    }

    @Test
    public void whenAddOneElementThenInsertMethodReturnTrue() {
        Book<String ,String> book = new ReferenceBook<>();
        boolean result = book.insert("key", "value");


        assertThat(result, is(true));
    }

    @Test
    public void whenAddTwoSameElementThenSecondInsertReturnFalse() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");

        boolean result = book.insert("key", "value");
        assertThat(result,is(false));
    }

    @Test
    public void whenAddTwoDifferentElementThenSizeEqualTwo() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");
        book.insert("key2", "value2");

        int result = book.size();
        assertThat(result, is(2));
    }

    @Test
    public void whenAddTwoDifferentElementThenBothInsertGetTrue() {
        Book<String ,String> book = new ReferenceBook<>();
        boolean resultFst = book.insert("key", "value");
        boolean resultScd = book.insert("key2", "value2");

        assertThat(resultFst, is(true));
        assertThat(resultScd, is(true));
    }

    @Test
    public void whenAddOneElementWithSameKayThenValueUpdate() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");
        book.insert("key", "value2");

        String result = book.get("key");
        assertThat(result, is("value2"));
    }

    @Test
    public void whenKeyIsExistThenGetMethodGetValueByThisKey() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");

        String result = book.get("key");
        assertThat(result, is("value"));
    }

    @Test
    public void whenDeleteElementByKetWhichExistThenDeleteMethodReturnTrue() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");

        boolean result = book.delete("key");
        assertThat(result, is(true));
    }

    @Test
    public void whenDeleteElementByKetWhichNotExistThenDeleteMethodReturnFalse() {
        Book<String ,String> book = new ReferenceBook<>();

        boolean result = book.delete("key");
        assertThat(result, is(false));
    }

    @Test
    public void whenDeleteElementByKeyWhichExistThenGetMethodWithThisKeyReturnNull() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key", "value");

        book.delete("key");
        String result = book.get("key");
        assertNull(result);
    }

    @Test
    public void whenIteratorWorkThenPassesByAllElementsList() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key1", "value1");
        book.insert("key2", "value2");
        book.insert("key3", "value3");
        Iterator<String> iterator = book.iterator();

        List<String> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("value1"));
        assertThat(result.get(1), is("value2"));
        assertThat(result.get(2), is("value3"));
    }

    @Test
    public void whenThen() {
        Book<String ,String> book = new ReferenceBook<>();
        book.insert("key1", "value1");
        book.insert("key2", "value2");
        book.insert("key3", "value3");
        book.insert("key4", "value4");
        book.insert("key5", "value5");
        book.insert("key6", "value6");
        book.insert("key7", "value7");
        book.insert("key8", "value8");
        book.insert("key9", "value9");
        book.insert("key10", "value10");
        book.insert("key11", "value11");
        book.insert("key12", "value12");
        book.insert("key13", "value13");
        book.insert("key14", "value14");
        book.insert("key15", "value15");
        book.insert("key16", "value16");
        book.insert("key17", "value17");

        int result = book.size();
        assertThat(result,is(17));

    }
}