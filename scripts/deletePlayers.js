const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/players';  

const playerIdToDelete = '6';  

axios.delete(`${apiUrl}/${playerIdToDelete}`)
  .then(response => {
    console.log('Joueur supprimé avec succès');
  })
  .catch(error => {
    console.error('Erreur lors de la suppression du joueur :', error.message);
  });
