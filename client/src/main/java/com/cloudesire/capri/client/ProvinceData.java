package com.cloudesire.capri.client;

public class ProvinceData
{
    private String province;

    private String abbreviation;

    private String region;

    public ProvinceData( String province, String abbreviation, String region )
    {
        this.province = province;
        this.abbreviation = abbreviation;
        this.region = region;
    }

    public ProvinceData()
    {
    }

    public String getProvince()
    {
        return province;
    }

    public void setProvince( String province )
    {
        this.province = province;
    }

    public String getAbbreviation()
    {
        return abbreviation;
    }

    public void setAbbreviation( String abbreviation )
    {
        this.abbreviation = abbreviation;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion( String region )
    {
        this.region = region;
    }
}
