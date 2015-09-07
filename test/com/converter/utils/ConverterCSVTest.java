package com.converter.utils;

import com.converter.use.Book;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author MEUS DOCUMENTOS
 */
public class ConverterCSVTest {

    public ConverterCSVTest() {
    }

    @Test
    public void testProduceCVSObjectNull() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Book b = null;
        StringBuilder expContent = new StringBuilder("");
        ConverterCSV instance = new ConverterCSV();
        instance.produceCVS("teste.csv", b);
        Field atribute = null;

        atribute = ConverterCSV.class.getDeclaredField("content");
        atribute.setAccessible(true);

        StringBuilder resultContent = (StringBuilder) atribute.get(instance);
        assertEquals(expContent.toString(), resultContent.toString());

    }

    @Test
    public void testProduceCVSObject() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Book b = new Book(1, "The end", 30.00);
                
        StringBuilder expContent = new StringBuilder("Id,Title,Price\n1,The end,30.0");
        ConverterCSV instance = new ConverterCSV();
        instance.produceCVS("teste.csv", b);
        Field atribute = null;

        atribute = ConverterCSV.class.getDeclaredField("content");
        atribute.setAccessible(true);

        StringBuilder resultContent = (StringBuilder) atribute.get(instance);
        assertEquals(expContent.toString(), resultContent.toString());

    }
    
    @Test
    public void testProduceCVSList() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        Book b = new Book(1, "The end", 30.00);
        Book b1 = new Book(2, "A Clockwork Orange", 40.00);
        List<Book> books = new ArrayList();
        books.add(b);
        books.add(b1);
        StringBuilder expContent = new StringBuilder("Id,Title,Price\n1,The end,30.0\n2,A Clockwork Orange,40.0");
        ConverterCSV instance = new ConverterCSV();
        instance.produceCVS("teste.csv", books);
        Field atribute = null;

        atribute = ConverterCSV.class.getDeclaredField("content");
        atribute.setAccessible(true);

        StringBuilder resultContent = (StringBuilder) atribute.get(instance);
        assertEquals(expContent.toString(), resultContent.toString());

    }
    
    @Test
    public void testProduceCVSListEmpty() throws IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
        List<Book> booksEmpty = new ArrayList();
        StringBuilder expContent = new StringBuilder("");
        ConverterCSV instance = new ConverterCSV();
        instance.produceCVS("teste.csv", booksEmpty);
        Field atribute = null;

        atribute = ConverterCSV.class.getDeclaredField("content");
        atribute.setAccessible(true);

        StringBuilder resultContent = (StringBuilder) atribute.get(instance);
        assertEquals(expContent.toString(), resultContent.toString());

    }

}
