import React from 'react';
import './App.css';
import { BrowserRouter as Router,Route } from 'react-router-dom'
import Searchbar from './components/Searchbar/Searchbar'
import SearchPage from './components/SearchPage/SearchPage'

function App() {
  return (
    <Router>
      <div className="App">
        <header className="App-header">
          <Route path="/dashboard" component={ Searchbar } />
          <Route path="/searchResults" component={ SearchPage } />
        </header>
      </div>
    </Router>
  );
}

export default App;
