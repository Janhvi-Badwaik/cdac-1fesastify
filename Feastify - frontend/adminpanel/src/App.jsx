import React, { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Menubar from './Components/Menubar/Menubar';
import Sidebar from './Components/Sidebar/Sidebar';

import AddFood from "./pages/AddFood/AddFood";
import ListFood from "./pages/ListFood/ListFood";
import Orders from "./pages/Orders/Orders";
import { ToastContainer } from 'react-toastify';


const App = () => {
  const [sidebarVisible, setSidebarVisible] = useState(true);

  const toggleSidebar = () => {
    setSidebarVisible(!sidebarVisible);
  }

  return (
    <div className="d-flex" id="wrapper">
      
      {/* Sidebar - Always Visible */}
      <Sidebar sidebarVisible = {sidebarVisible}/>

      <div id="page-content-wrapper" className="w-100">
        {/* Menubar - Always Visible */}
        <Menubar toggleSidebar = {toggleSidebar}/>
        <ToastContainer />
        

        {/* Routed Pages */}
        <div className="container mt-4">
          <Routes>
            <Route path="/add" element={<AddFood />} />
            <Route path="/list" element={<ListFood />} />
            <Route path="/orders" element={<Orders />} />
            <Route path="/" element={<ListFood />} />
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default App;
