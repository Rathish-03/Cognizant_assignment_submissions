import React from 'react';

const IndianPlayers = () => {
  const T20Players = ['Virat', 'Rohit', 'Pant', 'Bumrah', 'Surya'];
  const RanjiPlayers = ['Gill', 'Iyer', 'Saha', 'Natarajan', 'Unadkat'];

  // Merge using spread operator
  const allPlayers = [...T20Players, ...RanjiPlayers];

  // Destructuring to get odd/even indexed players
  const oddPlayers = allPlayers.filter((_, i) => i % 2 === 1);
  const evenPlayers = allPlayers.filter((_, i) => i % 2 === 0);

  return (
    <div>
      <h2>All Players (Merged)</h2>
      <ul>
        {allPlayers.map((name, index) => (
          <li key={index}>{name}</li>
        ))}
      </ul>

      <h2>Odd Team Players</h2>
      <ul>
        {oddPlayers.map((name, index) => (
          <li key={index}>{name}</li>
        ))}
      </ul>

      <h2>Even Team Players</h2>
      <ul>
        {evenPlayers.map((name, index) => (
          <li key={index}>{name}</li>
        ))}
      </ul>
    </div>
  );
};

export default IndianPlayers;
