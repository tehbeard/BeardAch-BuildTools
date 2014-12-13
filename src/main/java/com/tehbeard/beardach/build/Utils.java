package com.tehbeard.beardach.build;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

@SuppressWarnings("restriction")
public class Utils {

    public static Set<TypeMirror> getSuperTypes(Types typeUtils,TypeMirror type){
        Set<TypeMirror> val = new HashSet<TypeMirror>();
        for(TypeMirror superType : typeUtils.directSupertypes(type)){
            val.add(superType);
            val.addAll(getSuperTypes(typeUtils, superType));
        }
        return val;
    }

    public static boolean typeImplements(ProcessingEnvironment processingEnv, TypeMirror type, String superTypeFQN) {
        Set<TypeMirror> superTypes = getSuperTypes(processingEnv.getTypeUtils(), type);
        TypeMirror superTypeToCheck = processingEnv.getElementUtils().getTypeElement("com.tehbeard.beardach.datasource.configurable.IConfigurable").asType();
        return superTypes.contains(superTypeToCheck);
        
    }
    
    
}
