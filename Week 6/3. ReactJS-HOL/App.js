import React from 'react';
import CalculateScore from './Components/CalculateScore';
import './Stylesheets/mystyle.css'; // Import the stylesheet

function App() {
  return (
    <div className="App">
      <CalculateScore name="Alice" school="High School A" total={450} goal={80} />
      <CalculateScore name="Bob" school="High School B" total={380} goal={80} />
      <CalculateScore name="Charlie" school="High School C" total={410} goal={80} />
    </div>
  );
}

export default App;