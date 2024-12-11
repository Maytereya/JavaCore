## Основная структура проекта

src/
├── main/
│   ├── java/
│   │   └── com/example/taskmanagement/
│   │       ├── TaskManagementApplication.java  (Точка входа)
│   │       ├── model/
│   │       │   └── Task.java                    (Класс задачи)
│   │       ├── factory/
│   │       │   ├── TaskFactory.java            (Интерфейс фабрики)
│   │       │   ├── RegularTaskFactory.java     (Фабрика обычных задач)
│   │       │   └── UrgentTaskFactory.java      (Фабрика срочных задач)
│   │       ├── observer/
│   │       │   ├── TaskObserver.java           (Интерфейс наблюдателя)
│   │       │   └── EmailNotifier.java         (Наблюдатель - отправка email)
│   │       └── service/
│   │           └── TaskManager.java           (Синглтон-сервис управления задачами)
│   └── resources/
│       └── application.properties

