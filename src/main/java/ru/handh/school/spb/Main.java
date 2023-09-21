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
                    InMemoryHeadsXHandsService inMemoryHeadsXHandsService = new InMemoryHeadsXHandsService(1,1);
                    inMemoryHeadsXHandsService.fight();

                    break;
               /* case 2: break;
                case 3: break;
                case 4: break;
                case 0: break;*/
                default: System.out.println("Извините, такой команды пока нет."); break;

            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите режим игры:");
        System.out.println("1: 1 на 1 (Monster vs User)");
        System.out.println("2: Стенка на стенку (4 х Monster vs 4 x User)");
        System.out.println("3: Стенка на стенку со своими параметрами (M х Monster vs M x User)");
        System.out.println("4: Своя игра (M х Monster vs U x User)");
        System.out.println("0 - Выход");
    }
}
