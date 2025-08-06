import React, { useState } from "react";

function CurrencyConverter() {
  
  const [inr, setInr] = useState("");
  const [euro, setEuro] = useState(null);
  const conversionRate = 0.011; 

  
  const handleInputChange = (event) => {
    setInr(event.target.value);
  };

  const handleConvert = (event) => {
    event.preventDefault(); 
    if (!isNaN(inr) && inr !== "") {
      setEuro((parseFloat(inr) * conversionRate).toFixed(2));
    } else {
      setEuro("Invalid input");
    }
  };

  return (
    <div style={{ maxWidth: 350, margin: "2rem auto", padding: 30, border: "1px solid #ccc", borderRadius: 10 }}>
      <h2>INR to EUR Currency Converter</h2>
      <form onSubmit={handleConvert}>
        <label>
          Enter Amount in INR:
          <input
            type="text"
            value={inr}
            onChange={handleInputChange}
            placeholder="e.g. 1000"
            style={{ marginLeft: 10 }}
          />
        </label>
        <button type="submit" style={{ marginLeft: 10 }}>
          Convert
        </button>
      </form>
      {euro !== null && (
        <div style={{ marginTop: 20 }}>
          <strong>EUR: </strong>
          {euro}
        </div>
      )}
    </div>
  );
}

export default CurrencyConverter;
