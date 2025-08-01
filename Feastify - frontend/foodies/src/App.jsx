import React from "react";
import Menubar from "./Components/Menubar/Menubar";
import { Route, Routes } from "react-router-dom";
import Home from "./Pages/Home/Home";
import Contact from "./Pages/Contact/Contact";
import ExploreFood from "./Pages/ExploreFood/ExploreFood";
import FoodDetails from "./Pages/FoodDetails/FoodDetails";
import Cart from "./Pages/Cart/Cart";
import PlaceOrder from "./Pages/PlaceOrder/PlaceOrder";
import Login from "./Components/Login/Login";
import Register from "./Components/Register/Register";
import { ToastContainer, toast } from "react-toastify";

const App = () => {
  return (
    <div>
      <Menubar />
      <ToastContainer />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/explore" element={<ExploreFood />} />
        <Route path="/food/:id" element={<FoodDetails />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/order" element={<PlaceOrder />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </div>
  );
};

export default App;
