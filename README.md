# <img alt="Simbolo Java" height="35" width="45" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg"> Sistema de Gerenciamento de Exames Médicos

Este é um projeto de gerenciamento de exames médicos que permite CRUD de exames realizados por funcionários. O projeto foi desenvolvido em Java utilizando o framework Struts 2 e gerenciado com o Maven.

## Funcionalidades

- **Cadastro de Exames:** Permite adicionar novos exames para funcionários.
- **Listagem de Exames:** Exibe uma lista de todos os exames cadastrados.
- **Atualização de Exames:** Edita as informações de exames existentes.
- **Remoção de Exames:** Remove exames específicos do sistema.
- **Validação de Dados:** Implementa validação de entrada de dados com Struts 2.
- **Gerar Relatório:** Gerar um relatório em excel dos exames de um determinado período de tempo.
  
## Tecnologias Utilizadas

- **Java 8+**
- **Struts 2**
- **Maven** (Gerenciamento de dependências e build)
- **Servlets/JSP** (camada de apresentação)
- **JDBC** (interação com o banco de dados)
- **MySQL** (Banco de dados)
  
## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **JDK 8**: [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Eclipse**: [Download Eclipse](https://www.eclipse.org/downloads/)
- **MySQL**: [Download MySQL](https://dev.mysql.com/downloads/)

## Rodando a aplicação

- Dentro do **Ecplise** clique com o botão direito no projeto
- Vá em **Run As > Maven Build**
- No campo **Goals** digite **jetty:run**
- Clique em run, após isso acesse a URL: http://localhost:8080/avaliacao
