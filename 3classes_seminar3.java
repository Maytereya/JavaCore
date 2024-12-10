public class Main {

    // === Класс Сотрудник (Employee) ===
    static class Employee {
        private String name;
        private String position;
        private String phone;
        private int salary;
        private int birthYear;
        private int birthMonth;
        private int birthDay;

        public Employee(String name, String position, String phone, int salary, int birthYear, int birthMonth, int birthDay) {
            this.name = name;
            this.position = position;
            this.phone = phone;
            this.salary = salary;
            this.birthYear = birthYear;
            this.birthMonth = birthMonth;
            this.birthDay = birthDay;
        }

        // Геттеры и сеттеры
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

        public int getAge() {
            int currentYear = 2024; // Используем текущий год
            return currentYear - birthYear;
        }

        /**
         * Метод компаратора для сравнения дат рождения сотрудника с переданной датой.
         * Сравнение выполняется без использования условных операторов.
         * @param year год
         * @param month месяц
         * @param day день
         * @return отрицательное число, если дата сотрудника меньше переданной,
         * положительное, если больше, и ноль, если даты равны.
         */
        public int compareDate(int year, int month, int day) {
            int employeeDate = birthDay + (birthMonth << 6) + (birthYear << 11);
            int inputDate = day + (month << 6) + (year << 11);
            return employeeDate - inputDate;
        }

        @Override
        public String toString() {
            return String.format("Employee{name='%s', position='%s', salary=%d, birthDate=%d-%02d-%02d}", 
                    name, position, salary, birthYear, birthMonth, birthDay);
        }
    }

    // === Класс Руководитель (Manager) ===
    static class Manager extends Employee {

        public Manager(String name, String phone, int salary, int birthYear, int birthMonth, int birthDay) {
            super(name, "Manager", phone, salary, birthYear, birthMonth, birthDay);
        }

        /**
         * Метод для повышения зарплаты всем сотрудникам, кроме руководителей.
         * @param employees массив сотрудников
         * @param increment размер повышения зарплаты
         */
        public static void increaseSalaryForEmployees(Employee[] employees, int increment) {
            for (Employee employee : employees) {
                if (!(employee instanceof Manager)) {
                    employee.setSalary(employee.getSalary() + increment);
                }
            }
        }
    }

    // === Основной метод программы (Main) ===
    public static void main(String[] args) {
        // Создаем массив сотрудников, включая руководителя
        Employee[] employees = {
            new Employee("Ivan Petrov", "Developer", "123-456", 50000, 1990, 5, 20),
            new Employee("Olga Smirnova", "QA Engineer", "654-321", 45000, 1995, 7, 15),
            new Manager("Pavel Ivanov", "111-222", 100000, 1980, 3, 25),
            new Employee("Ekaterina Sidorova", "Analyst", "999-888", 55000, 1992, 12, 30)
        };

        // Отображаем сотрудников до повышения зарплаты
        System.out.println("Сотрудники до повышения зарплаты:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Повышаем зарплату всем, кроме руководителей
        Manager.increaseSalaryForEmployees(employees, 5000);

        // Отображаем сотрудников после повышения зарплаты
        System.out.println("\nСотрудники после повышения зарплаты:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // Проверяем метод компаратора для даты
        Employee employee = employees[0];
        int comparison = employee.compareDate(1990, 5, 20); // Сравним с датой рождения первого сотрудника
        System.out.println("\nСравнение дат: " + comparison + " (0 — даты равны, отрицательное — раньше, положительное — позже)");

        // Сравнение с другой датой
        int comparison2 = employee.compareDate(2000, 1, 1); // Сравним с произвольной датой
        System.out.println("Сравнение с датой 2000-01-01: " + comparison2 + " (0 — даты равны, отрицательное — раньше, положительное — позже)");
    }
}
