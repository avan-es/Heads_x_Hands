package ru.handh.school.spb.Interface;

import ru.handh.school.spb.Creatures.Creature;
import ru.handh.school.spb.Creatures.Monster;
import ru.handh.school.spb.Creatures.User;

import java.util.List;

public interface Action {

    void fight ();

    boolean attack (Creature attack, Creature defend);

    void checkHealth (User user);

    void finish(Creature monster, Creature user);
    Monster createMonster ();

    User createUser ();

    Creature createCreature(int id, String name);

    //Вычислить модификатор атаки (N)
    int calculateAttackPower (Creature attack, Creature defend);

    List<Integer> generateParam ();


}
