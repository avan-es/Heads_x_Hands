package ru.handh.school.spb.Services;

import ru.handh.school.spb.Creatures.Creature;
import ru.handh.school.spb.Creatures.CreatureMapper;
import ru.handh.school.spb.Creatures.Monster;
import ru.handh.school.spb.Creatures.User;
import ru.handh.school.spb.Interface.Action;

import java.util.*;

public class InMemoryHeadsXHandsService implements Action {

    private final int DEFAULT_ATTACK_POWER = 0;

    private int monsterId = 0;

    private int userId = 0;

    private Scanner scanner = new Scanner(System.in);

    private Random random = new Random();

    @Override
    public Creature createCreature(int id, String name) {
        List<Integer> params = generateParam();
        return new Creature(id, name, params.get(0), params.get(1), params.get(2), params.get(3), DEFAULT_ATTACK_POWER);
    }

    @Override
    public List<Integer> generateParam() {
        List<Integer> params = new ArrayList<>();
        int attack = random.nextInt(30) + 1;
        params.add(attack);
        int defense = random.nextInt(30) + 1;
        params.add(defense);
        int health = random.nextInt(100) + 1;
        params.add(health);
        int damage = random.nextInt(9) + 6;
        params.add(damage);
        return params;
    }

    @Override
    public Monster createMonster() {
        String name = "Monster_" + monsterId;
        Monster monster = CreatureMapper.INSTANT.creatureToMonster(createCreature(monsterId++, name));
        return monster;
    }

    @Override
    public User createUser() {
        String name = "User_" + userId;
        User user = CreatureMapper.INSTANT.creatureToUser(createCreature(userId++, name));
        return user;
    }

    @Override
    public void setAttackPower(Creature creatureOne, Creature creatureTwo) {
        creatureOne.setAttackPower(Math.abs(creatureOne.getAttack() - creatureTwo.getDefense()) + 1);
        creatureTwo.setAttackPower(Math.abs(creatureTwo.getAttack() - creatureOne.getDefense()) + 1);
    }

    @Override
    public void fight () {
        boolean showMustGoOn = true;
        Monster monster = createMonster();
        User user = createUser();
        setAttackPower(monster, user);
        System.out.println("Создан монстр с параметрами: " + "\n" + monster + "\n");
        System.out.println("Создан пользователь с параметрами: " + "\n" + user + "\n");
        while (showMustGoOn) {
            switch (random.nextInt(2 + 1)) {
                case 0:
                    System.out.println("Монстр атакует!");
                    showMustGoOn = attack(monster, user);
                    System.out.println("Состояние ПОЛЬЗОВАТЕЛЯ после атаки: " + "\n" + user + "\n");
                    checkHealth(user);
                    break;
                case 1:
                    System.out.println("Пользователь атакует!");
                    showMustGoOn = attack(user, monster);
                    System.out.println("Состояние МОНСТРА после атаки: " + "\n" + monster + "\n");
                    break;
            }
        }
        finish(monster, user);
    }

    @Override
    public boolean attack(Creature attack, Creature defend) {
        int damage = 0;
        for (int i = 0; i < attack.getAttackPower(); i++) {
            int destinyNumber = random.nextInt(6 + 1);
            if (destinyNumber == 5 || destinyNumber == 6) {
                damage = (random.nextInt(attack.getDamage() + 1));
                defend.setHealth(defend.getHealth() - damage);
                System.out.println("Нанесён ущерб в " + damage + " пунктов!");
                return defend.getHealth() >= 1;
            }
        }
        return true;
    }

    @Override
    public void checkHealth(User user) {
        if ((user.getMAX_HEALTH() - user.getHealth() >= user.getPainkillerPower()) &&
                (user.getHealth() > 0)) {
            if (user.getPainkiller() < 1) {
                System.out.println("Увы, аптечек больше нет!");
            } else {
                System.out.println("Нужно подлечиться. Осталось " + user.getPainkiller() + " аптечки (-чка)." + "\n"
                        + "1 - воспользоваться аптечкой." + "\n"
                        + "Что угодно - оставить на потом.");
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        user.setHealth(user.getHealth() + user.getPainkillerPower());
                        System.out.println("Аптечка применена! Текущий уровень здоровья: " +  user.getHealth());
                        user.setPainkiller((byte) (user.getPainkiller() - 1));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void finish(Creature monster, Creature user) {
        if (monster.getHealth() <= 0) {
            System.out.println("Пользователь победил!");
            System.out.println("Здоровье пользователя: " + user.getHealth());
            System.out.println("Здоровье монстра: " + monster.getHealth());
        } else {
            System.out.println("Монстр победил!");
            System.out.println("Здоровье монстра: " + monster.getHealth());
            System.out.println("Здоровье пользователя: " + user.getHealth());
        }
    }

}
