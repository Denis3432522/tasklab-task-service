package com.tasklab.taskservice.util;

import com.tasklab.taskservice.enumeration.Named;

import java.util.NoSuchElementException;

public class EnumUtil {

    public static <T extends Named> T getByName(String srcName, T[] values) {
        for(T access : values) {
            if(access.getName().equals(srcName))
                return access;
        }
        throw new NoSuchElementException("no such enum");
    }
}
