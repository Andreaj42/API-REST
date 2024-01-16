const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/players';
const joueurIdToUpdate = 5;
const updatedPlayerData = {
  weight: 82
};

axios.put(`${apiUrl}/${joueurIdToUpdate}`, updatedPlayerData)
  .then(response => {
    console.log('Joueur mis à jour avec succès :', response.data);
  })
  .catch(error => {
    console.error('Erreur lors de la mise à jour du joueur :', error.message);
  });
