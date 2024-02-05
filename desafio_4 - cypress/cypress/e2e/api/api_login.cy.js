describe('API Test', () => {
    before(() => {
        cy.sendRequest();
        cy.createUser();
        cy.login();
    });

    it('should check user', () => {
        cy.checkUser();
    });

    it('should create product with token', () => {
        cy.createProductWithToken();
    });

    it('should check product', () => {
        cy.checkProduct();
    });
});
