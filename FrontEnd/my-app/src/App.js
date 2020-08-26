import React from 'react';
import logo from './logo.svg';
import './App.css';
import Searchbar from './components/Searchbar/Searchbar'
import SearchResults from './components/SearchResults/SearchResults'

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Searchbar />
        {/* <SearchResults /> */}
      </header>
    </div>
  );
}

export default App;
