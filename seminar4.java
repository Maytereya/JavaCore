import java.util.Random;

public class Main {

    // === Перечисление полов (Genders) ===
    enum Genders {
        MALE, FEMALE
    }

    // === Перечисление праздников (Parties) ===
    enum Parties {
        NONE, NEW_YEAR, MARCH_8, FEB_23
    }

    // === Класс Сотрудник (Employee) ===
    static class Employee {
        private String name;
        private String position;
        private int salary;
        private Genders gender;

        public Employee(String name, String position, int salary, Genders gender) {
            this.name = name;
            this.position = position;
            this.salary = salary;
            this.gender = gender;
        }

        // Геттеры и сеттеры для поля "gender"
        public Genders getGender() {
            return gender;
        }

        public void setGender(Genders gender) {
            this.gender = gender;
        }

        // Геттеры для других полей
        public String getName() {
            return name;
        }

        public String getPosition() {
            return position;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("Employee{name='%s', position='%s', salary=%d, gender=%s}", 
                    name, position, salary, gender);
        }
    }

    // === Основной метод программы (Main) ===
    public static void main(String[] args) {
        // Устанавливаем текущий день как один из праздников
        Parties today = getRandomHoliday();
        System.out.println("Сегодняшний праздник: " + today);

        // Создаём массив сотрудников
        Employee[] employees = {
            new Employee("Ivan Petrov", "Developer", 50000, Genders.MALE),
            new Employee("Olga Smirnova", "QA Engineer", 45000, Genders.FEMALE),
            new Employee("Pavel Ivanov", "Manager", 100000, Genders.MALE),
            new Employee("Ekaterina Sidorova", "Analyst", 55000, Genders.FEMALE)
        };

        // Вывод информации о сотрудниках до поздравлений
        System.out.println("\nИнформация о сотрудниках до поздравлений:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Поздравляем сотрудников в зависимости от праздника
        celebrate(employees, today);
    }

    /**
     * Метод для поздравления сотрудников в зависимости от текущего праздника.
     * @param employees массив сотрудников
     * @param holiday текущий праздник
     */
    private static void celebrate(Employee[] employees, Parties holiday) {
        for (Employee employee : employees) {
            switch (holiday) {
                case NEW_YEAR:
                    System.out.println(employee.getName() + ", поздравляем с Новым Годом!");
                    break;
                case MARCH_8:
                    if (employee.getGender() == Genders.FEMALE) {
                        System.out.println(employee.getName() + ", поздравляем с 8 марта!");
                    }
                    break;
                case FEB_23:
                    if (employee.getGender() == Genders.MALE) {
                        System.out.println(employee.getName() + ", поздравляем с 23 февраля!");
                    }
                    break;
                default:
                    System.out.println(employee.getName() + ", сегодня обычный рабочий день.");
            }
        }
    }

    /**
     * Метод для случайного выбора сегодняшнего праздника.
     * @return случайный праздник из перечисления Parties
     */
    private static Parties getRandomHoliday() {
        Parties[] holidays = Parties.values();
        Random random = new Random();
        return holidays[random.nextInt(holidays.length)];
    }
}
