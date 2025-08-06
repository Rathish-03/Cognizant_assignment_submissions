import React, { useState } from "react";

function ConditionalDemo() {
  const [showMessage, setShowMessage] = useState(true);
  const [stopRendering, setStopRendering] = useState(false);

  
  const handleToggle = () => {
    setShowMessage((prev) => !prev);
  };

  
  const handleStopRendering = () => {
    setStopRendering((prev) => !prev);
  };

  
  if (stopRendering) {
    return (
      <div style={{ textAlign: "center", marginTop: 50 }}>
        <button onClick={handleStopRendering}>Allow Rendering</button>
        <p>Rendering is now prevented!</p>
      </div>
    );
  }

  return (
    <div style={{ maxWidth: 350, margin: "2rem auto", padding: 30, border: "1px solid #888", borderRadius: 10 }}>
      <h2>Conditional Rendering Demo</h2>
      <button onClick={handleToggle}>
        {showMessage ? "Hide" : "Show"} Message
      </button>
      <button onClick={handleStopRendering} style={{ marginLeft: 20 }}>
        Prevent Rendering
      </button>
      <div style={{ marginTop: 30 }}>
        {}
        {showMessage && <p>This message can be hidden or shown!</p>}
      </div>
    </div>
  );
}

export default ConditionalDemo;
