package xyz.ibudai.authority.manage;

import java.lang.annotation.Annotation;

public interface PermitHandler {

    String name();

    /**
     * Has permit.
     *
     * @param annotation the annotation
     * @param arg        the arg
     * @return the boolean
     */
    boolean lackPermit(Annotation annotation, Object arg);
}
