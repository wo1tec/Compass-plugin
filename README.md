Тестировался только на Paper 1.19.3.

Скачать плагин: [https://github.com/wo1tec/Compass-plugin/releases/tag/Realese]

В плагине реализован кастомный крафт на компас, показывающий корды игрока, после использования компас ломается.

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

