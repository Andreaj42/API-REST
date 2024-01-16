const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/players';

const player = {
  equipe: 'France',
  name: 'Kingsley Coman',
  position: 'ATTAQUANT',
  birthdate: '1996-06-13',
  nationality: 'French',
  height: 1.81,
  weight: 76
};


axios.post(apiUrl, player)
  .then(response => {
    const playerCreate = response.data;
    console.log('Joueur créé avec succès :', playerCreate);
  })
  .catch(error => {
    console.error('Erreur lors de la création du joueur :', error.message);
  });

