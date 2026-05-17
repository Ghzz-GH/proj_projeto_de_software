# Sistema de Assinaturas de Cesta

Implementação em Java do diagrama de sequência UML do sistema de assinaturas semanais de cestas de produtos.

## Estrutura do Projeto

```
src/
  fronteira/       → GuiaAssinatura.java        (interface console)
  controller/      → ControleAssinaturas.java   (coordena o fluxo)
  dominio/         → Assinante, Assinatura, Plano, Cesta, ItemCesta, Produto, Entrega
  persistencia/    → ColecaoAssinantes, ColecaoPlanos, ColecaoProdutos
  Main.java
data/
  assinantes.csv   → persistência dos assinantes
  planos.csv       → planos disponíveis
  produtos.csv     → catálogo de produtos
schema.sql         → estrutura do banco de dados (MySQL)
```

## Como Executar

```bash
# Compilar
javac -cp src -d out $(find src -name "*.java")

# Executar
java -cp out Main
```

## Fluxo Principal

1. Criar conta ou fazer login
2. Verificação por código enviado ao celular
3. Escolha do plano
4. Montagem da cesta respeitando os limites do plano
5. Confirmação da cesta e agendamento da entrega

## Persistência

O sistema utiliza **arquivos CSV** como meio de persistência (`data/`), garantindo funcionamento sem dependências externas.

### Migração para MySQL (preparada, não implementada)

A arquitetura do projeto foi pensada para facilitar a migração para banco de dados relacional. Todo o acesso a dados está isolado no pacote `persistencia/`, de forma que **nenhuma outra camada precisaria ser alterada**.

Para migrar, bastaria:

1. Executar o schema no MySQL:
   ```bash
   mysql -u root -p < schema.sql
   ```

2. Adicionar o driver JDBC ao projeto:
   ```
   mysql-connector-j-8.x.jar
   ```

3. Criar `persistencia/Conexao.java`:
   ```java
   public class Conexao {
       private static final String URL   = "jdbc:mysql://localhost:3306/assinaturas";
       private static final String USER  = "root";
       private static final String SENHA = "sua_senha";

       public static Connection getConexao() throws SQLException {
           return DriverManager.getConnection(URL, USER, SENHA);
       }
   }
   ```

4. Substituir a leitura de CSV por queries SQL apenas nas classes de `persistencia/` — sem tocar em `fronteira/`, `controller/` ou `dominio/`.

## Correspondência com o Diagrama de Sequência

| Diagrama de Sequência              | Implementação Java                        |
|------------------------------------|-------------------------------------------|
| guiaAssinatura <<Fronteira>>       | fronteira/GuiaAssinatura.java             |
| ControleAssinaturas <<Controller>> | controller/ControleAssinaturas.java       |
| colAssinantes:ColecaoAssinantes    | persistencia/ColecaoAssinantes.java       |
| assinante:Assinante                | dominio/Assinante.java                    |
| plano:Plano                        | dominio/Plano.java                        |
| assinatura:Assinatura              | dominio/Assinatura.java                   |
| cesta:Cesta                        | dominio/Cesta.java                        |
| item:ItemCesta                     | dominio/ItemCesta.java                    |
| produto:Produto                    | dominio/Produto.java                      |
| entrega:Entrega                    | dominio/Entrega.java                      |
