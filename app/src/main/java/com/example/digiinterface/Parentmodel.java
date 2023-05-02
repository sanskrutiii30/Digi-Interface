package com.example.digiinterface;

import java.util.List;

public class Parentmodel {
    String title;
    List<Childmodel> childmodels;

    public Parentmodel(String title, List<Childmodel> childmodels) {
        this.title = title;
        this.childmodels = childmodels;

    }
}
