package com.example.digiinterface;

import java.util.List;

public class ParentPCmodel {

    String title;
    List<ChildPCmodel> childNewVideomodels;

    public ParentPCmodel(String title, List<ChildPCmodel> childNewVideomodels)
    {
        this.title = title;
        this.childNewVideomodels = childNewVideomodels;

    }
}
