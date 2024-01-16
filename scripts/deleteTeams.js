const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/teams';  

const teamIdToDelete = '10';  

axios.delete(`${apiUrl}/${teamIdToDelete}`)
  .then(response => {
    console.log('Équipe supprimée avec succès');
  })
  .catch(error => {
    console.error('Erreur lors de la suppression de l\'équipe :', error.message);
  });
