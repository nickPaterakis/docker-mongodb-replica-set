package com.countryinfo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "countries")
public class Country {

    @Id
    private String id;
    private String code;
    private String name;
}
