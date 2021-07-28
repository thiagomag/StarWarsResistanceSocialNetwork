<h1 align="center">Star Wars Resistance Social Network</h1>
       
## Api da rede social da resistencia de Star Wars

> ### Feito por 
>
> - [Arthur Miranda](https://github.com/arthmrnd)   
> - [Elias Nogueira](https://github.com/lyusk8)    
> - [Thiago Magdalena](https://github.com/thiagomag)      
> - [José Luiz Alves](https://github.com/luiz92)    

---------------------------------------------------------------------------------
### Projeto feito com base no problema a seguir

### Descrição do problema:
*O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes. Você, como um soldado da resistência, foi designado para desenvolver um sistema para compartilhar recursos entre o rebeldes.*

### Requisitos:
Você irá desenvolver uma API REST (sim, nós levamos a arquitetura da aplicação a sério mesmo no meio de uma guerra), ao qual irá armazenar informação sobre os rebeldes, bem como os recursos que eles possuem.      

#### Adicionar rebeldes:
 - Um rebelde deve ter um nome, idade, gênero, localização(latitude, longitude e nome, na galáxia, da base ao qual faz parte).
 - Um rebelde também possui um inventário que deverá ser passado na requisição com os recursos em sua posse.
 - Atualizar localização do rebelde
 - Um rebelde deve possuir a capacidade de reportar sua última localização, armazenando a nova latitude/longitude/nome (não é necessário rastrear as localizações, apenas sobrescrever a última é o suficiente).

#### Reportar o rebelde como um traidor:
 - Eventualmente algum rebelde irá trair a resistência e se aliar ao império. Quando isso acontecer, nós precisamos informar que o rebelde é um traidor.
 - Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular seu inventário, nem ser exibido em relatórios.
 - Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes reportarem a traição.
 - Uma vez marcado como traidor, os itens do inventário se tornam inacessíveis (eles não podem ser negociados com os demais).
  
#### Rebeldes não podem Adicionar/Remover itens do seu inventário.
 - Seus pertences devem ser declarados quando eles são registrados no sistema. Após isso eles só poderão mudar seu inventário através de negociação com os outros rebeldes.    


#### Negociar itens:
 - Os rebeldes poderão negociar itens entre eles. Para isso, eles devem respeitar a tabela de preços abaixo, onde o valor do item é descrito em termo de pontos. Ambos os lados deverão oferecer a mesma quantidade de pontos. Por exemplo, 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3). A negociação em si não será armazenada, mas os itens deverão ser transferidos de um rebelde a outro.

   Item    Pontos
 - Arma    4 pontos
 - Munição 3 pontos
 - Água    2 pontos
 - Comida  1 ponto


#### Relatórios:
      
   API deve oferecer os seguintes relatórios:

 1. Porcentagem de traidores.
 2. Porcentagem de rebeldes.
 3. Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde).
 4. Pontos perdidos devido a traidores.
      
      
##### Notas:

 1. Deverá ser utilizado Java, Spring boot e como gerenciador de dependência Maven.
 2. Não será necessário autenticação.
 3. Nós ainda nos preocupamos com uma programação adequada (código limpo) e técnicas de arquitetura, você deve demonstrar que é um digno soldado da resistência através das suas habilidades.
 4. Não esqueça de minimamente documentar os endpoints da sua API e como usa-los.
 5. Sua API deve estar minimamente coberta por testes (Unitários e/ou integração).
 6. Da descrição acima você pode escrever uma solução básica ou adicionar requisitos não descritos. Use seu tempo com sabedoria; Uma solução ótima e definitiva pode levar muito tempo para ser efetiva na guerra, então você deve trazer a melhor solução possível, que leve o mínimo de tempo, mas que ainda seja capaz de demonstrar suas habilidades e provar que você é um soldado valioso para a resistência.
 7. Comente qualquer dúvida e cada decisão tomada.
