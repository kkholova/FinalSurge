package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Profile {
    String gender;
    String birthday;
    String weight;
    String weightMeasure;
    String country;
    String region;
    String city;
    String zip;

    public Profile(String gender,String birthday,String weight, String weightMeasure,String country,String region,String city,String zip){
        this.gender = gender;
        this.birthday = birthday;
        this.weight = weight;
        this.weightMeasure = weightMeasure;
        this.country = country;
        this.region = region;
        this.city = city;
        this.zip = zip;
    }
}
