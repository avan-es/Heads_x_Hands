package ru.handh.school.spb.Services;

import lombok.Builder;
import ru.handh.school.spb.Creatures.Creature;
import ru.handh.school.spb.Creatures.CreatureMapper;
import ru.handh.school.spb.Creatures.Monster;
import ru.handh.school.spb.Creatures.User;
import ru.handh.school.spb.Interface.Action;

import java.util.*;

public class InMemoryHeadsXHandsService implements Action {

    private final int DEFAULT_ATTACK_POWER = 0;
    protected int monstersCount;


    protected int usersCount;

    private int monsterId = 0;

    private int userId = 0;

    private Scanner scanner = new Scanner(System.in);



    public InMemoryHeadsXHandsService(int monsters, int users) {
        this.monstersCount = monsters;
        this.usersCount = users;
    }

    protected Map<Integer, Monster> monsters = new HashMap<>();

    protected Map<Integer, User> users = new HashMap<>();

    public void fight () {
        boolean showMustGoOn = true;
        Monster monster = createMonster();
        User user = createUser();
        monster.setAttackPower(Math.abs(user.getAttack() - monster.getDefense()) + 1);
        user.setAttackPower(Math.abs(monster.getAttack() - user.getDefense()) + 1);
        System.out.println("Создан монстр с параметрами: " + "\n" + monster + "\n");
        System.out.println("Создан пользователь с параметрами: " + "\n" + user + "\n");
        while (showMustGoOn) {
            switch ((int) (Math.random() * 2)) {
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

    private boolean attack (Creature attack, Creature defend) {
        int damage = 0;
        for (int i = 0; i < attack.getAttackPower(); i++) {
            int destinyNumber = (int) (Math.random() * 7 + 1);
            if (destinyNumber == 5 || destinyNumber == 6) {
                damage = ((int) (Math.random() * (attack.getDamage() + 1)));
                defend.setHealth(defend.getHealth() - damage);
                System.out.println("Нанесён ущерб в " + damage + " пунктов!");
                return defend.getHealth() >= 1;
            }
        }
        return true;
    }

    private void checkHealth (User user) {
        if (user.getMAX_HEALTH() - user.getHealth() >= user.getPainkillerPower()) {
            if (user.getPainkiller() < 1) {
                System.out.println("Увы, аптеек больше нет!");
            } else {
                System.out.println("Нужно подлечиться. Осталось " + user.getPainkiller() + " аптечки (-чка)." + "\n"
                        + "1 - воспользоваться аптечкой." + "\n"
                        + "Что угодно - оставить на потом.");
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        user.setHealth(user.getHealth() + user.getPainkillerPower());
                        System.out.println("Аптечка применина! Текущий уровень здоровья: " +  user.getHealth());
                        user.setPainkiller((byte) (user.getPainkiller() - 1));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void finish(Creature monster, Creature user) {
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

    @Override
    public Creature createCreature(int id, String name) {
        List<Integer> params = generateParam();
        return new Creature(id, name, params.get(0), params.get(1), params.get(2), params.get(3), DEFAULT_ATTACK_POWER);
    }

    @Override
    public Monster createMonster() {
        String name = "Monster_" + monsterId;
        Monster monster = CreatureMapper.INSTANT.creatureToMonster(createCreature(monsterId, name));
        monsterId++;
        return monster;
    }

    @Override
    public User createUser() {
        String name = "User_" + userId;
        User user = CreatureMapper.INSTANT.creatureToUser(createCreature(userId, name));
        userId++;

        return user;
    }


    @Override
    public int calculateAttackPower(Creature attack, Creature defend) {
        return Math.abs(attack.getAttack() - defend.getDefense() +1);
    }


    private List<Integer> generateParam () {
        List<Integer> params = new ArrayList<>();
        int attack = (int) (Math.random() * 31 + 1);
        params.add(attack);
        int defense = (int) (Math.random() * 31 + 1);
        params.add(defense);
        int health = (int) (Math.random() * 101 + 0);
        params.add(health);
        int damage = (int) (Math.random() * 16 + 5);
        params.add(damage);
        return params;
    }


}
