
eSchool Project

Проект "eSchool".
Принцип его работы авторизововать клиента и после авторизаций перенаправлять к списоку учеников.
Также он может выполнять запросы такие как : удалить, добавить, показать список учеников. Есть
форма заполнения для добавления нового ученика. Проект создан на Spring-е и подключен к базе
данных MySql. На сервере реализован тест при помощи TestNG и он проверяет на наличие ошибок:
1. Список НЕ должен быть null.
2. Проверяется размер списка после добавления ученика.
3. Проверяется размер списка после удаления ученика.
В проекте есть ещё WebSecurity для обеспечения безопасности, он проверяет правильность введённого
логина и пароля. Все неавтаризованные запросы перенаправляются на страницу формы для входа.
Логин для входа : "admin@mail.ru" и пароль : "1".