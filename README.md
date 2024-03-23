API LISTA DE TAREFAS
============================
__Esta aplicação é uma API REST que tem como objetivo gerenciar uma lista de tarefas.__

__A API foi desenvolvida utilizando a linguagem Java, com o framework Spring Boot.__

__A API possui as seguintes funcionalidades:__
* Cadastrar uma tarefa.
* Listar todas as tarefas cadastradas.
* Listar uma tarefa específica.
* Atualizar uma tarefa.
* Deletar uma tarefa.

Para acessar a documentação da API, clique no link abaixo:
[DOCUMENTAÇÃO](https://documenter.getpostman.com/view/25219814/2sA35A74u5)

Tutorial de configuração de ambiente
====================================
Este tutorial tem como objetivo auxiliar na configuração do ambiente para verificar o funcionamento da API.

Pré-requisitos:
* Ter o docker instalado na sua máquina.
* Ter alguma versão de JDK instalado na sua máquina.
* Ter algum software para realizar requisições HTTP, como por exemplo o Postman.

Recomendação:
* Utilizar o IntelliJ IDEA como IDE para desenvolvimento.

__Baixando o projeto:__
* Abra um terminal na pasta que deseja baixar o projeto.
* Execute o comando:
```shell
git clone https://github.com/danielmeneses1/apiNuven-Final.git .
```

* Após isso inicie o IntelliJ IDEA e abra o projeto.

__Configuração do banco de dados:__
* Abra um terminal na raiz do projeto.
* Execute os codigos em sequencia:
```shell
cd ListaDeTarefas
docker compose up -d

```
Apos essa configuração, o banco de dados estará pronto para ser utilizado.

Após isso, basta iniciar a aplicação, rodando o arquivo ListaDeTarefasApplication e realizar as requisições HTTP para a API por meio de algum serviço http.

Para fazer uma requisição get para a API, basta acessar a URL:
```shell
http://localhost:8090/tarefas/get
````
com o método GET.

Para fazer uma requisição post para a API, basta acessar a URL:
```shell
http://localhost:8090/tarefas/post
```

com o método POST e o corpo da requisição no formato JSON:
```json
{
  "titulo": "teste",
  "descricao": "Teste de descrição",
  "dataCriacao": "2024-03-22T11:09:50.708+00:00",
  "dataVencimento": "2024-03-22T11:13:52.708+00:00",
  "status": "PENDENTE"
}
```
lembrando que o unico campo obrigatório é o titulo.

__Para mais detalhes sobre as requisições, acesse a documentação oficial da API:__



__Após parar a aplicação, para desligar o banco de dados, basta executar o comando:__
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

