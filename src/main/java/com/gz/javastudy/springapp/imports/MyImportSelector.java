package com.gz.javastudy.springapp.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author gaozhen
 * @title: MyImportSelector
 * @projectName study-java
 * @description: TODO
 * @date 2019-12-0320:10
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{StudentDaoAop.class.getName()};
    }
}
