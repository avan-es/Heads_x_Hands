package ru.handh.school.spb.Creatures;

/**
 * CreatureMapper - маппер объектов Creature в его разновидности.
 * */
public enum CreatureMapper {
    INSTANT;

    public Monster creatureToMonster (Creature creature) {
        return new Monster(
                creature.getId(),
                creature.getName(),
                creature.getAttack(),
                creature.getDefense(),
                creature.getHealth(),
                creature.getDamage(),
                creature.getAttackPower());
    }

    public User creatureToUser (Creature creature) {
        return new User(
                creature.getId(),
                creature.getName(),
                creature.getAttack(),
                creature.getDefense(),
                creature.getHealth(),
                creature.getDamage(),
                creature.getAttackPower());
    }
}
