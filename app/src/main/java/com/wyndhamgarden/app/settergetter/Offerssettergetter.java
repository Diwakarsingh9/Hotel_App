package com.wyndhamgarden.app.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 9/17/2015.
 */
public class Offerssettergetter {

    @SerializedName("response")
    public String response;

    @SerializedName("message")

    public List<Inneroffers> inneroffers= new ArrayList<Inneroffers>();
}
