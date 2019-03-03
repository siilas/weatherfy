# language: pt
Funcionalidade: A API deve ser tolerante a falha, responsiva and resiliente. 

  Contexto: Quando alguma das APIs (OpenWeaterMaps e Spotify) falharem, 
	deve retornar uma lista de músicas genéricas para que o usuário não fique
	sem resposta


  Cenário: Falha na API do OpenWeatherMaps
    Quando houver alguma falha na API do OpenWeatherMaps
    Então deve sugerir uma playlist genérica

  Cenário: Falha na API do Spotify
    Quando houver alguma falha na API do Spotify
    Então deve sugerir uma playlist genérica
