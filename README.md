# Car Registry — Java

Java Swing desktop application for registering people and their cars, backed by MySQL. It was built during the Java classes of the Information Technology technical course at Senac Tech (2022–2023).

## Origin

The project was developed in class on top of the base provided by the instructor (which is why the original classes are tagged `@author jbferraz`). It demonstrates the concepts covered in the course:

- Object-oriented programming with `Pessoa` (person) and `Carro` (car) models
- Layered architecture: **view** (Swing) → **services** → **DAO** → MySQL
- **DAO** and **Factory** patterns
- JDBC with `PreparedStatement`
- Validation of CPF (Brazilian taxpayer ID check digits), license plates and car years

## 2026 review

Now a Software Engineering undergraduate, I revisited the project:

| Before | After |
| --- | --- |
| Database connections opened and never closed | `try-with-resources` in every DAO |
| Listing cars ran an extra query per car to fetch its owner (N+1) | A single query with a `JOIN` |
| NetBeans (Ant) build, with the MySQL driver committed as a `.jar` | Maven, with the driver as a dependency and an executable `.jar` |
| Database user and password hardcoded | Environment variables (`DB_URL`, `DB_USER`, `DB_PASSWORD`) |
| No easy way to start the database | MySQL via Docker Compose, initialized from the SQL script |
| Compiled files and local settings committed | Removed and ignored in `.gitignore` |

Each change is in a separate commit.

## Running

Requires Java 21+, Maven and Docker.

```sh
cd cadastro-carros
docker compose up -d
mvn package
```

Then, using the Compose database password:

```sh
DB_PASSWORD=root java -jar target/cadastro-carros-1.0.0.jar
```

In PowerShell, set the variable first with `$env:DB_PASSWORD="root"`. The project also opens in NetBeans as a Maven project, including the visual form editor (`.form` files).

## Structure

| Path | Contents |
| --- | --- |
| `view/` | Swing screens (main, people and cars) |
| `controller/` | In-memory lists from the earlier console version (before the database) |
| `servicos/` | Business logic between screens and DAOs |
| `dao/` | Database access with JDBC |
| `model/` | `Pessoa` and `Carro` classes |
| `util/` | CPF, license plate and year validators |
| `database/carro.sql` | Database and table creation |

The packages live in `cadastro-carros/src/main/java/`. The code itself (class names, UI and messages) is in Portuguese.
