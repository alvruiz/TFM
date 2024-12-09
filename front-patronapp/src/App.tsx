import React from 'react';

import './App.css';
import LoginSignInPage from './components/login/FormLoginSignin';
import Header from './components/header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Header />} />
        <Route path="/signInLogIn" element={<LoginSignInPage />} />
      </Routes>
    </Router>
  );
}

export default App;
