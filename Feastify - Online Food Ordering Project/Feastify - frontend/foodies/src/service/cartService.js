/*import axios from "axios";

const API_URL = "http://localhost:8080/cart";

export const addToCart = async(foodId, token) =>{
  try{
    await axios.post(
      API_URL,
      { foodId },
      { headers: { Authorization: `Bearer ${token}` } }
    );

  }catch(error){
     console.error('Error while adding the cart data', error);
  }
}

export const removeQtyFromCart = async(foodId, token) =>{
  try{
    axios.post(
      API_URL+"/remove",
      { foodId },
      { headers: { Authorization: `Bearer ${token}` } }
    );

  }catch(error){
     console.error('Error while removing quantity from cart', error);
  }
}

export const getCartData = async(token) =>{
  try{
    const response = await axios.get(API_URL, {
      headers: { Authorization: `Bearer ${token}` },
    });
    return response.data.items;

  }catch(error){
    console.error('Error while fetching the cart data', error);
  }
}

export const clearCartItems = async (token, setQuantities) => {
    try {
        await axios.delete(API_URL, {
            headers: { Authorization: `Bearer ${token}` },
        });
        setQuantities({});
    } catch (error) {
        console.error('Error while clearing the cart', error);
        throw error;
    }
}*/

import axios from "axios";

const API_URL = "http://localhost:8080/cart";

const getAuthHeader = (token) =>
  token ? { Authorization: `Bearer ${token}` } : {};

export const addToCart = async (foodId, token) => {
  try {
    if (!token) throw new Error("Token missing while adding to cart");
    await axios.post(API_URL, { foodId }, { headers: getAuthHeader(token) });
  } catch (error) {
    console.error("Error while adding the cart data", error);
  }
};

export const removeQtyFromCart = async (foodId, token) => {
  try {
    if (!token) throw new Error("Token missing while removing cart qty");
    await axios.post(
      API_URL + "/remove",
      { foodId },
      { headers: getAuthHeader(token) }
    );
  } catch (error) {
    console.error("Error while removing quantity from cart", error);
  }
};

export const getCartData = async (token) => {
  try {
    if (!token) throw new Error("Token missing while fetching cart");
    const response = await axios.get(API_URL, {
      headers: getAuthHeader(token),
    });
    return response.data.items;
  } catch (error) {
    console.error("Error while fetching the cart data", error);
  }
};

export const clearCartItems = async (token, setQuantities) => {
  try {
    if (!token) throw new Error("Token missing while clearing cart");
    await axios.delete(API_URL, {
      headers: getAuthHeader(token),
    });
    setQuantities({});
  } catch (error) {
    console.error("Error while clearing the cart", error);
    throw error;
  }
};
