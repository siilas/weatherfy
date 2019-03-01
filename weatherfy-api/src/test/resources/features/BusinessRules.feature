# language: pt
Funcionalidade: Sugestões de músicas de acordo com a temperatura da cidade requisitada

  Contexto: O usuário pode fazer a busca por longitude e latitude ou nome da cidade,
    de acordo com a temperatura deve-se sugerir músicas de um determinado gênero musical


  Cenário: Músicas para festa
    Dado que o usuário busque pela cidade "Araraquara"
    E a temperatura esteja acima de 30.0 graus
    Então deve sugerir músicas para festa

  Cenário: Músicas pop
    Dado que o usuário busque pela cidade "Bauru"
    E a temperatura esteja entre 15.0 e 30.0 graus
    Então deve sugerir músicas pop

  Cenário: Rock and Roll
    Dado que o usuário busque pela cidade "Campinas"
    E a temperatura esteja entre 10.0 e 14.0 graus
    Então deve sugerir rock and roll

  Cenário: Música clássica
    Dado que o usuário busque pela cidade "Porto Alegre"
    E a temperatura esteja abaixo de 10.0 graus
    Então deve sugerir música clássica
