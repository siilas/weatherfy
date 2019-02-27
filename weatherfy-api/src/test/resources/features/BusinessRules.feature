# language: pt
Funcionalidade: Sugestões de músicas de acordo com a temperatura da cidade requisitada

  Contexto: O usuário pode fazer a busca por longitude e latitude ou nome da cidade,
    de acordo com a temperatura deve-se sugerir músicas de um determinado gênero musical


  Esquema do Cenário: Músicas para festa
    Dado que o usuário busque pela "<cidade>"
    E a temperatura esteja acima de 30 graus
    Então deve sugerir músicas para festa

    Exemplos: 
      | cidade     |
      | Bauru      |
      | Campinas   |
      | Araraquara |

  Esquema do Cenário: Músicas pop
    Dado que o usuário busque pela "<cidade>"
    E a temperatura esteja entre 15 e 30 graus
    Então deve sugerir músicas pop

    Exemplos: 
      | cidade     |
      | Bauru      |
      | Campinas   |
      | Araraquara |

  Esquema do Cenário: Rock and Roll
    Dado que o usuário busque pela "<cidade>"
    E a temperatura esteja entre 10 e 14 graus
    Então deve sugerir rock and roll

    Exemplos: 
      | cidade     |
      | Bauru      |
      | Campinas   |
      | Araraquara |

  Esquema do Cenário: Música clássica
    Dado que o usuário busque pela "<cidade>"
    E a temperatura esteja abaixo de 10 graus
    Então deve sugerir música clássica

    Exemplos: 
      | cidade     |
      | Bauru      |
      | Campinas   |
      | Araraquara |
