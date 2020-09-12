package pl.javastart.sellegro.model;

import lombok.*;

@Setter
@Getter
@ToString
public class AuctionFilters {

    private String title;
    private String carMaker;
    private String carModel;
    private String color;
}