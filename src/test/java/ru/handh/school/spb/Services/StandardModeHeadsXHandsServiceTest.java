package ru.handh.school.spb.Services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.handh.school.spb.Creatures.Creature;
import ru.handh.school.spb.Creatures.Monster;
import ru.handh.school.spb.Creatures.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardModeHeadsXHandsServiceTest {

    private final int DEFAULT_ATTACK_POWER = 0;

    private StandardModeHeadsXHandsService service = new StandardModeHeadsXHandsService();

    private int id;

    private StringBuilder name = new StringBuilder();

    @BeforeEach
    void setUp() {
        id = 0;
        name.delete(0, name.length());
    }

    @Test
    @DisplayName("Генерация параметров для Creature в разрешенных диапазонах (100 существ).")
    void createCreature() {
        int id = 0;
        name.append("Creature_" + ++id);
        int attack;
        int defence;
        int health;
        int damage;
        int attackPower;
        List<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Creature creature = service.createCreature(id, name.toString());
            creatures.add(creature);
            name.delete(9, name.length()).append(++id);
        }

        for (Creature creature: creatures) {
            attack = creature.getAttack();
            defence = creature.getDefense();
            health = creature.getHealth();
            damage = creature.getDamage();
            attackPower = creature.getAttackPower();
            assertTrue(attack >= 1 && attack <= 30,
                            "Значение для х-ки \"Атака\" сгенерировано в неразрешенном диапазоне: [1; 30].");
            assertTrue(defence >= 1 && defence <= 30,
                            "Значение для х-ки \"Защита\" сгенерировано в неразрешенном диапазоне: [1; 30].");
            assertTrue(health >= 1 && health <= 100,
                            "Значение для х-ки \"Здоровье\" сгенерировано в неразрешенном диапазоне: [1; 100].");
            assertTrue(damage > 5 && damage < 15,
                            "Значение для х-ки \"Урон\" сгенерировано в неразрешенном диапазоне: (5; 15).");
            assertEquals(0, attackPower,
                            "Сила атаки до начала боя должна быть равна 0. Имеем: " + attackPower);
        }
    }

    @Test
    @DisplayName("Метод createMonster возвращает объект класса \"Monster\".")
    void createMonster() {
        Monster monster = service.createMonster();

        assertEquals("Monster", monster.getClass().getSimpleName(),
                "Ожидаемый класс для Монстра: Monster. Получен: " + monster.getClass().getSimpleName());
    }

    @Test
    @DisplayName("Метод createUser возвращает объект класса \"User\".")
    void createUser() {
        User user = service.createUser();

        assertEquals("User", user.getClass().getSimpleName(),
                "Ожидаемый класс для Пользователя: User. Получен: " + user.getClass().getSimpleName());
    }

    @Test
    @DisplayName("Корректность расчёта Модификатора атаки у существ.")
    void setAttackPower() {
        User user = service.createUser();
        Monster monster = service.createMonster();
        user.setAttack(20);
        user.setDefense(30);
        monster.setAttack(15);
        monster.setDefense(45);

        service.setAttackPower(user, monster);

        assertAll(
                () -> assertEquals(26, user.getAttackPower(),
                        "Ожидался Модификатор атаки Пользователя равный 26. Получено значение: " + user.getAttackPower()),
                () -> assertEquals(16, monster.getAttackPower(),
                        "Ожидался Модификатор атаки Монстра равный 16. Получено значение: " + monster.getAttackPower())
        );
    }

    @Test
    @DisplayName("Тест на использование одной аптечки.")
    void checkHealthX1() {
        User user = service.createUser();
        usePainkiller(user);
        assertEquals(3, user.getPainkiller(),
                "Ожидаемое число аптечек: 3. Однако получено: " + user.getPainkiller());
    }

    @Test
    @DisplayName("Тест на использование двух аптечек.")
    void checkHealthX2() {
        User user = service.createUser();
        usePainkiller(user);
        usePainkiller(user);
        assertEquals(2, user.getPainkiller(),
                "Ожидаемое число аптечек: 2. Однако получено: " + user.getPainkiller());
    }

    @Test
    @DisplayName("Тест на использование трёх аптечек.")
    void checkHealthX3() {
        User user = service.createUser();
        usePainkiller(user);
        usePainkiller(user);
        usePainkiller(user);
        assertEquals(1, user.getPainkiller(),
                "Ожидаемое число аптечек: 1. Однако получено: " + user.getPainkiller());
    }

    @Test
    @DisplayName("Тест на использование четырёх аптечек.")
    void checkHealthX4() {
        User user = service.createUser();
        usePainkiller(user);
        usePainkiller(user);
        usePainkiller(user);
        usePainkiller(user);
        assertEquals(0, user.getPainkiller(),
                "Ожидаемое число аптечек: 0. Однако получено: " + user.getPainkiller());
    }

    @Test
    @DisplayName("Тест на отказ от аптечки.")
    void checkHealthRefuse() {
        User user = service.createUser();
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        StandardModeHeadsXHandsService st = new StandardModeHeadsXHandsService();
        user.setHealth(user.getHealth() - user.getPainkillerPower());
        st.checkHealth(user);
        assertEquals(4, user.getPainkiller(),
                "Ожидаемое число аптечек: 4. Однако получено: " + user.getPainkiller());
    }

    @Test
    @DisplayName("Тест на попытку использовать аптечку сверх нормы (4).")
    void checkHealthWithOutPainkiller() {
        User user = service.createUser();
        user.setPainkiller((byte) 0);

        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        StandardModeHeadsXHandsService st = new StandardModeHeadsXHandsService();
        user.setHealth(user.getHealth() - user.getPainkillerPower());
        st.checkHealth(user);

        assertEquals("Увы, аптечек больше нет!", out.toString().substring(0, 24),
                "Ожидалось, сообщение об отсутствие аптечек.");
    }

    private User usePainkiller(User user) {
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        StandardModeHeadsXHandsService st = new StandardModeHeadsXHandsService();
        user.setHealth(user.getHealth() - user.getPainkillerPower());
        st.checkHealth(user);
        return user;
    }

    @Test
    @DisplayName("Вывод, если Монстр победил.")
    void finishForMonster() {
        Monster monster = service.createMonster();
        User user = service.createUser();
        user.setHealth(0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        service.finish(monster, user);
        String outLine = out.toString();
        String[] outLineSplitted = outLine.split("\r\n");

        assertAll(
                () -> assertEquals("Монстр победил!", outLineSplitted[0],
                        "Ожидалась победа Монстра."),
                () -> assertTrue(monster.getHealth() > user.getHealth(),
                        "Здоровье Монстра не может быть меньше здоровья Пользователя." )
        );
    }

    @Test
    @DisplayName("Вывод, если Пользователь победил.")
    void finishForUser() {
        Monster monster = service.createMonster();
        User user = service.createUser();
        monster.setHealth(0);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);
        System.setOut(printStream);

        service.finish(monster, user);
        String outLine = out.toString();
        String[] outLineSplitted = outLine.split("\r\n");

        assertAll(
                () -> assertEquals("Пользователь победил!", outLineSplitted[0],
                        "Ожидалась победа Пользователя."),
                () -> assertTrue(monster.getHealth() < user.getHealth(),
                        "Здоровье Пользователя не может быть меньше здоровья Монстра." )
        );

    }
}