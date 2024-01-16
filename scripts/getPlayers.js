const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/players';

axios.get(apiUrl)
  .then(response => {
    const joueurs = response.data;
    console.log('Liste des joueurs :', joueurs);
  })
  .catch(error => {
    console.error('Erreur lors de la récupération des équipes :', error.message);
  });
