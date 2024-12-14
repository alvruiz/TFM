import React from 'react';
import './App.css';
import LoginSignInPage from './components/login/FormLoginSignin';
import Header from './components/header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from 'react-toastify';  // Importa el ToastContainer
import 'react-toastify/dist/ReactToastify.css';    // Importa los estilos de react-toastify
import MainPage from './components/list/MainPage';
import VillagesPage from './components/village/VillagesPage';
function App() {
  return (
    <Router>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored"
      />
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/signInLogIn" element={<LoginSignInPage />} />
        <Route path="/province/:id" element={<VillagesPage />} />
      </Routes>
    </Router>
  );
}

export default App;
