### Padrões de projeto(Design Patterns):
Padrões de projeto são soluções típicas para problemas comuns em projeto de software. Eles são como plantas de obra pré fabricadas que você pode customizar para resolver um problema de projeto recorrente em seu código.</br>
- **[Padrão Arquitetural](#criacionaiscreational)**
    - [Model, View, Controller (MVC)](#model-view-controller-mvc)
- **[Criacionais(Creational)](#criacionaiscreational)**
    - [Builder](#builder)
    - [Singleton](#singleton)
- **[Estruturais(Structural)](#estruturaisstructural)**
    - [Adpter](#adapter )
- **[Comportamentais(Behavioral)](#comportamentaisbehavioral)**
  - [Template Method](#templatemethod) 
- **[Outros](#outros)**
    - [Data Transfer Object (DTO)](#data-transfer-object-dto)
    - [Data Transfer Object (DTO)](#data-access-object-dao)
- **[Bibliografia](#bibliografia)**

### Padrão de Arquitetural
#### Model, View, Controller (MVC)
O MVC é uma sigla do termo em inglês Model (modelo) View (visão) e Controller (Controle) que facilita a troca de informações entre a interface do usuário aos dados no banco, fazendo com que as respostas sejam mais rápidas e dinâmicas.

 - **Model:** Essa classe também é conhecida como Business Object Model (objeto modelo de negócio). Sua responsabilidade é gerenciar e controlar a forma como os dados se comportam por meio das funções, lógica e regras de negócios estabelecidas. Ele é o detentor dos dados que recebe as informações do Controller, válida se ela está correta ou não e envia a resposta mais adequada.
 - **Controller:** A camada de controle é responsável por intermediar as requisições enviadas pelo View com as respostas fornecidas pelo Model, processando os dados que o usuário informou e repassando para outras camadas.
 - **View:** Essa camada é responsável por apresentar as informações de forma visual ao usuário. Em seu desenvolvimento devem ser aplicados apenas recursos ligados a aparência como mensagens, botões ou telas.

### Criacionais(Creational)
#### Singleton
O **Singleton** permite a você garantir que uma classe tenha apenas uma instância, enquanto provê um ponto de acesso global para essa instância.</br>

#### Builder
O **Builder** permite a você construir objetos complexos passo a passo. O padrão permite que você produza diferentes tipos e representações de um objeto usando o mesmo código de construção.</br>

### Comportamentais(Behavioral)
#### Adapter
O **Adapter** é um padrão de projeto estrutural que permite objetos com interfaces incompatíveis colaborarem entre si.

### Comportamentais(Behavioral)
#### Template Method
O Template Method é um padrão de projeto comportamental que define o esqueleto de um algoritmo na superclasse mas deixa as subclasses sobrescreverem etapas específicas do algoritmo sem modificar sua estrutura.

### Outros

#### Data Transfer Object (DTO)
Padrão Objeto de Transferência de Dados (do inglês, Data transfer object design pattern, ou simplesmente DTO) é um padrão de arquitetura de objetos que agregam e encapsulam dados para transferência.

Diferente do que ocorre com os objetos de negócio e os objetos de acesso a dados (DAO), o DTO não possui qualquer tipo de comportamento. A sua função é obter e armazenar dados. Quando estamos trabalhando com uma interface remota, cada chamada ao servidor pode custar muito tempo de processamento, a depender da quantidade de dados. Com o DTO, podemos filtrar quais dados serão transmitidos e assim reduzir esse problema.

O DTO é bastante utilizado também quando não queremos expor todos os dados da nossa camada de persistência mas precisamos exibir ao nosso cliente estes mesmos dados. Vamos focar nosso post nessa linha de raciocício.

#### Data Access Object (DAO)
O DAO é um padrão para aplicações implementadas com linguagens de programação orientada a objetos.  E que utilizam persistência de dados, onde existe a separação das regras de negócio das regras de acesso a banco de dados. E ainda, onde todas as funcionalidades de bancos de dados, tais como obter conexões, mapear objetos para tipos de dados SQL ou executar comandos SQL, devem ser feitas por classes DAO.

Em síntese, o DAO é a camada do sistema (pacotes, classes e métodos) que abstrai todo o acesso ao banco de dados separadamente da lógica de negócio da aplicação. É no DAO que implementamos os métodos do CRUD (Create – Read – Update – Delete). Bem como é por meio do DAO que as operações no banco de dados são realizadas.
### Bibliografia
[Padrões de Projeto - Solucões Reutilizáveis de Software Orientado a Objetos.pdf](https://github.com/carlosalexandredev/desing-patterns/blob/c0ddbf7b49bed10d18ecf1941745d72e97ab6105/Padr%C3%B5es%20de%20Projeto%20-%20Soluc%C3%B5es%20Reutiliz%C3%A1veis%20de%20Software%20Orientado%20a%20Objetos.pdf)

