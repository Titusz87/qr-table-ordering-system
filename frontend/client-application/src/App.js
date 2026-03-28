import logo from './logo.svg';
import './App.css';
import Menu from './components/menu';
import BottomNavBar from './components/bottomNav';


function App() {
  return (
    <div className="App">
      <div className="container">
       <Menu />   
      <BottomNavBar />
      </div>
    </div>
  );
}

export default App;
