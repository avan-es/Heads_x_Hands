package ru.handh.school.spb;

import ru.handh.school.spb.Services.InMemoryHeadsXHandsService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            switch (command){
                case 1:
                    InMemoryHeadsXHandsService inMemoryHeadsXHandsService = new InMemoryHeadsXHandsService();
                    inMemoryHeadsXHandsService.fight();
                    break;
                case 0:
                    System.out.println("Отключение прошло успешно!");
                    return;
                default:
                    printMenu();
                    break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Какая таблетка?");
        System.out.println("1: Синяя (Новая игра).");
        System.out.println("0: Красная (Вернуться в реальность).");
    }
}
