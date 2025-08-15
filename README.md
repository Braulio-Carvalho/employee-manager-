# 💼 Projeto de Cálculo de Salários e Impostos para Funcionários

Este projeto fictício foi desenvolvido com o objetivo de realizar cálculos de **reajuste salarial** e **imposto de renda** para funcionários, utilizando uma API RESTful construída com **Spring Boot**.  
Totalmente dockerizado e com documentação interativa via Swagger, é fácil de executar e testar — sem precisar instalar nada além do Docker. 😉

---

## 🛠️ Tecnologias Utilizadas
☕ Java 17 — linguagem principal da aplicação

🧰 Spring Boot — framework para desenvolvimento ágil de APIs REST

📦 Maven — gerenciamento de dependências e build

🗄️ Hibernate / JPA — mapeamento objeto-relacional

🐘 PostgreSQL — banco de dados relacional, rodando em container

🐳 Docker & Docker Compose — empacotamento e orquestração local

📄 Swagger (springdoc-openapi) — documentação interativa da API

🧪 JUnit & Mockito — testes unitários e mocks

📤 DTO (Data Transfer Object) — encapsulamento de dados entre camadas

🛫 Flyway — controle de versões e migração de banco de dados

☸️ Kubernetes (Minikube) — orquestração de containers e deploy em cluster

---

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Docker** e **Docker Compose** instalados.
2. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

3. Execute o projeto com:

```bash
docker-compose up --build
```

4. Acesse a documentação da API via Swagger:

🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> O banco de dados será criado automaticamente via Flyway. Nenhuma configuração manual é necessária.

---

## 📚 Funcionalidades da API

### 📌 Cadastro e Cálculo de Reajuste Salarial

A API permite cadastrar funcionários e calcular o novo salário com base na seguinte tabela:

| Faixa Salarial (R$)     | Percentual de Reajuste |
|-------------------------|------------------------|
| 0 - 400.00              | 15%                    |
| 400.01 - 800.00         | 12%                    |
| 800.01 - 1200.00        | 10%                    |
| 1200.01 - 2000.00       | 7%                     |
| Acima de 2000.00        | 4%                     |

📥 **Entrada:** CPF do funcionário  
📤 **Saída:** CPF, novo salário, valor do reajuste e percentual aplicado

---

### 📌 Cálculo de Imposto de Renda

A API calcula o valor do imposto de renda com base na seguinte tabela:

| Faixa de Renda (R$)     | Alíquota IR (%) |
|-------------------------|-----------------|
| Até 2000.00             | Isento          |
| 2000.01 - 3000.00       | 8%              |
| 3000.01 - 4500.00       | 18%             |
| Acima de 4500.00        | 28%             |

📥 **Entrada:** CPF do funcionário  
📤 **Saída:** CPF, valor do imposto e mensagem formatada (ex: `"Imposto no valor de R$ 80.00"`)

---

## 🧪 Testes

Os testes unitários foram implementados com **JUnit** e **Mockito**.  
Para executá-los, basta rodar:

```bash
./mvnw test
```

---

## 🧾 Observações

- A tabela `employee` é criada automaticamente na primeira execução via Flyway.
- Todas as dependências estão encapsuladas nos containers Docker.
- A API pode ser testada diretamente pelo Swagger, sem necessidade de Postman.
- O projeto está pronto para produção e fácil de escalar.

---

## 👨‍💻 Autor

Feito com 💙 por ** Bráulio Carvalho **  
📧 braulio.carvalho@outlook.com  
🔗 [LinkedIn](https://www.linkedin.com/in/braulio-carvalho/) • [GitHub](https://github.com/Braulio-Carvalho)
