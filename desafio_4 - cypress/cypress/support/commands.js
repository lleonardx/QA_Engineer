/// <reference types="cypress" />

const token = Cypress.env('cypressToken');

Cypress.Commands.add('sendRequest', () => {
    // Aqui você envia a solicitação conforme necessário
    cy.log('Sending request...');
    cy.request({ method: 'GET', url: '/usuarios' });
});

Cypress.Commands.add('createUser', () => {
    const jsonBody = {
        nome: "Teste Cypress",
        email: "cypress24@teste.com",
        password: "testecypress",
        administrador: 'true'
    };
    cy.request({
        method: 'POST',
        url: '/usuarios',
        body: jsonBody,
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => {
        cy.log(`Create User Response Code: ${response.status}`);
        // Resposta do Servidor
        const responseBody = response.body;
        cy.log(`Create User Response Body: ${responseBody}`);
    });
});

Cypress.Commands.add('login', () => {
    const jsonBody = {
        email: 'cypress24@teste.com',
        password: 'testecypress'
    };
    cy.request({
        method: 'POST',
        url: '/login',
        body: jsonBody,
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => {
        expect(response.status).to.eq(200);
        const responseBody = response.body;
        expect(responseBody).to.have.property('authorization');
        const token = responseBody.authorization;
        Cypress.env('cypressToken', token);
    });
});

Cypress.Commands.add('checkUser', () => {
    const token = Cypress.env('token');
    cy.request({
        method: 'GET',
        url: '/usuarios',
        headers: {
            'Content-type': 'application/json',
            'Authorization': token
        }
    }).then(response => {
        cy.log(`Verificando Usuario Response Code: ${response.status}`);
        // Resposta do Servidor
        const responseBody = response.body;
        cy.log(`Verificando Usuario Response Body: ${responseBody}`);
    });
});

Cypress.Commands.add('createProductWithToken', () => {
    const authorization = `Bearer ${Cypress.env('cypressToken')}`;
    if (!token) {
        throw new Error('Token não encontrado. Faça login primeiro.');
    }

    // Implemente a criação de produto com token autorizado
    cy.request({
        method: 'POST',
        url: '/produtos',
        body: {
            nome: 'Produto de Teste',
            preco: 100,
            descricao: 'Descrição do produto',
            quantidade: 10
        },
        headers: { authorization }
        
    }).then(response => {
        expect(response.status).to.eq(200);
        const responseBody = response.body;
        cy.log(`Create Product Response Body: ${JSON.stringify(responseBody)}`);
    });
});


Cypress.Commands.add('checkProduct', () => {
    const token = Cypress.env('token');
    cy.request({
        method: 'GET',
        url: '/produtos',
        headers: {
            'Content-type': 'application/json',
            'Authorization': token
        }
    }).then(response => {
        cy.log(`Verificando Produto Response Code: ${response.status}`);
        // Resposta do Servidor
        const responseBody = response.body;
        cy.log(`Verificando Produto Response Body: ${responseBody}`);
    });
});

function extractToken(responseBody) {
    try {
        const jsonResponse = JSON.parse(responseBody);
        return jsonResponse.token;
    } catch (error) {
        cy.log(`Token not found in response: ${responseBody}`);
        return null;
    }
}
