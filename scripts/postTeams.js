const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/teams';

const team = {
  name: "Allemagne",
  color: [ 'Noir', 'Rouge' ],
  stadium: "Stade olympique de Berlin",
  trainer: "Julian Nagelsmann",

};


axios.post(apiUrl, team)
  .then(response => {
    const teamCreate = response.data;
    console.log('Équipe créée avec succès :', teamCreate);
  })
  .catch(error => {
    console.error('Erreur lors de la création de l\'équipe :', error.message);
  });

