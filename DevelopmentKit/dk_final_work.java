package com.example.montyhall;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int totalGames = 1000; // Количество симуляций
        boolean switchDoor = true; // Меняем выбор двери после открытия

        MontyHallSimulation simulation = new MontyHallSimulation(totalGames, switchDoor);
        simulation.run();

        // Показать результаты
        simulation.printResults();
    }
}

@Data
@AllArgsConstructor
class MontyHallSimulation {
    private int totalGames;
    private boolean switchDoor;
    private Random random = new Random();
    private HashMap<Integer, String> results = new HashMap<>();

    public void run() {
        int wins = 0;
        int losses = 0;

        for (int i = 0; i < totalGames; i++) {
            boolean win = playGame(switchDoor);
            results.put(i + 1, win ? "WIN" : "LOSS");
            if (win) wins++;
            else losses++;
        }

        System.out.println("\n--- Итоговая статистика ---");
        System.out.printf("Победы: %d из %d (%.2f%%)\n", wins, totalGames, (wins * 100.0 / totalGames));
        System.out.printf("Поражения: %d из %d (%.2f%%)\n", losses, totalGames, (losses * 100.0 / totalGames));
    }

    /**
     * Симуляция одной игры в парадокс Монти Холла.
     * @param switchDoor - если true, участник меняет дверь, иначе остаётся при своём выборе
     * @return true, если игрок выиграл машину, иначе false
     */
    private boolean playGame(boolean switchDoor) {
        // Инициализация дверей (0, 1, 2), где одна дверь — с машиной
        int[] doors = {0, 0, 0};
        int carDoor = random.nextInt(3); // Дверь с машиной (1 машина, 2 козла)
        doors[carDoor] = 1; // 1 - машина, 0 - козёл

        // Игрок делает первый выбор
        int playerChoice = random.nextInt(3);

        // Ведущий открывает одну из дверей (которая не машина и не выбор игрока)
        int hostOpens;
        do {
            hostOpens = random.nextInt(3);
        } while (hostOpens == playerChoice || doors[hostOpens] == 1);

        // Игрок принимает решение - меняет выбор или остаётся при своём
        int finalChoice = switchDoor ? getOtherDoor(playerChoice, hostOpens) : playerChoice;

        // Победа, если выбор игрока совпадает с позицией машины
        return doors[finalChoice] == 1;
    }

    /**
     * Получает номер двери, которую игрок может выбрать, исключая уже выбранную и открытую ведущим.
     * @param playerChoice - выбор игрока
     * @param hostOpens - дверь, открытую ведущим
     * @return оставшаяся дверь
     */
    private int getOtherDoor(int playerChoice, int hostOpens) {
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && i != hostOpens) {
                return i;
            }
        }
        throw new IllegalStateException("Не удалось найти другую дверь.");
    }

    /**
     * Выводит все результаты игр в консоль.
     */
    public void printResults() {
        System.out.println("\n--- Результаты всех игр ---");
        results.forEach((step, result) -> 
            System.out.printf("Игра %d: %s\n", step, result)
        );
    }
}
