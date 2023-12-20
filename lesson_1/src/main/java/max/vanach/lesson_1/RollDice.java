package max.vanach.lesson_1;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

//  Autor Damian Kluk //

public class RollDice {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj! Wybierz tryb gry:");
        System.out.println("1. ===SinglePlayer===");
        System.out.println("2. ===Multiplayer===");

        int gameMode = scanner.nextInt();

        switch (gameMode) {
            case 1:
                singlePlayerMenu(scanner);
                break;
            case 2:
                multiPlayerMenu(scanner);
                break;
            default:
                System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                break;
        }

        scanner.close();
    }

    public static void singlePlayerMenu(Scanner scanner) {
        System.out.println("Wybierz poziom gry:");
        System.out.println("1. Łatwy");
        System.out.println("2. Średni");
        System.out.println("3. Trudny");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Wybrano tryb łatwy.");
                String playerName = scanner.nextLine();
                easyMode(scanner, playerName, playerName + ".txt");
                break;
            case 2:
                System.out.println("Wybrano tryb średni.");
                playerName = scanner.nextLine();
                mediumMode(scanner, playerName, playerName + ".txt");
                break;
            case 3:
                System.out.println("Wybrano tryb trudny.");
                playerName = scanner.nextLine();
                hardMode(scanner, playerName, playerName + ".txt");
                break;
            default:
                System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                singlePlayerMenu(scanner);
                break;
        }
    }
    public static void multiPlayerMenu(Scanner scanner) {
        System.out.println("Wybierz tryb gry:");
        System.out.println("1. Zgaduje AI");
        System.out.println("2. Gracz VS AI");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Wybrano tryb Zgaduje AI.");
                String playerName = scanner.nextLine();
                guessAI(scanner, playerName + ".txt1");
                break;
            case 2:
                System.out.println("Wybrano tryb Gracz VS AI.");
              //  playerName = scanner.nextLine();
              //  userVSai(scanner, playerName);
                break;
            default:
                System.out.println("Nieprawidłowy wybór. Wybierz ponownie.");
                singlePlayerMenu(scanner);
                break;
        }
    }

    public static void guessAI(Scanner scanner, String fileName) {
        System.out.println("W tej rozgrywce to komputer zgaduje liczbę.");
        scanner = new Scanner(System.in);
        int dolnyZakres = 1;
        int gornyZakres = 100;
        int liczbaProb = 0;
        boolean guessed = false;
        int  najlepszyWynik = Integer.MAX_VALUE;

        System.out.println("Pomyśl liczbę między 1 a 100, a ja spróbuję ją zgadnąć.");

        while (!guessed) {
            int propozycja = (dolnyZakres + gornyZakres) / 2;
            liczbaProb++;
            System.out.println("Czy twoja liczba to: " + propozycja + "?");
            System.out.println("1. Szukana liczba jest większa.");
            System.out.println("2. Szukana liczba jest mniejsza.");
            System.out.println("3. Zgadłeś liczbę!");
            String odpowiedz = scanner.nextLine();

            if (odpowiedz.equalsIgnoreCase("3")) {
                System.out.println("Udało się! Zgadłem liczbę za " + liczbaProb + " razem!");
                guessed = true;

                saveComputerScore saveComputerScore = new saveComputerScore(fileName);

                String[] difficultyLevels = {"Łatwy"};
                int scores = liczbaProb;

                for (int i = 0; i < difficultyLevels.length; i++) {
                    saveComputerScore.saveToFile(difficultyLevels[i], scores);
                }

            } else if (odpowiedz.equalsIgnoreCase("1")) {
                dolnyZakres = propozycja + 1;
            } else if (odpowiedz.equalsIgnoreCase("2")) {
                gornyZakres = propozycja - 1;
            } else {
                System.out.println("Nieprawidłowa odpowiedź.");
            }

            if (dolnyZakres > gornyZakres) {
                System.out.println("Nie oszukuj! Twoja liczba nie mieści się w wyznaczonym zakresie!");
                break;
            }
        }
        scanner.close();

    }
    public static void userVSai(Scanner scanner, String fileName) {
        System.out.println("W tej rozgrywce zgadujecie na zmianę!");
    }
    public static void easyMode(Scanner scanner, String playerName, String fileName) {
        scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Podaj nazwę gracza: ");
        playerName = scanner.nextLine();
        fileName = playerName + ".txt";
        String difficultyLevel = "Łatwy";

        int loadedScore = loadPlayerScore(fileName, playerName, difficultyLevel);

        if (loadedScore > 0) {
            System.out.println("Witaj ponownie, " + playerName + "! Twój aktualny wynik w trybie " + difficultyLevel + " to: " + loadedScore + " prób.");
        } else {
            System.out.println("Witaj, " + playerName + "! Twój pierwszy raz tutaj. Zaczynamy grę!");
        }

        int liczbaDoOdgadniecia = random.nextInt(11); // Losowanie liczby od 0 do 100
        int propozycja, liczbaProb = 0;
        boolean odgadnieto = false;

        System.out.println("Zgadnij liczbę od 0 do 100.");

        while (!odgadnieto) {
            System.out.print("Podaj swoją propozycję: ");
            propozycja = scanner.nextInt();
            liczbaProb++;

            if (propozycja == liczbaDoOdgadniecia) {
                odgadnieto = true;
                System.out.println("Brawo! Odgadłeś liczbę " + liczbaDoOdgadniecia + " w " + liczbaProb + " próbach.");

                // Zapis wyniku do pliku
                SaveScore saveScore = new SaveScore(playerName);

                String[] difficultyLevels = {"Łatwy"};
                int scores = liczbaProb;

                for (int i = 0; i < difficultyLevels.length; i++) {
                    saveScore.saveToFile(difficultyLevels[i], scores);
                }
            } else if (propozycja < liczbaDoOdgadniecia) {
                System.out.println("Za mało. Spróbuj jeszcze raz.");
            } else {
                System.out.println("Za dużo. Spróbuj jeszcze raz.");
            }
        }

        scanner.close();
    }

    public static void mediumMode(Scanner scanner, String playerName, String fileName) {
        scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Podaj nazwę gracza: ");
        playerName = scanner.nextLine();
        fileName = playerName + ".txt";
        String difficultyLevel = "Normalny";

        int loadedScore = loadPlayerScore(fileName, playerName, difficultyLevel);

        if (loadedScore > 0) {
            System.out.println("Witaj ponownie, " + playerName + "! Twój aktualny wynik w trybie " + difficultyLevel + " to: " + loadedScore + " prób.");
        } else {
            System.out.println("Witaj, " + playerName + "! Twój pierwszy raz tutaj. Zaczynamy grę!");
        }

        int liczbaDoOdgadniecia = random.nextInt(11);
        int propozycja, liczbaProb = 0;
        boolean odgadnieto = false;

        System.out.println("Zgadnij liczbę od 0 do 10000.");

        while (!odgadnieto) {
            System.out.print("Podaj swoją propozycję: ");
            propozycja = scanner.nextInt();
            liczbaProb++;

            if (propozycja == liczbaDoOdgadniecia) {
                odgadnieto = true;
                System.out.println("Brawo! Odgadłeś liczbę " + liczbaDoOdgadniecia + " w " + liczbaProb + " próbach.");

                // Zapis wyniku do pliku
                SaveScore saveScore = new SaveScore(playerName);

                String[] difficultyLevels = {"Normalny"};
                int[] scores = {liczbaProb};

                for (int i = 0; i < difficultyLevels.length; i++) {
                    saveScore.saveToFile(difficultyLevels[i], scores[i]);
                }
            } else if (propozycja < liczbaDoOdgadniecia) {
                System.out.println("Za mało. Spróbuj jeszcze raz.");
            } else {
                System.out.println("Za dużo. Spróbuj jeszcze raz.");
            }
        }

        scanner.close();
    }

    public static void hardMode(Scanner scanner, String playerName, String fileName) {
        scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Podaj nazwę gracza: ");
        playerName = scanner.nextLine();
        fileName = playerName + ".txt";
        String difficultyLevel = "Trudny";

        int loadedScore = loadPlayerScore(fileName, playerName, difficultyLevel);

        if (loadedScore > 0) {
            System.out.println("Witaj ponownie, " + playerName + "! Twój aktualny wynik w trybie " + difficultyLevel + " to: " + loadedScore + " prób.");
        } else {
            System.out.println("Witaj, " + playerName + "! Twój pierwszy raz tutaj. Zaczynamy grę!");
        }

        int liczbaDoOdgadniecia = random.nextInt(11);
        int propozycja, liczbaProb = 0;
        boolean odgadnieto = false;

        System.out.println("Zgadnij liczbę od 0 do 1000000.");

        while (!odgadnieto) {
            System.out.print("Podaj swoją propozycję: ");
            propozycja = scanner.nextInt();
            liczbaProb++;

            if (propozycja == liczbaDoOdgadniecia) {
                odgadnieto = true;
                System.out.println("Brawo! Odgadłeś liczbę " + liczbaDoOdgadniecia + " w " + liczbaProb + " próbach.");

                // Zapis wyniku do pliku
                SaveScore saveScore = new SaveScore(playerName);

                String[] difficultyLevels = {"Trudny"};
                int[] scores = {liczbaProb};

                for (int i = 0; i < difficultyLevels.length; i++) {
                    saveScore.saveToFile(difficultyLevels[i], scores[i]);
                }
            } else if (propozycja < liczbaDoOdgadniecia) {
                System.out.println("Za mało. Spróbuj jeszcze raz.");
            } else {
                System.out.println("Za dużo. Spróbuj jeszcze raz.");
            }
        }

        scanner.close();
    }

    public static int loadPlayerScore(String fileName, String playerName, String difficultyLevel) {
        int playerScore = 0;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Player: " + playerName) && line.contains("Difficulty: " + difficultyLevel)) {
                        // Znaleziono wynik gracza, zwiększamy wynik o ilość prób
                        String[] parts = line.split(", ");
                        for (String part : parts) {
                            if (part.startsWith("Score:")) {
                                String score = part.replace("Score:","").trim();
                                playerScore = Integer.parseInt(score);
                                break;
                             }
                        }
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerScore;
    }
}

