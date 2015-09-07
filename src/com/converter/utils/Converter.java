
package com.converter.utils;

import java.io.OutputStream;

public interface Converter {
    
    /**
     * Transform object in CSV file.
    */
    OutputStream produceCVS(String fileName, Object o);
    
    
}
