package ru.handh.school.spb.Interface;

import ru.handh.school.spb.Creatures.Creature;
import ru.handh.school.spb.Creatures.Monster;
import ru.handh.school.spb.Creatures.User;

public interface Action {

    Monster createMonster ();

    User createUser ();

    Creature createCreature(int id, String name);

    //Вычислить модификатор атаки (N)
    int calculateAttackPower (Creature attack, Creature defend);


}
