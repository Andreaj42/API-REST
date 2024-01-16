const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/teams';
const equipeIdToUpdate = 7; 
const updatedEquipeData = {
  stadium: "Grand Stade"
};

axios.put(`${apiUrl}/${equipeIdToUpdate}`, updatedEquipeData)
  .then(response => {
    console.log('Équipe mise à jour avec succès :', response.data);
  })
  .catch(error => {
    console.error('Erreur lors de la mise à jour de l\'équipe :', error.message);
  });
