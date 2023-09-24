package ru.handh.school.spb.Creatures;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User extends Creature {

    private byte painkiller = 4;

    private int painkillerPower;

    private final int MAX_HEALTH;

    public User(int id, String name, int attack, int defense, int health, int damage, int attackPower) {
        super(id, name, attack, defense, health, damage, attackPower);
        this.painkillerPower = (int) Math.round(health * 0.3);
        this.MAX_HEALTH = health;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name +
                ", attack=" + attack +
                ", defense=" + defense +
                ", health=" + health +
                ", damage=" + damage +
                ", attackPower=" + attackPower +
                ", painkiller=" + painkiller +
                ", painkiller=" + painkillerPower + '}';
    }
}
