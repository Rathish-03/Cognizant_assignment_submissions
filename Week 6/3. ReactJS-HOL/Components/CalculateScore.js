import React from 'react';

function CalculateScore(props) {
  const { name, school, total, goal } = props;
  const average = total / 5; // Assuming total is for 5 subjects for average

  return (
    <div className="score-card">
      <h2>Student Score Details</h2>
      <p>Name: {name}</p>
      <p>School: {school}</p>
      <p>Total Score: {total}</p>
      <p>Goal: {goal}</p>
      <p>Average Score: {average.toFixed(2)}</p>
      {average >= goal ? (
        <p className="status-pass">Status: Passed (Average meets or exceeds goal)</p>
      ) : (
        <p className="status-fail">Status: Failed (Average is below goal)</p>
      )}
    </div>
  );
}

export default CalculateScore;