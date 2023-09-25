package ru.handh.school.spb.Services;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.handh.school.spb.Creatures.Creature;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardModeHeadsXHandsServiceTest {

    private final int DEFAULT_ATTACK_POWER = 0;

    private StandardModeHeadsXHandsService service = new StandardModeHeadsXHandsService();


    @Test
    @DisplayName("Генерация параметров для Creature в разрешенных диапазонах (100 существ).")
    void createCreature() {
        int id = 0;
        List<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String name = "Creature_" + ++id;
            Creature creature = service.createCreature(id, name);
            creatures.add(creature);
        }

        System.out.println("Сгенерировано " + creatures.size() + " существ.");
        for (Creature creature: creatures) {
            assertTrue(creature.getAttack() >= 1 && creature.getAttack() <= 30); //Атака [1; 30]
            assertTrue(creature.getDefense() >= 1 && creature.getDefense() <= 30); //Защита [1; 30]
            assertTrue(creature.getHealth() >= 1 && creature.getHealth() <= 100); //Здоровье [1; 100]
            assertTrue(creature.getDamage() > 5 && creature.getDamage() < 15); // Урон (5; 15)
            assertTrue(creature.getAttackPower() == 0); // Модификатор атаки = 0
        }

    }

    @Test
    void generateParam() {
    }

    @Test
    void createMonster() {
    }

    @Test
    void createUser() {
    }

    @Test
    void setAttackPower() {
    }

    @Test
    void fight() {
    }

    @Test
    void attack() {
    }

    @Test
    void checkHealth() {
    }

    @Test
    void finish() {
    }
}