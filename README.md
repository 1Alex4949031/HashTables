# HashTables

_Есть две хэш таблицы._

_Ключами являются URLы.
Значениями - html код страниц, находящихся по этим урлам._

_Обе таблицы отражают состояние всех страниц некоторого заданного множества веб сайтов._

_Первая таблица - состояние этих сайтов на вчера. Вторая - на сегодня._

_Задача - написать код, который бы, пользуясь этими таблицами, составил письмо следующего формата:_

--------

### **Здравствуйте, дорогая {имя} {отчество}.**

### **За последние сутки во вверенных Вам сайтах произошли следующие изменения:**

### **Исчезли следующие страницы: {здесь список урлов}**

### **Появились следующие новые страницы: {здесь список урлов}**

### **Изменились следующие страницы: {здесь список урлов}**

### **С уважением,**</br>
### **автоматизированная система** </br>
### **мониторинга.**

--------

