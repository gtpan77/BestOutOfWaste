package com.example.gauravsharma.bestoutofwaste.Model;

import java.util.ArrayList;

public class UserScrapItem {
    public String id;
    public ArrayList<String> list;
    public String status;

    public UserScrapItem() {

    }

    public UserScrapItem(String id, ArrayList<String> list, String status){
        this.id = id;
        this.list = list;
        this.status = status;
    }
}
