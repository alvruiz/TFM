import React, { useEffect } from 'react';
import './App.css';
import LoginSignInPage from './components/login/FormLoginSignin';
import Header from './components/header/Header';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from 'react-toastify';  // Importa el ToastContainer
import 'react-toastify/dist/ReactToastify.css';    // Importa los estilos de react-toastify
import MainPage from './components/list/MainPage';
import VillagesPage from './components/villages/VillagesPage';
import EditProfile from './components/editprofile/EditProfile';
import IndividualVillage from './components/individual-village/IndividualVillage';
import Agenda from './components/agenda/Agenda';
import useUserStore from './stores/user-store';
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
        <Route path="/editProfile" element={<EditProfile />} />
        <Route path="/province/:id" element={<VillagesPage />} />
        <Route path="/village/:id" element={<IndividualVillage />} />
        <Route path="/agenda" element={<Agenda />} />
      </Routes>
    </Router>
  );
}

export default App;
