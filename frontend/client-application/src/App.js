import logo from './logo.svg';
import './App.css';
import React, { useState } from "react";
import Menu from './components/menu';
import BottomNavBar from './components/bottomNav';


function App() {
  const [cartItems, setCartItems] = useState([]);
  const [cartCount, setCartCount] = useState(0);

  const increaseQty = (index) => {
    setCartItems(prev =>
      prev.map((item, i) =>
        i === index ? { ...item, quantity: item.quantity + 1 } : item
      )
    );
  };
  
  const decreaseQty = (index) => {
    setCartItems(prev =>
      prev.map((item, i) =>
        i === index
          ? { ...item, quantity: Math.max(item.quantity - 1, 1) }
          : item
      )
    );
  };
  
  return (
    <div className="App">
      <div className="container">
       <Menu setCartCount={setCartCount} setCartItems={setCartItems}/>   
       <BottomNavBar
  cartCount={cartCount}
  cartItems={cartItems}
  increaseQty={increaseQty}
  decreaseQty={decreaseQty}
/>
      </div>
    </div>
  );
}

export default App;
