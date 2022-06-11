package com.ikmo_app.model;

public class KangaroosPair {

    private Kangaroo male;
    private Kangaroo female;

    public KangaroosPair(Kangaroo male, Kangaroo female) {
        this.male = male;
        this.female = female;
    }

    public Kangaroo getMale() {
        return male;
    }

    public void setMale(Kangaroo male) {
        this.male = male;
    }

    public Kangaroo getFemale() {
        return female;
    }

    public void setFemale(Kangaroo female) {
        this.female = female;
    }
}
