Para acessar a documentação oficial acesse:
===========================================
https://documenter.getpostman.com/view/25219814/2sA35A74u5

TUTORIAL DE CONFIGURAÇÃO DE AMBIENTE
====================================
Este tutorial tem como objetivo auxiliar na configuração do ambiente para verificar o funcionamento da API.

Pré-requisitos:
* Ter o docker instalado na sua máquina.
* Ter alguma versão de JDK instalado na sua máquina.
* Ter algum software para realizar requisições HTTP, como por exemplo o Postman.

Recomendação:
* Utilizar o IntelliJ IDEA como IDE para desenvolvimento.

Configuração do banco de dados:
* Abra um terminal na raiz do projeto.
* Execute os codigos em sequencia:
```shell
cd ListaDeTarefas
docker compose up -d
docker exec -it db bash
mysql -uroot -p
``` 
Apos isso ele pedirá a senha para root, a senha é: root

no terminal mysql execute:
```sql
USE ListaDeTarefas;

    DROP TABLE tarefa;

    CREATE TABLE tarefa (
        id INT AUTO_INCREMENT PRIMARY KEY,
        titulo VARCHAR(100),
        descricao VARCHAR(100) NOT NULL,
        data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        data_vencimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        status ENUM('PENDENTE', 'CONCLUIDA', 'ATRASADA') NOT NULL DEFAULT 'PENDENTE'
    );
```
PS* Você pode apenas copiar e colar o código acima no terminal mysql.

Esta configuração inicial do banco é feita pois por algum motivo desconhecido, o banco inicia com um problema de campo faltando na tabela tarefa, que eu não consegui resolver.

Apos essa configuração, o banco de dados estará pronto para ser utilizado.

Após isso, basta iniciar a aplicação, rodando o arquivo ListaDeTarefasApplication e realizar as requisições HTTP para a API por meio de algum serviço http.

Após parar a aplicação, para desligar o banco de dados, basta executar o comando:
```shell
docker-compose down
```
no mesmo diretório que foi executado o comando docker-compose up -d.


TUTORIAL TESTES UNITÁRIOS
=========================

Para rodar os testes unitários, basta abrir o projeto no IntelliJ IDEA e clicar com o botão direito na pasta src/test/java e selecionar a opção Run 'All Tests'.

Caso queira rodar os testes via terminal, basta abrir um terminal na raiz do projeto e executar o comando:
```shell
./gradlew test
```

Tambem é possivel rodar cada teste individualmente, basta clicar com o botão direito no teste desejado e selecionar a opção Run 'NomeDoTeste', no arquivo de teste desejado.

Mais detalhes em:
https://documenter.getpostman.com/view/25219814/2sA35A74u5

