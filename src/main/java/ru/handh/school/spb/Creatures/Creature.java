package ru.handh.school.spb.Creatures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Creature {

    protected int id;

    protected String name;

    protected int attack;

    protected int defense;

    protected int health;

    protected int damage;

    protected int attackPower;

}
