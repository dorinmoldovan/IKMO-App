package com.ikmo_app.objective_functions;

public class OFCreator {

    public static OF generateOF(String ofName) {
        OF of = null;
        switch(ofName) {
            case "OF 1":
                of = new OF1();
                break;
            case "OF 2":
                of = new OF2();
                break;
            case "OF 3":
                of = new OF3();
                break;
            case "OF 4":
                of = new OF4();
                break;
            case "OF 5":
                of = new OF5();
                break;
            case "OF 6":
                of = new OF6();
                break;
            case "OF 7":
                of = new OF7();
                break;
            case "OF 8":
                of = new OF8();
                break;
            case "OF 9":
                of = new OF9();
                break;
            default:
                break;
        }
        return of;
    }

}
