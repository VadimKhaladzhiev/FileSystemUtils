MongoDB Rest from https://habrahabr.ru/post/217391/

Перед запуском надо добавить записи в коллекцию sequences
db.sequences.insert({"_id" : "contacts","sequence" : 0})
db.sequences.insert({"_id" : "searchResult","sequence" : 0})