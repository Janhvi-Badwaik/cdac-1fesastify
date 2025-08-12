import axios from "axios";
import { createContext, useEffect, useState } from "react";
import { fetchFoodList } from "../service/foodService";
import { addToCart, getCartData,removeQtyFromCart } from "../service/cartService";

export const StoreContext = createContext(null);

export const StoreContextProvider = (props) => {
  const [foodList, setFoodList] = useState([]);
  const [quantities, setQuantities] = useState({});
  const [token, setToken] = useState(localStorage.getItem("token") || ""); // ✅ Step 1

  const increaseQty = async (foodId) => {
    setQuantities((prev) => ({ ...prev, [foodId]: (prev[foodId] || 0) + 1 }));
    await addToCart(foodId, token);
  };


    const removeFromCart = (foodId) => {
    setQuantities((prevQuantities) => {
      const updated = { ...prevQuantities };
      delete updated[foodId];
      return updated;
    });
  };

  const decreaseQty = async (foodId) => {
    setQuantities((prev) => ({
      ...prev,
      [foodId]: prev[foodId] > 0 ? prev[foodId] - 1 : 0,
    }));
    await removeQtyFromCart(foodId, token);
  };



  const loadCartData = async (token) => {
    if (!token || token.trim() === "") return; // ✅ Step 2: safeguard
    const items = await getCartData(token);
    if (items) setQuantities(items);
  };


  /*useEffect(() => {
  async function loadData() {
    const data = await fetchFoodList();

    // ✅ Normalize the data
    const normalized = data.map(food => ({
      ...food,
      imageUrl: food.imageURL // 🔁 convert imageURL → imageUrl
    }));

    setFoodList(normalized); // ✅ use normalized data

    if (token) {
      await loadCartData(token);
    }
  }

  loadData();
}, [token]);
*/

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

  useEffect(() => {
    async function loadData() {
      const data = await fetchFoodList();
      setFoodList(data);
      if (localStorage.getItem("token")) {
        setToken(localStorage.getItem("token"));
        await loadCartData(localStorage.getItem("token"));
      }
    }
    loadData();
  }, []);


  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  );
};

