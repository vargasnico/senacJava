# Cadastro de Carros — Java

Aplicação desktop em Java Swing para cadastrar pessoas e seus carros, com persistência em MySQL. Foi desenvolvida nas aulas de Java do curso técnico em Tecnologia da Informação do Senac Tech (2022–2023).

## Origem

O projeto foi desenvolvido em aula, sobre a base apresentada pelo professor (por isso as classes originais têm `@author jbferraz`). Ele mostra os conceitos estudados na disciplina:

- Orientação a objetos com modelos `Pessoa` e `Carro`
- Arquitetura em camadas: **view** (Swing) → **serviços** → **DAO** → MySQL
- Padrões **DAO** e **Factory**
- JDBC com `PreparedStatement`
- Validação de CPF (dígitos verificadores), placa e anos do carro

## Revisão em 2026

Já na graduação em Engenharia de Software, revisei o projeto:

| Antes | Depois |
| --- | --- |
| Conexões com o banco abertas e nunca fechadas | `try-with-resources` em todos os DAOs |
| Listar carros fazia uma consulta extra por carro para buscar o proprietário (N+1) | Uma única consulta com `JOIN` |
| Build pelo NetBeans (Ant), com o driver do MySQL em um `.jar` versionado | Maven, com o driver como dependência e `.jar` executável |
| Usuário e senha do banco fixos no código | Variáveis de ambiente (`DB_URL`, `DB_USER`, `DB_PASSWORD`) |
| Sem forma simples de subir o banco | MySQL via Docker Compose, inicializado pelo script SQL |
| Arquivos compilados e configurações locais no repositório | Removidos e ignorados no `.gitignore` |

Cada mudança está em um commit separado.

## Como rodar

Requer Java 21+, Maven e Docker.

```sh
cd cadastro-carros
docker compose up -d
mvn package
```

Depois, com a senha do banco do Compose:

```sh
DB_PASSWORD=root java -jar target/cadastro-carros-1.0.0.jar
```

No PowerShell, defina a variável antes com `$env:DB_PASSWORD="root"`. O projeto também abre no NetBeans como projeto Maven, incluindo o editor visual das telas (`.form`).

## Estrutura

| Caminho | Conteúdo |
| --- | --- |
| `view/` | Telas Swing (principal, pessoas e carros) |
| `controller/` | Listas em memória da versão anterior, em console (antes do banco) |
| `servicos/` | Regras de negócio entre telas e DAOs |
| `dao/` | Acesso ao banco com JDBC |
| `model/` | Classes `Pessoa` e `Carro` |
| `util/` | Validadores de CPF, placa e ano |
| `database/carro.sql` | Criação do banco e das tabelas |

Os pacotes ficam em `cadastro-carros/src/main/java/`.
