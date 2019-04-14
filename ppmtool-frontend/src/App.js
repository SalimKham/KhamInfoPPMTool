import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom"
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import AddProject from './components/Project/AddProject';

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/Dashboard" component = {Dashboard}/>
          <Route exact path="/addProject" component = {AddProject}/>
        </div>
      </Router>
    );
  }
}

export default App;