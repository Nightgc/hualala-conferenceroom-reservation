package com.hualala.conference.global;

import lombok.Setter;

@Setter
public class GreatThree extends RuntimeException{

    protected String code;
    public GreatThree(String code,String msg){
        super(msg);

        this.code=code;
    }

}
