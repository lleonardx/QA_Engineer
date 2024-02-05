const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    baseUrl : 'http://localhost:3000',
    cypressToken: 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImZ1bGFub0BxYS5jb20iLCJwYXNzd29yZCI6InRlc3RlIiwiaWF0IjoxNzA3MTQ3Nzc4LCJleHAiOjE3MDcxNDgzNzh9.m0urZdGxzYhFDKYLwN4ayLvy0CzRjdWr3yiPioRNLhk'
  },
});

