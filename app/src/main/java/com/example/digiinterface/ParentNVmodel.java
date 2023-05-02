package com.example.digiinterface;

import java.util.List;

public class ParentNVmodel {
    String title;
    List<ChildNVmodel> childNVmodels;

    public ParentNVmodel(String title, List<ChildNVmodel> childNVmodels) {
        this.title = title;
        this.childNVmodels = childNVmodels;

    }
}
