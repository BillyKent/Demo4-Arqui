package com.software.miedo.demo4.model;

import java.io.Serializable;

public class Entry implements Serializable {

    private Boolean edited;
    private Boolean canEdit;
    private String id;

    public Entry() {
    }

    public Boolean getEdited() {
        return edited;
    }

    public void setEdited(Boolean edited) {
        this.edited = edited;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
