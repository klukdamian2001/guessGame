package max.vanach.lesson_1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class SaveScore {
    private String playerName;
    private String fileName;
    public SaveScore(String playerName) {
        this.playerName = playerName;
        this.fileName = playerName + ".txt";
    }

    private String readContentFromFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bfr.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }
        return content.toString();
    }

    private void writeContentToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu do pliku: " + e.getMessage());
        }
    }

    private String updateScore(String content, String difficultyLevel, int liczbaProb) {
        StringBuilder updatedContent = new StringBuilder();
        boolean updated = false;
        boolean found = false;

        String[] lines = content.split("\n");
        for (String line : lines) {
            if (line.contains("Player: " + playerName) && line.contains("Difficulty: " + difficultyLevel)) {
                String[] parts = line.split(", Score: ");
                int existingScore = Integer.parseInt(parts[1].trim());

                if (liczbaProb < existingScore) {
                    updatedContent.append("Player: ").append(playerName)
                            .append(", Difficulty: ").append(difficultyLevel)
                            .append(", Score: ").append(liczbaProb).append("\n");
                    updated = true;
                } else {
                    updatedContent.append(line).append("\n");
                }
                found = true;
            } else {
                updatedContent.append(line).append("\n");
            }
        }

        if (!found) {
            updatedContent.append("Player: ").append(playerName)
                    .append(", Difficulty: ").append(difficultyLevel)
                    .append(", Score: ").append(liczbaProb).append("\n");
            updated = true;
        }

        if (updated) {
            System.out.println("Nowy najlepszy wynik!");
        }

        return updatedContent.toString().trim();
    }

    public void saveToFile(String difficultyLevel, int liczbaProb) {
        String content = readContentFromFile();
        String updatedContent = updateScore(content, difficultyLevel, liczbaProb);
        writeContentToFile(updatedContent);
    }
}
