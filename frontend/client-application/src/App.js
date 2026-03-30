import logo from './logo.svg';
import './App.css';
import React, { useState } from "react";
import Menu from './components/menu';
import BottomNavBar from './components/bottomNav';


function App() {
  const [cartCount, setCartCount] = useState(0);

  return (
    <div className="App">
      <div className="container">
       <Menu setCartCount={setCartCount}/>   
      <BottomNavBar cartCount={cartCount}/>
      </div>
    </div>
  );
}

export default App;
