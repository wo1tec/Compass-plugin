Плагин для ядра Paper 1.19.3 
Тестировался только на 1.19.3

В плагине реализован кастомный крафт на компас, показывающий корды игрока, после чего компас ломается.

Конфиг:
``` 
special_compass:
# Имя предмета
  display_name: '&aCompass'
# Рецепт. Ничего сложного.
  recipe: 
    - '111'
    - '121'
    - '111'
  ingredients:
    2: 'DIAMOND'
    1: 'IRON_INGOT'
```

