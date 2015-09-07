
package com.converter.use;

import com.converter.utils.Converter;
import com.converter.utils.ConverterCSV;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        
        Book b = new Book(1, "The end", 30.00);
        Book b1 = new Book(2, "A Clockwork Orange", 40.00);
        Book b2 = new Book(3, "I Am America ", 34.00);
        Book b3 = new Book(4, "Neverwhere", 70.00);
        Book b4 = new Book(5, "The Grapes of Wrath", 98.00);
        Book b5 = new Book(6, "A Confederacy of Dunces ", 32.00);
        Book b6 = new Book();
        
        List<Book> books = new ArrayList();
        List<Book> books1 = new ArrayList();
        books.add(b6);
        books.add(b1);
        books.add(b6);
        books.add(b3);
        books.add(b4);
        books.add(b5);
        Book b8 = null;
        
        Converter c = new ConverterCSV();
        c.produceCVS("o.csv", b);
        c.produceCVS("os.csv", books1);
        
        
    }
    
}
