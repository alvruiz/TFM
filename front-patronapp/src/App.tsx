import React from 'react';

import './App.css';
import LoginSignInPage from './components/login/FormLoginSignin';
import Header from './components/header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainList from './components/list/MainList';
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MainList />} />
        <Route path="/signInLogIn" element={<LoginSignInPage />} />
      </Routes>
    </Router>
  );
}

export default App;
