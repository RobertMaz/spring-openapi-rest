# Spring RestAPI application with openapi codegen

1. Создаем openapi спецификацию в виде yaml файла. При создании может помочь онлайн редактор https://editor.swagger.io/.
   Можно посмотреть какие то специфичные вещи для генераторов https://openapi-generator.tech/docs/generators/spring/.
2. Добавляем openapi-generator-maven-plugin плагин в pom файл. Этот генератор будет генерить API, Dto на основе
   описанного шагом выше yaml файла.
3. Определяем набор параметров который нужен для вас
4. Добавляем необходимые зависимости в проект.  (springdoc-openapi-ui, jackson-databind-nullable,
   spring-boot-starter-validation)
5. Генерим код с помощью openapi-generator-maven-plugin плагина.
6. Реализуем сгенерированные API.