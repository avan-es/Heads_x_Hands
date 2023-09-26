package ru.handh.school.spb;

import ru.handh.school.spb.Services.StandardModeHeadsXHandsService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch (command){
                case "1":
                    StandardModeHeadsXHandsService standardModeHeadsXHandsService = new StandardModeHeadsXHandsService();
                    standardModeHeadsXHandsService.fight();
                    break;
                case "0":
                    System.out.println("Отключение прошло успешно!");
                    return;
                default:
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
