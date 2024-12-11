import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            // 1. Создание резервной копии файлов
            createBackup();

            // 2. Работа с массивом для игры в крестики-нолики
            int[] ticTacToeArray = {0, 1, 2, 3, 0, 1, 2, 3, 0}; // Пример массива
            String fileName = "save1.out";

            // Запись массива в файл (3 байта)
            writeTicTacToeArrayToFile(ticTacToeArray, fileName);

            // Чтение массива из файла
            int[] loadedArray = readTicTacToeArrayFromFile(fileName);
            System.out.println("Загруженный массив: " + Arrays.toString(loadedArray));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. Создаёт резервную копию всех файлов из текущей директории (без поддиректорий)
     * в папку ./backup
     */
    public static void createBackup() throws IOException {
        Path backupDir = Path.of("./backup");
        // Создаём папку для резервной копии
        if (!Files.exists(backupDir)) {
            Files.createDirectory(backupDir);
            System.out.println("Создана папка ./backup");
        }

        try (DirectoryStream<Path> dir = Files.newDirectoryStream(Path.of("."))) {
            for (Path file : dir) {
                if (Files.isDirectory(file)) continue; // Пропускаем поддиректории
                Path targetPath = backupDir.resolve(file.getFileName());
                Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Скопирован файл: " + file.getFileName() + " в " + targetPath);
            }
        }
    }

    /**
     * 2. Записывает массив, представляющий поле крестики-нолики (9 чисел диапазона [0, 3]), в файл.
     * @param array массив с числами
     * @param fileName имя файла для сохранения
     * @throws IOException если произойдёт ошибка записи файла
     */
    public static void writeTicTacToeArrayToFile(int[] array, String fileName) throws IOException {
        if (array.length != 9) throw new IllegalArgumentException("Массив должен содержать ровно 9 элементов.");
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (int i = 0; i < 3; i++) {
                byte packedByte = 0;
                for (int j = 0; j < 3; j++) {
                    int value = array[i * 3 + j] & 0x3; // Берём только 2 младших бита
                    packedByte |= (value << (j * 2)); // Сдвиг значений в 2, 4, 6 позиции
                }
                fos.write(packedByte);
            }
            System.out.println("Массив " + Arrays.toString(array) + " записан в файл " + fileName);
        }
    }

    /**
     * 3. Читает массив из 9 чисел из файла, где они закодированы в 3 байтах.
     * @param fileName имя файла, из которого читаются данные
     * @return массив из 9 чисел с диапазоном [0, 3]
     * @throws IOException если произойдёт ошибка чтения файла
     */
    public static int[] readTicTacToeArrayFromFile(String fileName) throws IOException {
        int[] array = new int[9];
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int i = 0;
            int byteData;
            while ((byteData = fis.read()) != -1) {
                for (int j = 0; j < 3; j++) {
                    array[i++] = (byteData >> (j * 2)) & 0x3; // Считываем по 2 бита (00, 01, 10, 11)
                }
            }
        }
        return array;
    }
}
