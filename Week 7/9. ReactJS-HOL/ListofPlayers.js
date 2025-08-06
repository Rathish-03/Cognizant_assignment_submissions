import React from 'react';

const ListofPlayers = () => {
  const players = [
    { name: 'Virat', score: 90 },
    { name: 'Rohit', score: 85 },
    { name: 'Dhawan', score: 60 },
    { name: 'Rahul', score: 55 },
    { name: 'Pant', score: 75 },
    { name: 'Jadeja', score: 68 },
    { name: 'Bumrah', score: 80 },
    { name: 'Shami', score: 50 },
    { name: 'Ashwin', score: 78 },
    { name: 'Surya', score: 65 },
    { name: 'Hardik', score: 88 },
  ];

  const lowScorers = players.filter(player => player.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      <ul>
        {players.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>

      <h2>Players with Score &lt; 70</h2>
      <ul>
        {lowScorers.map((player, index) => (
          <li key={index}>
            {player.name} - {player.score}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListofPlayers;
