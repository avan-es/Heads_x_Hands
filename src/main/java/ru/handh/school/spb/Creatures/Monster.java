package ru.handh.school.spb.Creatures;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Monster extends Creature {


    public Monster(int id, String name, int attack, int defense, int health, int damage, int attackPower) {
        super(id, name, attack, defense, health, damage, attackPower);
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name +
                ", attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", damage=" + damage +
                ", attackPower=" + attackPower +
                '}';
    }
}
