# Weatherfy

Olá! O Weatherfy é uma API que sugere músicas do Spotify de acordo com a temperatura da cidade requisitada. E segue as seguintes regras:

- Se a temperatura for maior que 30 graus, sugerir músicas para festa
- Se a temperatura estiver entre 15 e 30 graus, sugerir músicas pop
- Se estiver um pouco frio (entre 10 e 14 graus), sugerir rock and roll
- Caso contrário, se estiver congelando lá fora, sugerir músicas clássicas

# O Projeto

O projeto foi feito utilizando as seguintes tecnologias e frameworks:

- [Java 11](http://www.java.com)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](http://docker.com)
- [Hystrix](https://github.com/Netflix/Hystrix)
- [Cucumber](https://cucumber.io)
- [WireMock](http://wiremock.org)
- [Swagger](https://swagger.io)
- [Reactor](https://projectreactor.io)
- [Thymeleaf](https://www.thymeleaf.org)
- [Bootstrap](https://getbootstrap.com/)

Para buscar a temperatura e as músicas, são utilizadas as seguintes APIs:

- [Spotify](https://developer.spotify.com)
- [OpenWeatherMap](https://openweathermap.org)

E alguns dos padrões de projeto e arquiteturais utilizados:

- [Builder](https://pt.wikipedia.org/wiki/Builder)
- [Circuit Breaker](https://pt.wikipedia.org/wiki/Circuit_breaker)
- [REST](https://pt.wikipedia.org/wiki/REST)
- [MVC](https://pt.wikipedia.org/wiki/MVC)

# Instalação

Depois de baixar o projeto, rode o script (somente para Linux por enquanto) para verificar o Java, Docker e Docker Compose:


```sh
sh verification.sh
```

Deposi disso, basta executar o seguinte comando na pasta raiz do projeto para que o Maven baixe as dependências e faça o build:

```sh
./mvnw clean install
```

E para executar e rodar o projeto execute o seguinte comando:

```sh
docker-compose up
```

Depois basta abrir o browser e acessar o seguinte endereço:

```sh
http://localhost:8080
```

# O projeto

A ideial inicial era que o projeto fosse somente uma API que busca as músicas de acordo com a temperatura da cidade requisitada, mas posteriormente uma interface gráfica foi incluída, para que o usuário possa fazer a busca de forma amigável. Para isso foram utilizados o Thymeleaf (engine de templates) e o Bootstrap (componentes de interface e front-end), ambos pela facilidade de uso.

Para o back-end foi escolhido o Spring Boot, pela maturidade do framework e todas as ferramentas, facilidades e integração com outros frameworks e tecnologias que o mesmo oferece. A API foi desenvolvida de forma reativa e também utilizando o Hystrix (circuit breaker) para que seja tolerante a falha. E o BDD (Cucumber e WireMock) foi utilizado na parte de testes para validação das regras de negócio.

E por último, para que seja possível testar/acessar a API sem precisar fazer o download e instalação, foi feito deploy do app no Heroku. E você pode acessar clicando [aqui](https://weatherfy.herokuapp.com/).

# URLs do projeto

Segue lista com todas as URLs do projeto:

| URL | Descrição |
| --- | --------- |
| / | Interface gráfica |
| /docs | Documentação do serviços REST |
| /songs/city/{city} | Serviço que busca as músicas pelo nome da cidade |
| /songs/latitude/{latitude}/longitude/{longitude} | Serviço que busca as músicas por latitude e longitude |

# Acesso e exemplos de uso

A API pode acessada pelo seguinte endereço: 

[https://weatherfy.herokuapp.com](https://weatherfy.herokuapp.com)

A busca pode ser feita pela interface gráfica, mas também pode ser diretamente nos serviços como nos exemplos abaixo:

[https://weatherfy.herokuapp.com/songs/city/bauru](https://weatherfy.herokuapp.com/songs/city/bauru)

[https://weatherfy.herokuapp.com/songs/latitude/-22.6/longitude/-48.8](https://weatherfy.herokuapp.com/songs/latitude/-22.6/longitude/-48.8)

# Ideias de melhorias no projeto

- Melhorar os casos de teste e aumentar a cobertura dos mesmos
- Melhorar o fallback, talvez utilizando um cache/banco de dados
- Utilizar docker não só no desenvolvimento, mas também em produção (Heroku)
- Permitir que o usuário defina a quantidade de músicas que serão selecionada, como também popularidade e outros parâmetros
- Melhorar o script de verificação e criar uma versão para Windows

**Até mais e obrigado por todos os peixes! :)**
