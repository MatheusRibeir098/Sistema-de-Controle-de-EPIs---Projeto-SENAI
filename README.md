
# Controle de EPIs (Equipamentos de Proteção Individual)

Este é um sistema de controle de EPIs desenvolvido como **desafio prático do curso técnico de Desenvolvimento de Sistemas**. O objetivo do projeto é registrar e gerenciar o empréstimo e a devolução de EPIs utilizados por colaboradores de uma empresa.

## Funcionalidades

- Cadastro de EPIs
- Cadastro de usuários
- Registro de empréstimos e devoluções
- Menus para diferentes tipos de usuários (admin e operador)
- Conexão com banco de dados para persistência dos dados

## Tecnologias Utilizadas

- Java
- Maven
- MySQL
- JDBC

## Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. Importe o projeto em uma IDE como IntelliJ IDEA ou Eclipse.

3. Configure o banco de dados MySQL utilizando o script `Dump20250512.sql` que está na pasta `src/main/java/org/example/conexao`.

4. Execute a classe `Main.java` para iniciar o sistema.

## Estrutura do Projeto

- `dao/`: Classes responsáveis pela comunicação com o banco de dados.
- `Menus/`: Classes que compõem a interface de menus no terminal.
- `conexao/`: Gerenciamento da conexão com o banco e scripts SQL.
- `Main.java`: Ponto de entrada da aplicação.

## Status

✅ Projeto finalizado como parte do curso técnico e funcional em ambiente local.

---

📘 **Desenvolvido como desafio prático do curso técnico de Desenvolvimento de Sistemas – SENAI.**
