package com.example.wpzhao_mycarfootprint;

import java.util.Date;

public interface AddVisitDialogListener {
    // an interface to set the grounds of adding, delete and edit visits
    void addVisit(Visit visit);
    void editVisit(Visit visit, String name, Date date, String type, int amount, float price);

    void deleteVisit(Visit visit);
}
