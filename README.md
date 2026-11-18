# Sistema de Control de Multas de Tránsito

Proyecto de **Java Application** (Swing + JDBC + **PostgreSQL**) que
implementa el software solicitado para que la Policía Nacional lleve el
control de las multas de tránsito, con los módulos de **Multas**,
**Detalle de Infracciones** y **Reportes**, según el enunciado.

Requiere un servidor **PostgreSQL** (local o remoto). El proyecto ya
incluye el driver JDBC (`lib/postgresql-42.7.4.jar`); usted solo debe
crear la base de datos vacía una vez (ver sección 2) — las tablas y el
catálogo del departamento de Zacapa y sus municipios
se crean y siembran automáticamente la primera vez que se ejecuta el
programa.

---

## 1. Estructura del proyecto

```
ProyectoMultasTransito/
├── build.xml                     (proyecto Ant/NetBeans)
├── manifest.mf
├── nbproject/                    (metadatos de NetBeans)
├── lib/
│   └── postgresql-42.7.4.jar     (driver JDBC de PostgreSQL, ya incluido)
├── compilar.sh / ejecutar.sh     (alternativa sin NetBeans)
└── src/com/pnc/multas/
    ├── Main.java                          -> punto de entrada
    ├── modelo/
    │   ├── Departamento.java
    │   ├── Municipio.java
    │   ├── Multa.java
    │   └── DetalleInfraccion.java
    ├── dao/
    │   ├── DepartamentoDAO.java
    │   ├── MunicipioDAO.java
    │   ├── MultaDAO.java                  -> genera el correlativo-año
    │   └── DetalleInfraccionDAO.java
    ├── util/
    │   ├── ConexionBD.java                -> conecta, crea tablas y siembra catálogo
    │   ├── db.properties                  -> host/puerto/BD/usuario/clave (editable)
    │   └── seed_data.sql                  -> departamento de Zacapa y sus municipios
    └── vista/
        ├── MenuPrincipal.java             -> menú principal
        ├── FrmMulta.java                  -> módulo de Multas (RN1)
        ├── FrmDetalleInfraccion.java      -> módulo de Detalle de Infracciones
        └── FrmReportes.java               -> módulo de Reportes
```

---

## 2. Preparar PostgreSQL (una sola vez)

El driver JDBC no puede crear la base de datos por usted; debe existir
antes de ejecutar la aplicación:

```sql
-- conectado como el usuario postgres (psql, pgAdmin, DBeaver, etc.)
CREATE DATABASE multas_transito;
```

Luego edite `src/com/pnc/multas/util/db.properties` (o, si ya compiló,
también existe una copia en `build/classes/com/pnc/multas/util/`) con
los datos de su servidor:

```properties
db.host=localhost
db.port=5432
db.nombre=multas_transito
db.usuario=postgres
db.clave=postgres
```

Con eso listo, la primera vez que corra la aplicación se crean solas las
tablas (`departamentos`, `municipios`, `multas`, `detalle_infracciones`)
y se siembra el catálogo completo de los departamento de Zacapa y sus municipios.

## 3. Cómo abrirlo en NetBeans

