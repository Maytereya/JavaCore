__Базовый функционал__

* Передача сообщений — сообщения от клиента передаются серверу, который рассылает их всем подключённым клиентам.
* Обработка клиентов — для каждого клиента создаётся отдельный поток, что позволяет поддерживать несколько подключений.
* Закрытие соединения — клиенты могут выйти из чата, сервер корректно их удаляет.
* Команда выхода — команда /exit позволяет завершить работу клиента.
