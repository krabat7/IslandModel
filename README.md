# Island Model
Задача — запрограммировать модель острова с изменяемыми параметрами, состоящую из массива локаций. Локации будут заполнены растительностью и животными. 

**Использованные технологии: ООП, многопоточность, работа со статистикой, планировщик задач ScheduledThreadPoolExecutor, управление параметрами, Git.**

Животные могут:
1) есть растения и/или других животных (если в их локации есть подходящая еда),
2) передвигаться (в соседние локации),
3) размножаться (при наличии пары в их локации),
4) умирать от голода или быть съеденными.

___
Обязательная часть задания:

- Иерархия животных (ООП)
- Поведение животных
- Многопоточность
- Статистика по состоянию острова на каждом такте (в консоль)

Опциональная часть задания:
- Вынести параметры в одно место, чтоб было удобно управлять “симуляцией”

О параметрах 
Чтобы при запуске программы было удобно менять различные ее параметры, нужно все эти параметры вынести куда-то, например, в отдельный класс. Должна быть возможность изменять следующие параметры:

- размер острова на старте симуляции
- количество животных каждого типа на старте симуляции
- количество растений на старте симуляции
