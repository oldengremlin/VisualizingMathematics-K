# Позбавитися артефакту java в шляху до файлів:

1. Перенести `AboutController.kt`, `App.kt`, `Launcher.kt`, `PrimaryController.kt` у `src/main/kotlin/ua/org/olden/visualizingmathematics/`.
2. `module-info.java` залишити там, де він є — у `src/main/java/`.
3. Прибрати блок <sourceDirs> з kotlin-maven-plugin у `pom.xml`.
