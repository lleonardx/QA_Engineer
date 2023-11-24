# Desafio - QA_Engineer

- Fundamentos de Teste:
> Elaboração de Plano de Teste.
> Levantamento de Cenários de Teste.
> Reporte de Falhas Encontradas.
> Parecer da aplicação.

 - Automação Web I
> Busca de Valores no site dos Correios: "69005-040" e "Lojas Bemol".

 - Automação Web II
> Automatição de Fluxo de acesso ao site, inserção em campo de pesquisa, ordenação por Avaliação e verificação de Nome, Avaliação e Preço.

 - Automação de Teste API
 > Requisições de Criação de Usuário, verificação do usuário criado, autenticação de token, criação de produto e verificação de produto criado.
<br>
Obs: Utilizar o seguinte comando, para rodar a API localmente com docker:

```
docker run -p 3000:3000 paulogoncalvesbh/serverest:latest
```

## Documentação
[Selenium](https://www.selenium.dev/documentation/webdriver/getting_started/)

[Maven Repository](https://mvnrepository.com/)

## Variáveis de Ambiente
Para configuração do projeto, é necessário:
- Download do "Webdriver" compatível com a versão do navegador. Foi utilizado o Chrome driver.
- Download do "Selenium" pelo site do "Maven Repository" - Selenium Chrome Driver.
- Download do "JUnit" pelo site do "Maven Repository" - JUnit.
- Colocar as dependencias no arquivo "pom.xlm"
- Na raiz do projetom criar uma pasta, para armazenar o driver. Ex: drivers/chromedriver.exe

## Features
<ol>
	<li><code>Configuração de Testes Selenium WebDriver + Java</code></li>
	<li><code>WebDriver</code></li>
	<li><code>Actions</code></li>
	<li><code>WebDriverWait</code></li>
	<li><code>Select</code></li>
</ol>

 ## Tecnologias Utilizadas
 - Java, Selenium e JUnit.
