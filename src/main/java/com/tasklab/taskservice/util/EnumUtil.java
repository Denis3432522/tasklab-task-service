package com.tasklab.taskservice.util;

import com.tasklab.taskservice.enumeration.Named;

import java.util.NoSuchElementException;

public class EnumUtil {

    public static <T extends Named> T getByName(String srcName, T[] values) {
        for(T value : values) {
            if(value.getName().equals(srcName))
                return value;
        }
        throw new NoSuchElementException("no such enum");
    }
}
