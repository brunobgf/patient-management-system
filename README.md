# Patient Management System

![Capa do Projeto](https://picsum.photos/850/280)

# Sobre o Projeto

Este projeto é uma API RESTful para gerenciamento de pacientes em uma clínica médica. A API permite realizar operações CRUD (Create, Read, Update, Delete) para gerenciar os dados dos pacientes. Além disso, a API oferece funcionalidades para calcular o IMC, peso ideal, idade, e outras informações relevantes dos pacientes.

# Índice/Sumário

- [Sobre](#sobre-o-projeto)
- [Sumário](#índice/sumário)
- [Arquitetura](#arquitetura)
- [Instruções de Uso](#instruções-de-uso)
- [Tecnologias Usadas](#tecnologias-usadas)
- [Tecnologias Usadas](#tecnologias-usadas)
- [Contribuição](#contribuição)
- [Autor](#autor)
- [Licença](#licença)

# Arquitetura

A aplicação utiliza Spring Boot com um banco de dados relacional gerenciado pelo Spring Data JPA, em termos de arquitetura do sistema, optou-se pela utilização de uma arquitetura em camadas de forma que permitirá que o projeto se torne escalável e fácil de realizar manutenções futuras. A API segue os princípios RESTful e utiliza Maven para gerenciamento de dependências.

# Instruções de Uso

1. **Clone o repositório do projeto:**

   ```bash
   git clone https://github.com/brunobgf/patient-management-system.git
   ```

2. **Instruções de utilização:**

- Para executar o sistema localmente, basta possuir instalado o [Docker](https://docs.docker.com/engine/install/ubuntu/).

- Uma vez que tenha instalado o Docker corretamente, basta acessar a raiz da pasta `patient-manager` e realizar o seguinte comando no terminal: `docker compose up`.

4. **Testando**

- Os testes unitários verificam funcionalidades específicas do modelo Patient. Incluem testes para o cálculo do peso ideal para homens e mulheres `testGetIdealWeightMale()` e `testGetIdealWeightFemale()`, o cálculo do IMC `testCalculateIMC()`, a idade baseada na data de nascimento `testCalculateAge()`, a validação de CPF `testValidateCpf()`, e a formatação do CPF com máscara `testGetMaskedCpf()`. Esses testes garantem que os métodos do modelo funcionem corretamente isoladamente.
- Já os testes de integração verificam a interação entre a API e o repositório de dados. Testam os endpoints da API de pacientes: obter todos os pacientes `testGetAllPatients()`, criar um novo paciente `testCreatePatient()`, obter um paciente por ID `testGetPatientById()`, atualizar um paciente existente `testUpdatePatient()`, e deletar um paciente `testDeletePatient()`. Esses testes garantem que os diferentes componentes da aplicação funcionem corretamente em conjunto.
- Para executar os testes basta utilizar o comando:

* `mvn verify`

5. **Deploy**

- Ao realizar o deploy, deve-se atualizar o link do servidor nos seguintes lugares (enquanto não houver dns fixo):

* `docker-compose.yaml`

# Tecnologias Usadas

- [Java](https://docs.oracle.com/en/java/)
- [Springboot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [Postgres](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)
- [Mockito](https://site.mockito.org/)
- [Github Actions](https://docs.github.com/en/actions)

# Contribuição

Leia o arquivo [CONTRIBUTING.md](CONTRIBUTING.md) para saber detalhes sobre o nosso código de conduta e o processo de envio de solicitações _pull_ (_Pull Request_) para nós.

# Autor

- Bruno Gomes Ferreira

# Licença

Este projeto está licenciado sob a Licença MIT, consulte o arquivo [LICENSE.md](LICENSE.md) para mais detalhes.
