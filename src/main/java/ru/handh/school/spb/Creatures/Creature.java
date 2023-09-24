package ru.handh.school.spb.Creatures;

/**
 * Creature - родительский класс с ключевыми полями.
 * Использование: для создания разновидностей существ.
 * */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Creature {

    protected int id;

    protected String name;

    /**
     * Атака существа. Допустимые значения: [1; 30].
     * */
    protected int attack;

    /**
     * Защита существа. Допустимые значения: [1; 30].
     * */
    protected int defense;

    /**
     * Здоровье существа. Допустимые значения: [0; N). N - максимальное значение диапазона здоровья.
     * По умолчанию равно 100.
     * */
    protected int health;

    /**
     * Урон - максимальный урон, который может наносить существо.
     * Допустимое значение (M; N). Значение по умолчанию (5; 15).
     * */
    protected int damage;

    /** Модификатор атаки существа. Равен разности Атаки атакующего и Защиты защищающегося плюс 1.
     * */
    protected int attackPower;

}
