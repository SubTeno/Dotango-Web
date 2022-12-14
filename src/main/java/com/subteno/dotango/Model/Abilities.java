package com.subteno.dotango.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abilities {
    public String img;
    public String dname;
    public String lore;
    public String desc;
    public List<String> CD;
    public String behaviour;
    public String bkbpierce;
    public List<String> MC;
}
