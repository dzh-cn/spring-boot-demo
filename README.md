# 项目收货

## 1. 项目中可以使用两种视图解析器，只要引入解析器jar包引入即可

 目前发现，默认html用thymeleaf（如果引入了该解析器）

 如果引入freemarker，'.ftl'结尾的页面会用该解析器解析
 ## 2. mybatis-generator
 - test/resources/mybatis-generator/generatorConfig.xml
 - mybatis-generator-maven-plugin
 - mvn mybatis-generator:generate