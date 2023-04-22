package com.example.tfc;

public class Metal
{
    private String name;
    private int imageResource;
    public int amount;
    public float partLow;
    public float partHigh;

    public Metal(String name, int imageResource, float partLow, float partHigh)
    {
        this.name = name;
        this.imageResource = imageResource;
        this.amount = 0;
        this.partLow = partLow/100;
        this.partHigh = partHigh/100;
    }

    public String getName() {
        return this.name;
    }

    public int getImageResource() {
        return this.imageResource;
    }
}
