const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/teams';

axios.get(apiUrl)
  .then(response => {
    const equipes = response.data;
    console.log('Liste des équipes :', equipes);
  })
  .catch(error => {
    console.error('Erreur lors de la récupération des équipes :', error.message);
  });
