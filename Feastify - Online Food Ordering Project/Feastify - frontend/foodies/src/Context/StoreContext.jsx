import axios from "axios";
import { createContext, useEffect, useState } from "react";
import { fetchFoodList } from "../service/foodService";
import { addToCart, getCartData } from "../service/cartService";

export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
  const [foodList, setFoodList] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [token, setToken] = useState(localStorage.getItem("token") || ""); // ✅ Step 1

  const increaseQty = async (foodId) => {
    setQuantities((prev) => ({ ...prev, [foodId]: (prev[foodId] || 0) + 1 }));
    await addToCart(foodId, token);
  };

  const decreaseQty = async (foodId) => {
    setQuantities((prev) => ({
      ...prev,
      [foodId]: prev[foodId] > 0 ? prev[foodId] - 1 : 0,
    }));
    await removeFromCart(foodId, token);
  };

  const removeFromCart = (foodId) => {
    setQuantities((prevQuantities) => {
      const updated = { ...prevQuantities };
      delete updated[foodId];
      return updated;
    });
  };

  const loadCartData = async (token) => {
    if (!token || token.trim() === "") return; // ✅ Step 2: safeguard
    const items = await getCartData(token);
    if (items) setQuantities(items);
  };

  useEffect(() => {
    async function loadData() {
      const data = await fetchFoodList();
      setFoodList(data);
      if (token) {
        await loadCartData(token);
      }
    }
    loadData();
  }, [token]); // ✅ re-run if token changes

  const contextValue = {
    foodList,
    increaseQty,
    decreaseQty,
    quantities,
    removeFromCart,
    token,
    setToken,
    setQuantities,
    loadCartData,
  };

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};

/*
import axios from "axios";
import { createContext, useEffect, useState } from "react";
import { fetchFoodList } from "../service/foodService";
import { addToCart, getCartData } from "../service/cartService";

export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
  const [foodList, setFoodList] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [token, setToken] = useState(localStorage.getItem("token") || "");

  const increaseQty = async (foodId) => {
    if (!token || token.trim() === "") {
      console.error("❌ Cannot add to cart — token is missing");
      return;
    }
    try {
      await addToCart(foodId, token);
      setQuantities((prev) => ({
        ...prev,
        [foodId]: (prev[foodId] || 0) + 1,
      }));
    } catch (err) {
      console.error("Error in increaseQty", err);
    }
  };

  const decreaseQty = async (foodId) => {
    if (!token || token.trim() === "") {
      console.error("❌ Cannot remove from cart — token is missing");
      return;
    }
    try {
      setQuantities((prev) => ({
        ...prev,
        [foodId]: prev[foodId] > 0 ? prev[foodId] - 1 : 0,
      }));
      await removeFromCart(foodId, token);
    } catch (err) {
      console.error("Error in decreaseQty", err);
    }
  };

  const removeFromCart = (foodId) => {
    setQuantities((prevQuantities) => {
      const updated = { ...prevQuantities };
      delete updated[foodId];
      return updated;
    });
  };

  const loadCartData = async (token) => {
    if (!token || token.trim() === "") return;
    const items = await getCartData(token);
    if (items) setQuantities(items);
  };

  useEffect(() => {
    async function loadData() {
      const data = await fetchFoodList();
      setFoodList(data);
      if (token) {
        await loadCartData(token);
      }
    }
    loadData();
  }, [token]);

  const contextValue = {
    foodList,
    increaseQty,
    decreaseQty,
    quantities,
    removeFromCart,
    token,
    setToken,
    setQuantities,
    loadCartData,
  };

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};*/
