package com.example.testSoft.utils;

import com.example.testSoft.error.IllegalRequestDataException;
import com.example.testSoft.util.HasId;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }
}
