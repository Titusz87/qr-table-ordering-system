import logo from './logo.svg';
import './App.css';
import React, { useEffect, useState } from "react";
import Menu from './components/menu';
import BottomNavBar from './components/bottomNav';
import { createSession } from "./session";
import { useSearchParams } from "react-router-dom";


function App() {
  const [cartItems, setCartItems] = useState([]);
  const [cartCount, setCartCount] = useState(0);
  const [sessionReady, setSessionReady] = useState(false);
  const [tableId, setTableId] = useState(null);
  const [searchParams] = useSearchParams();

  useEffect(() => {
    const id = searchParams.get("tableId");
  
    if (id) {
      createSession(id)
        .then((tableIdFromBackend) => {
          setTableId(tableIdFromBackend);
          setSessionReady(true);
        })
        .catch(err => console.error("Session failed", err));
    }
  }, []);

  const billTotal = cartItems.reduce((total, item) => total + item.price * item.quantity, 0);

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
          ? { ...item, quantity: Math.max(item.quantity - 1, 0) }
          : item
      )
    );
  };
  
  return (
    <div className="App">
      <div className="container">
       <Menu 
       setCartCount={setCartCount} 
       setCartItems={setCartItems} 
       cartItems={cartItems}
       sessionReady={sessionReady}/>

       <BottomNavBar
  cartCount={cartCount}
  setCartCount={setCartCount}
  cartItems={cartItems}
  setCartItems={setCartItems}
  increaseQty={increaseQty}
  decreaseQty={decreaseQty}
  billTotal={billTotal}
  tableId={tableId}
  sessionReady={sessionReady}
/>
      </div>
    </div>
  );
}

export default App;
