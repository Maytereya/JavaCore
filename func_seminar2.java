public class ArrayUtils {

    /**
     * Подсчитывает количество чётных элементов в массиве.
     *
     * @param arr массив целых чисел
     * @return количество чётных элементов
     */
    public static int countEvens(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Вычисляет разницу между максимальным и минимальным элементами массива.
     *
     * @param arr массив целых чисел
     * @return разница между максимальным и минимальным элементами
     */
    public static int spread(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] < min) min = arr[i];
            if (arr[i] > max) max = arr[i];
        }
        return max - min;
    }

    /**
     * Проверяет, есть ли в массиве два соседних элемента с нулевым значением.
     *
     * @param arr массив целых чисел
     * @return true, если два соседних элемента равны нулю, иначе false
     */
    public static boolean zero2(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i] == 0 && arr[i + 1] == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Основной метод для тестирования функций.
     */
    public static void main(String[] args) {
        int[] array1 = {2, 1, 2, 3, 4};
        System.out.println("Количество чётных элементов: " + countEvens(array1)); // Ожидается: 3

        int[] array2 = {10, 3, 5, 6};
        System.out.println("Разница между max и min: " + spread(array2)); // Ожидается: 7 (10 - 3)

        int[] array3 = {1, 0, 0, 2};
        System.out.println("Есть ли два соседних нуля: " + zero2(array3)); // Ожидается: true
    }
}
