## Projeto de Cálculo de Salários e Impostos para Funcionários

Este documento tem a finalidade de propor a criação de um projeto fictício de cálculos de salários e impostos para
funcionários, utilizando Spring Boot.

### Tecnologias utilizadas

- Java 8
- Maven (Build)
- Hibernate (Implementação JPA)
- Postgres (Data Base)
- Padrão de Projetos DTO (Data transfer object)
- JUnit e Mockito (Testes Unitários)
- API RESTful

A API deverá prover uma URL base e utilizar os verbos HTTP que indicará qual ação está sendo requisitada pelo consumidor
do serviço. A URL base do serviço será /api/nomeservico.

### Funcionalidades

1. Cadastro e cálculo de salário de funcionários

A API deve permitir o cadastro de funcionários e calcular os novos salários dos mesmos, de acordo com a tabela abaixo:

| Salário           | Percentual |
|-------------------|------------|
| 0 - 400.00        | 15%        |
| 400.01 - 800.00   | 12%        |
| 800.01 - 1200.00  | 10%        |
| 1200.01 - 2000.00 | 7%         |
| Acima de 2000.00  | 4%         |

Entrada e Saída de dados:

**Entrada:**

- CPF do funcionário

**Saída:**

- CPF do funcionário
- Novo salário
- Valor ganho de reajuste
- Percentual de reajuste ganho

Exemplo de entrada:

CPF: 000.000.000-00

**Exemplo de saída:**

| CPF:            | 000.000.000-00 |
|-----------------|----------------|
| Novo salário:   | 460.00         |
| Reajuste ganho: | 60.00          |
| Em percentual:  | 15%            |

2. Cálculo de imposto de renda

A API deve permitir o cálculo do valor de imposto de renda a ser pago por cada funcionário, de acordo com a tabela
abaixo:

| Renda                        | Imposto de Renda |
|------------------------------|------------------|
| De 0.00 a R$ 2000.00         | Isento           |
| De R$ 2000.01 até R$ 3000.00 | 8%               |
| De R$ 3000.01 até R$ 4500.00 | 18%              |
| Acima de R$ 4500.00          | 28%              |

Entrada e Saída de dados:

**Entrada:**

- CPF do funcionário

**Saída:**

- Um objeto com os seguintes campos : cpf, mensagem com detalhes, e o valor do imposto
- A mensagem irá conter o seguinte texto "Imposto R$ 580.92", com duas casas após o ponto.
- Se o valor de entrada for menor ou igual a 2000, deverá ser impressa a mensagem "Isento".

Exemplo de entrada:

CPF: 000.000.000-00

Exemplo de saída:

CPF: 000.000.000-00
Imposto: R$ 80.00

| CPF:     | 000.000.000-00                 |
|----------|--------------------------------|
| menssage | "Imposto no valor de R$ 80.00" |
| value    | 80.00                          |

**Collection de exemplo para ser testado via Postman:** 
/emploee-manager-service/Employee_Manager_postman_collection.json

**Obs:** para executar o projeto será necessário a configuração de um banco de dados postgres em sua máquina,
após instalado e configurado o db conforme o caminho sugerido no arquivo application.properties, a ferramenta de migração
flyway
irá gerar a tabela _employee_ automaticamente conforme o arquivo de migraçao que se encontra **_em:
src/main/resources/db/migration/V1__employee.sql_**