package ru.handh.school.spb.Interface;

/**
 * Интерфейс с основными методами для реализации.
 * */

import ru.handh.school.spb.Creatures.Creature;
import ru.handh.school.spb.Creatures.Monster;
import ru.handh.school.spb.Creatures.User;

import java.util.List;

public interface Action {

    /**
     * Метод создает объект класса Creature.
     */
    Creature createCreature(int id, String name);

    /**
     * Метод генерирует псевдослучайные значения для характеристик Creature.
     * Атака (attack) [1; 30];
     * Защита (defense) [1; 30];
     * Здоровье (health) [1; 100];
     * Урон (damage) (5; 15).
     * */
    List<Integer> generateParam();

    /**
     * Метод создаёт объект класса Monster.
     * */
    Monster createMonster();

    /**
     * Метод создаёт объект класса User.
     * */
    User createUser();

    /**
     * Метод вычисляет и устанавливает Модификатор атаки по формуле:
     * Атака атакующего - Защита защищающегося + 1;
     * */
    void setAttackPower(Creature creatureOne, Creature creatureTwo);

    /**
     * Метод борьбы между существами.
     * */
    void fight ();

    /**
     * Метод атаки одного существа - другого.
     * */
    boolean attack(Creature attack, Creature defend);

    /**
     * Проверка уровня здоровья Пользователя. Позволяет предложить аптечку.
     */
    void checkHealth(User user);

    /**
     * Метод обработки результатов боя.
     */
    void finish(Creature monster, Creature user);










}
