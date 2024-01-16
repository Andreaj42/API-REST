const axios = require('axios');

const apiUrl = 'http://localhost:8080/api/v1/teams';

axios.get(apiUrl)
  .then(response => {
    const equipes = response.data;
    console.log('Liste des équipes :');
    
    equipes.forEach(equipe => {
      console.log('--------------------------');
      console.log('ID:', equipe.id);
      console.log('Nom:', equipe.name);
      console.log('Couleur:', equipe.color);
      console.log('Stade:', equipe.stadium);
      console.log('Entraîneur:', equipe.trainer);
      console.log('Joueurs:');
      equipe.players.forEach(player => {
        console.log('  - ', player.name);
      });
    });
  })
  .catch(error => {
    console.error('Erreur lors de la récupération des équipes :', error.message);
  });
