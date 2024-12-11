## Структура проекта

1. Точка входа (TaskManagementApplication.java)
2. Модель задачи (Task.java)
3. Интерфейс фабрики (TaskFactory.java)
4. Фабрика для срочных задач (UrgentTaskFactory.java)
5. Фабрика для обычных задач (RegularTaskFactory.java)
6. Интерфейс наблюдателя (TaskObserver.java)
7. Наблюдатель для отправки email (EmailNotifier.java)
8. Синглтон-сервис для управления задачами (TaskManager.java)
9. Контроллер для тестирования API-запросами (TaskController.java)

