Парсер принимает на вход номер телефона, строит из него ссылку на сервис
https://receive-sms.cc/ 
смотрит таблицу сообщений, находит нужное сообщение и достает из него шестизначный код подтверждения,
если тот присутствует. Далее он создает файл sms.json, который содержит строку с текстом в формате json вида
{"smsCode":"601220","success":true}
В строке указан смс код, а также параметр success, который показывает, удалось ли найти смс код на странице.
Если код не был найден, то параметр success вернется со значением false, smsCode будет равен null. 

Для работы с российских ip адресов 
необходимо соединение через vpn другой страны или через прокси сервер из другой страны. 

Во время теста перед вызовом парсера необходимо сделать ожидание около 35-40 секунд (за это время сервис
принимает смс сообщение и выводит его в таблицу). Если не делать ожидание, можно либо получить код из предыдущего
сообщения, либо не получить его совсем. 

Можно скомпилировать в jar файл и запускать командой "java -jar rigsmsparser.jar +15557778899" (номер телефона можно подставить тот, на котором проходит тестирование)

Парсер также может возвращать код от stripe. Для этого в параметрах запуска после номера телефона нужно указать дополнительный параметр stripe. 

Присутствуют две версии парсера. HttpWebParser гарантирует получение корректного кода, если он был доставлен на сервис для получения сообщений.
В сообщении, которое приходит из веб сервиса, имеется строка, с помощью которой можно уникально идентифицировать, что сообщение именно для рига.
В сообщении для мобильного приложения способа идентифицировать, что оно именно для рига, нет. Номер, с которого приходят сообщения с кодом для 
мобильной версии (именно по нему производится идентификация в HttpMobileParser), также используется другими сервисами, и иногда они могут пересечься 
с нашей работой. Поэтому, если парсер используется для автотестов веба, надежнее будет использовать соответствующую версию. При мобильном автотестировании
за неимением лучшей альтернативы стоит использовать HttpMobileParser. 