1. Abra NetBeans → **File → Open Project…**
2. Seleccione la carpeta `ProyectoMultasTransito` (NetBeans la reconoce
   automáticamente como un proyecto Java con Ant; si pide "regenerar
   build-impl.xml" acéptelo, es normal).
3. Verifique que la librería `lib/postgresql-42.7.4.jar` haya quedado
   agregada en **Libraries** (ya está referenciada en
   `nbproject/project.properties`; si NetBeans no la detecta, click
   derecho en el proyecto → Properties → Libraries → Add JAR/Folder →
   seleccione `lib/postgresql-42.7.4.jar`).
4. Click derecho sobre `Main.java` → **Run File** (o F6 para correr el
   proyecto completo, ya que `com.pnc.multas.Main` está configurado como
   clase principal).

## 4. Cómo ejecutarlo sin NetBeans

```bash
./compilar.sh     # compila con javac (requiere JDK 17+)
./ejecutar.sh      # abre la aplicación
```

En Windows (cmd/PowerShell) el equivalente manual es:

```
javac -encoding UTF-8 -cp "lib\postgresql-42.7.4.jar" -d build\classes @sources.txt
java -cp "build\classes;lib\postgresql-42.7.4.jar" com.pnc.multas.Main
```

No olvide copiar `db.properties` y `seed_data.sql` dentro de
`build\classes\com\pnc\multas\util\` (el script `compilar.sh` ya lo hace
automáticamente; en Windows cópielos a mano si compila manualmente).

---

## 5. Trazabilidad con los requisitos del enunciado

### Módulo de Multas (`FrmMulta.java`)
| Requisito | Implementación |
|---|---|
| Número de multa correlativo-año | Se genera automáticamente en `MultaDAO.guardar()`; reinicia el correlativo a 1 cuando cambia el año. |
| Fecha y hora automática | Se toma con `LocalDateTime.now()` al guardar, no editable en pantalla. |
| Agente, Departamento, Municipio, Dirección | Campos obligatorios; Municipio se filtra dinámicamente según el Departamento elegido (`JComboBox` en cascada). |
| DPI, Licencia, Placa opcionales | Campos de texto libres. |
| Nombre del conductor obligatorio si hay DPI o licencia | Validado antes de guardar. |
| **RN1** (al menos placa, dpi o licencia) | Validado en `guardarMulta()`: si los tres campos están vacíos se muestra un `JOptionPane` de alerta y **no se guarda** el registro. |
| Estado (activa/anulada/pagada) y justificación de anulado | Panel inferior de la pantalla para cambiar el estado de la multa seleccionada en la tabla; exige justificación si el nuevo estado es "ANULADA". |

### Módulo de Detalle de Infracciones (`FrmDetalleInfraccion.java`)
- Se busca primero la multa por su número.
- Permite agregar varias infracciones a una misma multa (correlativo
  autogenerado, artículo de ley, descripción, monto con dos decimales,
  estado activo/inactivo).

### Módulo de Reportes (`FrmReportes.java`)
| Requisito | Pestaña / método |
|---|---|
| Por día, mes, año o rango de fechas, filtrando estado | Pestaña "Por fecha" → `MultaDAO.reportePorFecha()` |
| Por municipio o departamento, filtrando estado | Pestaña "Por departamento / municipio" → `MultaDAO.reportePorUbicacion()` |
| Historial de multas por agente | Pestaña "Historial por agente" → `MultaDAO.historialPorAgente()` |
| Buscar multa por número, placa, dpi o licencia | Pestaña "Buscar multa" → `MultaDAO.buscar()` |

---

## 6. Notas de diseño

- Las 4 pantallas (`MenuPrincipal`, `FrmMulta`, `FrmDetalleInfraccion` y
  `FrmReportes`) tienen su archivo **`.form`** junto al `.java`, por lo que se
  pueden abrir en el **editor gráfico de NetBeans**: doble clic en el `.java`
  y elegir la pestaña **Design** (arriba, junto a *Source*). Desde ahí se
  pueden mover componentes, cambiar textos, colores, etc. La lógica (guardar,
  buscar, reportes) está en la parte **Source**, fuera de las zonas azules
  «Generated Code», que NetBeans regenera y no deben editarse a mano.
- Todas las reglas de negocio (RN1, obligatoriedad de nombre del
  conductor, obligatoriedad de la justificación al anular) se validan del
  lado de la interfaz antes de tocar la base de datos.
- El catálogo incluye únicamente el departamento de Zacapa y sus municipios
  (lista escrita a mano; verifíquela contra una fuente oficial como SEGEPLAN
  o el INE). Para corregir o ampliar municipios, edite
  `src/com/pnc/multas/util/seed_data.sql`. Ese script solo se ejecuta la
  primera vez (cuando la tabla `departamentos` está vacía); si ya sembró
  datos y quiere volver a partir de cero, vacíe las tablas o simplemente
  elimine y vuelva a crear la base de datos (`DROP DATABASE
  multas_transito;` seguido de `CREATE DATABASE multas_transito;`).
- Los datos de conexión (host, puerto, base, usuario, clave) están en
  `src/com/pnc/multas/util/db.properties`. Si cambia ese archivo después
  de compilar, recuerde copiarlo también a
  `build/classes/com/pnc/multas/util/` (o volver a correr
  `./compilar.sh`), ya que se lee como recurso del classpath, no desde
  la carpeta `src`.
