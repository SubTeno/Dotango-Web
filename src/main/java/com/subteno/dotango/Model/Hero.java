package com.subteno.dotango.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hero {
    public String name;
    public String localized_name;
    public String img;
    public List<String> roles;
    public String primary_attr;
    public int move_speed;
    public int id;
    public HeroAbilities hero_abilities;
}
