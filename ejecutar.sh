#!/bin/bash
# Ejecuta la aplicación (debe correr ./compilar.sh primero al menos una vez).
cd "$(dirname "$0")"
java -cp "build/classes:lib/postgresql-42.7.4.jar" com.pnc.multas.Main
