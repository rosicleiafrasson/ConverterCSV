package com.converter.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

public class ConverterCSV implements Converter {

    private StringBuilder content;
    private Logger log = Logger.getLogger(ConverterCSV.class.getName());

    @Override
    public OutputStream produceCVS(String fileName, Object o) {
        content = new StringBuilder();

        if (o instanceof List) {
            produceCVSList((List) o);
        } else {
            produceCVSObject(o);
        }
      
        return writeFile(fileName);
    }

    private void produceCVSObject(Object o) {
        if(o != null){
            Class classType = o.getClass();
            buildHeader(classType);
            buildContent(o);
            content.deleteCharAt(content.length() - 1);
        }else{
            log.warning("Object is null");
        }
    }

    private void produceCVSList(List<Object> objects) {
        if (objects.isEmpty()) {
            log.warning("List is empty");
        } else {
            Class classType = objects.get(0).getClass();
            buildHeader(classType);
            for (Object o : objects) {
                buildContent(o);
            }
            content.deleteCharAt(content.length() - 1);
        }
    }

    private void buildHeader(Class classType) {
        Method[] methods = classType.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                if (m.getName().startsWith("get")) {
                    content.append(m.getName().substring(3)).append(',');
                } else if (m.getName().startsWith("is")) {
                    content.append(m.getName().substring(2)).append(',');
                }
            }
        }
        content.deleteCharAt(content.length() - 1);
        content.append('\n');
    }

    private void buildContent(Object o) {
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method m : methods) {
            if (m.getParameterTypes().length == 0) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    try {
                        if (m.invoke(o) != null) {
                            content.append(m.invoke(o).toString()).append(',');
                        } else {
                            content.append(',');
                        }
                    } catch (Exception ex) {
                        log.severe("Error in the invocation method");

                    }
                }
            }
        }
        content.deleteCharAt(content.length() - 1);
        content.append('\n');
    }

    private OutputStream writeFile(String fileName) {
        try {
            OutputStream fos = new FileOutputStream(fileName);
            fos.write(content.toString().getBytes());
            return fos;
        } catch (IOException ex) {
            log.severe("Error in the write file");
        }
        return null;
    }

}
