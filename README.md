Тестовое задание: банковское приложение

Приложение позволяет управлять счетами клиентов и проводить транзакции по счетам

1. Для управления аккаунтами предоставлен api /v1/accounts

GET
Получить список всех аккаунтов

GET
/{id}
Получить конкретный аккаунт по ID

POST
Создать новый аккаунт
Пример входных данных AccountCreatingDto:
{
"beneficialName": "Pushkin Aleksandr Sergeich",
"pincode": "1234"
}
При создании нового счёта ему присваивается номер, сгенерированный /util/AccountNumberGenerator

PUT
Изменить текущий аккаунт

DELETE/{id}
Удалить аккаунт по ID

2. Для проведения транзакций предоставлен api /v1/accounts/{account_id}/transactions

GET
Получить все транзакции определённого аккаунта c {account_id}

GET
/{transactionId}
Получить транзакцию по ID

Для каждого типа транзакций используется свой uri

POST
/deposit
Пример входных данных DepositDto:
{
"account":"408178105678645504837",
"value": 10
}

POST
/withdraw
Списание со счёта
Пример входных данных WithdrawDto:
{
"account":"408178105678645504837",
"value": 10,
"pincode": "1234"
}

POST
/transfer
Перевод с одного счёта на другой
Пример входных данных TransferDto:
{
"account":"408178105678645504837",
"accountReciever":"408178108026038733162",
"value": 10,
"pincode": "1234"
}

Возможности редактирования и удаления транзакций не предусмотрено

Исключительные ситуации проверяются методами /service/ValidationService
Обрабатываются исключения в /exceptionhandler/ControllerExceptionHandler

