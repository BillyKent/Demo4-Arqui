package com.software.miedo.demo4.model;

import java.io.Serializable;

public class ComentarioResponse implements Serializable {

    private Entry entry;

    public ComentarioResponse() {
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }
}
