describe('API Test', () => {
  const token = Cypress.env('cypressToken');

  it.only('should create a new user', () => {
    cy.request({
      method: 'POST',
      url: '/usuarios',
      body: {
          nome: 'Usuario Teste',
          email: 'teste_qa888@teste.com.br',
          password: 'teste_qa7',
          administrador: 'true'
      }
    }).then((response) => {
        expect(response.status).to.equal(201) // Verificação do Usuário criado
    })
  })

  it.only('should login and retrieve bearer token', () => {
    cy.request({
      method: 'POST',
      url:'/usuarios',
      body: {
          email: 'teste_qa888@teste.com.br',
          password: 'teste_qa7'
      }
    }).then((response) => {
      expect(response.status).to.equal(200) // Verificação de Login bem-sucedido
      expect(response.body).to.have.property('token') // Verificação se o Token de Autenticação foi retornado
    })
  })

  it('should verify if the user was created' , () => {
    cy.request({
      method: 'GET',
      url: '/usuarios',
    }).then((response) =>{
        expect(response.status).to.equal(200);
        expect(response.body.usuarios[50].nome).to.equal('Usuario Teste')
        expect(response.body.usuarios[50].email).to.equal('teste_qa000@teste.com.br')
    })
  })

  it('should create a new product', () => {
    cy.request({
        method: 'POST',
        url: `${apiUrl}/products`,
        headers: {
            Authorization: `Bearer ${token}` // inclui o token de autenticação no cabeçalho da requisição
        },
        body: {
            // dados do novo produto
        }
    }).then((response) => {
        expect(response.status).to.eq(201) // verifica se o produto foi criado com sucesso
    })
  })

  it('should verify if the product was created', () => {
    const expectedResult = [
      {
        nome: 'Logitech MX Vertical',
        descricao: 'Mouse',
        preco: 470,
        quantidade: 382
      },
      {
        nome: 'Samsung 60 polegadas',
        descricao: 'TV',
        preco: 5240,
        quantidade: 49977
      }
    ]

    cy.request({
      method: 'GET',
      url: '/produtos'
    }).then((response) => {
      expect(response.status).to.equal(200);
      response.body.produtos.forEach((produto, indice) => {
        expect(produto.nome).to.equal(expectedResult[indice].nome)
        expect(produto.descricao).to.equal(expectedResult[indice].descricao)
        expect(produto.preco).to.equal(expectedResult[indice].preco)
        expect(produto.quantidade).to.equal(expectedResult[indice].quantidade)
      })
    })
  })

})
