#!/bin/bash
# Compila el proyecto sin necesidad de NetBeans (requiere JDK 17+ instalado).
set -e
cd "$(dirname "$0")"
mkdir -p build/classes
find src -name "*.java" > sources.txt
javac -encoding UTF-8 -cp "lib/postgresql-42.7.4.jar" -d build/classes @sources.txt
rm sources.txt
# Copia el catálogo de municipios y el archivo de configuración de conexión
# al classpath compilado (son leídos como recursos en tiempo de ejecución)
mkdir -p build/classes/com/pnc/multas/util
cp src/com/pnc/multas/util/seed_data.sql build/classes/com/pnc/multas/util/
cp src/com/pnc/multas/util/db.properties build/classes/com/pnc/multas/util/
echo "Compilación terminada. Use ./ejecutar.sh para correr la aplicación."
