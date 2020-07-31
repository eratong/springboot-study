package com.ontg.codeclean.codeclean;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class Tests {

    @Test
    public void s(){
        List<Object> objects = Collections.emptyList();
        System.out.println();
    }

    public double xProjection(Integer p1, Integer p2) {
        assert p1 != null : "p1 should not be null";
        assert p2 != null : "p2 should not be null";
        return (p2-p1)*1.5;
    }
}
