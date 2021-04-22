package com.example.beerspinnerinflate;

import java.util.ArrayList;
import java.util.List;

public class BeerList {

    List<String> getList(String beer){
        List<String> beerList = new ArrayList<>();

        if(beer.equals("amber")){
            beerList.add("Jack Amber");
            beerList.add("Red Moose");
        }else if(beer.equals("brown")){
            beerList.add("Jail Pale Ale");
            beerList.add("Gout Stout");
        }else if(beer.equals("dark")){
            beerList.add("Black Lager");
            beerList.add("Dry Stout");
        }else{
            beerList.add("Munich");
            beerList.add("Dortmunder");
        }
        return  beerList;
    }
}
