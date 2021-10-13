<h1>Pact Framework - Consumer e Provider</h1> 

<p align="left">
  <img src="https://img.shields.io/static/v1?label=spring&message=framework&color=GREEN&style=for-the-badge&logo=SPRING"/> 
</p>

> Exemplo de implementação de testes de contrato utilizando Pact Framework

## Descrição do projeto

<p align="justify">
    Este projeto foi criado com o intúito de aplicar o conhecimento adquirido sobre testes de contrato utilizando o Pact Framework.
</p>
<p align="justify">
    Foram criados dois sistemas, um provider e um consumer, que possuem comunicação entre si. O consumer é responsável por 
enviar requisições ao provider, que por sua vez realiza as operações com a base de dados.
</p>
<p align="justify">
    As operações criadas são simples e necessárias apenas para o exercício do aprendizado.
</p>
<p align="justify">
    O sistema é capaz de criar um usuário enviando um nome e um e-mail, realizar a busca de todos os usuários cadastrados e 
realizar uma busca de usuário por id.
</p>
<p align="justify">
    O consumer executa na porta 8081 e o provider na porta 8080.
</p>

## Processo de validação dos contratos

 <p align="justify">
    O consumer é o responsável por expor suas expectativas de consumo e diretrizes que serão estabelecidas no contrato.
</p>
<p align="justify">
    O consumer testa suas expectativas através de testes de unidade, mockando o provider com as respostas desejadas. Se os testes forem bem sucedidos o contrato é gerado.
</p>
<p align="justify">
    O consumer publica o contrato no Pact Brocker.
</p>
<p align="justify">
    O provider recebe este contrato do Pact Brocker e valida se as expectativas do consumer estão sendo atendidas em seus endpoints.
</p>
<p align="justify">
    O provider publica sua respectiva validação do contrato no Pact Brocker.
</p>

<p align="justify">
    O Pact Brocker gerencia os contratos publicados e suas respectivas validações, produzindo informações importantes para análise completa da comunicação entre os serviços.
</p>

## Ferramentas utilizadas

- [Java](https://docs.oracle.com/en/java/javase/11/index.html)
- [Spring Framework](https://docs.spring.io/spring-framework/docs/current/reference/html/)
- [Open Feign](https://spring.io/projects/spring-cloud-openfeign)
- [Pact Framework](https://docs.pact.io/)
- [Artigo Zup Pact](https://www.zup.com.br/blog/testes-de-contratos-com-pact-1-conceitos)
- [Pact Brocker](https://github.com/pact-foundation/pact_broker)
