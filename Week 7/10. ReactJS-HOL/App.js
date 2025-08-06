import React from 'react';
import officeImg from './office.jpg'; // Add an office image to src/ and rename it to office.jpg

function App() {
  // Single office object
  const office = {
    name: 'Alpha Workspace',
    rent: 55000,
    address: '123 Main Street, Chennai'
  };

  // List of offices
  const officeList = [
    { name: 'Alpha Workspace', rent: 55000, address: 'Chennai' },
    { name: 'Beta Space', rent: 75000, address: 'Bangalore' },
    { name: 'Gamma Hub', rent: 62000, address: 'Mumbai' },
    { name: 'Delta Workzone', rent: 40000, address: 'Hyderabad' }
  ];

  return (
    <div className="App">
      {/* Heading */}
      <h1 style={{ textAlign: 'center' }}>Office Space Rental</h1>

      {/* Office Image */}
      <div style={{ textAlign: 'center' }}>
        <img src={officeImg} alt="Office Space" width="400" />
      </div>

      {/* Single office detail */}
      <h2>Featured Office</h2>
      <p><strong>Name:</strong> {office.name}</p>
      <p>
        <strong>Rent:</strong>{' '}
        <span style={{ color: office.rent < 60000 ? 'red' : 'green' }}>
          ₹{office.rent}
        </span>
      </p>
      <p><strong>Address:</strong> {office.address}</p>

      <hr />

      {/* Loop through office list */}
      <h2>Available Offices</h2>
      {officeList.map((off, index) => (
        <div key={index} style={{ border: '1px solid gray', margin: '10px', padding: '10px' }}>
          <p><strong>Name:</strong> {off.name}</p>
          <p>
            <strong>Rent:</strong>{' '}
            <span style={{ color: off.rent < 60000 ? 'red' : 'green' }}>
              ₹{off.rent}
            </span>
          </p>
          <p><strong>Address:</strong> {off.address}</p>
        </div>
      ))}
    </div>
  );
}

export default App;
